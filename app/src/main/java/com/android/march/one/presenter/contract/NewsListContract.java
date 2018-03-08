package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.NewsListBean;

public interface NewsListContract {

    interface Presenter extends BasePresenter {

        void getLatestNews();
    }

    interface View extends BaseView<Presenter> {

        void setLatestNews(NewsListBean newsListBean);
    }
}