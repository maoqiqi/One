package com.android.march.one.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataBean {
    @SerializedName("error")
    private boolean error;
    @SerializedName("results")
    private List<ItemBean> resultList;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ItemBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ItemBean> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "error=" + error +
                ", resultList=" + resultList +
                '}';
    }
}