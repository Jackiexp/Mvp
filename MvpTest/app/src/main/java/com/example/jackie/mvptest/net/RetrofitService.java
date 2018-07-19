package com.example.jackie.mvptest.net;

/**
 * Created by Jackie on 2018/6/13.
 */

import com.example.jackie.mvptest.entity.Meta;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {

    String BASE_URL = "https://odrcloud.net/";
//    String BASE_URL = "https://yundr.gov.cn/";//线上环境
//    String BASE_URL = "http://172.18.0.68:8083/";
//    String BASE_URL = "https://api.douban.com/v2/movie/";

//    @POST("mobileLogin/login")
//    Observable<String> getString(@FieldMap Map<String, String> map);

//    @FormUrlEncoded
//    @POST("mobileLogin/login")
//    Observable<String> login(@FieldMap Map<String, String> map);

    @POST("mobileLogin/login")
    Observable<Meta> login(@Body RequestBody body);

//    添加headers
//    @FormUrlEncoded
//    @POST("mobileLogin/login")
//    Observable<Meta> login(@HeaderMap Map<String, String> headers, @FieldMap Map<String, String> map);

    @POST("mobileInit/versionUpdate")
    Observable<Meta> update(@Body RequestBody body);

    @GET("top250")
    Observable<Object> getMovies(@Query("start") int start, @Query("count") int count);
}
