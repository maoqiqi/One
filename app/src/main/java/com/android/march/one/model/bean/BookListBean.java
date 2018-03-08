package com.android.march.one.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookListBean {
    @SerializedName("count")
    private int count;
    @SerializedName("start")
    private int start;
    @SerializedName("total")
    private int total;
    @SerializedName("books")
    private List<BookBean> bookBeanList;

    public class BookBean {
        @SerializedName("rating")
        private RatingBean ratingBean;
        @SerializedName("subtitle")
        private String subTitle;
        @SerializedName("author")
        private List<String> authorList;
        @SerializedName("pubdate")
        private String pubDate;
        @SerializedName("tags")
        private List<TagBean> tagBeanList;
        @SerializedName("origin_title")
        private String originTitle;
        @SerializedName("image")
        private String image;
        @SerializedName("binding")
        private String binding;
        @SerializedName("translator")
        private List<String> translatorList;
        @SerializedName("catalog")
        private String catalog;
        @SerializedName("pages")
        private String pages;
        @SerializedName("images")
        private ImageBean imageBean;
        @SerializedName("alt")
        private String alt;
        @SerializedName("id")
        private String id;
        @SerializedName("publisher")
        private String publisher;
        @SerializedName("isbn10")
        private String isbn10;
        @SerializedName("isbn13")
        private String isbn13;
        @SerializedName("title")
        private String title;
        @SerializedName("url")
        private String url;
        @SerializedName("alt_title")
        private String altTitle;
        @SerializedName("author_intro")
        private String authorintro;
        @SerializedName("summary")
        private String summary;
        @SerializedName("price")
        private String price;

        public RatingBean getRatingBean() {
            return ratingBean;
        }

        public void setRatingBean(RatingBean ratingBean) {
            this.ratingBean = ratingBean;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public List<String> getAuthorList() {
            return authorList;
        }

        public void setAuthorList(List<String> authorList) {
            this.authorList = authorList;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public List<TagBean> getTagBeanList() {
            return tagBeanList;
        }

        public void setTagBeanList(List<TagBean> tagBeanList) {
            this.tagBeanList = tagBeanList;
        }

        public String getOriginTitle() {
            return originTitle;
        }

        public void setOriginTitle(String originTitle) {
            this.originTitle = originTitle;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBinding() {
            return binding;
        }

        public void setBinding(String binding) {
            this.binding = binding;
        }

        public List<String> getTranslatorList() {
            return translatorList;
        }

        public void setTranslatorList(List<String> translatorList) {
            this.translatorList = translatorList;
        }

        public String getCatalog() {
            return catalog;
        }

        public void setCatalog(String catalog) {
            this.catalog = catalog;
        }

        public String getPages() {
            return pages;
        }

        public void setPages(String pages) {
            this.pages = pages;
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

        public String getPublisher() {
            return publisher;
        }

        public void setPublisher(String publisher) {
            this.publisher = publisher;
        }

        public String getIsbn10() {
            return isbn10;
        }

        public void setIsbn10(String isbn10) {
            this.isbn10 = isbn10;
        }

        public String getIsbn13() {
            return isbn13;
        }

        public void setIsbn13(String isbn13) {
            this.isbn13 = isbn13;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAltTitle() {
            return altTitle;
        }

        public void setAltTitle(String altTitle) {
            this.altTitle = altTitle;
        }

        public String getAuthorintro() {
            return authorintro;
        }

        public void setAuthorintro(String authorintro) {
            this.authorintro = authorintro;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

    public class RatingBean {
        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public class TagBean {
        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class ImageBean {
        private String small;
        private String large;
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

    public List<BookBean> getBookBeanList() {
        return bookBeanList;
    }

    public void setBookBeanList(List<BookBean> bookBeanList) {
        this.bookBeanList = bookBeanList;
    }
}