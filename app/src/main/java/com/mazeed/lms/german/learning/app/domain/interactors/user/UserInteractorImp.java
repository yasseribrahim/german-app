package com.mazeed.lms.german.learning.app.domain.interactors.user;

import android.view.View;

import com.mazeed.lms.german.learning.app.domain.controller.Controller;
import com.mazeed.lms.german.learning.app.domain.interactors.BaseInteractor;
import com.mazeed.lms.german.learning.app.domain.models.user.Authorization;
import com.mazeed.lms.german.learning.app.domain.models.user.User;
import com.mazeed.lms.german.learning.app.domain.utils.UserManager;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import retrofit2.HttpException;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public class UserInteractorImp extends BaseInteractor implements UserInteractor {
    private final Controller.UserController controller;
    private final UserCallbackStates callback;

    public UserInteractorImp(Controller.UserController controller, UserCallbackStates callback) {
        this.controller = controller;
        this.callback = callback;
    }

    @Override
    public void login(User user) {
        callback.showProgress();
        prepare(controller.login(user), new LoginCompleteObserver(callback, user));
    }

    @Override
    public void register(User user) {
        callback.showProgress();
        prepare(controller.register(user), new RegisterCompleteObserver(callback, user));
    }

    @Override
    public void logout() {
        callback.showProgress();
        prepare(Observable.just(Boolean.TRUE), new LogoutCompleteObserver(callback));
    }

    private final class LoginCompleteObserver extends BaseObserver<Authorization> {
        private User user;

        public LoginCompleteObserver(CallbackStates callback, User user) {
            super(callback);
            this.user = user;
        }

        @Override
        public void onNext(Authorization authorization) {
            UserManager.getInstance().prepareAndStoreCurrentUser(authorization);
            callback.onLoginComplete();
            super.onNext(authorization);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (e instanceof HttpException) {
                if (((HttpException) e).code() == HttpsURLConnection.HTTP_UNAUTHORIZED || ((HttpException) e).code() == HttpsURLConnection.HTTP_BAD_REQUEST) {
                    callback.unAuthorized();
                    return;
                }
            }
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login(user);
                }
            });
        }
    }

    private final class RegisterCompleteObserver extends BaseObserver<Authorization> {
        private User user;

        public RegisterCompleteObserver(CallbackStates callback, User user) {
            super(callback);
            this.user = user;
        }

        @Override
        public void onNext(Authorization authorization) {
            callback.onRegisterComplete();
            super.onNext(authorization);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            if (e instanceof HttpException) {
                if (((HttpException) e).code() == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                    callback.unAuthorized();
                    return;
                }
            }
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    register(user);
                }
            });
        }
    }

    private final class LogoutCompleteObserver extends BaseObserver<Boolean> {
        public LogoutCompleteObserver(CallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(Boolean result) {
            UserManager.getInstance().logout();
            callback.onLogoutComplete();
            super.onNext(result);
        }

        @Override
        public void onComplete() {
            super.onComplete();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            String message = callback.getErrorMessage(e);
            callback.failure(message != null ? message : e.getMessage(), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    logout();
                }
            });
        }
    }
}
