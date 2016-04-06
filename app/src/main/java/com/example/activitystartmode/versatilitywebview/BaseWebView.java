package com.example.activitystartmode.versatilitywebview;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

/**
 * Created by pxl on 16-4-1.
 * Description TODO
 */
public abstract class BaseWebView extends WebView {

    private Context mContext;

    private WebSettings webSettings;

    //    private  WebViewJsUtil mWebViewJsUtil;
    public BaseWebView(Context context) {
        super(context);
        mContext = context;
        configWebView();
    }


    public void configWebView() {
        webSettings = getSettings();
        webSettings.setBuiltInZoomControls(true);
    }

    public WebSettings getWebSetting() {
        return webSettings;
    }

    public void setClient(WebChromeClient webChromeClient, WebViewClient webViewClient) {
        setWebChromeClient(webChromeClient);
        setWebViewClient(webViewClient);
    }

    /**
     * 设置数据库
     *
     * @param databaseName 数据库名称
     */
    public void setDatabase(String databaseName) {
        webSettings.setDatabaseEnabled(true);
        webSettings.setDatabasePath(mContext.getDir(databaseName, Context.MODE_PRIVATE).getPath());
    }


    public static final int defaultCacheSize = 1024 * 1024 * 8;

    /**
     * 设置缓存
     */
    public abstract void setCache(int cacheMaxSize, String cacheName);

    public abstract void setCache(String cacheName);
}
