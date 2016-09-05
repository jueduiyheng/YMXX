package com.lxd.ymxx.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.lxd.ymxx.fragment.PreferentialFragment;
import com.lxd.ymxx.utlis.TitleBuilder;

/**
 * 优惠卷
 */
public class PreferentialActivity extends FragmentActivity implements View.OnClickListener {
    private final String[] preferential = {"未使用", "以使用", "以过期"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferential);
        initui();
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("优惠卷").setLeftOnClickListener(this);
        ViewPager mPagerBanner = (ViewPager) findViewById(R.id.loginPager);
        FragmentManager fm = this.getSupportFragmentManager();
        mPagerBanner.setAdapter(new LifePagerAdapter(fm));
        PagerSlidingTabStrip psts = (PagerSlidingTabStrip) findViewById(R.id.tabs_preferential);
        psts.setTextColor(Color.parseColor("#ffffff"));
        psts.setViewPager(mPagerBanner);
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

    class LifePagerAdapter extends FragmentPagerAdapter {
        public LifePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return preferential[position];
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new PreferentialFragment();
            } else if (position == 1) {
                return new PreferentialFragment();
            }
            return new PreferentialFragment();
        }

        @Override
        public int getCount() {
            return preferential.length;
        }
    }
}
