package com.example.activitystartmode.versatilitywebview;

import com.example.activitystartmode.versatilitywebview.base.BaseInterceptUrl;
import com.example.activitystartmode.versatilitywebview.base.BaseWebViewClient;
import com.example.activitystartmode.versatilitywebview.versatility.OpenFileChooser;
import com.example.activitystartmode.versatilitywebview.versatility.VersatilityInterceptUrl;
import com.example.activitystartmode.versatilitywebview.versatility.VersatilityWebChromeClient;
import com.example.activitystartmode.versatilitywebview.versatility.VersatilityWebView;
import com.example.activitystartmode.versatilitywebview.versatility.VersatilityWebViewClient;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /** 测试调用js方法 */
    private String Strparam
            = "{\"content\":\"<p>杨德龙:两会行情正式启动</p><p>2016-02-22 15:54 和讯基金 杨德龙</p><p>上周A股市场低开高走，成长股强劲反弹。虽然受春节期间海外市场剧震影响指数节后大幅低开，但人民币汇率企稳回升、1月信贷投放大幅超过历史同期水平等提升了投资者情绪，各大指数随后震荡上行，两市成交也有所回升。从行业及主题情况来看，政策托底预期升温，基建相关的建材、建筑、机械等行业领涨市场。而在金价反弹趋势下贵金属板块也延续大幅上涨态势。</p><p>海外市场，上周欧洲银行危机出现转机，减轻了全球投资者担忧，而油价也出现大幅反弹，全球股市普遍反弹。标普500指数全周上涨2.8%，纳斯达克反弹3.9%。板块方面，零售、耐用消费品、半导体和硬件表现最好，涨幅都在4%以上;而防御型板块如食品、电信等相对落后。上周，美国公布了包括通胀在内的一系列主要经济数据，显示美国经济仍正处于复苏的轨道。其中，1月CPI同比上涨1.4%，好于预期的1.3%，环比增速持平;而剔除能源和食品价格的核心CPI同比上涨2.2%，环比上涨0.3%。前一周首次申请失业救济金人数为26.2万人，好于预期的27.5万人，且低于早一期的26.9万人。1月工业产出环比增长0.9%，也好于预期的0.4%。</p><p>房地产方面，继央行降低首付比例之后，财政部又下调契税和营业税，楼市利好政策接二连三，旨在引导刚需入市，提振楼市成交量，进而实现楼市去库存。当前，中央与地方政府都在发力楼市去库存，政策宽松大势所趋，未来仍有政策空间。&quot;北上广深&quot;一线城市由于新增土地供应少，库存量不大，去库存压力小，房价比较坚挺。而二三线城市过去五年大量开发新楼盘，导致库存非常大，去库存任务艰巨，而房价也出现较大幅度下跌。</p><p>今天大盘放量上涨，沪指站上2900点，两市成交量放大到6000亿以上，标志着两会行情正式启动。春节之后，大盘迎来难得的反弹窗口，上周主要是超跌反弹，一些前期跌幅较大的中小创成长股大幅反弹，蓝筹股则按兵不动。而今天以保险、券商为代表的蓝筹股异军崛起，表明反弹深化，沪指有望重新站上3000点整数关口。两会临近，两类市场热点将持续表现，一是&quot;供给侧改革&quot;概念股，如钢铁、煤炭、有色、建材等板块可能继续反弹，一是政策受益板块，如科技电子为代表的新兴产业个股会有所表现。在仓位上，3000点之下，投资者可以继续加仓绩优成长股。</p><p>责任编辑:赵倩 HF007</p><div><div class=\\\"transcoding\\\"><span>原网页已转码便于移动设备阅读</span>&nbsp;&nbsp;<a href='http://m.hexun.com/funds/2016-02-22/182371038.html'>查看原文</a></div></div><p class='more'>【更多互动，请添加优顾理财官方微信号：<font color='#F89333'>yougulicai</font>】</p>\",\"createTime\":\"1456127640000\",\"source\":\"和讯基金\",\"title\":\"杨德龙：两会行情正式启动\"}";

    private RelativeLayout rootView;

    private VersatilityWebView mWebView;

    /** 文件选择器 */
    private OpenFileChooser mOpenFileChooser;

    /** 调用js工具类 */
    private WebViewJsUtil webViewJsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = (RelativeLayout) findViewById(R.id.rootView);
        initView();
    }

    public void initView() {
        mWebView = new VersatilityWebView(this);
        webViewJsUtil = new WebViewJsUtil(mWebView);
        setClient();
        rootView.addView(mWebView);
//        mWebView.loadUrl("https://www.baidu.com");
//        mWebView.loadUrl("http://test.youguu.com/mobile/fund/features/featered-second.html?id=3&type=2");//测试拦截url以及文件下载
//        mWebView.loadUrl("http://119.253.36.116/mobile/template/");//测试调用js方法
        mWebView.loadUrl("http://192.168.3.119:18080/start");//测试文件上传(注，此地址是本人服务器地址)
    }

    private void setClient() {
        //设置WebChromeClient
        VersatilityWebChromeClient versatilityWebChromeClient = new VersatilityWebChromeClient();
        mOpenFileChooser = new OpenFileChooser(this);
        versatilityWebChromeClient.setOpenFileChooser(mOpenFileChooser);

        //设置WebViewClient
        VersatilityWebViewClient versatilityWebViewClient = new VersatilityWebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webViewJsUtil.callJavascript("window.trigger", "setData", Strparam);
            }
        };

        //设置拦截url
        final List<String> needInterceptUrl = new ArrayList<>();
        needInterceptUrl.add("http://");
        needInterceptUrl.add("https://");
        needInterceptUrl.add("youguu://");
        versatilityWebViewClient.setNeedInterceptUrl(needInterceptUrl);
        versatilityWebViewClient.setInterceptUrl(new VersatilityInterceptUrl() {
            @Override
            public void interceptUrl(String interceptType, WebView view, String url) {
                if (interceptType.equals(needInterceptUrl.get(0))) {

                } else if (interceptType.equals(needInterceptUrl.get(1))) {

                } else if (interceptType.equals(needInterceptUrl.get(2))) {

                }
            }
        });
        mWebView.configWeb(versatilityWebChromeClient, versatilityWebViewClient);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mWebView.onBackKeyDown(false, keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri result = null;
        try {
            if (resultCode != RESULT_OK) {
                result = null;
            } else {
                // retrieve from the private variable if the intent is null
                result = (data == null || data.getData() == null) ? mOpenFileChooser
                        .getCapturedImageURI() : data.getData();
            }
        } catch (Exception e) {
            Toast.makeText(this, "activity :" + e,
                    Toast.LENGTH_LONG).show();
        }
        if (mOpenFileChooser.getValueCallbackUri() != null) {
            mOpenFileChooser.getValueCallbackUri().onReceiveValue(result);
            mOpenFileChooser.setValueCallbackUri(null);
        }
        if (mOpenFileChooser.getValueCallbackUris() != null) {
            mOpenFileChooser.getValueCallbackUris().onReceiveValue(new Uri[]{result});
            mOpenFileChooser.setValueCallbackUris(null);
        }
    }
}
