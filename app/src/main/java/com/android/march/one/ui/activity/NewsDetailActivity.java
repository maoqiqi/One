package com.android.march.one.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.android.march.one.R;
import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.presenter.NewsDetailPresenter;
import com.android.march.one.ui.view.NewsDetailView;

public class NewsDetailActivity extends BaseActivity {

    private NewsDetailView newsDetailView;

    public static void start(Context context, int id, String title) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("title", title);
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(newsDetailView = new NewsDetailView(this));
        StatusBarUtils.setStatusFullscreen(this);
        new NewsDetailPresenter(newsDetailView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            newsDetailView.setData(bundle.getInt("id"), bundle.getString("title"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        newsDetailView.back();
    }
}