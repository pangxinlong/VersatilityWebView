package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseWebSettings;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by pxl on 16-4-6.
 * Description TODO
 */
public class VersatilityWebSettings extends BaseWebSettings {

    public static final int DEFAULT_CACHE_SIZE = 1024 * 1024 * 8;// 默认缓存大小（8M）

    public static final String DEFAULT_CACHE_NAME = "default_cache_name";//默认缓存文件名称

    public static final String DEFAULT_DATABASE_NAME = "default_database_name";//默认数据库名称

    public VersatilityWebSettings(Context context, WebView webView) {
        super(context, webView);
    }


    @Override
    public void setWebSettings(WebSettings webSettings) {
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebView.setWebContentsDebuggingEnabled(true);
        }
        setDataBase();
        setCache();
    }

    /**
     * 设置数据库
     */
    private void setDataBase() {
        setDataBase("");
    }

    public void setDataBase(String dataBaseDirName) {
        mWebSettings.setDatabaseEnabled(true);
        String dbPath;
        if (dataBaseDirName == null || dataBaseDirName.isEmpty()||dataBaseDirName.equals("")) {
            dbPath = mContext.getDir(DEFAULT_DATABASE_NAME, Context.MODE_PRIVATE).getPath();
        } else {
            dbPath = mContext.getDir(dataBaseDirName, Context.MODE_PRIVATE).getPath();
        }
        mWebSettings.setDatabasePath(dbPath);
        // 可以读取文件缓存
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setGeolocationDatabasePath(dbPath);
    }

    /**
     * 设置缓存
     */

    private void setCache() {
        setCache(0, "");
    }

    public void setCache(int cacheSize) {
        setCache(cacheSize, "");
    }

    public void setCache(String cacheDirName) {
        setCache(0, cacheDirName);
    }

    public void setCache(int cacheSize, String cacheDirName) {
        if (cacheSize <= 0) {
            mWebSettings.setAppCacheMaxSize(DEFAULT_CACHE_SIZE);
        } else {
            mWebSettings.setAppCacheMaxSize(cacheSize);
        }
        String appCacheDir;
        if (cacheDirName == null || cacheDirName.isEmpty()||cacheDirName.equals("")) {
            appCacheDir = mContext.getDir(DEFAULT_CACHE_NAME, Context.MODE_PRIVATE).getPath();
        } else {
            appCacheDir = mContext.getDir(cacheDirName, Context.MODE_PRIVATE).getPath();
        }
        mWebSettings.setAppCachePath(appCacheDir);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

    }

}
