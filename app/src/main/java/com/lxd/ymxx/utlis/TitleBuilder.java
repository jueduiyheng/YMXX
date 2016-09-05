package com.lxd.ymxx.utlis;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxd.ymxx.app.R;

public class TitleBuilder {
	private View viewTitle;
	private TextView tvTitle;
	private TextView tvLeft;
	private TextView tvRight;
	private RelativeLayout img_back;
	private ImageView img_right;

	public TitleBuilder(Activity context) {
		viewTitle = context.findViewById(R.id.titlebar);
		tvTitle = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
		tvLeft = (TextView) viewTitle.findViewById(R.id.titlebar_iv_left);
		tvRight = (TextView) viewTitle.findViewById(R.id.titlebar_tv_right);
		img_back = (RelativeLayout) viewTitle.findViewById(R.id.titlebar_img_back);
		img_right = (ImageView) viewTitle.findViewById(R.id.titlebar_img_right);
	}

	public TitleBuilder(View context) {
		viewTitle = context.findViewById(R.id.titlebar);
		tvTitle = (TextView) viewTitle.findViewById(R.id.titlebar_tv);
		tvLeft = (TextView) viewTitle.findViewById(R.id.titlebar_iv_left);
		tvRight = (TextView) viewTitle.findViewById(R.id.titlebar_tv_right);
		img_back = (RelativeLayout) viewTitle.findViewById(R.id.titlebar_img_back);
		img_right = (ImageView) viewTitle.findViewById(R.id.titlebar_img_right);
	}



	// title标题
	public TitleBuilder setTitleText(String text) {
		tvTitle.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
		tvTitle.setText(text);
		return this;
	}

	// left左边的标题
	public TitleBuilder setLeftText(String text) {
		tvLeft.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
		tvLeft.setText(text);
		return this;
	}

	// 返回按钮
	public TitleBuilder setLeftOnClickListener(OnClickListener listener) {
		img_back.setOnClickListener(listener);
		return this;
	}

	// right右边的
	public TitleBuilder setRightText(String text) {
		tvRight.setVisibility(TextUtils.isEmpty(text) ? View.GONE : View.VISIBLE);
		tvRight.setText(text);
		return this;
	}

	public TitleBuilder setRightImage(int resId) {
		img_right.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
		img_right.setImageResource(resId);
		return this;
	}

	public TitleBuilder setRightOnClickListener(OnClickListener listener) {
		if (img_right.getVisibility() == View.VISIBLE) {
			img_right.setOnClickListener(listener);
		} else if (tvRight.getVisibility() == View.VISIBLE) {
			tvRight.setOnClickListener(listener);
		}
		return this;
	}

	public View build() {
		return viewTitle;
	}
}