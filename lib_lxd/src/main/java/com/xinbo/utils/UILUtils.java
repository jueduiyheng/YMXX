package com.xinbo.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yuchen.lib.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UILUtils {
    private static DisplayImageOptions options;
    private static DisplayImageOptions optionsCorner;
    private static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public static void clearCache() {
        ImageLoader.getInstance().clearDiskCache();
    }

    public static void displayImageNoAnim(String imageUrls, ImageView mImageView) {
        initOptions();
        ImageLoader.getInstance().displayImage(imageUrls, mImageView, options,
                null);
    }

    public static void displayImage(String imageUrls, ImageView mImageView) {
        initOptions();
        ImageLoader.getInstance().displayImage(imageUrls, mImageView, options,
                animateFirstListener);
    }

    public static void displayImageWithRounder(String imageUrls,
                                               ImageView mImageView, int cornerRadiusPixels) {
        initOptions(cornerRadiusPixels);
        ImageLoader.getInstance().displayImage(imageUrls, mImageView, optionsCorner,
                animateFirstListener);
    }
    private static void initOptions() {
        if (options == null) {
            options = new DisplayImageOptions.Builder()
//					.showImageOnLoading(R.drawable.ic_stub)
//					.showImageForEmptyUri(R.drawable.ic_empty)
//					.showImageOnFail(R.drawable.ic_error).cacheInMemory(false)
                    .showImageOnLoading(R.drawable.a11111111)
                    .showImageForEmptyUri(R.drawable.remarkloading_fail)
                    .showImageOnFail(R.drawable.a11111111).cacheInMemory(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .cacheOnDisk(true).considerExifParams(true)
                    .build();
        }
    }

    private static void initOptions(int cornerRadiusPixels) {
        if (optionsCorner == null) {
            optionsCorner = new DisplayImageOptions.Builder()
//					.showImageOnLoading(R.drawable.ic_stub)
//					.showImageForEmptyUri(R.drawable.ic_empty)
//					.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
                    .showImageOnLoading(R.drawable.a11111111)
                    .showImageForEmptyUri(R.drawable.re)
                    .showImageOnFail(R.drawable.a11111111).cacheInMemory(true)
                    .cacheOnDisk(true).considerExifParams(true)
                    .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                    .build();
        }
    }

    private static class AnimateFirstDisplayListener extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 0);//int 设置图片渐显的时间
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
