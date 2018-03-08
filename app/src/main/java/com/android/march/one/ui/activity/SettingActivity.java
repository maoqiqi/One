package com.android.march.one.ui.activity;

import android.os.Bundle;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.SettingView;

public class SettingActivity extends BaseActivity {

    private SettingView settingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(settingView = new SettingView(this));
        StatusBarUtils.setStatusFullscreen(this);
        settingView.setData();
    }

    @Override
    public void onBackPressed() {
        settingView.back();
    }
}