package com.lxd.ymxx.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxd.ymxx.app.AboutActivity;
import com.lxd.ymxx.app.IntegralActivity;
import com.lxd.ymxx.app.PaymentPWDActivity;
import com.lxd.ymxx.app.PersonalActivity;
import com.lxd.ymxx.app.PreferentialActivity;
import com.lxd.ymxx.app.PwdActivity;
import com.lxd.ymxx.app.R;
import com.lxd.ymxx.model.Data;
import com.lxd.ymxx.utlis.Constants;
import com.lxd.ymxx.utlis.LoginState;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.UILUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * 个人中心
 */
public class MyFragment extends Fragment implements OnClickListener {
    private SharedPreferences sp;
    private View view;
    private TextView tv_user_name;
    private ImageView img_portrait;
    private Data data;

    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            data = Utlis.getcredential(getActivity());
            view = inflater.inflate(R.layout.fragment_my, container, false);
            view.findViewById(R.id.ll_login).setOnClickListener(this);
            view.findViewById(R.id.tv_xiugai_pwd).setOnClickListener(this);
            view.findViewById(R.id.tv_payment_pwd).setOnClickListener(this);
            view.findViewById(R.id.tv_record).setOnClickListener(this);
            view.findViewById(R.id.tv_integral).setOnClickListener(this);
            view.findViewById(R.id.tv_preferential).setOnClickListener(this);
            view.findViewById(R.id.tv_about).setOnClickListener(this);
            view.findViewById(R.id.tv_loginout).setOnClickListener(this);
            tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
            img_portrait = (ImageView) view.findViewById(R.id.img_portrait);
            tv_user_name.setText(data.getUserAccount().trim());
            if (!"".equals(data.getUserImage())) {
                UILUtils.displayImage(Constants.URL.HOST + data.getUserImage(), img_portrait);
                Log.e("UserImage", "=====");
            }
        }
        return view;
    }

    @Override
    public void onStart() {
        data = Utlis.getcredential(getActivity());
        tv_user_name.setText(data.getUserAccount().trim());
        UILUtils.displayImage(Constants.URL.HOST + data.getUserImage(), img_portrait);
        Log.e("onStart: ", "触发");
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_login:
                LoginState.getInstance().startActivity(getActivity(), PersonalActivity.class);
                break;
            case R.id.tv_xiugai_pwd:
                LoginState.getInstance().startActivity(getActivity(), PwdActivity.class);
                break;
            case R.id.tv_payment_pwd:// 支付密码
                LoginState.getInstance().startActivity(getActivity(), PaymentPWDActivity.class);
                break;
            case R.id.tv_record:
                break;
            case R.id.tv_integral:
                LoginState.getInstance().startActivity(getActivity(), IntegralActivity.class);
                break;
            case R.id.tv_preferential:
                LoginState.getInstance().startActivity(getActivity(), PreferentialActivity.class);
                break;
            case R.id.tv_about:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.tv_loginout:
                new SweetAlertDialog(getActivity()).setTitleText("您确定要退出吗?")
                        .setCancelText("取消").setConfirmText("确定").showCancelButton(true)
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                            }
                        }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sp = getActivity().getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("is", false);
                        editor.commit();
                        sDialog.dismiss();
                        Utlis.cleardential(getActivity());
                        data = Utlis.getcredential(getActivity());
                        tv_user_name.setText(data.getUserAccount());
                        img_portrait.setImageResource(R.drawable.touxiang);
                    }
                }).show();
                break;
            default:
                break;
        }
    }
}
