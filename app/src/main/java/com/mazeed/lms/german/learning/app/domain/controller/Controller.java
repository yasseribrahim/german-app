package com.mazeed.lms.german.learning.app.domain.controller;

import com.mazeed.lms.german.learning.app.BuildConfig;
import com.mazeed.lms.german.learning.app.domain.models.user.Authorization;
import com.mazeed.lms.german.learning.app.domain.models.user.User;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by interactive on 7/23/18.
 */

public interface Controller {
    interface UserController {
        @FormUrlEncoded
        @POST(BuildConfig.BASE_URL + "Token")
        Observable<Authorization> getToken(@Field("grant_type") String grantType,
                                           @Field("username") String username,
                                           @Field("password") String password);

        @GET(BuildConfig.BASE_URL + "api/account/me")
        Observable<User> getUserInfo(@Header("Authorization") String authorization);

        @POST(BuildConfig.BASE_URL + "api/account/setToken")
        Observable<ResponseBody> setDeviceToken(@Header("Authorization") String authorization, @Query("token") String token);

        @GET(BuildConfig.BASE_URL + "api/account/DeleteToken")
        Observable<ResponseBody> deleteToken(@Header("Authorization") String authorization);
    }
}
