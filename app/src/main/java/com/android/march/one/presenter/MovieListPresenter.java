package com.android.march.one.presenter;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.MovieListBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.MovieListContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieListPresenter implements MovieListContract.Presenter {

    private MovieListContract.View movieListView;

    public MovieListPresenter(MovieListContract.View movieListView) {
        this.movieListView = movieListView;
        movieListView.setPresenter(this);
    }

    @Override
    public void getInTheatersMovies(String city, int start, int count) {
        RetrofitManager.getInstance().getMovieAPI().inTheatersMovies(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MovieListBean>() {
                    @Override
                    protected void onData(MovieListBean movieListBean) {
                        movieListView.setData(movieListBean);
                    }
                });
    }

    @Override
    public void getComingSoonMovies(int start, int count) {
        RetrofitManager.getInstance().getMovieAPI().comingSoonMovies(start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MovieListBean>() {
                    @Override
                    protected void onData(MovieListBean movieListBean) {
                        movieListView.setData(movieListBean);
                    }
                });
    }

    @Override
    public void searchMovie(String q, String tag, int start, int count) {
        RetrofitManager.getInstance().getMovieAPI().searchMovie(q, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<MovieListBean>() {
                    @Override
                    protected void onData(MovieListBean movieListBean) {
                        movieListView.setData(movieListBean);
                    }
                });
    }
}