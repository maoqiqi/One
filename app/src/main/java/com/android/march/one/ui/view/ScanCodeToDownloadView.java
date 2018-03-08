package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;

import butterknife.BindView;

public class ScanCodeToDownloadView extends RootView<BasePresenter> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public ScanCodeToDownloadView(Context context) {
        super(context);
    }

    public ScanCodeToDownloadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_scan_code_to_download;
    }

    public void setData() {
        setToolBar(toolbar);
    }
}