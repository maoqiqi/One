package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.ItemBean;

import java.util.List;

public interface GankListContract {

    interface Presenter extends BasePresenter {

        void getData(String type, int pageIndex, int pageCount);
    }

    interface View extends BaseView<Presenter> {

        void setData(List<ItemBean> list);
    }
}