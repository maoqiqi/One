package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.BookListBean;

public interface BookListContract {

    interface Presenter extends BasePresenter {

        void getBook(String q, String tag, int start, int count);
    }

    interface View extends BaseView<Presenter> {

        void setBook(BookListBean bookListBean);
    }
}