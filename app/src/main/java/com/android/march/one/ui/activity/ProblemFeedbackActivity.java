package com.android.march.one.ui.activity;

import android.os.Bundle;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.ProblemFeedbackView;

public class ProblemFeedbackActivity extends BaseActivity {

    private ProblemFeedbackView problemFeedbackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(problemFeedbackView = new ProblemFeedbackView(this));
        StatusBarUtils.setStatusFullscreen(this);
        problemFeedbackView.setData();
    }

    @Override
    public void onBackPressed() {
        problemFeedbackView.back();
    }
}