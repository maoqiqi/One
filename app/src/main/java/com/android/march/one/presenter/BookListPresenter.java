package com.android.march.one.presenter;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.BookListBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.BookListContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookListPresenter implements BookListContract.Presenter {

    private BookListContract.View bookListView;

    public BookListPresenter(BookListContract.View bookListView) {
        this.bookListView = bookListView;
        bookListView.setPresenter(this);
    }

    @Override
    public void getBook(String q, String tag, int start, int count) {
        RetrofitManager.getInstance().getBookAPI().getBook(q, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<BookListBean>() {
                    @Override
                    protected void onData(BookListBean bookListBean) {
                        bookListView.setBook(bookListBean);
                    }
                });
    }
}