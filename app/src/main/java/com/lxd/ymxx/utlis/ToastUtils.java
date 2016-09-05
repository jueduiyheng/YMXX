package com.lxd.ymxx.utlis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lxd.ymxx.app.R;

public final class ToastUtils {
	private static Toast toast;

	public static void showToast(Context context, String text) {
		if (toast != null) {
			toast.cancel();
		}
		toast = new Toast(context);
		View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
		TextView tvText = (TextView) view.findViewById(R.id.tv_toast);
		tvText.setText(text);
		toast.setView(view);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.show();
	}

	private ToastUtils() {

	}
}
