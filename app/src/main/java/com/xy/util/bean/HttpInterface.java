package com.xy.util.bean;

import com.xy.bean.BaseBean;
import com.xy.bean.IPInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface HttpInterface {
    @FormUrlEncoded
    @POST("index.php?s=api/check/seat_cancel")
    Observable<BaseBean> ticket(@FieldMap Map<String, Object> map);


    @GET("api/index")
    Observable<IPInfo> lookupIP(@QueryMap Map<String, Object> params);
}
