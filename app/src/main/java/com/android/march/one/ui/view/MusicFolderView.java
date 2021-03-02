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
import com.android.march.one.presenter.MusicListPresenter;
import com.android.march.one.utils.MusicUtils;

import butterknife.BindView;

public class MusicFolderView extends RootView<BasePresenter> {

    @BindView(R.id.ivHeader)
    ImageView ivHeader;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.musicListView)
    MusicListView musicListView;

    public MusicFolderView(Context context) {
        super(context);
    }

    public MusicFolderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_music_folder;
    }

    public void setData(String folderPath) {
        setToolBar(toolbar);
        toolbar.setTitle(MusicUtils.getFolderName(folderPath));

        new MusicListPresenter(musicListView);
        musicListView.setData(activity, 0, 0, folderPath);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        musicListView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        musicListView.onActivityResult(requestCode, resultCode, data);
    }
}