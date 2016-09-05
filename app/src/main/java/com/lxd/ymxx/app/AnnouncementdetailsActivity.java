package com.lxd.ymxx.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.lxd.ymxx.model.announcement.Announcement;
import com.lxd.ymxx.utlis.TitleBuilder;

public class AnnouncementdetailsActivity extends Activity implements OnClickListener {

	private TextView tv_title;
	private TextView tv_author;
	private TextView tv_time;
	private TextView tv_content;
	private Announcement.DataBean dataBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_announcement);
		dataBean = (Announcement.DataBean) getIntent().getSerializableExtra("aaa");
		Log.e("dataBean",""+dataBean.getAdvanceTitle());
		initui();
	}


	private void initui() {
		new TitleBuilder(this).setTitleText("店铺公告").setLeftOnClickListener(this);
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_author = (TextView) findViewById(R.id.tv_author);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_time.setText(dataBean.getAdvanceTime());
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titlebar_img_back:
			finish();
			break;

		default:
			break;
		}
	}

}
