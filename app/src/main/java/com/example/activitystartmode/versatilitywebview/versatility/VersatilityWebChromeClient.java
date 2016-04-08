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
 * Created by pxl on 16-4-6.
 * Description 多功能WebChromeClient
 * 拥有选择上传文件功能
 */
public class VersatilityWebChromeClient extends BaseWebChromeClient {


    private OpenFileChooser mOpenFileChooser;

    public VersatilityWebChromeClient() {

    }

    public void setOpenFileChooser(OpenFileChooser openFileChooser) {
        mOpenFileChooser = openFileChooser;
    }


    // openFileChooser for Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {

        if (mOpenFileChooser != null) {
            mOpenFileChooser.setValueCallbackUri(uploadMsg);
            mOpenFileChooser.openCustomFileChooser();
        }
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


    @Override
    public boolean onShowFileChooser(WebView webView,
            ValueCallback<Uri[]> filePathCallback,
            FileChooserParams fileChooserParams) {
        if (mOpenFileChooser != null) {
            mOpenFileChooser.setValueCallbackUris(filePathCallback);
            mOpenFileChooser.openCustomFileChooser();
        }
        return true;
    }
}
