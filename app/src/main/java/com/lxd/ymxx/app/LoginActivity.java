package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.Data;
import com.lxd.ymxx.model.Login;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.RegexValidateUtil;
import com.xinbo.utils.VolleyListener;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements OnClickListener {
	private UMShareAPI mShareAPI = null;
	SHARE_MEDIA platform = null;
	private SharedPreferences sp;
	private EditText edit_name;
	private EditText edit_pw;
	private Button btn_login;
	private String user;
	private String pwd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		mShareAPI = UMShareAPI.get(this);
		initui();
	}

	private void initui() {
		new TitleBuilder(this).setTitleText("登录").setLeftOnClickListener(this);
		edit_name = (EditText) this.findViewById(R.id.edit1_login);
		edit_pw = (EditText) this.findViewById(R.id.edit2_login);
		btn_login = (Button) this.findViewById(R.id.btn_login);
		this.findViewById(R.id.img_qq_login).setOnClickListener(this);
		this.findViewById(R.id.image_weibo_login).setOnClickListener(this);
		btn_login.setOnClickListener(this);
		this.findViewById(R.id.text1_login).setOnClickListener(this);
		this.findViewById(R.id.text2_login).setOnClickListener(this);
		this.findViewById(R.id.img_wxin_login).setOnClickListener(this);
	}

	private void initdata() {
		Map<String, String> params = new HashMap<String, String>();
		user = edit_name.getText().toString().trim();
		pwd = edit_pw.getText().toString().trim();
		params.put("name", user);
		params.put("pwd", pwd);
		if (!RegexValidateUtil.checkMobileNumber(user)) {
			ToastUtils.showToast(this, "请输入正确的账号！");
			return;
		}
		if (!RegexValidateUtil.checkCharacter(pwd)) {
			ToastUtils.showToast(this, "请输入正确的密码！");
			return;
		}
		ApiClient.getlogin(this, params, new VolleyListener() {
			@Override
			public void onResponse(String json) {
				String string = Utlis.cutout(json);
				Log.e("json", string);
				Login login = GsonUtils.parseJSON(string, Login.class);
				if (!"0".equals(login.getCode())) {
					ToastUtils.showToast(LoginActivity.this, login.getMsg());
					Data data = login.getData();
					/**
					 * 保存个人信息
					 */
					Utlis.savecredential(LoginActivity.this, data.getUserID(), data.getUserName(),
							data.getUserAccount(), data.getUserAdvancePassword(), data.getUserSex(),
							data.getUserScores(), data.getUserPhone(), data.getUserBirthYear(),
							data.getUserBirthMonth(), data.getUserEmail(), data.getUserImage(),
							data.getUserCreateTime(), data.getUserPassword(),data.getCompanyID());
					// 保存登录状态
					SharedPreferences.Editor editor = sp.edit();
					editor.putBoolean("is", true);
					editor.commit();
					finish();
				} else {
					ToastUtils.showToast(LoginActivity.this, login.getMsg());
				}
			}

			@Override
			public void onErrorResponse(VolleyError arg0) {

			}
		});
	}

	/** QQ授权 **/
	private UMAuthListener umAuthListener = new UMAuthListener() {
		@Override
		public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
			ToastUtils.showToast(LoginActivity.this, "授权成功");
			mShareAPI = UMShareAPI.get(LoginActivity.this);
			mShareAPI.getPlatformInfo(LoginActivity.this, platform, umAuthListener1);

		}

		@Override
		public void onError(SHARE_MEDIA platform, int action, Throwable t) {
			ToastUtils.showToast(LoginActivity.this, "授权失败");
		}

		@Override
		public void onCancel(SHARE_MEDIA platform, int action) {
			ToastUtils.showToast(LoginActivity.this, "授权取消");
		}
	};

	/**
	 * 获取用户信息
	 */
	private UMAuthListener umAuthListener1 = new UMAuthListener() {
		@Override
		public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
			Log.e("获取用户信息", data.toString());
//			if (data != null) {
//				String json = Utlis.mapToJson(data);
//				Qq qq = GsonUtils.parseJSON(json, Qq.class);
//				String screenName = qq.getScreenName();
//				Log.e("screenName", screenName);
//				Utlis.savecredential(LoginActivity.this, 1, screenName, "13215917999", "", 1, 1, "", "", "", "", "", "",
//						"",222);
//				SharedPreferences.Editor editor = sp.edit();
//				editor.putBoolean("is", true);
//				editor.commit();
//				finish();
//			}
		}

		@Override
		public void onError(SHARE_MEDIA platform, int action, Throwable t) {
			ToastUtils.showToast(LoginActivity.this, "获取失败");
		}

		@Override
		public void onCancel(SHARE_MEDIA platform, int action) {
			ToastUtils.showToast(LoginActivity.this, "获取取消");
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mShareAPI.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.text1_login) {
			startActivity(new Intent(this, RegisterActivity.class));
		} else if (id == R.id.text2_login) {
		} else if (id == R.id.btn_login) {
			initdata();
		} else if (id == R.id.titlebar_img_back) {
			finish();
		} else if (id == R.id.img_qq_login) {
			platform = SHARE_MEDIA.QQ;
			mShareAPI.doOauthVerify(LoginActivity.this, platform, umAuthListener);
		} else if (id == R.id.image_weibo_login) {
			platform = SHARE_MEDIA.SINA;
			mShareAPI.doOauthVerify(LoginActivity.this, platform, umAuthListener);
		}else if (id == R.id.img_wxin_login) {
			platform = SHARE_MEDIA.WEIXIN;
			mShareAPI.doOauthVerify(LoginActivity.this, platform, umAuthListener);
		}
		Log.e("点击", "====" + platform);
	}

}
