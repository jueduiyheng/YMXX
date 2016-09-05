package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.lxd.ymxx.utlis.TitleBuilder;

public class PaymentPWDActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment_pwd);
		initui();
	}

	private void initui() {
		new TitleBuilder(this).setTitleText("支付密码 ").setLeftOnClickListener(this);
		findViewById(R.id.lv_modifypayment_pwd).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebar_img_back:
			finish();
			break;
		case R.id.lv_modifypayment_pwd:
			startActivity(new Intent(PaymentPWDActivity.this, ModifypaymentpwdActivity.class));
			break;

		default:
			break;
		}

	}
}