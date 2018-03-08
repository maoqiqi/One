package com.android.march.one.presenter;

import android.content.Context;

import com.android.march.one.MediaLoader;
import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.MusicAlbumBean;
import com.android.march.one.model.bean.MusicArtistBean;
import com.android.march.one.model.bean.MusicSongBean;
import com.android.march.one.presenter.contract.MusicListContract;
import com.android.march.one.utils.SortOrder;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MusicListPresenter implements MusicListContract.Presenter {

    private MusicListContract.View musicListView;

    public MusicListPresenter(MusicListContract.View musicListView) {
        this.musicListView = musicListView;
        musicListView.setPresenter(this);
    }

    @Override
    public void getSongList(Context context, Long artistId, long albumId, String folderPath, String sortOrder) {
        Observable.create((Observable.OnSubscribe<List<MusicSongBean>>) subscriber -> {
            try {
                subscriber.onNext(MediaLoader.getSongBeanList(context, artistId, albumId, folderPath, sortOrder));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<List<MusicSongBean>>() {

                    @Override
                    protected void onData(List<MusicSongBean> musicSongBeanList) {
                        musicListView.setSongList(musicSongBeanList);
                    }
                });
    }

    @Override
    public void getArtistList(Context context, String sortOrder) {
        Observable.create((Observable.OnSubscribe<List<MusicArtistBean>>) subscriber -> {
            try {
                subscriber.onNext(MediaLoader.getArtistBeanList(context, sortOrder));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<List<MusicArtistBean>>() {
                    @Override
                    protected void onData(List<MusicArtistBean> musicArtistBeanList) {
                        musicListView.setArtistList(musicArtistBeanList);
                    }
                });
    }

    @Override
    public void getAlbumList(Context context, String sortOrder) {
        Observable.create((Observable.OnSubscribe<List<MusicAlbumBean>>) subscriber -> {
            try {
                subscriber.onNext(MediaLoader.getAlbumBeanList(context, sortOrder));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<List<MusicAlbumBean>>() {
                    @Override
                    protected void onData(List<MusicAlbumBean> musicAlbumBeanList) {
                        musicListView.setAlbumList(musicAlbumBeanList);
                    }
                });
    }

    @Override
    public void getFolderList(Context context) {
        Observable.create((Observable.OnSubscribe<List<String>>) subscriber -> {
            try {
                subscriber.onNext(MediaLoader.getFolderList(context));
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<List<String>>() {
                    @Override
                    protected void onData(List<String> folderList) {
                        getCount(context, folderList);
                    }
                });
    }

    private void getCount(Context context, List<String> folderList) {
        Observable.from(folderList)
                .subscribeOn(Schedulers.io())
                .map(s -> MediaLoader.getSongBeanList(context, 0, 0, s, SortOrder.SongSortOrder.SONG_A_Z).size()).toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<List<Integer>>() {
                    @Override
                    protected void onData(List<Integer> integers) {
                        musicListView.setFolderList(folderList, integers);
                    }
                });
    }
}