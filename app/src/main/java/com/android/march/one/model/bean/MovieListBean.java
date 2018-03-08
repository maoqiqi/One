package com.android.march.one.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListBean {
    @SerializedName("count")
    private int count;
    @SerializedName("start")
    private int start;
    @SerializedName("total")
    private int total;
    @SerializedName("title")
    private String title;
    @SerializedName("subjects")
    private List<MovieBean> movieBeanList;

    public class MovieBean {
        @SerializedName("rating")
        private RatingBean ratingBean;
        @SerializedName("genres")
        private List<String> genreList;
        @SerializedName("title")
        private String title;
        @SerializedName("casts")
        private List<CastBean> castBeanList;
        @SerializedName("collect_count")
        private int collectCount;
        @SerializedName("original_title")
        private String originalTitle;
        @SerializedName("subtype")
        private String subType;
        @SerializedName("directors")
        private List<DirectorBean> directorBeanList;
        @SerializedName("year")
        private String year;
        @SerializedName("images")
        private ImageBean imageBean;
        @SerializedName("alt")
        private String alt;
        @SerializedName("id")
        private String id;

        public RatingBean getRatingBean() {
            return ratingBean;
        }

        public void setRatingBean(RatingBean ratingBean) {
            this.ratingBean = ratingBean;
        }

        public List<String> getGenreList() {
            return genreList;
        }

        public void setGenreList(List<String> genreList) {
            this.genreList = genreList;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<CastBean> getCastBeanList() {
            return castBeanList;
        }

        public void setCastBeanList(List<CastBean> castBeanList) {
            this.castBeanList = castBeanList;
        }

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public String getSubType() {
            return subType;
        }

        public void setSubType(String subType) {
            this.subType = subType;
        }

        public List<DirectorBean> getDirectorBeanList() {
            return directorBeanList;
        }

        public void setDirectorBeanList(List<DirectorBean> directorBeanList) {
            this.directorBeanList = directorBeanList;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImageBean getImageBean() {
            return imageBean;
        }

        public void setImageBean(ImageBean imageBean) {
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
    }

    public class RatingBean {
        @SerializedName("max")
        private int max;
        @SerializedName("average")
        private Float average;
        @SerializedName("stars")
        private String stars;
        @SerializedName("min")
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public Float getAverage() {
            return average;
        }

        public void setAverage(Float average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public class AvatarBean {
        @SerializedName("small")
        private String small;
        @SerializedName("large")
        private String large;
        @SerializedName("medium")
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public class CastBean {
        @SerializedName("alt")
        private String alt;
        @SerializedName("avatars")
        private AvatarBean avatarBean;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarBean getAvatarBean() {
            return avatarBean;
        }

        public void setAvatarBean(AvatarBean avatarBean) {
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
    }

    public class DirectorBean {
        @SerializedName("alt")
        private String alt;
        @SerializedName("avatars")
        private AvatarBean avatarBean;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarBean getAvatarBean() {
            return avatarBean;
        }

        public void setAvatarBean(AvatarBean avatarBean) {
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
    }

    public class ImageBean {
        @SerializedName("small")
        private String small;
        @SerializedName("large")
        private String large;
        @SerializedName("medium")
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieBean> getMovieBeanList() {
        return movieBeanList;
    }

    public void setMovieBeanList(List<MovieBean> movieBeanList) {
        this.movieBeanList = movieBeanList;
    }
}