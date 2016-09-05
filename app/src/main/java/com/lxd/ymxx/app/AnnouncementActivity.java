package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.lxd.ymxx.model.announcement.Announcement;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnouncementActivity extends Activity implements View.OnClickListener {
    private List<Announcement.DataBean> datalist = new ArrayList<>();
    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcementdetails);
        initui();
        initdata();
    }
    private void initdata() {
        Map<String, String> params = new HashMap<>();
        params.put("companyid", Utlis.getcompanyid(this) + "");
        ApiClient.getadvance(this, params, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                Log.e("json", json);
                Announcement announcement = GsonUtils.parseJSON(json, Announcement.class);
                if ("1".equals(announcement.getCode())) {
                    datalist.addAll(announcement.getData());
                    adapter.notifyDataSetChanged();
                } else {
                }
            }
        });
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("店铺公告").setLeftOnClickListener(this);
        ListView lv_announcement = (ListView) findViewById(R.id.lv_announcement);
        adapter = new ListAdapter();
        lv_announcement.setAdapter(adapter);
        lv_announcement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Announcement.DataBean dataBean = datalist.get(i);
                Intent intent = new Intent(AnnouncementActivity.this, AnnouncementdetailsActivity.class);
                intent.putExtra("aaa",dataBean);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            default:
                break;
        }
    }

    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return datalist.size();
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
            Announcement.DataBean dataBean = datalist.get(i);
            view = getLayoutInflater().inflate(R.layout.item_announcement, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_title.setText(dataBean.getAdvanceContent());
            tv_time.setText(dataBean.getAdvanceTime());
            return view;
        }
    }
}
