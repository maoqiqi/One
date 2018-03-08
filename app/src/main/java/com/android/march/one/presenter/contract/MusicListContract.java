package com.android.march.one.presenter.contract;

import android.content.Context;
import android.content.Intent;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.model.bean.MusicSongBean;

import java.util.List;

public interface MusicListContract {

    interface Presenter extends BasePresenter {

        /**
         * 得到歌曲列表
         */
        void getSongList(Context context, Long artistId, long albumId, String folderPath, String sortOrder);

        /**
         * 得到艺术家列表
         */
        void getArtistList(Context context, String sortOrder);

        /**
         * 得到专辑列表
         */
        void getAlbumList(Context context, String sortOrder);

        /**
         * 获取包含音频文件的文件夹信息
         */
        void getFolderList(Context context);
    }

    interface View extends BaseView<Presenter> {

        void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        /**
         * 显示歌曲数据
         */
        void setSongList(List<MusicSongBean> musicSongBeanList);

        /**
         * 显示艺术家数据
         */
        void setArtistList(List<MusicArtistBean> musicArtistBeanList);

        /**
         * 显示专辑数据
         */
        void setAlbumList(List<MusicAlbumBean> musicAlbumBeanList);

        /**
         * 显示文件夹数据
         */
        void setFolderList(List<String> folderList, List<Integer> integers);
    }
}