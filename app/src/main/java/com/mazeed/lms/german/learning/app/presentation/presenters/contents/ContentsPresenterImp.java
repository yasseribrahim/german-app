package com.mazeed.lms.german.learning.app.presentation.presenters.contents;

import android.view.View;

import com.mazeed.lms.german.learning.app.GermanApplication;
import com.mazeed.lms.german.learning.app.domain.controller.Controller;
import com.mazeed.lms.german.learning.app.domain.interactors.contents.ContentsInteractor;
import com.mazeed.lms.german.learning.app.domain.interactors.contents.ContentsInteractorImp;
import com.mazeed.lms.german.learning.app.domain.models.contents.Grade;
import com.mazeed.lms.german.learning.app.domain.models.contents.Lesson;
import com.mazeed.lms.german.learning.app.domain.models.contents.LessonDetails;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.ContentsCallback;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class ContentsPresenterImp implements ContentsPresenter, ContentsInteractor.ContentsCallbackStates {
    @Inject
    protected Retrofit retrofit;
    private ContentsCallback view;
    private ContentsInteractor interactor;

    public ContentsPresenterImp(ContentsCallback view) {
        GermanApplication.getComponent().inject(this);
        this.view = view;
        this.interactor = new ContentsInteractorImp(retrofit.create(Controller.ContentsController.class), this);
    }

    @Override
    public void getAllGrades() {
        interactor.getAllGrades();
    }

    @Override
    public void getLessons(int gradeId) {
        interactor.getLessons(gradeId);
    }

    @Override
    public void getLessonDetails(int lessonId) {
        interactor.getLessonDetails(lessonId);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
        interactor.onDestroy();
    }

    @Override
    public void failure(String message, View.OnClickListener onClickListener) {
        if (view != null) {
            view.onShowError(message, onClickListener);
        }
    }

    @Override
    public void showProgress() {
        if (view != null) {
            view.onShowProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (view != null) {
            view.onHideProgress();
        }
    }

    @Override
    public void unAuthorized() {
        if (view != null) {
            view.onUnAuthorized();
        }
    }

    @Override
    public String getErrorMessage(Throwable throwable) {
        if (view != null) {
            view.onGetErrorMessage(throwable);
        }
        return null;
    }

    @Override
    public void onGetGradesComplete(List<Grade> grades) {
        if (view != null) {
            view.onGetGradesComplete(grades);
        }
    }

    @Override
    public void onGetLessonsComplete(List<Lesson> lessons) {
        if (view != null) {
            view.onGetLessonsComplete(lessons);
        }
    }

    @Override
    public void onGetLessonDetailsComplete(LessonDetails details) {
        if (view != null) {
            view.onGetLessonDetailsComplete(details);
        }
    }
}
