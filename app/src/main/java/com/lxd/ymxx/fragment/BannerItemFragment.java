package com.lxd.ymxx.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.lxd.ymxx.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerItemFragment extends Fragment implements OnClickListener {
    private int mImgRes;
    private int position;

    public BannerItemFragment(int position, int mImgRes) {
        this.position = position;
        this.mImgRes = mImgRes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_banner_item,
                container, false);
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView1);
        imageView.setOnClickListener(this);
        imageView.setImageResource(mImgRes);
        return layout;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "position: " + position, Toast.LENGTH_SHORT).show();
    }


}
