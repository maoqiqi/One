package com.android.march.one.presenter;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.NewsListBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.NewsListContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsListPresenter implements NewsListContract.Presenter {

    private NewsListContract.View newsListView;

    public NewsListPresenter(NewsListContract.View newsListView) {
        this.newsListView = newsListView;
        newsListView.setPresenter(this);
    }

    @Override
    public void getLatestNews() {
        RetrofitManager.getInstance().getNewsAPI().getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<NewsListBean>() {
                    @Override
                    protected void onData(NewsListBean newsListBean) {
                        newsListView.setLatestNews(newsListBean);
                    }
                });
    }
}