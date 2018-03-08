package com.android.march.one.model.bean;

public class MusicMoreItemBean {

    // 信息标题
    private String title;

    // 图片ID
    private int imageId;

    public MusicMoreItemBean() {
    }

    public MusicMoreItemBean(String title, int imageId) {
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "MusicMoreItemBean{" +
                "title='" + title + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
