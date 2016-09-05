package com.lxd.ymxx.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.app.AboutstoreActivity;
import com.lxd.ymxx.app.AnnouncementActivity;
import com.lxd.ymxx.app.MapActivity;
import com.lxd.ymxx.app.R;
import com.lxd.ymxx.app.TechnicianActivity;
import com.lxd.ymxx.app.TechnicianScheduleActivity;
import com.lxd.ymxx.app.VIPopActivity;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.VolleyListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class FirstFragment2 extends Fragment {
    int[] drawImg = {R.drawable.btn_fenlei2x, R.drawable.btn_vip, R.drawable.btn_linghongbao, R.drawable.btn_qiandao2x,
            R.drawable.btn_shengxianzhuanqu2x, R.drawable.btn_shequfuwu2x, R.drawable.btn_shequfuwu2x,
            R.drawable.btn_linghongbao};
    private String[] gridlist = {"会员开卡", "会员充值", "染发充值", "店铺公告", "服务预约", "技师作品", "技师排班", "店铺简介"};
    private LayoutInflater inflater;
    private GridView mGridView;// 分类&推荐
    private View view;
    private String companyid;
    private TextView tv_popularity;// 人气
    private TextView tv_balance;// 余额

    public FirstFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            this.inflater = inflater;
            view = inflater.inflate(R.layout.fragment_first2, container, false);
            companyid = Utlis.getcompanyid(getActivity()).toString();
            initui();
            initdata();// 人气
            initdata1();// 余额
        }
        return view;
    }

    private void initdata1() {
        Map<String, String> params = new HashMap<>();
        params.put("companyid", companyid);
        params.put("userid", Utlis.getcredential(getActivity()).getUserID() + "");
        ApiClient.getcompanyusermoney(getActivity(), params, new VolleyListener() {
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                JSONObject object;
                try {
                    object = new JSONObject(json);
                    String string = object.getString("code");
                    if ("1".equals(string)) {
                        tv_balance.setText(object.getString("data"));
                    } else if ("0".equals(string)) {
                        tv_balance.setText("未绑定该店铺");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
    }

    private void initdata() {
        Map<String, String> params = new HashMap<>();
        params.put("companyid", companyid);
        ApiClient.getcompanyusercout(getActivity(), params, new VolleyListener() {
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                JSONObject object;
                try {
                    object = new JSONObject(json);
                    String string = object.getString("code");
                    if ("1".equals(string)) {
                        tv_popularity.setText(object.getString("data"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
    }

    private void initui() {
        view.findViewById(R.id.tv_first_search).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapActivity.class));
                getActivity().finish();
            }
        });
        tv_popularity = (TextView) view.findViewById(R.id.tv_popularity);
        tv_balance = (TextView) view.findViewById(R.id.tv_balance);
        view.findViewById(R.id.btn_Binding).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("userid", "" + Utlis.getcredential(getActivity()).getUserID());
                params.put("companyid", companyid);
                ApiClient.getbindingcompany(getActivity(), params, new VolleyListener() {
                    @Override
                    public void onResponse(String json) {
                        json = Utlis.cutout(json);
                    }

                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                    }
                });
            }
        });
        mGridView = (GridView) view.findViewById(R.id.gridViewScrollView1);
        GridAdapter gridAdapter = new GridAdapter();
        mGridView.setAdapter(gridAdapter);
        mGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// 会员开卡
                        startActivity(new Intent(getActivity(), VIPopActivity.class));
                        break;
                    case 1:// 会员充值
                        startActivity(new Intent(getActivity(), VIPopActivity.class));
                        break;
                    case 2:// 染发充值
                        break;
                    case 3:// 店铺公告
                        startActivity(new Intent(getActivity(), AnnouncementActivity.class));
                        break;
                    case 4:// 预约服务
                        break;
                    case 5:// 技师作品
                        startActivity(new Intent(getActivity(), TechnicianActivity.class));
                        break;
                    case 6:// 技师排班
                        startActivity(new Intent(getActivity(), TechnicianScheduleActivity.class));
                        break;
                    case 7:// 店铺介绍
                        startActivity(new Intent(getActivity(), AboutstoreActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 分类GridView
     */
    class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return gridlist.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_gridview, null);
            ImageView iv_tubiao = (ImageView) view.findViewById(R.id.item_grid_image);
            TextView tv_daohang = (TextView) view.findViewById(R.id.item_grid_text);
            tv_daohang.setText(gridlist[position]);
            iv_tubiao.setImageResource(drawImg[position]);
            return view;
        }
    }

}
