package com.example.activitystartmode.versatilitywebview.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Browser;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by pxl on 16-4-7.
 * Description TODO
 */
public class WebViewDownload {

    private Activity mActivity;

    public WebViewDownload(WebView webView, Activity activity) {
        mActivity=activity;
        init(webView, activity);
    }

    private void init(final WebView webView, final Activity activity) {
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                    String mimetype, long contentLength) {
                if (activity != null) {
                    Uri uri = Uri.parse(url);
                    if (isBrowerExists(uri)) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.putExtra(Browser.EXTRA_APPLICATION_ID,
                                activity.getPackageName());
                        activity.startActivity(intent);
//                        activity.finish();
                    } else {
                        if (activity != null && !activity.isFinishing()) {
                            Toast.makeText(activity, "浏览器不存在，无法下载文件", Toast.LENGTH_SHORT).show();
                        }
                        webView.goBack();
                    }
                }
            }
        });
    }

    public boolean isBrowerExists(final Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        List<ResolveInfo> queryIntentActivities =
                mActivity.getPackageManager().queryIntentActivities(intent,
                        PackageManager.GET_RESOLVED_FILTER);
        return queryIntentActivities != null && !queryIntentActivities.isEmpty();
    }
}
