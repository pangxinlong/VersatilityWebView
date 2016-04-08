package com.example.activitystartmode.versatilitywebview;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by pxl on 16-4-1.
 * Description 调用JS工具类
 */
public class WebViewJsUtil {

    private WebView mWebView;


    public WebViewJsUtil(WebView webView) {
        mWebView = webView;
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

    /**
     * java层调用js
     * @param callFun 调用方式
     * @param method  调用方法
     * @param jsonStr json串
     */
    public synchronized void callJavascript(String callFun,String method, String jsonStr) {
        if (method != null && method.length() != 0 && mWebView != null) {
            StringBuffer jsBuffer = new StringBuffer();
            jsBuffer.append("javascript: ");
//            jsBuffer.append("window.trigger");
            jsBuffer.append(callFun);
            jsBuffer.append("(");
            jsBuffer.append("'" + method + "'");
            jsBuffer.append("," + jsonStr + "");
            jsBuffer.append(")");
            try {
                mWebView.loadUrl(jsBuffer.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
