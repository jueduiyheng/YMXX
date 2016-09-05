package com.lxd.ymxx.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.about.About;
import com.lxd.ymxx.model.about.Data;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

/**
 * 关于
 */
public class AboutActivity extends Activity implements OnClickListener {

	private TextView tv_tel;
	private TextView tv_mail;
	private TextView tv_companayname;
	private TextView tv_companayaddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		initui();
		initdata();
	}

	private void initdata() {
		ApiClient.getAbout(this, new VolleyListener() {

			@Override
			public void onResponse(String json) {
				json = Utlis.cutout(json);
				About about = GsonUtils.parseJSON(json, About.class);
				Data data = about.getData();
				Log.e("about", "" + data.getCompanayAddress());
				tv_tel.setText("客服电话:" + data.getCompanayTEL());
				tv_mail.setText("E-mail:" + data.getCompanayEmail());
				tv_companayname.setText(data.getCompanayName());
				tv_companayaddress.setText(data.getCompanayAddress());
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	private void initui() {
		new TitleBuilder(this).setTitleText("关于").setLeftOnClickListener(this);
		tv_tel = (TextView) findViewById(R.id.tv_tel);
		tv_mail = (TextView) findViewById(R.id.tv_mail);
		tv_companayname = (TextView) findViewById(R.id.tv_companayname);
		tv_companayaddress = (TextView) findViewById(R.id.tv_companayaddress);
		RelativeLayout rl_update = (RelativeLayout) findViewById(R.id.rl_update);
		rl_update.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_update:
			break;
		case R.id.titlebar_img_back:
			finish();
			break;

		default:
			break;
		}
	}

}
