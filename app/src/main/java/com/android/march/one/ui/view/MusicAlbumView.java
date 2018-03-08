package com.android.march.one.ui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.march.one.R;
import com.android.march.one.RootView;
import com.android.march.one.base.BasePresenter;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.presenter.MusicListPresenter;
import com.android.march.one.utils.MusicUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;

public class MusicAlbumView extends RootView<BasePresenter> {

    @BindView(R.id.ivMusic)
    ImageView ivMusic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.musicListView)
    MusicListView musicListView;

    public MusicAlbumView(Context context) {
        super(context);
    }

    public MusicAlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_music_album;
    }

    public void setData(MusicAlbumBean albumBean, String imageUrl) {
        setToolBar(toolbar);
        toolbar.setTitle(MusicUtils.getAlbum(albumBean.getAlbum()));

        Glide.with(getContext()).load(imageUrl).placeholder(R.drawable.ic_music).into(ivMusic);

        new MusicListPresenter(musicListView);
        musicListView.setData(activity, 0, albumBean.get_id(), null);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        musicListView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        musicListView.onActivityResult(requestCode, resultCode, data);
    }
}