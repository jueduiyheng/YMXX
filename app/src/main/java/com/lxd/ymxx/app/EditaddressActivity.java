package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.Data;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.Constants;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.VolleyListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 编辑收获地址
 */
public class EditaddressActivity extends Activity implements View.OnClickListener {

    private EditText edit_address_name;
    private EditText edit_address_phone;
    private EditText edit_address_street;
    private int request;
    private String address_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaddress);
        initui();
    }

    private void initui() {
        Intent intent = getIntent();
        request = intent.getIntExtra(Constants.Code.KEY_ADDRESS, 0);
        new TitleBuilder(this).setTitleText("编辑收获地址").setLeftOnClickListener(this).setRightText("保存").setRightOnClickListener(this);
        edit_address_name = (EditText) findViewById(R.id.edit_address_name);
        edit_address_phone = (EditText) findViewById(R.id.edit_address_phone);
        edit_address_street = (EditText) findViewById(R.id.edit_address_street);
        if (request == 1) {
            //修改地址
            String name = intent.getStringExtra("name");
            String phone = intent.getStringExtra("phone");
            String address1 = intent.getStringExtra("address1");
            address_id = intent.getStringExtra("address_id");
            edit_address_name.setText(name);
            edit_address_phone.setText(phone);
            edit_address_street.setText(address1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            case R.id.titlebar_tv_right:
                initdata();
                break;
            default:
                break;
        }
    }

    private void initdata() {
        Map<String, String> params = new HashMap<>();
        Data credential = Utlis.getcredential(this);
        if (request == 1) {
            //修改地址
            params.put("addressid", address_id);
        }
        //新增地址
        params.put("userid", credential.getUserID().toString());
        params.put("receiver", edit_address_name.getText().toString());
        params.put("province", "");
        params.put("city", "");
        params.put("area", "");
        params.put("address1", edit_address_street.getText().toString());
        params.put("code", "10086");
        params.put("phone", edit_address_phone.getText().toString());
        ApiClient.getaddress(this, params, request, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.showToast(EditaddressActivity.this, "请求失败");
            }
            @Override
            public void onResponse(String json) {
                json = Utlis.cutout(json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if ("1".equals(jsonObject.getString("code"))) {
                        ToastUtils.showToast(EditaddressActivity.this, "添加成功");
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
