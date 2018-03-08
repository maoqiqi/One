package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.BookListBean;

public interface BookDetailContract {

    interface Presenter extends BasePresenter {

        // 得到图书详情
        void getBookDetail(String id);
    }

    interface View extends BaseView<Presenter> {

        void setBookDetail(BookListBean.BookBean bookBean);

        void moreInfo();
    }
}