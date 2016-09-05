package com.lxd.ymxx.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.technicianschedule.Datum;
import com.lxd.ymxx.model.technicianschedule.Technicianschedule;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnicianScheduleActivity extends Activity implements OnClickListener {
    private List<Datum> datalist = new ArrayList<>();
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_schedule);
        initui();
        initdata();


    }

    private void initdata() {
        Map<String, String> params = new HashMap<>();
        params.put("companyid", Utlis.getcompanyid(this) + "");
        params.put("date", "2016-08-04");
        ApiClient.gettechnicianschedule(this, params, new VolleyListener() {
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                Technicianschedule technicianschedule = GsonUtils.parseJSON(json, Technicianschedule.class);
                if ("1".equals(technicianschedule.getCode())) {
                    datalist.addAll(technicianschedule.getData());
                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showToast(TechnicianScheduleActivity.this, technicianschedule.getMsg());
                }
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {

            }
        });
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("技师排班").setLeftOnClickListener(this);
        ListView lv_technicianschedule = (ListView) findViewById(R.id.lv_technicianschedule);
        adapter = new ListAdapter();
        lv_technicianschedule.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;

            default:
                break;
        }
    }

    class ListAdapter extends BaseAdapter {

        private Datum datum;

        @Override
        public int getCount() {
            return datalist.size();
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
            viewHolder vHolder;
            if (convertView == null) {
                datum = datalist.get(position);
                vHolder = new viewHolder();
                convertView = getLayoutInflater().inflate(R.layout.item_technicianschedule, null);
                vHolder.img_picpath = (ImageView) convertView.findViewById(R.id.img_picpath);
                vHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                vHolder.tv_position = (TextView) convertView.findViewById(R.id.tv_position);
                vHolder.tv_iswork = (TextView) convertView.findViewById(R.id.tv_iswork);
            } else {
                vHolder = (viewHolder) convertView.getTag();
            }
            vHolder.tv_name.setText(datum.getTechnicianName());
            if (datum.getIsWork() == 1) {
                vHolder.tv_iswork.setText("上班");
                vHolder.tv_iswork.setBackgroundResource(R.drawable.rect_blue);
            }
            return convertView;
        }

        class viewHolder {
            ImageView img_picpath; // 头像
            TextView tv_name; // 名字
            TextView tv_position; // 职位
            TextView tv_iswork; // 是否上班
        }
    }
}
