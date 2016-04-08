package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseWebViewClient;

import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pxl on 16-4-7.
 * Description 多功能WebViewClient
 * 拥有拦截url功能
 */
public class VersatilityWebViewClient extends BaseWebViewClient {

    private List<String> needInterceptUrl = new ArrayList<>();

    /***
     * 设置需要拦截的url
     */
    public void setNeedInterceptUrl(List<String> list) {
        needInterceptUrl.clear();
        needInterceptUrl.addAll(list);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (mVersatilityInterceptUrl == null) {
            return super.shouldOverrideUrlLoading(view, url);
        }
        for (int i = 0; i < needInterceptUrl.size(); i++) {
            String interceptUrl = needInterceptUrl.get(i);
            if (url.startsWith(interceptUrl)) {
                mVersatilityInterceptUrl
                        .interceptUrl(interceptUrl, view, url);
            }
        }
        return super.shouldOverrideUrlLoading(view, url);
    }


    /**
     * 自定义接口
     * @param versatilityInterceptUrl
     */
    public void setInterceptUrl(VersatilityInterceptUrl versatilityInterceptUrl) {
        mVersatilityInterceptUrl = versatilityInterceptUrl;
    }
    VersatilityInterceptUrl mVersatilityInterceptUrl;
}
