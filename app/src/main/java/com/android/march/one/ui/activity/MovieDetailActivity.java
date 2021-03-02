package com.android.march.one.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.march.one.R;
import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.presenter.MovieDetailPresenter;
import com.android.march.one.ui.view.MovieDetailView;

public class MovieDetailActivity extends BaseActivity {

    private MovieDetailView movieDetailView;

    public static void start(Activity activity, String id, String title, String imageUrl, ImageView ivMovie) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("title", title);
        bundle.putString("imageUrl", imageUrl);
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtras(bundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, ivMovie, ViewCompat.getTransitionName(ivMovie));
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(movieDetailView = new MovieDetailView(this));
        StatusBarUtils.setStatusFullscreen(this);
        new MovieDetailPresenter(movieDetailView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movieDetailView.setData(bundle.getString("id"), bundle.getString("title"), bundle.getString("imageUrl"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        getMenuInflater().inflate(R.menu.menu_more_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_more_info:
                movieDetailView.moreInfo();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        movieDetailView.back();
    }
}