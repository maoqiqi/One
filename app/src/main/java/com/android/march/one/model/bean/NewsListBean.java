package com.android.march.one.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsListBean {
    @SerializedName("date")
    private String date;
    @SerializedName("stories")
    private List<NewsBean> newsBeans;
    @SerializedName("top_stories")
    private List<TopNewsBean> topNewsBeans;

    public class NewsBean {
        @SerializedName("id")
        private int id;
        @SerializedName("type")
        private int type;
        @SerializedName("ga_prefix")
        private String ga_prefix;
        @SerializedName("title")
        private String title;
        @SerializedName("images")
        private List<String> images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public class TopNewsBean {
        @SerializedName("id")
        private int id;
        @SerializedName("type")
        private int type;
        @SerializedName("ga_prefix")
        private String ga_prefix;
        @SerializedName("title")
        private String title;
        @SerializedName("image")
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<NewsBean> getNewsBeans() {
        return newsBeans;
    }

    public void setNewsBeans(List<NewsBean> newsBeans) {
        this.newsBeans = newsBeans;
    }

    public List<TopNewsBean> getTopNewsBeans() {
        return topNewsBeans;
    }

    public void setTopNewsBeans(List<TopNewsBean> topNewsBeans) {
        this.topNewsBeans = topNewsBeans;
    }
}