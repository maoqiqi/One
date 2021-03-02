package com.android.march.one.ui.view;

import android.content.Context;
import android.os.Build;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.android.march.one.ObservableWebView;
import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;

import butterknife.BindView;

public class WebViewView extends RootView<BasePresenter> {

    @BindView(R.id.view)
    View view;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    ObservableWebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.fabCollection)
    FloatingActionButton fabCollection;

    public WebViewView(Context context) {
        super(context);
    }

    public WebViewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_web_view;
    }

    @Override
    public void back() {
        if (webView.canGoBack()) webView.goBack();
        else super.back();
    }

    public void setData() {
        setToolBar(toolbar);
        progressBar.setVisibility(View.VISIBLE);

        WebSettings setting = webView.getSettings();
        // 告诉WebView启用JavaScript执行.默认的是false.
        setting.setJavaScriptEnabled(true);
        //  页面加载好以后,再放开图片
        setting.setBlockNetworkImage(false);
        // 网页内容的宽度是否可大于WebView控件的宽度
        setting.setLoadWithOverviewMode(false);
        // 使用localStorage则必须打开
        setting.setDomStorageEnabled(true);
        // 排版适应屏幕
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是否支持多个窗口.
        setting.setSupportMultipleWindows(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // webView从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启.
            setting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 设置字体默认缩放大小(改变网页字体大小,setTextSize api14 被弃用)
        setting.setTextZoom(100);
        // 设置此属性,可任意比例缩放.
        setting.setUseWideViewPort(true);

        webView.setInitialScale(1);
        webView.setWebViewClient(new MyWebViewClient());
        webView.setWebChromeClient(new MyWebChromeClient());
    }

    public void loadUrl(int titleColor, String title, String url, boolean showCollection) {
        view.setBackgroundResource(titleColor);
        toolbar.setBackgroundResource(titleColor);
        toolbar.setPopupTheme(getPopupTheme(titleColor));
        if (showCollection) {
            fabCollection.setVisibility(View.VISIBLE);
            fabCollection.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), titleColor));
            webView.setOnScrollChangeListener((l, t, oldl, oldt) -> {
                if (t - oldt > 0) {
                    fabCollection.hide();
                } else {
                    fabCollection.show();
                }
            });

            fabCollection.setOnClickListener(v -> {
            });
        }
        if (!title.equals("")) toolbar.setTitle(title);
        webView.loadUrl(url);
    }

    public int getPopupTheme(int titleColor) {
        switch (titleColor) {
            case R.color.colorHome:
                return R.style.ToolBarPopupTheme_Home;
            case R.color.colorNews:
                return R.style.ToolBarPopupTheme_News;
            case R.color.colorBook:
                return R.style.ToolBarPopupTheme_Book;
            case R.color.colorMusic:
                return R.style.ToolBarPopupTheme_Music;
            case R.color.colorMovie:
                return R.style.ToolBarPopupTheme_Movie;
            default:
                return R.style.ToolBarPopupTheme_Navigation;
        }
    }

    private final class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }
    }

    private final class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (progressBar == null) return;
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (toolbar == null) return;
            toolbar.setTitle(title);
        }
    }
}