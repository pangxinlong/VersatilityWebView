package com.example.activitystartmode.versatilitywebview.base;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by pxl on 16-4-6.
 * Description WebView settings类
 */
public abstract class BaseWebSettings {

    public WebView mWebView;

    public Context mContext;

    public WebSettings mWebSettings;

    public BaseWebSettings(Context context, WebView webView) {
        mWebView = webView;
        mContext = context;
        mWebSettings = mWebView.getSettings();
        setWebSettings(mWebSettings);
    }

    public abstract void setWebSettings(WebSettings webSettings);

    /**
     * 设置数据库
     */
    public void setDataBase() {
    }

    public void setDataBase(String dataBaseDirName) {
    }


    /**
     * 设置缓存
     */
    public void setCache() {
    }

    public void setCache(int cacheSize) {
    }

    public void setCache(String cacheDirName) {
    }

    public void setCache(int cacheSize, String cacheDirName) {
    }
}
