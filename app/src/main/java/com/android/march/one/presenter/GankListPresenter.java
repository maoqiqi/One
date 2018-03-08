package com.android.march.one.presenter;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.DataBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.GankListContract;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GankListPresenter implements GankListContract.Presenter {

    private GankListContract.View gankListView;

    public GankListPresenter(GankListContract.View gankListView) {
        this.gankListView = gankListView;
        gankListView.setPresenter(this);
    }

    @Override
    public void getData(String type, int pageIndex, int pageCount) {
        RetrofitManager.getInstance().getGankAPI().getData(type, pageIndex, pageCount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<DataBean>() {
                    @Override
                    protected void onData(DataBean dataBean) {
                        gankListView.setData(dataBean.getResultList());
                    }
                });
    }
}