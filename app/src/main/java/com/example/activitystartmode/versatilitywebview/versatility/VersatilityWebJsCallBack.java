package com.example.activitystartmode.versatilitywebview.versatility;

import com.example.activitystartmode.versatilitywebview.base.BaseWebJsCallBack;
import com.example.activitystartmode.versatilitywebview.base.BaseWebJsInterface;

import android.webkit.WebView;

/**
 * Created by pxl on 16-4-6.
 * Description TODO
 */
public class VersatilityWebJsCallBack extends BaseWebJsCallBack {

    public static final String DEFAULT_INTERFACE_NAME = "jsBridge";

    private BaseWebJsInterface mBaseWebJsInterface;

    public VersatilityWebJsCallBack(WebView webView) {
        super(webView);
    }

    /**
     * 设置js交互类
     */
    public void setJsInterface() {
        setJsInterface(null, "");
    }

    public void setJsInterface(String jsInterfaceName) {
        setJsInterface(null, jsInterfaceName);
    }

    public void setJsInterface(Object object) {
        setJsInterface(object, "");
    }

    public void setJsInterface(Object object, String jsInterfaceName) {
        String interfaceName;
        if (jsInterfaceName == null || jsInterfaceName.isEmpty() || jsInterfaceName.equals("")) {
            interfaceName = DEFAULT_INTERFACE_NAME;
        } else {
            interfaceName = jsInterfaceName;
        }

        Object InterfaceObject;
        if (object == null) {
            if (mBaseWebJsInterface == null) {
                mBaseWebJsInterface = new BaseWebJsInterface();
            }
            InterfaceObject = mBaseWebJsInterface;
        } else {
            InterfaceObject = object;
        }

        mWebView.addJavascriptInterface(InterfaceObject, interfaceName);
    }

}
