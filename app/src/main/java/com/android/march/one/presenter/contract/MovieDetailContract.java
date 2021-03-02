package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.MovieDetailBean;

public interface MovieDetailContract {

    interface Presenter extends BasePresenter {

        void getMovieDetail(String id);

        void getMovieDetailPersonList(String id);
    }

    interface View extends BaseView<Presenter> {

        void setMovieDetail(MovieDetailBean movieDetailBean);

        void moreInfo();
    }
}