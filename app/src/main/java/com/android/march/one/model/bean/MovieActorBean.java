package com.android.march.one.model.bean;

public class MovieActorBean {
    private String alt;
    private MovieListBean.AvatarBean avatarBean;
    private String name;
    private String id;
    private String typeName;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public MovieListBean.AvatarBean getAvatarBean() {
        return avatarBean;
    }

    public void setAvatarBean(MovieListBean.AvatarBean avatarBean) {
        this.avatarBean = avatarBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}