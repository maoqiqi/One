package com.android.march.one.presenter;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.NewsDetailBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.NewsDetailContract;
import com.android.march.one.ui.view.NewsDetailView;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailPresenter implements NewsDetailContract.Presenter {

    private NewsDetailContract.View newsDetailView;

    public NewsDetailPresenter(NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
        newsDetailView.setPresenter(this);
    }

    @Override
    public void getNewsDetail(int id) {
        RetrofitManager.getInstance().getNewsAPI().getNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<NewsDetailBean>() {
                    @Override
                    protected void onData(NewsDetailBean newsDetailBean) {
                        newsDetailView.setNewsDetail(newsDetailBean);
                    }
                });
    }
}