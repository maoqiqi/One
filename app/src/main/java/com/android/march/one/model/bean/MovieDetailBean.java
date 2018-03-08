package com.android.march.one.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetailBean {
    @SerializedName("rating")
    private MovieListBean.RatingBean ratingBean;
    @SerializedName("reviews_count")
    private int reviewsCount;
    @SerializedName("wish_count")
    private int wishCount;
    @SerializedName("douban_site")
    private String douBanSite;
    @SerializedName("year")
    private String year;
    @SerializedName("images")
    private MovieListBean.ImageBean imageBean;
    @SerializedName("alt")
    private String alt;
    @SerializedName("id")
    private String id;
    @SerializedName("mobile_url")
    private String mobileUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("do_count")
    private int doCount;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("seasons_count")
    private int seasonsCount;
    @SerializedName("schedule_url")
    private String scheduleUrl;
    @SerializedName("episodes_count")
    private int episodesCount;
    @SerializedName("countries")
    private List<String> countryList;
    @SerializedName("genres")
    private List<String> genreList;
    @SerializedName("collect_count")
    private int collectCount;
    @SerializedName("casts")
    private List<MovieListBean.CastBean> castBeanList;
    @SerializedName("current_season")
    private String currentSeason;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("summary")
    private String summary;
    @SerializedName("subtype")
    private String subType;
    @SerializedName("directors")
    private List<MovieListBean.DirectorBean> directorBeanList;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("ratings_count")
    private int ratingsCount;
    @SerializedName("aka")
    private List<String> akaList;

    public MovieListBean.RatingBean getRatingBean() {
        return ratingBean;
    }

    public void setRatingBean(MovieListBean.RatingBean ratingBean) {
        this.ratingBean = ratingBean;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public int getWishCount() {
        return wishCount;
    }

    public void setWishCount(int wishCount) {
        this.wishCount = wishCount;
    }

    public String getDouBanSite() {
        return douBanSite;
    }

    public void setDouBanSite(String douBanSite) {
        this.douBanSite = douBanSite;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public MovieListBean.ImageBean getImageBean() {
        return imageBean;
    }

    public void setImageBean(MovieListBean.ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDoCount() {
        return doCount;
    }

    public void setDoCount(int doCount) {
        this.doCount = doCount;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getSeasonsCount() {
        return seasonsCount;
    }

    public void setSeasonsCount(int seasonsCount) {
        this.seasonsCount = seasonsCount;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    public void setScheduleUrl(String scheduleUrl) {
        this.scheduleUrl = scheduleUrl;
    }

    public int getEpisodesCount() {
        return episodesCount;
    }

    public void setEpisodesCount(int episodesCount) {
        this.episodesCount = episodesCount;
    }

    public List<String> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public List<MovieListBean.CastBean> getCastBeanList() {
        return castBeanList;
    }

    public void setCastBeanList(List<MovieListBean.CastBean> castBeanList) {
        this.castBeanList = castBeanList;
    }

    public String getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(String currentSeason) {
        this.currentSeason = currentSeason;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public List<MovieListBean.DirectorBean> getDirectorBeanList() {
        return directorBeanList;
    }

    public void setDirectorBeanList(List<MovieListBean.DirectorBean> directorBeanList) {
        this.directorBeanList = directorBeanList;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public List<String> getAkaList() {
        return akaList;
    }

    public void setAkaList(List<String> akaList) {
        this.akaList = akaList;
    }
}