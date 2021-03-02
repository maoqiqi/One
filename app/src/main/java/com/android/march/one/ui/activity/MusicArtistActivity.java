package com.android.march.one.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import android.view.View;

import com.android.march.one.StatusBarUtils;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.ui.view.MusicArtistView;

public class MusicArtistActivity extends BaseActivity {

    private MusicArtistView artistView;

    public static void start(Activity activity, MusicArtistBean artistBean, String imageUrl, Pair<View, String> ivMusicPair) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("artistBean", artistBean);
        bundle.putString("imageUrl", imageUrl);
        Intent intent = new Intent(activity, MusicArtistActivity.class);
        intent.putExtras(bundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, ivMusicPair);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(artistView = new MusicArtistView(this));
        StatusBarUtils.setStatusFullscreen(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            artistView.setData((MusicArtistBean) bundle.getSerializable("artistBean"), bundle.getString("imageUrl"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        artistView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        artistView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        artistView.back();
    }
}