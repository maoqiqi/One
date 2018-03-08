package com.android.march.one.presenter;

import com.android.march.one.Constants;
import com.android.march.one.MyObserver;
import com.android.march.one.model.bean.DayBean;
import com.android.march.one.model.bean.ItemBean;
import com.android.march.one.model.net.RetrofitManager;
import com.android.march.one.presenter.contract.HomeContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View homeView;

    public HomePresenter(HomeContract.View homeView) {
        this.homeView = homeView;
        homeView.setPresenter(this);
    }

    @Override
    public void getDay(String year, String month, String day) {
        RetrofitManager.getInstance().getGankAPI().getDay(year, month, day)
                .map(dayBean -> {
                    List<List<ItemBean>> list = new ArrayList<>();

                    DayBean.ResultBean resultBean = dayBean.getResultBean();
                    setRandomImageUrl(list, resultBean.getAndroidList());
                    setRandomImageUrl(list, resultBean.getIosList());
                    setRandomImageUrl(list, resultBean.getFrontList());
                    setRandomImageUrl(list, resultBean.getRestMovieList());
                    setRandomImageUrl(list, resultBean.getResourceList());
                    setRandomImageUrl(list, resultBean.getRecommendList());
                    setImageUrl(list, resultBean.getWelfareList());

                    return list;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<List<List<ItemBean>>>() {
                    @Override
                    protected void onData(List<List<ItemBean>> lists) {
                        homeView.setDay(lists);
                    }
                });
    }

    // 随机设置图地址
    private void setRandomImageUrl(List<List<ItemBean>> list, List<ItemBean> itemBeanList) {
        if (itemBeanList != null && itemBeanList.size() > 0) {
            int size = itemBeanList.size();
            if (size % 3 == 0) {
                for (int i = 0; i < size; i++) {
                    itemBeanList.get(i).setImageUrl(Constants.THREE_URLS[getRandom(3)]);
                }
            } else if (size % 3 == 1) {
                int num = size / 3;
                if (num != 0) {
                    for (int i = 0; i < num * 3; i++) {
                        itemBeanList.get(i).setImageUrl(Constants.THREE_URLS[getRandom(3)]);
                    }
                }
                itemBeanList.get(num * 3).setImageUrl(Constants.ONE_URLS[getRandom(1)]);
            } else if (size % 3 == 2) {
                int num = size / 3;
                if (num != 0) {
                    for (int i = 0; i < num * 3; i++) {
                        itemBeanList.get(i).setImageUrl(Constants.THREE_URLS[getRandom(3)]);
                    }
                }
                itemBeanList.get(num * 3).setImageUrl(Constants.TWO_URLS[getRandom(2)]);
                itemBeanList.get(num * 3 + 1).setImageUrl(Constants.TWO_URLS[getRandom(2)]);
            }
            list.add(itemBeanList);
        }
    }

    // 设置图片地址
    private void setImageUrl(List<List<ItemBean>> list, List<ItemBean> itemBeanList) {
        if (itemBeanList != null && itemBeanList.size() > 0) {
            for (int i = 0; i < itemBeanList.size(); i++) {
                itemBeanList.get(i).setImageUrl(itemBeanList.get(i).getUrl());
            }
            list.add(itemBeanList);
        }
    }

    // 取不同的随机数
    private int getRandom(int type) {
        int len;
        if (type == 1) {
            len = Constants.ONE_URLS.length;
        } else if (type == 2) {
            len = Constants.TWO_URLS.length;
        } else {
            len = Constants.THREE_URLS.length;
        }
        return new Random().nextInt(len);
    }
}