package com.lxd.ymxx.app;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.socialize.PlatformConfig;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
		// 新浪微博
		PlatformConfig.setSinaWeibo("1507413625", "6c7909635ed57baeddc02de46b7cc3b8");
		//QQ和zone
		PlatformConfig.setQQZone("1105542461", "hGLaMnNXEYMjQdQk");
		//微信 appid appsecret
		PlatformConfig.setWeixin("wx3957721434e550f8", "a2308b34c7563a44ec0ab1d5ab6e74db");
		initImageLoader(getApplicationContext());
	}
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(50 * 1024 * 1024)
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

}
