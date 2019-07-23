package com.mazeed.lms.german.learning.app.domain.interactors.user;

import com.mazeed.lms.german.learning.app.domain.interactors.MainInteractor;
import com.mazeed.lms.german.learning.app.domain.models.user.User;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserInteractor extends MainInteractor {
    void login(User user);

    void register(User user);

    void logout();

    interface UserCallbackStates extends CallbackStates {
        void onLoginComplete();

        void onRegisterComplete();

        void onLogoutComplete();
    }
}
