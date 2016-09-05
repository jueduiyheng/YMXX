package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.Data;
import com.lxd.ymxx.model.Login;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.Constants;
import com.lxd.ymxx.utlis.LoadingDialog;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.lxd.ymxx.view.ChangeBirthDialog;
import com.lxd.ymxx.view.photo.ImageUtils;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.UILUtils;
import com.xinbo.utils.VolleyListener;

import java.util.HashMap;
import java.util.Map;

public class PersonalActivity extends Activity implements View.OnClickListener {
    private TextView tv_nickname;
    private TextView tv_sex;
    private TextView tv_birthday;
    private TextView tv_address;
    private ImageView img_portrait;
    private String userBirthYear;
    private String userBirthMonth;
    private LoadingDialog dialog;
    private String photo = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initui();
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("个人简介").setLeftOnClickListener(this).setRightText("保存").setRightOnClickListener(this);
        img_portrait = (ImageView) findViewById(R.id.img_portrait);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_address = (TextView) findViewById(R.id.tv_address);
        Data credential = Utlis.getcredential(this);
        UILUtils.displayImage(Constants.URL.HOST + credential.getUserImage(), img_portrait);
        tv_nickname.setText(credential.getUserAccount().trim() + " ");
        userBirthYear = credential.getUserBirthYear().trim();
        userBirthMonth = credential.getUserBirthMonth().trim();
        tv_birthday.setText(userBirthYear + "-" + userBirthMonth);
        if (credential.getUserSex() == 0) {
            tv_sex.setText("男 ");
        } else if (credential.getUserSex() == 1) {
            tv_sex.setText("女 ");
        }
        tv_sex.setOnClickListener(this);
        tv_nickname.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        tv_birthday.setOnClickListener(this);
        img_portrait.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_FROM_ALBUM: {//相册

                if (resultCode == RESULT_CANCELED) {   //取消操作
                    Log.e("resultCode", "相册触发");
                    return;
                }
                Uri originalUri = data.getData();
                Log.e("imageUri", "" + originalUri);
                ImageUtils.copyImageUri(this, originalUri);
                ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                break;
            }
            case ImageUtils.REQUEST_CODE_FROM_CAMERA: {//相机

                if (resultCode == RESULT_CANCELED) {     //取消操作
                    Log.e("resultCode", "相机取消");
//                    ImageUtils.deleteImageUri(this, ImageUtils.getCurrentUri());   //删除Uri
                    return;
                }
                ImageUtils.cropImageUri(this, ImageUtils.getCurrentUri(), 200, 200);
                break;
            }
            case ImageUtils.REQUEST_CODE_CROP: {
                if (resultCode == RESULT_CANCELED) {     //取消操作
                    Log.e("resultCode", "剪切取消");
                    return;
                }
                Uri imageUri = ImageUtils.getCurrentUri();
                if (imageUri != null) {
                    img_portrait.setImageURI(imageUri);
                    String substring = imageUri.toString().substring(7);
                    photo = ImageUtils.GetImageStr(substring);
//                    Log.e("photo_b64", "==" + substring);
//                    Log.e("imageUri", "==" + photo);
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            case R.id.titlebar_tv_right:
                initupdateuser();
                break;
            case R.id.tv_nickname:
                initnickname();
                break;
            case R.id.tv_sex:
                initsex();
                break;
            case R.id.tv_birthday:
                initbirthday();
                break;
            case R.id.tv_address:
                startActivity(new Intent(PersonalActivity.this, AddressActivity.class));
                break;
            case R.id.img_portrait:
                ImageUtils.showImagePickDialog(PersonalActivity.this);
                break;
            default:
                break;
        }
    }

    private void initupdateuser() {
        // 显示Dialog
        dialog = new LoadingDialog(this, "数据加载中...");
        dialog.show();

        final Map<String, String> params = new HashMap<>();
        params.put("name", tv_nickname.getText().toString());
        tv_sex.getText().toString();

        if ("男".equals(tv_sex.getText().toString().trim())) {
            params.put("sex", "0");
        } else {
            params.put("sex", "1");
        }
        params.put("year", userBirthYear);
        params.put("month", userBirthMonth);
        params.put("imgBytesIn", photo);
        params.put("userid", Utlis.getcredential(this).getUserID() + "");
        tv_nickname.postDelayed(new Runnable() {
            @Override
            public void run() {
                ApiClient.getupdateuser(PersonalActivity.this, params, new VolleyListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        ToastUtils.showToast(PersonalActivity.this, "请求失败");
                    }

                    @Override
                    public void onResponse(String json) {
                        initgetuser(Utlis.getcredential(PersonalActivity.this).getUserID() + "");
                        dialog.close();
                    }
                });
            }
        }, 2000);
    }

    /**
     * 获取个人信息
     *
     * @param userid
     */
    private void initgetuser(String userid) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        ApiClient.getgetuser(this, params, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.showToast(PersonalActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String json) {
                String string = Utlis.cutout(json);
                Login login = GsonUtils.parseJSON(string, Login.class);
                if ("1".equals(login.getCode())) {
//                    ToastUtils.showToast(PersonalActivity.this, "修改成功");
                    Data data = login.getData();
                    /**
                     * 保存个人信息
                     */
                    Utlis.savecredential(PersonalActivity.this, data.getUserID(), data.getUserName(),
                            data.getUserAccount(), data.getUserAdvancePassword(), data.getUserSex(),
                            data.getUserScores(), data.getUserPhone(), data.getUserBirthYear(),
                            data.getUserBirthMonth(), data.getUserEmail(), data.getUserImage(),
                            data.getUserCreateTime(), data.getUserPassword(), data.getCompanyID());
                }
                finish();
            }
        });
    }

    /**
     * 生日
     */
    private void initbirthday() {
        ChangeBirthDialog mChangeBirthDialog = new ChangeBirthDialog(
                PersonalActivity.this);
        mChangeBirthDialog.setDate(1989, 01, 01);
        mChangeBirthDialog.show();
        mChangeBirthDialog.setBirthdayListener(new ChangeBirthDialog.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {
                PersonalActivity.this.userBirthYear = year;
                PersonalActivity.this.userBirthMonth = month;
                tv_birthday.setText(year + "-" + month);
            }
        });
    }

    /**
     * 昵称
     */
    private void initnickname() {
        View layout_nickname = getLayoutInflater().inflate(R.layout.item_dialog_nickname, null);
        final AlertDialog dialog_nickname = new AlertDialog.Builder(this).setView(layout_nickname).show();
        final EditText edit_name = (EditText) layout_nickname.findViewById(R.id.edit_name);
        layout_nickname.findViewById(R.id.bt_confirm_nickname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_nickname.setText(edit_name.getText().toString());
                dialog_nickname.dismiss();
            }
        });
    }

    /**
     * 性别
     */
    private void initsex() {
        View layout_sex = getLayoutInflater().inflate(R.layout.item_sex, null);
        final AlertDialog dialog_sex = new AlertDialog.Builder(this).setView(layout_sex).show();
        final TextView tv_boy = (TextView) layout_sex.findViewById(R.id.tv_boy);
        tv_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sex.setText(tv_boy.getText().toString());
                dialog_sex.dismiss();

            }
        });
        final TextView tv_girl = (TextView) layout_sex.findViewById(R.id.tv_girl);
        tv_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sex.setText(tv_girl.getText().toString());
                dialog_sex.dismiss();
            }
        });
    }

}
