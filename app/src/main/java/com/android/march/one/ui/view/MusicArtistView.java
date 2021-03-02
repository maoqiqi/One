package com.android.march.one.ui.view;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.presenter.MusicListPresenter;
import com.android.march.one.utils.MusicUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class MusicArtistView extends RootView<BasePresenter> {

    @BindView(R.id.ivMusic)
    ImageView ivMusic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.musicListView)
    MusicListView musicListView;

    public MusicArtistView(Context context) {
        super(context);
    }

    public MusicArtistView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_music_artist;
    }

    public void setData(MusicArtistBean artistBean, String imageUrl) {
        setToolBar(toolbar);
        toolbar.setTitle(MusicUtils.getArtist(artistBean.getArtist()));

        Glide.with(getContext()).load(imageUrl).placeholder(R.drawable.ic_music).into(ivMusic);

        new MusicListPresenter(musicListView);
        musicListView.setData(activity, artistBean.get_id(), 0, null);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        musicListView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        musicListView.onActivityResult(requestCode, resultCode, data);
    }
}