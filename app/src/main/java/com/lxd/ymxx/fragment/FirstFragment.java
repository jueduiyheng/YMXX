package com.lxd.ymxx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxd.ymxx.app.R;
import com.xinbo.widget.GridView4ScrollView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    private int[] mBannerRes = new int[] { R.drawable.b_mig, R.drawable.b_mig_fou, R.drawable.b_mig_thr,
            R.drawable.b_mig_two, R.drawable.b_mig_fou };
    private String[] gridlist = { "会员开发", "会员充值", "染发充值", "店铺公告", "服务预约", "技师作品", "技师排班", "店铺简介" };
    int[] drawImg = { R.drawable.btn_fenlei2x, R.drawable.btn_vip, R.drawable.btn_linghongbao, R.drawable.btn_qiandao2x,
            R.drawable.btn_shengxianzhuanqu2x, R.drawable.btn_shequfuwu2x, R.drawable.btn_shequfuwu2x,
            R.drawable.btn_linghongbao };
    private ViewPager mPager;
    private LayoutInflater inflater;
    private static final int MAX_COUNT = 100000;// 轮播图的数量
    protected boolean isDrag = false;// 是手动拖拽
    private GridView4ScrollView mGridView;// 分类&推荐
    private View view;

    public FirstFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        view = inflater.inflate(R.layout.fragment_first, container, false);
        initui();
        autoScroll();
        return view;
    }

    private void initui() {
        mGridView = (GridView4ScrollView) view.findViewById(R.id.gridViewScrollView1);
        GridAdapter gridAdapter = new GridAdapter();
        mGridView.setAdapter(gridAdapter);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        FragmentManager fm = getChildFragmentManager();
        mPager.setAdapter(new BannerAdapter(fm));
        mPager.setCurrentItem(MAX_COUNT / 2, false);// 从中间开始轮播
        mPager.setOnPageChangeListener(new BannerListener());
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:// 会员开卡
//                        startActivity(new Intent(getActivity(), VIPopActivity.class));
                        break;
                    case 1:// 会员充值
//                        startActivity(new Intent(getActivity(), VIPopActivity.class));
                        break;
                    case 2:// 染发充值
                        break;
                    case 3:// 店铺公告

                        break;
                    case 4:// 预约服务

                        break;
                    case 5:// 技师作品

                        break;
                    case 6:// 技师排班

                        break;
                    case 7:// 店铺介绍

                        break;

                    default:
                        break;
                }
            }
        });
    }

    private class BannerAdapter extends FragmentPagerAdapter {
        public BannerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new BannerItemFragment(position % mBannerRes.length, mBannerRes[position % mBannerRes.length]);
        }

        @Override
        public int getCount() {
            return MAX_COUNT;
        }
    }

    class BannerListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    isDrag = true;
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    isDrag = false;
                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    isDrag = false;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrolled(int position, float percent, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

    /**
     * 自动轮播
     */
    private void autoScroll() {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 改变ViewPager显示的当前页面
                int index = mPager.getCurrentItem() + 1;
                if (!isDrag) {
                    mPager.setCurrentItem(index);
                }
                view.postDelayed(this, 3000);
            }
        }, 3000);
    }

    /**
     * 分类GridView
     */
    class GridAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return gridlist.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_gridview, null);
            ImageView iv_tubiao = (ImageView) view.findViewById(R.id.item_grid_image);
            TextView tv_daohang = (TextView) view.findViewById(R.id.item_grid_text);
            tv_daohang.setText(gridlist[position]);
            iv_tubiao.setImageResource(drawImg[position]);
            return view;
        }
    }


}
