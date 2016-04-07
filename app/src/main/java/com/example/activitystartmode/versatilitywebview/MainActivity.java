package com.example.activitystartmode.versatilitywebview;

import com.example.activitystartmode.versatilitywebview.base.BaseInterceptUrl;
import com.example.activitystartmode.versatilitywebview.base.BaseWebViewClient;
import com.example.activitystartmode.versatilitywebview.versatility.VersatilityWebChromeClient;
import com.example.activitystartmode.versatilitywebview.versatility.VersatilityWebView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout rootView;
    private VersatilityWebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = (RelativeLayout) findViewById(R.id.rootView);
        initView();
    }

    public void initView() {
         mWebView = new VersatilityWebView(this);
        rootView.addView(mWebView);
//        mWebView.loadUrl("https://www.baidu.com");
//        mWebView.loadUrl("http://test.youguu.com/mobile/fund/features/featered-second.html?id=3&type=2");//测试拦截url

        mWebView.loadUrl("http://119.253.36.116/mobile/template/");//测试调用js方法
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(mWebView.onBackKeyDown(false,keyCode,event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
