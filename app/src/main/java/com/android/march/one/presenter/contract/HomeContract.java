package com.android.march.one.presenter.contract;

import com.android.march.one.base.BasePresenter;
import com.android.march.one.base.BaseView;
import com.android.march.one.model.bean.ItemBean;

import java.util.List;

public interface HomeContract {

    interface Presenter extends BasePresenter {

        void getDay(String year, String month, String day);
    }

    interface View extends BaseView<Presenter> {

        void setDay(List<List<ItemBean>> list);
    }
}