package com.lxd.ymxx.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.Datum;
import com.lxd.ymxx.model.Integral;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IntegralActivity extends Activity implements OnClickListener {
	private ArrayList<Datum> datumlist = new ArrayList<>();
	private ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_integral);
		initui();
	}

	private void initui() {
		new TitleBuilder(this).setLeftOnClickListener(this).setTitleText("积分查询");
		ListView lv_integral = (ListView) findViewById(R.id.lv_integral);
		adapter = new ListAdapter();
		lv_integral.setAdapter(adapter);
		initdata();
	}

	class ListAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return datumlist.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.item_integral, null);
			Datum datum = datumlist.get(position);
			Integer type = datum.getType();
			Log.e("type", "" + type);
			TextView tv_item_integral_time = (TextView) convertView.findViewById(R.id.tv_item_integral_time);
			TextView tv_item_integral_describe = (TextView) convertView.findViewById(R.id.tv_item_integral_describe);
			TextView tv_item_integral_type = (TextView) convertView.findViewById(R.id.tv_item_integral_type);
			String[] split = datum.getScoresTime().split("T");
			tv_item_integral_time.setText(split[0]);
			tv_item_integral_describe.setText(datum.getDescribe());
			if ("0".equals(type.toString())) {
				tv_item_integral_type.setText("+" + datum.getScores1());
			} else {
				tv_item_integral_type.setText("-" + datum.getScores1());
			}
			return convertView;
		}
	}

	private void initdata() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", Utlis.getcredential(this).getUserID().toString());
		ApiClient.getScoreByUser(this, params, new VolleyListener() {
			@Override
			public void onResponse(String json) {
				json = Utlis.cutout(json);
				Integral integral = GsonUtils.parseJSON(json, Integral.class);
				String code = integral.getCode();
				if ("1".equals(code)) {
					datumlist.addAll(integral.getData());
					adapter.notifyDataSetChanged();
					Log.e("data", datumlist + "~~~~");
				} else {
					ToastUtils.showToast(IntegralActivity.this, "连接失败");
				}
				Log.e("json", json);
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});

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
