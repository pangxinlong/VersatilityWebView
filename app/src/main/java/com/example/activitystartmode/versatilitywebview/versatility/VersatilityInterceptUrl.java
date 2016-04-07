package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseInterceptUrl;

import android.webkit.WebView;

/**
 * Created by pxl on 16-4-7.
 * Description TODO
 */
public interface VersatilityInterceptUrl extends BaseInterceptUrl {
    void interceptYouguuUrl(WebView view, String url);
}
