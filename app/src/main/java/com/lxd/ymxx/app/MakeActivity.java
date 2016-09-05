package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lxd.ymxx.utlis.TitleBuilder;

public class MakeActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        initui();
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("预约").setLeftOnClickListener(this);
        findViewById(R.id.img_haircare).setOnClickListener(this);
        findViewById(R.id.img_cutting).setOnClickListener(this);
        findViewById(R.id.img_marcel).setOnClickListener(this);
        findViewById(R.id.img_hairdye).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            case R.id.img_haircare:
                startActivity(new Intent(this, MakedetailsActivity.class));
                break;
            case R.id.img_cutting:
                startActivity(new Intent(this, MakedetailsActivity.class));
                break;
            case R.id.img_marcel:
                startActivity(new Intent(this, MakedetailsActivity.class));
                break;
            case R.id.img_hairdye:
                startActivity(new Intent(this, MakedetailsActivity.class));
                break;
            default:
                break;
        }
    }
}
