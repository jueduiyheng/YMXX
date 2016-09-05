package com.lxd.ymxx.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.lxd.ymxx.utlis.TitleBuilder;

public class AboutstoreActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutstore);
        initui();

    }

    private void initui() {
        new TitleBuilder(this).setTitleText("店铺简介").setLeftOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            default:
                break;
        }


    }
}
