package com.mazeed.lms.german.learning.app.domain.controller;

import com.mazeed.lms.german.learning.app.BuildConfig;
import com.mazeed.lms.german.learning.app.domain.models.contents.Contents;
import com.mazeed.lms.german.learning.app.domain.models.contents.Grades;
import com.mazeed.lms.german.learning.app.domain.models.contents.LessonDetails;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lessons;
import com.mazeed.lms.german.learning.app.domain.models.user.Authorization;
import com.mazeed.lms.german.learning.app.domain.models.user.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by interactive on 7/23/18.
 */

public interface Controller {
    interface UserController {
        @POST(BuildConfig.BASE_URL + "Account/Login")
        Observable<Authorization> login(@Body User user);

        @POST(BuildConfig.BASE_URL + "Account/Register")
        Observable<Authorization> register(@Body User user);
    }

    interface ContentsController {
        @GET(BuildConfig.BASE_URL + "Grade/GetAll")
        Observable<Grades> getAllGrades(@Header("Authorization") String authorization);

        @GET(BuildConfig.BASE_URL + "Lesson/GetAllByGradeId")
        Observable<Lessons> getLessonsByGradeId(@Header("Authorization") String authorization, @Query("GradeId") int gradeId);

        @GET(BuildConfig.BASE_URL + "Lesson/GetById")
        Observable<LessonDetails> getLessonDetails(@Header("Authorization") String authorization, @Query("Id") int id);
    }
}
