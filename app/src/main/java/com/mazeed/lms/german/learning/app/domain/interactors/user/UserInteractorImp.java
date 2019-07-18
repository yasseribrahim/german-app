package com.mazeed.lms.german.learning.app.domain.interactors.user;

import android.view.View;

import com.mazeed.lms.german.learning.app.domain.controller.Controller;
import com.mazeed.lms.german.learning.app.domain.interactors.BaseInteractor;
import com.mazeed.lms.german.learning.app.domain.models.user.Authorization;
import com.mazeed.lms.german.learning.app.domain.models.user.AuthorizationBody;
import com.mazeed.lms.german.learning.app.domain.models.user.User;
import com.mazeed.lms.german.learning.app.domain.utils.UserManager;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
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
    public void getToken(AuthorizationBody body) {
        callback.showProgress();
        prepare(controller.getToken(body.getGrantType(), body.getUsername(), body.getPassword()),
                new GetTokenObserver(callback, body));
    }

    @Override
    public void getUserInfo() {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.getUserInfo(user.getAuthorization().getToken()), new GetUserInfoCompleteObserver(callback));
    }

    @Override
    public void logout() {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.deleteToken(user.getAuthorization().getToken()), new LogoutObserver(callback));
    }

    @Override
    public void setDeviceToken(final String token) {
        callback.showProgress();
        User user = UserManager.getInstance().getCurrentUser();
        prepare(controller.setDeviceToken(user.getAuthorization().getToken(), token), new SetDeviceTokenCompleteObserver(callback, token, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDeviceToken(token);
            }
        }));
    }

    private final class GetTokenObserver extends BaseObserver<Authorization> {
        private AuthorizationBody body;

        public GetTokenObserver(CallbackStates callback, AuthorizationBody body) {
            super(callback);
            this.body = body;
        }

        @Override
        public void onNext(Authorization authorization) {
            UserManager.getInstance().prepareAndStoreCurrentUser(authorization);
            callback.onGetTokenComplete();
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
                    getToken(body);
                }
            });
        }
    }

    private final class GetUserInfoCompleteObserver extends BaseObserver<User> {

        public GetUserInfoCompleteObserver(CallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(User user) {
            UserManager.getInstance().prepareAndStoreCurrentUser(user);
            if(user.isSupportedUser()) {
                callback.onGetUserInfoComplete();
            } else {
                callback.ontUserNotSupportCallback();
                callback.hideProgress();
            }
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
                    getUserInfo();
                }
            });
        }
    }

    private final class LogoutObserver extends BaseObserver<ResponseBody> {
        public LogoutObserver(CallbackStates callback) {
            super(callback);
        }

        @Override
        public void onNext(ResponseBody result) {
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

    private final class SetDeviceTokenCompleteObserver extends BaseObserver<ResponseBody> {
        private View.OnClickListener listener;
        private String token;

        public SetDeviceTokenCompleteObserver(CallbackStates callback, String token, View.OnClickListener listener) {
            super(callback);
            this.listener = listener;
            this.token = token;
        }

        @Override
        public void onNext(ResponseBody response) {
            UserManager.getInstance().prepareAndStoreCurrentUser(token);
            callback.onSetDeviceTokenComplete();
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
            callback.failure(message != null ? message : e.getMessage(), listener);
        }
    }
}
