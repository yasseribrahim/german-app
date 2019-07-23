package com.mazeed.lms.german.learning.app.presentation.presenters.user;

import com.mazeed.lms.german.learning.app.domain.models.user.User;
import com.mazeed.lms.german.learning.app.presentation.presenters.MainPresenter;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserPresenter extends MainPresenter {
    void login(User user);

    void register(User user);

    void logout();
}
