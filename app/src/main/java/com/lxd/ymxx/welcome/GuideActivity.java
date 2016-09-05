package com.lxd.ymxx.welcome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.lxd.ymxx.app.R;

public class GuideActivity extends FragmentActivity {
	
	private ViewPager vp;
	private int[] lauoutId={R.layout.welcome1,R.layout.welcome2,R.layout.welcome3};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		vp=(ViewPager) findViewById(R.id.main_vp);
		
		MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
		// 页面滑动监听器
		vp.setPageTransformer(true, new TranslatePageTransformer());
		vp.setAdapter(adapter);
	}
	
	
	class MyAdapter extends FragmentStatePagerAdapter{

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment f=new TranslateFragment();
			Bundle bundle=new Bundle();
			bundle.putInt("layoutId", lauoutId[position]);
			f.setArguments(bundle);
			return f;
		}

		@Override
		public int getCount() {
			
			return 3;
		}
		
	}
}
