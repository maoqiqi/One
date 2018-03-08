package com.android.march.one.ui.activity;

import android.os.Bundle;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.ui.view.GankCollectionView;

public class GankCollectionActivity extends BaseActivity {

    private GankCollectionView gankCollectionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(gankCollectionView = new GankCollectionView(this));
        StatusBarUtils.setStatusFullscreen(this);
        gankCollectionView.setData();
    }

    @Override
    public void onBackPressed() {
        gankCollectionView.back();
    }
}