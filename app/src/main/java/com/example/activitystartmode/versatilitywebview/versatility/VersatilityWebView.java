package com.example.activitystartmode.versatilitywebview.versatility;


import com.example.activitystartmode.versatilitywebview.WebViewJsUtil;
import com.example.activitystartmode.versatilitywebview.base.BaseWebSettings;
import com.example.activitystartmode.versatilitywebview.base.BaseWebViewClient;
import com.example.activitystartmode.versatilitywebview.base.WebViewDownload;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebView;

/**
 * Created by pxl on 16-4-6.
 * Description 多功能webView
 */
public class VersatilityWebView extends WebView {

    private BaseWebSettings mBaseWebSettings;

    private Activity mActivity;

    public VersatilityWebView(Context context) {
        super(context);
        mActivity = (Activity) context;
    }

    public void configWeb(VersatilityWebChromeClient chromeClient, BaseWebViewClient viewClient) {
        setWebSettings();
        setClient(chromeClient, viewClient);
        setJs();

        //设置下载
        WebViewDownload webViewDownload = new WebViewDownload(this, mActivity);
    }

    /**
     * 配置webview Settings
     */
    private void setWebSettings() {
        mBaseWebSettings = new VersatilityWebSettings(mActivity, this);
        mBaseWebSettings.setCache();
        mBaseWebSettings.setDataBase();
    }

    /**
     * 设置Client
     * @param chromeClient
     * @param viewClient
     */
    private void setClient(VersatilityWebChromeClient chromeClient, BaseWebViewClient viewClient) {
        setWebChromeClient(chromeClient);
        setWebViewClient(viewClient);
    }

    /**
     * 设置JS交互
     */
    private void setJs() {
        //设置JS回调
        VersatilityWebJsCallBack versatilityWebJs = new VersatilityWebJsCallBack(this);
        versatilityWebJs.setJsInterface("jhssJSBridge");//自定义
    }


    public boolean onBackKeyDown(boolean finishOnBackPressed, final int keyCode,
            final KeyEvent keyevent) {
        boolean flag;
        if (finishOnBackPressed || keyCode != KeyEvent.KEYCODE_BACK || !canGoBack()) {
            if (!canGoBack()) {
                stopLoading();
            }
            return false;
        } else {
            goBack();
            flag = true;
        }
        return flag;
    }
}
