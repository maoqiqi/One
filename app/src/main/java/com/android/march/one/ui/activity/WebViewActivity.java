package com.android.march.one.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.march.one.R;
import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.WebViewView;

import java.lang.reflect.Method;

public class WebViewActivity extends BaseActivity {

    private WebViewView webViewView;

    private int titleColor;

    public static void start(Context context, int titleColor, String title, String url) {
        start(context, titleColor, title, url, false);
    }

    public static void start(Context context, int titleColor, String title, String url, boolean showCollection) {
        Bundle bundle = new Bundle();
        bundle.putInt("titleColor", titleColor);
        bundle.putString("title", title);
        bundle.putString("url", url);
        bundle.putBoolean("showCollection", showCollection);
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(webViewView = new WebViewView(this));
        StatusBarUtils.setStatusFullscreen(this);
        webViewView.setData();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            titleColor = bundle.getInt("titleColor", R.color.colorNavigation);
            String title = bundle.getString("title", "");
            String url = bundle.getString("url", "");
            boolean showCollection = bundle.getBoolean("showCollection", false);
            webViewView.loadUrl(titleColor, title, url, showCollection);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //  显示popup内的图片
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    Log.e(getClass().getSimpleName(), "onMenuOpened...unable to set icons for overflow menu", e);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                for (int i = 0; i < 3; i++) {
                    menu.getItem(i).getIcon().setTint(getColor(titleColor));
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_web_share:
                return true;
            case R.id.menu_copy_url:
                // 链接复制成功
                return true;
            case R.id.menu_open_url:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        webViewView.back();
    }
}