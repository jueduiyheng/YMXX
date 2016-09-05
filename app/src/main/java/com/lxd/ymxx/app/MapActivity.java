package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.lxd.ymxx.model.Company;
import com.lxd.ymxx.model.CompanyDatum;
import com.lxd.ymxx.model.Data;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapActivity extends Activity {
    public MyLocationListenner myListener = new MyLocationListenner();
    // 定位相关
    LocationClient mLocClient;
    MapView mMapView;
    BaiduMap mBaiduMap;
    // 初始化全局 bitmap 信息，不用时及时 recycle
    BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
    boolean isFirstLoc = true; // 是否首次定位
    private ArrayList<CompanyDatum> search_list = new ArrayList<CompanyDatum>();
    private ListAdapter adapter;
    private EditText edit_map_search;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Data data = Utlis.getcredential(this);
        initui();
        initdata();
//        if (data.getCompanyID() != 0) {
//            Intent intent = new Intent(MapActivity.this, MainActivity.class);
//            Utlis.savecompanyid(MapActivity.this, data.getCompanyID());
//            startActivity(intent);
//            finish();
//        }
    }

    private void initui() {
        // 地图初始化
        mMapView = (MapView) findViewById(R.id.bmapView);
        lv = (ListView) findViewById(R.id.listView);
        edit_map_search = (EditText) findViewById(R.id.edit_map_search);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setEnableSimulateGps(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                ToastUtils.showToast(MapActivity.this, "点击地图");

            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                //从marker中获取info信息
                Bundle bundle = marker.getExtraInfo();
                final CompanyDatum infoUtil = (CompanyDatum) bundle.getSerializable("info");
                View inflate = getLayoutInflater().inflate(R.layout.map_item, null);
                Button button2 = (Button) inflate.findViewById(R.id.button2);
                Button button1 = (Button) inflate.findViewById(R.id.button1);
                TextView tv_company_name = (TextView) inflate.findViewById(R.id.tv_companyname);
                tv_company_name.setText(infoUtil.getComanyName());
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBaiduMap.hideInfoWindow();//关闭弹窗
                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mBaiduMap.hideInfoWindow();
                        Intent intent = new Intent(MapActivity.this, MainActivity.class);
                        Utlis.savecompanyid(MapActivity.this, infoUtil.getComanyId());
                        startActivity(intent);
                        finish();
                    }
                });
                LatLng ll = marker.getPosition();
                InfoWindow mInfoWindow = new InfoWindow(inflate, ll, -47);
                mBaiduMap.showInfoWindow(mInfoWindow);
                return true;
            }
        });
        edit_map_search.setOnKeyListener(new View.OnKeyListener() {


            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {// 修改回车键功能
                    // 隐藏键盘
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    String string = edit_map_search.getText().toString();
                    lv.setVisibility(View.VISIBLE);
                    adapter = new ListAdapter();
                    lv.setAdapter(adapter);
                    initsearch(URLEncoder.encode(string));
                }
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MapActivity.this, MainActivity.class);
                Utlis.savecompanyid(MapActivity.this, search_list.get(i).getComanyId());
                startActivity(intent);
                finish();
            }
        });
    }

    private void initsearch(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("companyname", name);
        params.put("companyid", "");
        ApiClient.getcompanyt(MapActivity.this, params, new VolleyListener() {
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                Log.e("json", json + "");
                Company company = GsonUtils.parseJSON(json, Company.class);
                if ("1".equals(company.getCode())) {
                    search_list.clear();
                    if (company.getData().size() == 0) {
                        ToastUtils.showToast(MapActivity.this, "没有找到相应店铺");
                    } else {
                        search_list.addAll(company.getData());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    ToastUtils.showToast(MapActivity.this, "===" + company.getMsg());
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
    }

    private void initdata() {
        Map<String, String> params = new HashMap<>();
        params.put("companyname", "");
        params.put("companyid", "");
        ApiClient.getcompanyt(MapActivity.this, params,  new VolleyListener() {
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                Company company = GsonUtils.parseJSON(json, Company.class);
                initOverlay(company.getData());
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
    }

    public void initOverlay(List<CompanyDatum> infos) {
        //添加覆盖物
//        for (int i = 0; i < companlist.size(); i++) {
//            if (!companlist.get(i).getCompanyLong().equals("null") && !companlist.get(i).getCompanyLat().equals("null")) {
//                LatLng llA = new LatLng(companlist.get(i).getCompanyLat(), companlist.get(i).getCompanyLong());
//                MarkerOptions ooA = new MarkerOptions().position(llA).icon(bd).zIndex(9).draggable(true);
//                // 生长动画
//                ooA.animateType(MarkerOptions.MarkerAnimateType.grow);
//                mBaiduMap.addOverlay(ooA);
//            }
//        }
        LatLng latLng = null;
        Marker marker;
        MarkerOptions options;
        for (CompanyDatum info : infos) {
            //获取经纬度
            latLng = new LatLng(info.getCompanyLat(), info.getCompanyLong());
            options = new MarkerOptions().position(latLng).icon(bd).zIndex(9).draggable(true);
            // 生长动画
            options.animateType(MarkerOptions.MarkerAnimateType.grow);
            //添加marker
            marker = (Marker) mBaiduMap.addOverlay(options);
            //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
            Bundle bundle = new Bundle();
            //info必须实现序列化接口
            bundle.putSerializable("info", info);
            marker.setExtraInfo(bundle);
        }
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        // 回收 bitmap 资源
        bd.recycle();
        super.onDestroy();
    }

    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return search_list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.item_map, null);
            TextView tv_search_name = (TextView) view.findViewById(R.id.tv_search_name);
            CompanyDatum datum = search_list.get(i);
            tv_search_name.setText(datum.getComanyName());
            return view;
        }
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder().accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }
    }
}
