package com.android.march.one.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DayBean {
    @SerializedName("error")
    private boolean error;
    @SerializedName("category")
    private List<String> categoryList;
    @SerializedName("results")
    private ResultBean resultBean;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }

    @Override
    public String toString() {
        return "DayBean{" +
                "error=" + error +
                ", categoryList=" + categoryList +
                ", resultBean=" + resultBean +
                '}';
    }

    public static class ResultBean {
        @SerializedName("Android")
        private List<ItemBean> androidList;
        @SerializedName("iOS")
        private List<ItemBean> iosList;
        @SerializedName("前端")
        private List<ItemBean> frontList;
        @SerializedName("休息视频")
        private List<ItemBean> restMovieList;
        @SerializedName("拓展资源")
        private List<ItemBean> resourceList;
        @SerializedName("瞎推荐")
        private List<ItemBean> recommendList;
        @SerializedName("福利")
        private List<ItemBean> welfareList;

        public List<ItemBean> getAndroidList() {
            return androidList;
        }

        public void setAndroidList(List<ItemBean> androidList) {
            this.androidList = androidList;
        }

        public List<ItemBean> getIosList() {
            return iosList;
        }

        public void setIosList(List<ItemBean> iosList) {
            this.iosList = iosList;
        }

        public List<ItemBean> getFrontList() {
            return frontList;
        }

        public void setFrontList(List<ItemBean> frontList) {
            this.frontList = frontList;
        }

        public List<ItemBean> getRestMovieList() {
            return restMovieList;
        }

        public void setRestMovieList(List<ItemBean> restMovieList) {
            this.restMovieList = restMovieList;
        }

        public List<ItemBean> getResourceList() {
            return resourceList;
        }

        public void setResourceList(List<ItemBean> resourceList) {
            this.resourceList = resourceList;
        }

        public List<ItemBean> getRecommendList() {
            return recommendList;
        }

        public void setRecommendList(List<ItemBean> recommendList) {
            this.recommendList = recommendList;
        }

        public List<ItemBean> getWelfareList() {
            return welfareList;
        }

        public void setWelfareList(List<ItemBean> welfareList) {
            this.welfareList = welfareList;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "androidList=" + androidList +
                    ", iosList=" + iosList +
                    ", frontList=" + frontList +
                    ", restMovieList=" + restMovieList +
                    ", resourceList=" + resourceList +
                    ", recommendList=" + recommendList +
                    ", welfareList=" + welfareList +
                    '}';
        }
    }
}