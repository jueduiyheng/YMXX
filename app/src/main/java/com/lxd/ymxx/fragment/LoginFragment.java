package com.lxd.ymxx.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.lxd.ymxx.app.R;
import com.lxd.ymxx.app.RegisterActivity;
import com.lxd.ymxx.model.Data;
import com.lxd.ymxx.model.LogReg.Sina;
import com.lxd.ymxx.model.LogReg.Weixin;
import com.lxd.ymxx.model.Login;
import com.lxd.ymxx.model.Qq;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.LoadingDialog;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.RegexValidateUtil;
import com.xinbo.utils.VolleyListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {
    SHARE_MEDIA platform = null;
    private UMShareAPI mShareAPI = null;
    private SharedPreferences sp;
    private EditText edit_name;
    private EditText edit_pw;
    private Button btn_login;
    private String user;
    private String pwd;
    private View view;
    private LoadingDialog dialog;
    /**
     * 授权
     **/
    private UMAuthListener umAuthListener = new UMAuthListener() {
        String name = null;
        String sex = null;
        String userlogo = null;
        String openid = null;
        private String json = null;

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            dialog.close();
            /**
             * 获取用户信息
             */
            mShareAPI.getPlatformInfo(getActivity(), platform, new UMAuthListener() {
                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> data) {
                    if (data != null) {
                        if (SHARE_MEDIA.QQ.equals(share_media)) {
                            //QQ登录
                            json = Utlis.mapToJson(data);
                            Log.e("QQjson", json + "");
                            Qq qq = GsonUtils.parseJSON(json, Qq.class);
                            name = qq.getScreenName();
                            openid = qq.getOpenid();
                            userlogo = qq.getProfileImageUrl();
                            if ("男".equals(qq.getGender())) {
                                sex = "0";
                            } else {
                                sex = "1";
                            }
                        } else if (SHARE_MEDIA.WEIXIN.equals(share_media)) {
                            //微信登入
                            json = Utlis.mapToJson(data);
                            Log.e("微信json", json + "");
                            Weixin weixin = GsonUtils.parseJSON(json, Weixin.class);
                            name = weixin.getNickname();
                            openid = weixin.getOpenid();
                            sex = weixin.getSex();
                            userlogo = weixin.getHeadimgurl();
                        } else if (SHARE_MEDIA.SINA.equals(share_media)) {
                            //微博登入
                            //遍历map中的值
                            for (String value : data.values()) {
                                json = value;
                            }
                            Log.e("微博json", json + "");
                            Sina sina = GsonUtils.parseJSON(json, Sina.class);
                            name = sina.getScreen_name();
                            openid = sina.getIdstr();
                            //	性别，m：男、f：女、n：未知
                            if ("m".equals(sina.getGender())) {
                                sex = "0";
                            } else {
                                sex = "1";
                            }
                            userlogo = sina.getProfile_image_url();
                        }
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("companyid", Utlis.getcompanyid(getActivity()) + "");
                        params.put("name", name);
                        params.put("sex", sex);
                        params.put("userlogo", userlogo);
                        params.put("openid", openid);
                        ApiClient.getuserregister(getActivity(), params, new VolleyListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                            }

                            @Override
                            public void onResponse(String json) {
                                json = Utlis.cutout(json);
                                try {
                                    JSONObject object = new JSONObject(json);
                                    object.getString("msg");
                                    String userid = object.getString("userid");
                                    if ("4".equals(object.getString("code"))) {
                                        initgetuser(userid);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    ToastUtils.showToast(getActivity(), "获取失败");
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    ToastUtils.showToast(getActivity(), "获取取消");
                }
            });
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            dialog.close();
            ToastUtils.showToast(getActivity(), "授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            dialog.close();
            ToastUtils.showToast(getActivity(), "授权取消");
        }

    };

    public LoginFragment() {
    }

    /**
     * 获取个人信息
     *
     * @param userid
     */
    private void initgetuser(String userid) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        ApiClient.getgetuser(getActivity(), params, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.showToast(getActivity(), "请求失败");
            }

            @Override
            public void onResponse(String json) {
                String string = Utlis.cutout(json);
                Log.e("initgetuser", string);
                Login login = GsonUtils.parseJSON(string, Login.class);
                if ("1".equals(login.getCode())) {
                    ToastUtils.showToast(getActivity(), login.getMsg());
                    Data data = login.getData();
                    /**
                     * 保存个人信息
                     */
                    Utlis.savecredential(getActivity(), data.getUserID(), data.getUserName(),
                            data.getUserAccount(), data.getUserAdvancePassword(), data.getUserSex(),
                            data.getUserScores(), data.getUserPhone(), data.getUserBirthYear(),
                            data.getUserBirthMonth(), data.getUserEmail(), data.getUserImage(),
                            data.getUserCreateTime(), data.getUserPassword(), data.getCompanyID());
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("is", true);
                    editor.commit();
                    getActivity().finish();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        initui();
        return view;
    }

    private void initui() {
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        mShareAPI = UMShareAPI.get(getActivity());
        edit_name = (EditText) this.view.findViewById(R.id.edit1_login);
        edit_pw = (EditText) this.view.findViewById(R.id.edit2_login);
        btn_login = (Button) this.view.findViewById(R.id.btn_login);
        this.view.findViewById(R.id.img_qq_login).setOnClickListener(this);
        this.view.findViewById(R.id.image_weibo_login).setOnClickListener(this);
        this.view.findViewById(R.id.img_wxin_login).setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    private void initdata() {
        Map<String, String> params = new HashMap<String, String>();
        user = edit_name.getText().toString().trim();
        pwd = edit_pw.getText().toString().trim();
        params.put("name", user);
        params.put("pwd", pwd);
        if (!RegexValidateUtil.checkMobileNumber(user)) {
            ToastUtils.showToast(getActivity(), "请输入正确的账号！");
            return;
        }
        if (!RegexValidateUtil.checkCharacter(pwd)) {
            ToastUtils.showToast(getActivity(), "请输入正确的密码！");
            return;
        }
        dialog = new LoadingDialog(getActivity(), "请稍后...");
        dialog.show();
        ApiClient.getlogin(getActivity(), params, new VolleyListener() {
            @Override
            public void onResponse(String json) {
                String string = Utlis.cutout(json);
                Log.e("json", string);
                Login login = GsonUtils.parseJSON(string, Login.class);
                if (!"0".equals(login.getCode())) {
                    ToastUtils.showToast(getActivity(), login.getMsg());
                    Data data = login.getData();
                    /**
                     * 保存个人信息
                     */
                    Utlis.savecredential(getActivity(), data.getUserID(), data.getUserName(),
                            data.getUserAccount(), data.getUserAdvancePassword(), data.getUserSex(),
                            data.getUserScores(), data.getUserPhone(), data.getUserBirthYear(),
                            data.getUserBirthMonth(), data.getUserEmail(), data.getUserImage(),
                            data.getUserCreateTime(), data.getUserPassword(), data.getCompanyID());
                    // 保存登录状态
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("is", true);
                    editor.commit();
                    getActivity().finish();
                } else {
                    ToastUtils.showToast(getActivity(), login.getMsg());
                }
                dialog.close();
            }

            @Override
            public void onErrorResponse(VolleyError arg0) {

                ToastUtils.showToast(getActivity(), "请求失败");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        {
            int id = view.getId();
            if (id == R.id.text1_login) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            } else if (id == R.id.text2_login) {
            } else if (id == R.id.btn_login) {
                initdata();
            } else if (id == R.id.titlebar_img_back) {
                getActivity().finish();
            } else if (id == R.id.img_qq_login) {
                platform = SHARE_MEDIA.QQ;
                mShareAPI.doOauthVerify(getActivity(), platform, umAuthListener);
                dialog = new LoadingDialog(getActivity(), "请稍后...");
                dialog.show();
            } else if (id == R.id.image_weibo_login) {
                platform = SHARE_MEDIA.SINA;
                mShareAPI.doOauthVerify(getActivity(), platform, umAuthListener);
                dialog = new LoadingDialog(getActivity(), "请稍后...");
                dialog.show();
            } else if (id == R.id.img_wxin_login) {
                platform = SHARE_MEDIA.WEIXIN;
                mShareAPI.doOauthVerify(getActivity(), platform, umAuthListener);
                dialog = new LoadingDialog(getActivity(), "请稍后...");
                dialog.show();
            }

        }

    }
}
