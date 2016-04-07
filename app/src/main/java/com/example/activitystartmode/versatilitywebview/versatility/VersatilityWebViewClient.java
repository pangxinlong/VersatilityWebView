package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseWebViewClient;

import android.webkit.WebView;

/**
 * Created by pxl on 16-4-7.
 * Description TODO
 */
public class VersatilityWebViewClient extends BaseWebViewClient {
    public static final String YOUGUU_URL = "youguu";
    public VersatilityWebViewClient() {

    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if(url.startsWith(YOUGUU_URL)){
            mVersatilityInterceptUrl.interceptYouguuUrl(view,url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    private VersatilityInterceptUrl mVersatilityInterceptUrl;
    public void setYouguuUrl(VersatilityInterceptUrl versatilityInterceptUrl){

    }
}
