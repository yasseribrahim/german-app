package com.mazeed.lms.german.learning.app.domain.interactors.user;

import com.mazeed.lms.german.learning.app.domain.interactors.MainInteractor;
import com.mazeed.lms.german.learning.app.domain.models.user.AuthorizationBody;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserInteractor extends MainInteractor {
    void getToken(AuthorizationBody body);

    void getUserInfo();

    void logout();

    void setDeviceToken(String token);

    interface UserCallbackStates extends CallbackStates {
        void onGetTokenComplete();

        void onGetUserInfoComplete();

        void ontUserNotSupportCallback();

        void onLogoutComplete();

        void onSetDeviceTokenComplete();
    }
}
