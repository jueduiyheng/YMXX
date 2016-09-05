package com.lxd.ymxx.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.lxd.ymxx.fragment.BannerDetailFragment;
import com.lxd.ymxx.utlis.TitleBuilder;

import java.util.ArrayList;
import java.util.List;

public class ImgdetailsActivity extends FragmentActivity implements View.OnClickListener {

    private List<String> mlist;
    private String ccc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgdetails);
        new TitleBuilder(this).setTitleText("技师作品").setLeftOnClickListener(this);
        ViewPager vp_detail_ima = (ViewPager) findViewById(R.id.vp_detail_ima);
        FragmentManager fm = getSupportFragmentManager();
        mlist = new ArrayList<>();
        String bbb = getIntent().getStringExtra("bbb");
        ccc = getIntent().getStringExtra("ccc");
        String[] strlist = bbb.split(",");
        if (mlist.size() > 0) {
            mlist.clear();
        }
        for (int i = 0; i < strlist.length; i++) {
            mlist.add(strlist[i]);
        }
        Log.e("mlist", "" + mlist.size());
        vp_detail_ima.setAdapter(new BannerAdapter(fm));

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

    private class BannerAdapter extends FragmentPagerAdapter {
        public BannerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new BannerDetailFragment(position % mlist.size(), mlist.get(position % mlist.size()),ccc);
        }

        @Override
        public int getCount() {
            return mlist.size();
        }
    }
}
