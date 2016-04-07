package com.example.activitystartmode.versatilitywebview.versatility;


import com.example.activitystartmode.versatilitywebview.WebViewJsUtil;
import com.example.activitystartmode.versatilitywebview.base.BaseInterceptUrl;
import com.example.activitystartmode.versatilitywebview.base.BaseWebSettings;
import com.example.activitystartmode.versatilitywebview.base.BaseWebViewClient;
import com.example.activitystartmode.versatilitywebview.base.WebViewDownload;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by pxl on 16-4-6.
 * Description TODO
 */
public class VersatilityWebView extends WebView {
    private String Strparam="{\"content\":\"<p>杨德龙:两会行情正式启动</p><p>2016-02-22 15:54 和讯基金 杨德龙</p><p>上周A股市场低开高走，成长股强劲反弹。虽然受春节期间海外市场剧震影响指数节后大幅低开，但人民币汇率企稳回升、1月信贷投放大幅超过历史同期水平等提升了投资者情绪，各大指数随后震荡上行，两市成交也有所回升。从行业及主题情况来看，政策托底预期升温，基建相关的建材、建筑、机械等行业领涨市场。而在金价反弹趋势下贵金属板块也延续大幅上涨态势。</p><p>海外市场，上周欧洲银行危机出现转机，减轻了全球投资者担忧，而油价也出现大幅反弹，全球股市普遍反弹。标普500指数全周上涨2.8%，纳斯达克反弹3.9%。板块方面，零售、耐用消费品、半导体和硬件表现最好，涨幅都在4%以上;而防御型板块如食品、电信等相对落后。上周，美国公布了包括通胀在内的一系列主要经济数据，显示美国经济仍正处于复苏的轨道。其中，1月CPI同比上涨1.4%，好于预期的1.3%，环比增速持平;而剔除能源和食品价格的核心CPI同比上涨2.2%，环比上涨0.3%。前一周首次申请失业救济金人数为26.2万人，好于预期的27.5万人，且低于早一期的26.9万人。1月工业产出环比增长0.9%，也好于预期的0.4%。</p><p>房地产方面，继央行降低首付比例之后，财政部又下调契税和营业税，楼市利好政策接二连三，旨在引导刚需入市，提振楼市成交量，进而实现楼市去库存。当前，中央与地方政府都在发力楼市去库存，政策宽松大势所趋，未来仍有政策空间。&quot;北上广深&quot;一线城市由于新增土地供应少，库存量不大，去库存压力小，房价比较坚挺。而二三线城市过去五年大量开发新楼盘，导致库存非常大，去库存任务艰巨，而房价也出现较大幅度下跌。</p><p>今天大盘放量上涨，沪指站上2900点，两市成交量放大到6000亿以上，标志着两会行情正式启动。春节之后，大盘迎来难得的反弹窗口，上周主要是超跌反弹，一些前期跌幅较大的中小创成长股大幅反弹，蓝筹股则按兵不动。而今天以保险、券商为代表的蓝筹股异军崛起，表明反弹深化，沪指有望重新站上3000点整数关口。两会临近，两类市场热点将持续表现，一是&quot;供给侧改革&quot;概念股，如钢铁、煤炭、有色、建材等板块可能继续反弹，一是政策受益板块，如科技电子为代表的新兴产业个股会有所表现。在仓位上，3000点之下，投资者可以继续加仓绩优成长股。</p><p>责任编辑:赵倩 HF007</p><div><div class=\\\"transcoding\\\"><span>原网页已转码便于移动设备阅读</span>&nbsp;&nbsp;<a href='http://m.hexun.com/funds/2016-02-22/182371038.html'>查看原文</a></div></div><p class='more'>【更多互动，请添加优顾理财官方微信号：<font color='#F89333'>yougulicai</font>】</p>\",\"createTime\":\"1456127640000\",\"source\":\"和讯基金\",\"title\":\"杨德龙：两会行情正式启动\"}";
    private BaseWebSettings mBaseWebSettings;
    private  WebViewJsUtil webViewJsUtil;
    private Activity mActivity;

    public VersatilityWebView(Context context) {
        super(context);
        mActivity = (Activity) context;
        init();
    }

    private void init() {

        //配置webview Settings
        mBaseWebSettings = new VersatilityWebSettings(mActivity, this);

        //JS工具类
       final WebViewJsUtil webViewJsUtil = new WebViewJsUtil(mActivity, this);
        //设置JS回调
        VersatilityWebJsCallBack versatilityWebJs = new VersatilityWebJsCallBack(this);
        versatilityWebJs.setJsInterface("jhssJSBridge");//自定义


        //设置WebChromeClient
        setWebChromeClient(new VersatilityWebChromeClient(mActivity));

        //设置WebViewClient
        BaseWebViewClient baseWebViewClient = new BaseWebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webViewJsUtil.callJavascript("window.trigger","setData",Strparam);
            }
        };

        baseWebViewClient.customInterceptUrl("youguu");//自定义
        baseWebViewClient.setInterceptUrl(new BaseInterceptUrl() {
            @Override
            public void interceptHttpUrl(WebView view, String url) {
                Toast.makeText(mActivity, "intercept http", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void interceptHttpsUrl(WebView view, String url) {
                Toast.makeText(mActivity, "intercept https", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void interceptCustom(String customName, WebView view, String url) {
                Toast.makeText(mActivity, "intercept " + customName, Toast.LENGTH_SHORT).show();
            }
        });
        setWebViewClient(baseWebViewClient);


        //设置下载
        WebViewDownload webViewDownload=new WebViewDownload(this,mActivity);
    }

    public boolean onBackKeyDown(boolean finishOnBackPressed,final int keyCode, final KeyEvent keyevent) {
        boolean flag;
        if (finishOnBackPressed || keyCode != KeyEvent.KEYCODE_BACK || !canGoBack()) {
            if (!canGoBack()) {
                stopLoading();
            }
            return false;

        } else {
                goBack();
            flag = true;
        }
        return flag;
    }
}
