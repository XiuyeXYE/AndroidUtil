package com.xy.network;

import com.xy.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ScanGunCodeInterface {

    @FormUrlEncoded
    @POST("index.php?s=api/check/seat_cancel")
    Observable<BaseBean> ticket(@FieldMap Map<String, Object> map);

}
