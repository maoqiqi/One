package com.android.march.one.presenter;

import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.DataBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.GankContract;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GankPresenter implements GankContract.Presenter {

    private GankContract.View gankView;

    public GankPresenter(GankContract.View gankView) {
        this.gankView = gankView;
        gankView.setPresenter(this);
    }

    @Override
    public void getRandomHeaderImage(boolean isRandom) {
        Observable<DataBean> observable;
        if (isRandom) {
            observable = RetrofitManager.getInstance().getGankAPI().getRandomData("福利", 1);
        } else {
            observable = RetrofitManager.getInstance().getGankAPI().getData("福利", 1, 1);
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<DataBean>() {
                    @Override
                    protected void onData(DataBean dataBean) {
                        if (dataBean != null && dataBean.getResultList() != null && dataBean.getResultList().size() > 0) {
                            gankView.setHeaderImage(dataBean.getResultList().get(0).getUrl());
                        }
                    }
                });
    }
}