package com.android.march.one.presenter;

import android.util.Log;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.MovieDetailBean;
import com.android.march.one.model.bean.MovieTypePersonBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.MovieDetailContract;
import com.android.march.one.ui.view.MovieDetailView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieDetailContract.View movieDetailView;

    public MovieDetailPresenter(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
        movieDetailView.setPresenter(this);
    }

    @Override
    public void getMovieDetail(String id) {
        RetrofitManager.getInstance().getMovieAPI().getMovieDetail("561", id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MovieDetailBean>() {
                    @Override
                    protected void onData(MovieDetailBean movieDetailBean) {
                        movieDetailView.setMovieDetail(movieDetailBean);
                    }
                });
    }

    @Override
    public void getMovieDetailPersonList(String id) {
        RetrofitManager.getInstance().getMovieAPI().getMovieDetailPersonList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MovieTypePersonBean>() {
                    @Override
                    protected void onData(MovieTypePersonBean movieTypePersonBean) {
                        // movieDetailView.setMovieDetail(movieDetailBean);
                        Log.e("info", movieTypePersonBean.toString());
                    }
                });
    }
}