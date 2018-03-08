package com.android.march.one.model.net;

import com.android.march.one.model.bean.MovieDetailBean;
import com.android.march.one.model.bean.MovieListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieAPI {

    /**
     * 正在热映
     */
    @GET("movie/in_theaters")
    Observable<MovieListBean> inTheatersMovies(@Query("city") String city, @Query("start") int start, @Query("count") int count);

    /**
     * 即将上映
     */
    @GET("movie/coming_soon")
    Observable<MovieListBean> comingSoonMovies(@Query("start") int start, @Query("count") int count);

    /**
     * 电影条目信息
     *
     * @param id
     */
    @GET("movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") String id);

    /**
     * 影人条目信息
     *
     * @param id
     */
    @GET("movie/celebrity/{id}")
    Observable<String> getCelebrity(@Path("id") String id);

    /**
     * 搜索电影
     */
    @GET("movie/search")
    Observable<MovieListBean> searchMovie(@Query("q") String q, @Query("tag") String tag, @Query("start") int start, @Query("count") int count);
}