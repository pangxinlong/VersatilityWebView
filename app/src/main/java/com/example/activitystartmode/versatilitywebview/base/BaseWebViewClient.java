package com.example.activitystartmode.versatilitywebview.base;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by pxl on 16-4-7.
 * Description TODO
 */
public class BaseWebViewClient extends WebViewClient {

    public static final String HTTP_URL = "http://";

    public static final String HTTPS_URL = "https://";

    private BaseInterceptUrl mInterceptHttpUrl;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (mInterceptHttpUrl == null) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        if (url.startsWith(HTTP_URL)) {
            mInterceptHttpUrl.interceptHttpUrl(view, url);
        } else if (url.startsWith(HTTPS_URL)) {
            mInterceptHttpUrl.interceptHttpsUrl(view, url);
        } else if (mCustomUrl != null && url.startsWith(mCustomUrl)) {
            mInterceptHttpUrl.interceptCustom(mCustomUrl,view, url);
        }

        return super.shouldOverrideUrlLoading(view, url);
    }

    public void setInterceptUrl(BaseInterceptUrl interceptHttpUrl) {
        mInterceptHttpUrl = interceptHttpUrl;
    }


    public void customInterceptUrl(String customUrl) {
        mCustomUrl=customUrl;
    }


    public String mCustomUrl;
}
