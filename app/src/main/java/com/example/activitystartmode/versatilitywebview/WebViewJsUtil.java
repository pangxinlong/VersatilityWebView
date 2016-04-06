package com.example.activitystartmode.versatilitywebview;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by pxl on 16-4-1.
 * Description TODO
 */
public class WebViewJsUtil {

    private WebView mWebView;

    private Context mContext;

    public WebViewJsUtil(Context context, WebView webView, String jSInterfaceName) {
        mContext = context;
        mWebView = webView;
        init(jSInterfaceName);
    }

    private void init(String jSInterfaceName) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(mContext, jSInterfaceName);

    }


    /**
     * java层调用js
     *
     * @param funName js侧方法名称
     * @param params  js侧方法传入的变量
     */
    public synchronized void callJavascript(String funName, String[] params) {
        if (funName != null && funName.length() != 0 && mWebView != null) {

            StringBuffer jsBuffer = new StringBuffer();
            String jsParams = "";
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    String param = params[i];
                    if (i == params.length - 1) {
                        jsParams += ("'" + param + "'");
                    } else {
                        jsParams += ("'" + param + "' ,");
                    }
                }
            }
            jsBuffer.append("javascript: ").append(funName + "(" + jsParams + ");");
            try {
                mWebView.loadUrl(jsBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
