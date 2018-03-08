package com.android.march.one.model.net;

import com.android.march.one.model.bean.BookListBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface BookAPI {

    /**
     * 搜索图书(https://api.douban.com/v2/book/search)
     *
     * @param q     查询关键字
     * @param tag   查询的tag
     * @param start 取结果的offset 默认为0
     * @param count 取结果的条数 默认为20，最大为100
     */
    @GET("book/search")
    Observable<BookListBean> getBook(@Query("q") String q, @Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    /**
     * 获取图书信息(https://api.douban.com/v2/book/:id)
     *
     * @param id
     */
    @GET("book/{id}")
    Observable<BookListBean.BookBean> getBookDetail(@Path("id") String id);
}