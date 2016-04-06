package com.example.activitystartmode.versatilitywebview;

import com.example.activitystartmode.versatilitywebview.webinterface.WebChromeClientListener;
import com.example.activitystartmode.versatilitywebview.webinterface.WebViewClientListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by pxl on 16-4-1.
 * Description TODO
 */
public class VersatilityWebView extends BaseWebView {

    private Context mContext;

    private WebSettings mWebSettings;

    public VersatilityWebView(Context context) {
        super(context);
        mContext = context;
//        addWebChromeClient();
//        addWebViewClient();
        init();
    }

    /**
     * 设置缓存
     * @param cacheName
     */
    @Override
    public void setCache(String cacheName) {
        setCache(0, cacheName);
    }

    @Override
    public void setCache(int cacheMaxSize, String cacheName) {
        String appCacheDir = mContext.getDir(cacheName, Context.MODE_PRIVATE).getPath();
        if (appCacheDir != null && !appCacheDir.equals("")) {
            mWebSettings.setAppCachePath(appCacheDir);
        }
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (cacheMaxSize == 0) {
            mWebSettings.setAppCacheMaxSize(defaultCacheSize);
        } else {
            mWebSettings.setAppCacheMaxSize(cacheMaxSize);
        }

        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setGeolocationDatabasePath(appCacheDir);
    }


    private void init() {
        mWebSettings = getSettings();
        setClient(new WebChromeClient() {
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                if (mWebChromeClientListener != null) {
                    mWebChromeClientListener.onFileChooser(uploadMsg, acceptType);
                }
            }

            // openFileChooser for Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                openFileChooser(uploadMsg, "");
            }

            //openFileChooser for other Android versions
            public void openFileChooser(ValueCallback<Uri> uploadMsg,
                    String acceptType,
                    String capture) {
                openFileChooser(uploadMsg, acceptType);
            }


            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                    GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                if (mWebChromeClientListener != null) {
                    mWebChromeClientListener.onGeolocationPermissionsShowPrompt(origin, callback);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (mWebChromeClientListener != null) {
                    mWebChromeClientListener.onReceivedTitle(view, title);
                }
            }
        }, new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (mWebViewClientListener != null) {
                    mWebViewClientListener.shouldOverrideUrlLoading(view, url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (mWebViewClientListener != null) {
                    mWebViewClientListener.onPageStarted(view, url, favicon);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                    WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (mWebViewClientListener != null) {
                    mWebViewClientListener.onReceivedError(view, request, error);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mWebViewClientListener != null) {
                    mWebViewClientListener.onPageFinished(view, url);
                }
            }


        });
    }

//    /**
//     * 设置WebChromeClient
//     */
//    private void addWebChromeClient() {
//        setWebChromeClient(new WebChromeClient() {
//            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
//                if (mWebChromeClientListener != null) {
//                    mWebChromeClientListener.onFileChooser(uploadMsg, acceptType);
//                }
//            }
//
//            // openFileChooser for Android < 3.0
//            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
//                openFileChooser(uploadMsg, "");
//            }
//
//            //openFileChooser for other Android versions
//            public void openFileChooser(ValueCallback<Uri> uploadMsg,
//                    String acceptType,
//                    String capture) {
//                openFileChooser(uploadMsg, acceptType);
//            }
//
//
//            @Override
//            public void onGeolocationPermissionsShowPrompt(String origin,
//                    GeolocationPermissions.Callback callback) {
//                super.onGeolocationPermissionsShowPrompt(origin, callback);
//                if (mWebChromeClientListener != null) {
//                    mWebChromeClientListener.onGeolocationPermissionsShowPrompt(origin, callback);
//                }
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                if (mWebChromeClientListener != null) {
//                    mWebChromeClientListener.onReceivedTitle(view, title);
//                }
//            }
//        });
//    }
//
//    /**
//     * 设置webViewClient
//     */
//    public void addWebViewClient() {
//        setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (mWebViewClientListener != null) {
//                    mWebViewClientListener.shouldOverrideUrlLoading(view, url);
//                }
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                if (mWebViewClientListener != null) {
//                    mWebViewClientListener.onPageStarted(view, url, favicon);
//                }
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request,
//                    WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                if (mWebViewClientListener != null) {
//                    mWebViewClientListener.onReceivedError(view, request, error);
//                }
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                if (mWebViewClientListener != null) {
//                    mWebViewClientListener.onPageFinished(view, url);
//                }
//            }
//
//
//        });
//    }

    private WebChromeClientListener mWebChromeClientListener;

    public void setWebChromeClientListener(WebChromeClientListener webChromeClientListener) {
        mWebChromeClientListener = webChromeClientListener;
    }

    private WebViewClientListener mWebViewClientListener;

    public void setWebViewClientListener(WebViewClientListener webViewClientListener) {
        mWebViewClientListener = webViewClientListener;
    }
}
