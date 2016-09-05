package com.lxd.StudyDemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lxd.myapplication.R;

public class MainActivity extends Activity implements View.OnClickListener, ImageUtil.CropHandler {
    private ImageView selectPhoto, takePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectPhoto = (ImageView) findViewById(R.id.photo_select);
        takePhoto = (ImageView) findViewById(R.id.photo_take);
        selectPhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo_select:
                //选择相册
                Intent galleryIntent = ImageUtil
                        .getCropHelperInstance()
                        .buildGalleryIntent();
                startActivityForResult(galleryIntent,
                        ImageUtil.REQUEST_GALLERY);
                break;
            case R.id.photo_take:
                //拍摄照片
                Intent cameraIntent = ImageUtil
                        .getCropHelperInstance()
                        .buildCameraIntent();
                startActivityForResult(cameraIntent,
                        ImageUtil.REQUEST_CAMERA);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageUtil.getCropHelperInstance().sethandleResultListerner(MainActivity.this, requestCode, resultCode,
                data);
    }

    @Override
    public void onPhotoCropped(Bitmap photo, int requestCode) {
        switch (requestCode) {
            case ImageUtil.RE_GALLERY:
                selectPhoto.setImageBitmap(photo);
                break;
            case ImageUtil.RE_CAMERA:
                takePhoto.setImageBitmap(photo);
                break;
        }

    }

    @Override
    public void onCropCancel() {

    }

    @Override
    public void onCropFailed(String message) {

    }

    @Override
    public Activity getContext() {
        return MainActivity.this;
    }
}