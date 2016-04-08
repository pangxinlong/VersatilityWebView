package com.example.activitystartmode.versatilitywebview.versatility;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.webkit.ValueCallback;

import java.io.File;

/**
 * Created by pxl on 16-4-7.
 * Description 打开文件选择器
 */
public class OpenFileChooser {

    private Uri mCapturedImageURI = null;

    public static final int FILECHOOSER_REQUESTCODE = 2888;

    private int mRequestCode;

    private String mDirName;//文件路径

    private ValueCallback<Uri> mUploadMessage;//Android 3.0+

    private ValueCallback<Uri[]> mUploadMessageL;//Android <3.0

    private static final String DEFAULT_FILE_NAME = "defautl_file_name";

    private Activity mActivity;

    public OpenFileChooser(Activity activity) {
        mActivity = activity;
    }

    /***
     * 设置文件夹名称
     */
    public void setOpenCustomDirName(String dirName) {
        mDirName = dirName;
    }

    private String getOpenCustomDirName() {
        if (mDirName == null || mDirName.isEmpty() || mDirName.equals("")) {
            mDirName = DEFAULT_FILE_NAME;
        }
        return mDirName;
    }


    public void setFileChooserRequestCode(int resultCode) {
        mRequestCode = resultCode;
    }

    private int getFileChooserRequestCode() {
        if (mRequestCode == 0) {
            mRequestCode = FILECHOOSER_REQUESTCODE;
        }
        return mRequestCode;
    }

    public Uri getCapturedImageURI() {
        return mCapturedImageURI;
    }


    public void setValueCallbackUri(ValueCallback<Uri> valueCallback) {
        mUploadMessage = valueCallback;
    }

    public ValueCallback<Uri> getValueCallbackUri() {
        return mUploadMessage;
    }


    public void setValueCallbackUris(ValueCallback<Uri[]> valueCallback) {
        mUploadMessageL = valueCallback;
    }

    public ValueCallback<Uri[]> getValueCallbackUris() {
        return mUploadMessageL;
    }


    public void openCustomFileChooser() {
        try {
            // Create AndroidExampleFolder at sdcard
            File imageStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES)
                    , getOpenCustomDirName());

            if (!imageStorageDir.exists()) {
                // Create AndroidExampleFolder at sdcard
                imageStorageDir.mkdirs();
            }
            // Create camera captured image file path and name
            File file = new File(
                    imageStorageDir + File.separator + "IMG_"
                            + String.valueOf(System.currentTimeMillis())
                            + ".jpg");
            mCapturedImageURI = Uri.fromFile(file);
            // Camera capture image intent
            final Intent captureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("image/*");
            // Create file chooser intent
            Intent chooserIntent = Intent.createChooser(i, "上传身份证照");
            // Set camera intent to file chooser
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    new Parcelable[]{captureIntent});
            // On select image call onActivityResult method of activity
            mActivity.startActivityForResult(chooserIntent, getFileChooserRequestCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
