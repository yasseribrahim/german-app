package com.mazeed.lms.german.learning.app.presentation.presenters.user;

import android.view.View;

import com.mazeed.lms.german.learning.app.GermanApplication;
import com.mazeed.lms.german.learning.app.domain.controller.Controller;
import com.mazeed.lms.german.learning.app.domain.interactors.user.UserInteractor;
import com.mazeed.lms.german.learning.app.domain.interactors.user.UserInteractorImp;
import com.mazeed.lms.german.learning.app.domain.models.user.AuthorizationBody;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.UserCallback;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class UserPresenterImp implements UserPresenter, UserInteractor.UserCallbackStates {
    @Inject
    protected Retrofit retrofit;

    private UserCallback view;
    private UserInteractor interactor;

    public UserPresenterImp(UserCallback view) {
        GermanApplication.getComponent().inject(this);
        this.view = view;
        this.interactor = new UserInteractorImp(retrofit.create(Controller.UserController.class), this);
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
    public String getErrorMessage(Throwable throwable) {
        if (view != null) {
            return view.onGetErrorMessage(throwable);
        }
        return null;
    }

    @Override
    public void login(AuthorizationBody body) {
        interactor.getToken(body);
    }

    @Override
    public void getUserInfo() {
        interactor.getUserInfo();
    }

    @Override
    public void logout() {
        interactor.logout();
    }

    @Override
    public void setDeviceToken(String token) {
        interactor.setDeviceToken(token);
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
    public void onGetUserInfoComplete() {
        if (view != null) {
            view.onGetUserInfoComplete();
        }
    }

    @Override
    public void onLogoutComplete() {
        if (view != null) {
            view.onLogoutComplete();
        }
    }

    @Override
    public void onSetDeviceTokenComplete() {
        if (view != null) {
            view.onSetDeviceTokenComplete();
        }
    }

    @Override
    public void onGetTokenComplete() {
        if (view != null) {
            view.onGetTokenComplete();
        }
    }

    @Override
    public void ontUserNotSupportCallback() {
        if (view != null) {
            view.ontUserNotSupportCallback();
        }
    }
}
