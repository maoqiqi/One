package com.android.march.one.ui.activity;

import android.os.Bundle;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.ProjectIntroductionView;

public class ProjectIntroductionActivity extends BaseActivity {

    private ProjectIntroductionView projectIntroductionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(projectIntroductionView = new ProjectIntroductionView(this));
        StatusBarUtils.setStatusFullscreen(this);
        projectIntroductionView.setData();
    }

    @Override
    public void onBackPressed() {
        projectIntroductionView.back();
    }
}