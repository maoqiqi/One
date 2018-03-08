package com.android.march.one.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.ui.view.MusicAlbumView;

public class MusicAlbumActivity extends BaseActivity {

    private MusicAlbumView albumView;

    public static void start(Activity activity, MusicAlbumBean albumBean, String imageUrl, Pair<View, String> ivMusicPair) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("albumBean", albumBean);
        bundle.putString("imageUrl", imageUrl);
        Intent intent = new Intent(activity, MusicAlbumActivity.class);
        intent.putExtras(bundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, ivMusicPair);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(albumView = new MusicAlbumView(this));
        StatusBarUtils.setStatusFullscreen(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            albumView.setData((MusicAlbumBean) bundle.getSerializable("albumBean"), bundle.getString("imageUrl"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        albumView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        albumView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        albumView.back();
    }
}