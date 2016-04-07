package com.example.activitystartmode.versatilitywebview.base;

import android.webkit.WebView;

/**
 * Created by pxl on 16-4-7.
 * Description 链接url的基类
 */
public interface BaseInterceptUrl {
    void interceptHttpUrl(WebView view, String url);
    void interceptHttpsUrl(WebView view, String url);
    void interceptCustom(String CustomName,WebView view, String url);
}
