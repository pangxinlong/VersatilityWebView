package com.example.activitystartmode.versatilitywebview.webinterface;

import android.net.Uri;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/**
 * Created by password on 16-4-5.
 * Description TODO
 */
public interface WebChromeClientListener {
    void onFileChooser(ValueCallback<Uri> uploadMsg, String acceptType);
    void onGeolocationPermissionsShowPrompt(final String origin,
            final GeolocationPermissions.Callback callback);
    void onReceivedTitle(WebView view, String title);
}
