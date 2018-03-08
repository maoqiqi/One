package com.android.march.one.ui.view;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.ShareUtils;
import com.android.march.one.base.BasePresenter;

import butterknife.BindView;

public class ProjectIntroductionView extends RootView<BasePresenter> {

    @BindView(R.id.ivHeader)
    ImageView ivHeader;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.fabShare)
    FloatingActionButton fabShare;

    public ProjectIntroductionView(Context context) {
        super(context);
    }

    public ProjectIntroductionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_project_introduction;
    }

    public void setData() {
        setToolBar(toolbar);
        fabShare.setOnClickListener(v -> ShareUtils.shareText(activity, getString(R.string.share_download_content, getString(R.string.download_url))));
    }
}