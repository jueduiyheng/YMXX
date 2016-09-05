package com.lxd.ymxx.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.lxd.ymxx.fragment.LoginFragment;
import com.lxd.ymxx.fragment.RegisterFragment;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMShareAPI;

public class LogRegActivity extends FragmentActivity implements View.OnClickListener {
    private final String[] LOGIN = {"登录", "注册"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消lialog
        Config.dialogSwitch = false;
        Config.dialog = null;
        setContentView(R.layout.activity_logreg);
        initui();
    }

    private void initui() {
        findViewById(R.id.img_houtui).setOnClickListener(this);
        ViewPager mPagerBanner = (ViewPager) findViewById(R.id.loginPager);
        FragmentManager fm = this.getSupportFragmentManager();
        mPagerBanner.setAdapter(new LifePagerAdapter(fm));
        PagerSlidingTabStrip psts = (PagerSlidingTabStrip) findViewById(R.id.logintabs);
        psts.setTextColor(Color.parseColor("#ffffff"));
        psts.setViewPager(mPagerBanner);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_houtui:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    class LifePagerAdapter extends FragmentPagerAdapter {
        public LifePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return LOGIN[position];
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new LoginFragment();
            }
            return new RegisterFragment();
        }

        @Override
        public int getCount() {
            return LOGIN.length;
        }
    }
}
