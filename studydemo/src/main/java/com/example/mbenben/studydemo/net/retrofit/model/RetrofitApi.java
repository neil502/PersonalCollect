package com.example.mbenben.studydemo.net.retrofit.model;


import android.graphics.Bitmap;

import com.example.mbenben.studydemo.net.retrofit.model.bean.RetrofitBean;
import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

import retrofit2.http.GET;

/**
 * Created by MBENBEN on 2017/1/5.
 */
public interface RetrofitApi {
    String URL = "http://www.ohonor.xyz/";

    @GET("retrofit")
    Observable<List<RetrofitBean>> getRetrofitDatas();
    @POST("retrofitPost")
    @FormUrlEncoded
    Call<String> postRetrofit(@Field("username") String suername, @Field("password") String password);

    @GET("retrofit")
    Observable<List<RetrofitBean>> getRetrofitDatasByRxJava();

    @Multipart
    @POST("fileUpload")
    Observable<String> postImgAndString(@Part("name") RequestBody name,
                                        @Part("content")RequestBody content,
                                        @Part MultipartBody.Part img);

}
