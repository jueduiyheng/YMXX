package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.technician.Datum;
import com.lxd.ymxx.model.technician.Technician;
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
public class TechnicianActivity extends Activity implements OnClickListener {
	private List<Datum> datalist = new ArrayList<>();
	private ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_technician);
		initui();
		initdata();
	}

	private void initdata() {
		Map<String, String> params = new HashMap<>();
		params.put("companyid", Utlis.getcompanyid(this) + "");
		ApiClient.gettechnician(this, params, new VolleyListener() {
			@Override
			public void onResponse(String json) {
				json = Utlis.cutout(json);
				Technician technician = GsonUtils.parseJSON(json, Technician.class);
				if ("1".equals(technician.getCode())) {
					datalist.addAll(technician.getData());
					adapter.notifyDataSetChanged();
				} else {
					ToastUtils.showToast(TechnicianActivity.this, technician.getMsg());
				}

			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	private void initui() {
		new TitleBuilder(this).setTitleText("技师作品").setLeftOnClickListener(this);
		ListView lv_technician = (ListView) findViewById(R.id.lv_technician);
		adapter = new ListAdapter();
		lv_technician.setAdapter(adapter);
		lv_technician.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Datum datum = datalist.get(position);
				Intent intent = new Intent(TechnicianActivity.this, TechnicianpersonalActivity.class);
				intent.putExtra("techniciaid", datum.getID() + "");
				startActivity(intent);
			}
		});
	}

	class ListAdapter extends BaseAdapter {

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
			Datum datum = datalist.get(position);
			if (convertView == null) {
				vHolder = new viewHolder();
				convertView = getLayoutInflater().inflate(R.layout.tiem_technician, null);
				vHolder.img_picpath = (ImageView) convertView.findViewById(R.id.img_picpath);
				vHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				vHolder.tv_position = (TextView) convertView.findViewById(R.id.tv_position);
			} else {
				vHolder = (viewHolder) convertView.getTag();
			}
			vHolder.tv_name.setText(datum.getTechnicianName()+"　");
			return convertView;
		}
	}

	class viewHolder {
		ImageView img_picpath; // 头像
		TextView tv_name; // 名字
		TextView tv_position; // 职位
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
}
