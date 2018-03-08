package com.android.march.one.ui.activity;

import android.os.Bundle;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.AboutView;

public class AboutActivity extends BaseActivity {

    private AboutView aboutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(aboutView = new AboutView(this));
        StatusBarUtils.setStatusFullscreen(this);
        aboutView.setData();
    }

    @Override
    public void onBackPressed() {
        aboutView.back();
    }
}