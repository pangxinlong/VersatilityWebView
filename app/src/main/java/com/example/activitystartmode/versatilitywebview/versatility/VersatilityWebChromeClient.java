package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseWebChromeClient;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.File;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by pxl on 16-4-6.
 * Description 多功能WebChromeClient
 * 拥有选择上传文件功能
 */
public class VersatilityWebChromeClient extends BaseWebChromeClient {

    public static final int REQUEST_PERMISSION_CODE = 8;

    private static String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private OpenFileChooser mOpenFileChooser;

    private Activity mActivity;

    public VersatilityWebChromeClient(Activity activity) {
        mActivity = activity;
    }

    public void setOpenFileChooser(OpenFileChooser openFileChooser) {
        mOpenFileChooser = openFileChooser;
    }


    // openFileChooser for Android 3.0+
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
        mOpenFileChooser.setValueCallbackUri(uploadMsg);
        if (checkPermission()) {
            if (mOpenFileChooser != null) {
                mOpenFileChooser.openCustomFileChooser();
            }
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
        mOpenFileChooser.setValueCallbackUris(filePathCallback);
        if (checkPermission()) {
            if (mOpenFileChooser != null) {
                mOpenFileChooser.openCustomFileChooser();
            }
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == VersatilityWebChromeClient.REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("WelcomeActivity", "权限允许");
                if (mOpenFileChooser != null) {
                    mOpenFileChooser.openCustomFileChooser();
                }
            } else {
                Log.i("WelcomeActivity", "权限拒绝");
                cancelCallback();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri result;
        try {
            switch (resultCode) {
                case RESULT_OK:
                    if (requestCode == OpenFileChooser.FILECHOOSER_REQUESTCODE) {
                        // retrieve from the private variable if the intent is null
                        result = (data == null || data.getData() == null) ? mOpenFileChooser
                                .getCapturedImageURI() : data.getData();

                        if (mOpenFileChooser.getValueCallbackUri() != null) {
                            mOpenFileChooser.getValueCallbackUri().onReceiveValue(result);
                            mOpenFileChooser.setValueCallbackUri(null);
                        }
                        if (mOpenFileChooser.getValueCallbackUris() != null) {
                            if (result != null) {
                                mOpenFileChooser.getValueCallbackUris().onReceiveValue(new Uri[]{result});
                            } else {
                                mOpenFileChooser.getValueCallbackUris().onReceiveValue(null);
                            }
                            mOpenFileChooser.setValueCallbackUris(null);
                        }
                    }
                    break;
                case RESULT_CANCELED:
                    Log.e("pxl===", "RESULT_CANCELED");
                    cancelCallback();
                    break;
            }
        } catch (Exception e) {
            Log.e("onActivityResult", "activity :" + e.getMessage());
        }
    }

    public void cancelCallback() {
        if (mOpenFileChooser.getValueCallbackUri() != null) {
            mOpenFileChooser.getValueCallbackUri().onReceiveValue(null);
        }
        if (mOpenFileChooser.getValueCallbackUris() != null) {
            mOpenFileChooser.getValueCallbackUris().onReceiveValue(null);
        }
    }

    /**
     * Android 运行时权限
     */
    private boolean checkPermission() {
        try {
            boolean isPermission = true;
            int permission = ActivityCompat
                    .checkSelfPermission(mActivity, Manifest.permission.CAMERA);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS, REQUEST_PERMISSION_CODE);//请求码
                return false;
            }
            return isPermission;
        } catch (Exception e) {
            Log.e("pxl===", e.getMessage());
            return true;
        }
    }
}
