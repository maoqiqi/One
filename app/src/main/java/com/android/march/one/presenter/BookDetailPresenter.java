package com.android.march.one.presenter;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.BookListBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.BookDetailContract;
import com.android.march.one.ui.view.BookDetailView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookDetailPresenter implements BookDetailContract.Presenter {

    private BookDetailContract.View bookDetailView;

    public BookDetailPresenter(BookDetailView bookDetailView) {
        this.bookDetailView = bookDetailView;
        bookDetailView.setPresenter(this);
    }

    @Override
    public void getBookDetail(String id) {
        RetrofitManager.getInstance().getBookAPI().getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BookListBean.BookBean>() {
                    @Override
                    protected void onData(BookListBean.BookBean bookBean) {
                        bookDetailView.setBookDetail(bookBean);
                    }
                });
    }
}