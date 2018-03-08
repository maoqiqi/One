package com.android.march.one.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;

import butterknife.BindView;

public class GankCollectionView extends RootView<BasePresenter> {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public GankCollectionView(Context context) {
        super(context);
    }

    public GankCollectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_gank_collection;
    }

    public void setData() {
        setToolBar(toolbar);
        // 收藏失败,请重试
        // 取消收藏失败,请重试
        // 暂无收藏
    }
}