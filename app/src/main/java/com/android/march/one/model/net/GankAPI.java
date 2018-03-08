package com.android.march.one.model.net;

import com.android.march.one.model.bean.DataBean;
import com.android.march.one.model.bean.DayBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GankAPI {

    // 每日数据:http://gank.io/api/day/年/月/日
    // eg:http://gank.io/api/day/2015/08/06
    @GET("day/{year}/{month}/{day}")
    Observable<DayBean> getDay(@Path("year") String year, @Path("month") String month, @Path("day") String day);

    // 分类数据:http://gank.io/api/data/数据类型/请求个数/第几页
    // 数据类型:福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
    // 请求个数:数字,大于0
    // 第几页:数字,大于0
    // eg:http://gank.io/api/data/Android/10/1
    @GET("data/{type}/{pageCount}/{pageIndex}")
    Observable<DataBean> getData(@Path("type") String type, @Path("pageIndex") int pageIndex, @Path("pageCount") int pageCount);

    // 随机数据：http://gank.io/api/random/data/分类/个数
    @GET("random/data/{type}/{number}")
    Observable<DataBean> getRandomData(@Path("type") String type, @Path("number") int number);

    // 搜索 API
    @GET("search/query/{key}/category/{type}/count/{pageCount}/page/{pageIndex}")
    Observable<DataBean> search(@Path("key") String key, @Path("type") String type, @Path("pageIndex") int pageIndex, @Path("pageCount") int pageCount);
}