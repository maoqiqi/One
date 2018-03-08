package com.android.march.one.ui.activity;

import android.os.Bundle;

import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.SplashView;

public class SplashActivity extends BaseActivity {

    private SplashView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(splashView = new SplashView(this));
        splashView.setData();
    }

    @Override
    public void onBackPressed() {
        splashView.back();
    }
}