package com.example.activitystartmode.versatilitywebview.webinterface;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

/**
 * Created by pxl on 16-4-5.
 * Description TODO
 */
public interface WebViewClientListener {

    boolean shouldOverrideUrlLoading(WebView view, String url);

    void onPageStarted(WebView view, String url, Bitmap favicon);

    void onReceivedError(WebView view, WebResourceRequest request,
            WebResourceError error);

    void onPageFinished(WebView view, String url);
}
