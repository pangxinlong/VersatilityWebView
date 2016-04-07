package com.example.activitystartmode.versatilitywebview.base;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by pxl on 16-4-6.
 * Description TODO
 */
public abstract class BaseWebSettings {

    public WebView mWebView;
    public Context mContext;
    public WebSettings mWebSettings;
    public BaseWebSettings(Context context,WebView webView) {
        mWebView = webView;
        mContext=context;
        mWebSettings=mWebView.getSettings();
        setWebSettings(mWebSettings);
    }

    public abstract void setWebSettings(WebSettings webSettings);

}
