package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.NewsDetailBean;

public interface NewsDetailContract {

    interface Presenter extends BasePresenter {

        // 得到新闻详情
        void getNewsDetail(int id);
    }

    interface View extends BaseView<Presenter> {

        void setNewsDetail(NewsDetailBean newsDetailBean);
    }
}