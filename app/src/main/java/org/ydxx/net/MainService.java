package org.ydxx.net;

import org.ydxx.entity.JxzyBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MainService {

    @FormUrlEncoded
    @POST("/get_data/")
    Observable<JxzyBean> getData(
            @Field("table_name") String tableName
    );

    @FormUrlEncoded
    @POST("/update_data/")
    Observable<Object> updateData(
            @Field("table_name") String tableName,
            @Field("data") String object
    );

    @FormUrlEncoded
    @POST("/del_data/")
    Observable<Object> delData(
            @Field("table_name") String tableName,
            @Field("data") String object
    );

    @FormUrlEncoded
    @POST("/add_data/")
//    @Headers("Content-Type:application/json;charset=UTF-8")
    Observable<Object> addData(
            @Field("table_name") String tableName,
            @Field("data") String object
    );
}
