package com.lxd.ymxx.welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.lxd.ymxx.app.MainActivity;
import com.lxd.ymxx.app.MapActivity;
import com.lxd.ymxx.app.R;
import com.lxd.ymxx.utlis.Utlis;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {

    private boolean i;
    private SharedPreferences sp;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sp = getSharedPreferences("i", 0);
        i = sp.getBoolean("i", true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (i) {
                    intent.setClass(WelcomeActivity.this, GuideActivity.class);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("i", false);
                    edit.commit();
                } else {
                    if (Utlis.getcompanyid(WelcomeActivity.this) != 0) {
                        intent.setClass(WelcomeActivity.this, MainActivity.class);
                    } else {
                        intent.setClass(WelcomeActivity.this, MapActivity.class);
                    }
                }
                Log.e("商户ID:", ""+Utlis.getcompanyid(WelcomeActivity.this) );
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task, 1000 * 3);
    }
}
