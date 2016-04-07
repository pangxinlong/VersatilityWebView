package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseWebChromeClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import java.io.File;

/**
 * Created by password on 16-4-6.
 * Description TODO
 */
public class VersatilityWebChromeClient extends BaseWebChromeClient {

    private Activity mActivity;

    public VersatilityWebChromeClient(Activity activity) {
        mActivity = activity;
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        openCustomFileChooser();
    }

    // openFileChooser for Android < 3.0
    public void openFileChooser(ValueCallback<Uri> uploadMsg) {
        openFileChooser(uploadMsg, "");
    }

    //openFileChooser for other Android versions
    public void openFileChooser(ValueCallback<Uri> uploadMsg,
            String acceptType,
            String capture) {
        openFileChooser(uploadMsg, acceptType);
    }

    private Uri mCapturedImageURI = null;

    public static final int FILECHOOSER_RESULTCODE = 2888;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageL;
    private void openCustomFileChooser() {
        try {
            // Create AndroidExampleFolder at sdcard
            File imageStorageDir = new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES)
                    , "default");
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
            mActivity.startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onShowFileChooser(WebView webView,
            ValueCallback<Uri[]> filePathCallback,
            FileChooserParams fileChooserParams) {
        mUploadMessageL = filePathCallback;
        openCustomFileChooser();
        return true;
    }
}
