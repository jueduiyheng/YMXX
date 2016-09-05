package com.lxd.ymxx.view.photo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.lxd.ymxx.view.ActionSheetDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import Decoder.BASE64Encoder;

/**
 * Created by kelvin on 16/4/20.
 */
public class ImageUtils {

    public static final int REQUEST_CODE_FROM_CAMERA = 5001;
    public static final int REQUEST_CODE_FROM_ALBUM = 5002;
    public static final int REQUEST_CODE_CROP = 5003;
    public static final int INT = 25;
    public static final int INT1 = 24;
    public static final int INT2 = 49;

    /**
     * 存放拍照图片的uri地址
     */
    private static Uri imageUriFromALBUM;
    private static Uri imageUriFromCamera;

    /**
     * 记录是处于什么状态：拍照or相册
     */
    private static int state = 0;

    /**
     * 显示获取照片不同方式对话框
     */
    public static void showImagePickDialog(final Activity activity) {
        new ActionSheetDialog(activity)
                .builder()
                .setTitle("请选择操作")
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("拍照", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                state = 1;
                                pickImageFromCamera(activity);

                            }
                        })
                .addSheetItem("从相册选取", ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                state = 2;
                                pickImageFromAlbum(activity);
                            }
                        }).show();
    }

    /**
     * 打开本地相册选取图片
     */
    public static void pickImageFromAlbum(final Activity activity) {

        //隐式调用，可能出现多种选择
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, REQUEST_CODE_FROM_ALBUM);

        /**
         Intent intent = new Intent();
         intent.setAction(Intent.ACTION_PICK);
         intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         activity.startActivityForResult(intent,REQUEST_CODE_FROM_ALBUM);
         */
    }


    /**
     * 打开相机拍照获取图片
     */
    public static void pickImageFromCamera(final Activity activity) {

        imageUriFromCamera = getImageUri();

        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);    //指定拍照的图片存在imageUriFromCamera下,如果直接在返回时使用getData()获取的是压缩过的Bitmap数据
        activity.startActivityForResult(intent, REQUEST_CODE_FROM_CAMERA);
    }


    /**
     * 根据指定目录产生一条图片Uri
     */
    private static Uri getImageUri() {
        String imageName = new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + ".jpg";
        String path = MyConstant.PhotoDir + imageName;
        return UriUtils.getUriFromFilePath(path);
    }

    /**
     * 复制一条Uri，避免因为操作而对原图片产生影响
     */
    public static void copyImageUri(Activity activity, Uri uri) {

        String imageName = new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + ".jpg";
        String copyPath = MyConstant.PhotoDir + imageName;
        FileUtils.copyfile(UriUtils.getRealFilePath(activity, uri), copyPath, true);
        imageUriFromALBUM = UriUtils.getUriFromFilePath(copyPath);
    }

    /**
     * 删除一条图片Uri
     */
    public static void deleteImageUri(Context context, Uri uri) {
        context.getContentResolver().delete(uri, null, null);
    }


    /**
     * 裁剪图片返回
     */
    public static void cropImageUri(Activity activity, Uri uri, int outputX, int outputY) {

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        activity.startActivityForResult(intent, REQUEST_CODE_CROP);
    }

    /**
     * 根据状态返回图片Uri
     */
    public static Uri getCurrentUri() {

        if (state == 1) {
            return imageUriFromCamera;
        } else if (state == 2) {
            return imageUriFromALBUM;
        } else return null;
    }

    //图片转化成base64字符串
    public static String GetImageStr(String path) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        File file = new File(path);
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
}
