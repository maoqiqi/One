package com.android.march.one.ui.activity;

import android.os.Bundle;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.UpdateDescriptionView;

public class UpdateDescriptionActivity extends BaseActivity {

    private UpdateDescriptionView updateDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(updateDescriptionView = new UpdateDescriptionView(this));
        StatusBarUtils.setStatusFullscreen(this);
        updateDescriptionView.setData();
    }

    @Override
    public void onBackPressed() {
        updateDescriptionView.back();
    }
}