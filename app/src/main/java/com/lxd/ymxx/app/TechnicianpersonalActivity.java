package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.technicianpersonal.Image;
import com.lxd.ymxx.model.technicianpersonal.Technicianpersonal;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TechnicianpersonalActivity extends Activity implements OnClickListener {
    private ImageView img_picpath;// 头像
    private TextView tv_name;// 名字
    private TextView tv_position;// 职位
    private ListAdapter adapter;
    private List<Image> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicianpersonal);
        initui();
        initdata();
    }

    private void initdata() {
        Intent intent = getIntent();
        Map<String, String> params = new HashMap<>();
        intent.getStringExtra("techniciaid");
        params.put("techniciaid", intent.getStringExtra("techniciaid"));
        ApiClient.gettechnicianbyid(this, params, new VolleyListener() {
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                Log.e("json", "" + json);
                Technicianpersonal technicianpersonal = GsonUtils.parseJSON(json, Technicianpersonal.class);
                if ("1".equals(technicianpersonal.getCode())) {

                    tv_name.setText(technicianpersonal.getData().getTechnicianName() + "　");
                    tv_position.setText(technicianpersonal.getData().getTechnicianCategoryName());
                    imageList.addAll(technicianpersonal.getData().getImages());
                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showToast(TechnicianpersonalActivity.this, technicianpersonal.getMsg());
                }

            }

            @Override
            public void onErrorResponse(VolleyError arg0) {

            }
        });
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("个人作品").setLeftOnClickListener(this);
        findViewById(R.id.btn_order).setOnClickListener(this);
        img_picpath = (ImageView) findViewById(R.id.img_picpath);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_position = (TextView) findViewById(R.id.tv_position);
        GridView gv_personal = (GridView) findViewById(R.id.gv_personal);
        adapter = new ListAdapter();
        gv_personal.setAdapter(adapter);
        gv_personal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> mlist = new ArrayList<>();
                Intent intent = new Intent(TechnicianpersonalActivity.this, ImgdetailsActivity.class);
                String describe = (String) imageList.get(i).getTechniciaImages();
                intent.putExtra("bbb", describe);
                intent.putExtra("ccc", imageList.get(i).getTechniciaImagesName());
                startActivity(intent);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            case R.id.btn_order:
                startActivity(new Intent(this, MakeActivity.class));
                break;

            default:
                break;
        }
    }

    class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageList.size();
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
            view = getLayoutInflater().inflate(R.layout.item_personal_works, null);
            Image image = imageList.get(i);
            TextView tv_personal_works = (TextView) view.findViewById(R.id.tv_personal_works);
            ImageView img_personal_works = (ImageView) view.findViewById(R.id.img_personal_works);
            String imagesCovers = (String) image.getTechniciaImagesCovers();
            UILUtils.displayImage("http://ys.wljsd.cn" + imagesCovers, img_personal_works);
            tv_personal_works.setText(image.getTechniciaImagesName());
            return view;
        }
    }
}
