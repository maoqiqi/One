package com.android.march.one.model.net;

import com.android.march.one.model.bean.MovieDetailBean;
import com.android.march.one.model.bean.MovieListBean;
import com.android.march.one.model.bean.MovieTypePersonBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieAPI {

    /**
     * 正在热映
     */
    // https://ticket-api-m.mtime.cn/Showtime/LocationMovies.api?locationId=561
    @GET("Showtime/LocationMovies.api")
    Observable<MovieListBean> inTheatersMovies(@Query("locationId") String locationId);

    /**
     * 即将上映
     */
    @GET("movie/coming_soon")
    Observable<MovieListBean> comingSoonMovies(@Query("start") int start, @Query("count") int count);

    /**
     * 获取电影详情
     */
    // https://api-m.mtime.cn/movie/detail.api?&locationId=290&movieId=215121
    @GET("movie/detail.api")
    Observable<MovieDetailBean> getMovieDetail(@Query("locationId") String locationId, @Query("movieId") String movieId);

    // https://api-m.mtime.cn/Movie/MovieCreditsWithTypes.api?&movieId=215121
    @GET("Movie/MovieCreditsWithTypes.api")
    Observable<MovieTypePersonBean> getMovieDetailPersonList(@Query("movieId") String movieId);

    // https://api-m.mtime.cn/Showtime/HotMovieComments.api?&movieId=215121&pageIndex=1

    // https://api-m.mtime.cn/Movie/MovieComingNew.api?locationId=290

    // https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=561

    // https://api-m.mtime.cn/OnlineLocationCinema/OnlineCinemasByCity.api?locationId=290

    /**
     * 影人条目信息
     */
    @GET("movie/celebrity/{id}")
    Observable<String> getCelebrity(@Path("id") String id);

    /**
     * 搜索电影
     */
    @GET("movie/search")
    Observable<MovieListBean> searchMovie(@Query("q") String q, @Query("tag") String tag, @Query("start") int start, @Query("count") int count);
}