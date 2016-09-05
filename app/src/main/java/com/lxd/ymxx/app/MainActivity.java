package com.lxd.ymxx.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lxd.ymxx.fragment.FirstFragment2;
import com.lxd.ymxx.fragment.MyFragment;
import com.xinbo.utils.TextViewUtils;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        inflater = getLayoutInflater();
        addTab(getString(R.string.shouye), R.drawable.ic_tab_artists, FirstFragment2.class);
        addTab(getString(R.string.shangcheng), R.drawable.ic_tab_albums, Fragment.class);
        addTab(getString(R.string.dingdan), R.drawable.ic_tab_songs, Fragment.class);
        addTab(getString(R.string.geren), R.drawable.ic_tab_playlists, MyFragment.class);
    }
    private void addTab(String title, int drawableRes, Class fragmentClass) {
        View tabItem1 = inflater.inflate(R.layout.tab_item, null);
        TextView textView = (TextView) tabItem1.findViewById(R.id.item_name);
        textView.setText(title);
        TextViewUtils.setTextDrawable(this, drawableRes, textView);
        mTabHost.addTab(mTabHost.newTabSpec(title).setIndicator(tabItem1), fragmentClass, null);

    }
}
