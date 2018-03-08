package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;

public interface GankContract {

    interface Presenter extends BasePresenter {

        void getRandomHeaderImage(boolean isRandom);
    }

    interface View extends BaseView<Presenter> {

        void setHeaderImage(String url);
    }
}