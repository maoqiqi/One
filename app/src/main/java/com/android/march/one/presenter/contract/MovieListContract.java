package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.MovieListBean;

public interface MovieListContract {

    interface Presenter extends BasePresenter {

        void getInTheatersMovies(String city, int start, int count);

        void getComingSoonMovies(int start, int count);

        void searchMovie(String q, String tag, int start, int count);
    }

    interface View extends BaseView<Presenter> {

        void setData(MovieListBean movieListBean);
    }
}