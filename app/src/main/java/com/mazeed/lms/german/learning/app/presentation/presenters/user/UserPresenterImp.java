package com.mazeed.lms.german.learning.app.presentation.presenters.user;

import android.view.View;

import com.mazeed.lms.german.learning.app.GermanApplication;
import com.mazeed.lms.german.learning.app.domain.controller.Controller;
import com.mazeed.lms.german.learning.app.domain.interactors.user.UserInteractor;
import com.mazeed.lms.german.learning.app.domain.interactors.user.UserInteractorImp;
import com.mazeed.lms.german.learning.app.domain.models.user.User;
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
    public void login(User user) {
        interactor.login(user);
    }

    @Override
    public void register(User user) {
        interactor.register(user);
    }

    @Override
    public void logout() {
        interactor.logout();
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
    public void onRegisterComplete() {
        if (view != null) {
            view.onRegisterComplete();
        }
    }

    @Override
    public void onLogoutComplete() {
        if (view != null) {
            view.onLogoutComplete();
        }
    }

    @Override
    public void onLoginComplete() {
        if (view != null) {
            view.onLoginComplete();
        }
    }
}
