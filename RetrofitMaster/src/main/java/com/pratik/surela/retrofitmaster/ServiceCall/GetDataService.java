package com.pratik.surela.retrofitmaster.ServiceCall;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface GetDataService {

    @POST()
    Call<Object> CallSimplePostServiceCall(@Url String strUrl, @Body RequestBody params);

    @GET()
    Call<Object> CallSimpleGetServiceCall(@Url String strUrl);

    @POST()
    Call<Object> CallHeaderPostServiceCall(@Url String strUrl, @Header("Authorization") String strHeader, @Body RequestBody params);

    @FormUrlEncoded
    @POST()
    Call<Object> CallPostTokenServiceCall(@Url String strUrl, @FieldMap Map<String, String> requestMap);
}