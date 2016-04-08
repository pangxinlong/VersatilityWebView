package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseInterceptUrl;

import android.webkit.WebView;

/**
 * Created by pxl on 16-4-7.
 * Description TODO
 */
public interface VersatilityInterceptUrl {
    void interceptUrl(String interceptType,WebView view, String url);
}
