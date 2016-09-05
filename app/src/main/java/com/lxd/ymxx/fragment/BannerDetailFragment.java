package com.lxd.ymxx.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxd.ymxx.app.R;
import com.xinbo.utils.UILUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerDetailFragment extends Fragment {
    private int position;
    private String img_url;
    private String name;

    public BannerDetailFragment(int position, String img_url, String name) {
        this.position = position;
        this.img_url = img_url;
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_banner_item, container, false);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView1);
        TextView textView12 = (TextView) layout.findViewById(R.id.textView12);
        textView12.setText(name);
        UILUtils.displayImage("http://ys.wljsd.cn" + img_url, imageView);
        return layout;
    }

}
