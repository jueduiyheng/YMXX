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
import com.lxd.ymxx.model.card.Datum;
import com.lxd.ymxx.model.card.Rechargecard;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员开卡
 */
public class VIPopActivity extends Activity implements OnClickListener {
	private List<Datum> datumlist = new ArrayList<>();
	private ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vipop);
		initui();
		initdata();
	}

	private void initdata() {
		Map<String, String> params = new HashMap<>();
		params.put("companyid", Utlis.getcompanyid(this).toString());
		ApiClient.getrecharge(this, params, new VolleyListener() {

			@Override
			public void onResponse(String json) {
				json = Utlis.cutout(json);
				Log.e("json", json);
				Rechargecard card = GsonUtils.parseJSON(json, Rechargecard.class);
				datumlist.addAll(card.getData());
				adapter.notifyDataSetChanged();
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});

	}

	private void initui() {
		new TitleBuilder(this).setTitleText("会员开卡").setLeftOnClickListener(this);
		ListView lv_recharge = (ListView) findViewById(R.id.lv_recharge);
		adapter = new ListAdapter();
		lv_recharge.setAdapter(adapter);
	}

	public class ListAdapter extends BaseAdapter {

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
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = getLayoutInflater().inflate(R.layout.recharge_item, null);
			TextView tv_recharge_price = (TextView) convertView.findViewById(R.id.tv_recharge_price);
			Datum datum = datumlist.get(position);
			tv_recharge_price.setText(datum.getRechargeMoney()+"元");
			return convertView;
		}

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
