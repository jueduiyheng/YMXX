package com.lxd.ymxx.utlis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.lxd.ymxx.app.LogRegActivity;

public class LoginState {
	public void startActivity(Context context, Class clazz) {
		SharedPreferences sp = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		if (!sp.getBoolean("is", false)) {
			Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();
			context.startActivity(new Intent(context, LogRegActivity.class));
//			context.startActivity(new Intent(context, LoginActivity.class));
		} else {
			context.startActivity(new Intent(context, clazz));
		}
	}

	public void logout() {
	}

	private static LoginState instance = new LoginState();

	private LoginState() {
	}

	public static LoginState getInstance() {
		return instance;
	}

}