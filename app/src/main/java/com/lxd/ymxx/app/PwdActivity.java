package com.lxd.ymxx.app;

import android.app.Activity;
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
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.RegexValidateUtil;
import com.xinbo.utils.VolleyListener;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改密码
 */
public class PwdActivity extends Activity implements OnClickListener {

    private EditText edit_oldpwd;
    private EditText edit_newpwd;
    private Button btn_alter;
    private String pwd;
    private String old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
        initui();
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("修改支付密码").setLeftOnClickListener(this);
        edit_newpwd = (EditText) this.findViewById(R.id.edit_newpwd);
        edit_oldpwd = (EditText) this.findViewById(R.id.edit_oldpwd);
        btn_alter = (Button) this.findViewById(R.id.btn_alter);
        btn_alter.setOnClickListener(this);

    }

    private void initdata() {
        Data data = Utlis.getcredential(this);
        pwd = edit_newpwd.getText().toString().trim();
        old = edit_oldpwd.getText().toString().trim();
        Log.e("data", "" + data.getUserID().toString());
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", data.getUserID().toString());
        params.put("password", pwd);
        params.put("oldpassword", old);
        params.put("type", "1");
        if (!RegexValidateUtil.checkCharacter(pwd)) {
            ToastUtils.showToast(this, "请输入正确的密码！");
            return;
        }
        ApiClient.getUpdatePwd(this, params, new VolleyListener() {
            @Override
            public void onResponse(String json) {
                String string = Utlis.cutout(json);
                Log.e(json, string);
                Login login = GsonUtils.parseJSON(string, Login.class);
                if (!"0".equals(login.getCode())) {
                    ToastUtils.showToast(PwdActivity.this, login.getMsg());
                } else {
                    ToastUtils.showToast(PwdActivity.this, login.getMsg());
                }
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
            case R.id.btn_alter:
                initdata();
                break;

            default:
                break;
        }
    }

}
