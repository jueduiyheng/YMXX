package com.lxd.ymxx.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.view.ActionSheetDialog;

public class MakedetailsActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makedetails);
        ininui();
    }

    private void ininui() {
        new TitleBuilder(this).setTitleText("预约信息").setLeftOnClickListener(this);
        EditText edit_make_name = (EditText) findViewById(R.id.edit_make_name);
        TextView edit_make_addr = (TextView) findViewById(R.id.edit_make_addr);
        TextView edit_make_date = (TextView) findViewById(R.id.edit_make_date);
        TextView edit_make_hair = (TextView) findViewById(R.id.edit_make_hair);
        TextView edit_make_product = (TextView) findViewById(R.id.edit_make_product);
        edit_make_product.setOnClickListener(this);
        edit_make_hair.setOnClickListener(this);
        edit_make_date.setOnClickListener(this);
        edit_make_addr.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            case R.id.edit_make_product:
                new ActionSheetDialog(MakedetailsActivity.this)
                        .builder()
                        .setTitle("请选择操作")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("棕色", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(MakedetailsActivity.this,
                                                "item1" + which, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })
                        .addSheetItem("红色", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(MakedetailsActivity.this,
                                                "item1" + which, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })
                        .addSheetItem("黄色", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(MakedetailsActivity.this,
                                                "item1" + which, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }).show();
                break;
            case R.id.edit_make_hair:
                new ActionSheetDialog(MakedetailsActivity.this)
                        .builder()
                        .setTitle("请选择操作")
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("短发", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(MakedetailsActivity.this,
                                                "item1" + which, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })
                        .addSheetItem("中发", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(MakedetailsActivity.this,
                                                "item1" + which, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                })
                        .addSheetItem("长发", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        Toast.makeText(MakedetailsActivity.this,
                                                "item1" + which, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }).show();
                break;
            case R.id.edit_make_addr:
                break;
            default:
                break;

        }
    }
}
