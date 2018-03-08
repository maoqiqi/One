package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;

import butterknife.BindView;

public class UpdateDescriptionView extends RootView<BasePresenter> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public UpdateDescriptionView(Context context) {
        super(context);
    }

    public UpdateDescriptionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_update_description;
    }

    public void setData() {
        setToolBar(toolbar);
    }
}