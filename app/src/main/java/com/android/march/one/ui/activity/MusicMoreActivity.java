package com.android.march.one.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.android.march.one.R;
import com.android.march.one.base.BaseActivity;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.model.bean.MusicSongBean;
import com.android.march.one.ui.view.MusicMoreView;
import com.android.march.one.utils.MusicUtils;
import com.android.march.one.utils.ScreenUtils;

public class MusicMoreActivity extends BaseActivity {

    private MusicMoreView musicMoreView;

    public static void start(Context context, MusicSongBean songBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_SONG);
        bundle.putSerializable("songBean", songBean);
        Intent intent = new Intent(context, MusicMoreActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void start(Context context, MusicArtistBean artistBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_ARTIST);
        bundle.putSerializable("artistBean", artistBean);
        Intent intent = new Intent(context, MusicMoreActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void start(Context context, MusicAlbumBean albumBean) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_ALBUM);
        bundle.putSerializable("albumBean", albumBean);
        Intent intent = new Intent(context, MusicMoreActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static void start(Context context, String folderPath) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", MusicUtils.TYPE_FOLDER);
        bundle.putSerializable("folderPath", folderPath);
        Intent intent = new Intent(context, MusicMoreActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(musicMoreView = new MusicMoreView(this));

        musicMoreView.post(() -> {
            Window window = getWindow();
            window.setWindowAnimations(R.style.DialogSheetAnimation);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            int halfScreenHeight = ScreenUtils.getScreenHeight() / 2;
            if (musicMoreView.getHeight() > halfScreenHeight)
                params.height = halfScreenHeight;
            else
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            window.setAttributes(params);
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int type = bundle.getInt("type", -1);
            if (type == MusicUtils.TYPE_SONG) {
                MusicSongBean songBean = (MusicSongBean) bundle.getSerializable("songBean");
                musicMoreView.setData(songBean);
            } else if (type == MusicUtils.TYPE_ARTIST) {
                MusicArtistBean artistBean = (MusicArtistBean) bundle.getSerializable("artistBean");
                musicMoreView.setData(artistBean);
            } else if (type == MusicUtils.TYPE_ALBUM) {
                MusicAlbumBean albumBean = (MusicAlbumBean) bundle.getSerializable("albumBean");
                musicMoreView.setData(albumBean);
            } else if (type == MusicUtils.TYPE_FOLDER) {
                String folderPath = bundle.getString("folderPath");
                musicMoreView.setData(folderPath);
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        // 设置了自己的动画之后,必须重写,解决退出动画无效或者被干扰的问题
        overridePendingTransition(0, 0);
    }
}