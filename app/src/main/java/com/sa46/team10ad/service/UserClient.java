package com.sa46.team10ad.service;

import com.sa46.team10ad.model.AccessToken;
import com.sa46.team10ad.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserClient {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("token")
    Call<AccessToken> getAccessToken(@Field("username")String username,
                                     @Field("password") String password,
                                     @Field("grant_type")String grantType);

    @GET("user")
    Call<User> getSecret(@Header("Authorization") String authToken);
}
