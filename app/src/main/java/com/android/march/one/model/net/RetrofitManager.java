package com.android.march.one.model.net;

import com.android.march.one.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {

    private final String BASE_GANK_URL = "https://gank.io/api/";
    private final String BASE_NEWS_URL = "http://news-at.zhihu.com/api/4/";
    private final String BASE_BOOK_URL = "https://api.douban.com/v2/";
    private final String BASE_MUSIC_URL = "";
    private final String BASE_MOVIE_URL = "https://api-m.mtime.cn";

    private static RetrofitManager instance;

    private OkHttpClient okHttpClient;

    private GankAPI gankAPI;
    private NewsAPI newsAPI;
    private BookAPI bookAPI;
    private MusicAPI musicAPI;
    private MovieAPI movieAPI;

    private int DEFAULT_TIMEOUT = 15;

    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    private void initOKHttp() {
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            // 添加日志
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(interceptor);
            }

            // 设置超时
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            // 错误重连
            builder.retryOnConnectionFailure(true);
            okHttpClient = builder.build();
        }
    }

    private <T> T getAPI(Class<T> t, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(t);
    }

    public GankAPI getGankAPI() {
        initOKHttp();
        if (gankAPI == null) gankAPI = getAPI(GankAPI.class, BASE_GANK_URL);
        return gankAPI;
    }

    public NewsAPI getNewsAPI() {
        initOKHttp();
        if (newsAPI == null) newsAPI = getAPI(NewsAPI.class, BASE_NEWS_URL);
        return newsAPI;
    }

    public BookAPI getBookAPI() {
        initOKHttp();
        if (bookAPI == null) bookAPI = getAPI(BookAPI.class, BASE_BOOK_URL);
        return bookAPI;
    }

    public MusicAPI getMusicAPI() {
        initOKHttp();
        if (musicAPI == null) musicAPI = getAPI(MusicAPI.class, BASE_MUSIC_URL);
        return musicAPI;
    }

    public MovieAPI getMovieAPI() {
        initOKHttp();
        if (movieAPI == null) movieAPI = getAPI(MovieAPI.class, BASE_MOVIE_URL);
        return movieAPI;
    }
}