package com.android.march.one.model.net;

import com.android.march.one.model.bean.NewsDetailBean;
import com.android.march.one.model.bean.NewsListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface NewsAPI {

    /**
     * 得到最新的新闻
     */
    @GET("stories/latest")
    Observable<NewsListBean> getLatestNews();

    /**
     * 得到之前的新闻
     *
     * @param date 时间
     */
    @GET("stories/before/{date}")
    Observable<NewsListBean> getBeforeNews(@Path("date") String date);

    /**
     * 得到新闻详情
     *
     * @param id 新闻id
     */
    @GET("story/{id}")
    Observable<NewsDetailBean> getNewsDetail(@Path("id") int id);
}