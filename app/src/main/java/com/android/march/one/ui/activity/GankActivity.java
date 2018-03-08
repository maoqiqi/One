package com.android.march.one.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.presenter.GankPresenter;
import com.android.march.one.ui.view.GankView;

public class GankActivity extends BaseActivity {

    private GankView gankView;

    public static void start(Context context, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        Intent intent = new Intent(context, GankActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(gankView = new GankView(this));
        StatusBarUtils.setStatusFullscreen(this);
        new GankPresenter(gankView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gankView.setData(bundle.getString("type", "all"));
        }
    }

    @Override
    public void onBackPressed() {
        gankView.back();
    }
}