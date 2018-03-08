package com.android.march.one.ui.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.ui.activity.WebViewActivity;

import butterknife.BindView;

public class ProblemFeedbackView extends RootView<BasePresenter> implements View.OnClickListener {

    private final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1335354725";
    private final String emailUrl = "mailto:fengqi.mao.march@gmail.com";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvIssues)
    TextView tvIssues;
    @BindView(R.id.tvProblems)
    TextView tvProblems;
    @BindView(R.id.tvQQ)
    TextView tvQQ;
    @BindView(R.id.tvEmail)
    TextView tvEmail;

    public ProblemFeedbackView(Context context) {
        super(context);
    }

    public ProblemFeedbackView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_problem_feedback;
    }

    public void setData() {
        setToolBar(toolbar);
        tvIssues.setOnClickListener(this);
        tvProblems.setOnClickListener(this);
        tvQQ.setOnClickListener(this);
        tvEmail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvIssues:
                WebViewActivity.start(activity, R.color.colorNavigation, getString(R.string.issues_name), getString(R.string.issues_url));
                break;
            case R.id.tvProblems:
                WebViewActivity.start(activity, R.color.colorNavigation, getString(R.string.faq_name), getString(R.string.faq_url));
                break;
            case R.id.tvQQ:
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                break;
            case R.id.tvEmail:
                activity.startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(emailUrl)));
                break;
        }
    }
}