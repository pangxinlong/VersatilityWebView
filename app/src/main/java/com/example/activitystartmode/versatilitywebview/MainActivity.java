package com.example.activitystartmode.versatilitywebview;

import com.example.activitystartmode.versatilitywebview.webinterface.WebChromeClientListener;
import com.example.activitystartmode.versatilitywebview.webinterface.WebViewClientListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = (RelativeLayout) findViewById(R.id.rootView);
        initView();
    }

    public void initView() {
        VersatilityWebView versatilityWebView = new VersatilityWebView(this);
        WebViewJsUtil webViewJsUtil = new WebViewJsUtil(this, versatilityWebView, "");
        versatilityWebView.setWebChromeClientListener(new WebChromeClientListener() {
            @Override
            public void onFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
//                openCustomFileChooser();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                    GeolocationPermissions.Callback callback) {

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        });

        versatilityWebView.setWebViewClientListener(new WebViewClientListener() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                    WebResourceError error) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });

        rootView.addView(versatilityWebView);
        versatilityWebView.setCache("test");
        versatilityWebView.setDatabase("test");
        versatilityWebView.loadUrl("https://www.baidu.com");
    }

//    private Uri mCapturedImageURI = null;
//
//    public static final int FILECHOOSER_RESULTCODE = 2888;
//
//    public void openCustomFileChooser() {
//        try {
//            // Create AndroidExampleFolder at sdcard
//            File imageStorageDir = new File(
//                    Environment.getExternalStoragePublicDirectory(
//                            Environment.DIRECTORY_PICTURES)
//                    , "jhss");
//            if (!imageStorageDir.exists()) {
//                // Create AndroidExampleFolder at sdcard
//                imageStorageDir.mkdirs();
//            }
//            // Create camera captured image file path and name
//            File file = new File(
//                    imageStorageDir + File.separator + "IMG_"
//                            + String.valueOf(System.currentTimeMillis())
//                            + ".jpg");
//            mCapturedImageURI = Uri.fromFile(file);
//            // Camera capture image intent
//            final Intent captureIntent = new Intent(
//                    MediaStore.ACTION_IMAGE_CAPTURE);
//            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
//            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//            i.addCategory(Intent.CATEGORY_OPENABLE);
//            i.setType("image/*");
//            // Create file chooser intent
//            Intent chooserIntent = Intent.createChooser(i, "上传身份证照");
//            // Set camera intent to file chooser
//            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
//                    new Parcelable[]{captureIntent});
//            // On select image call onActivityResult method of activity
//            startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
