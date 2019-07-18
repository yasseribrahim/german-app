package com.mazeed.lms.german.learning.app;

import com.mazeed.lms.german.learning.app.domain.modules.ApiModule;
import com.mazeed.lms.german.learning.app.domain.modules.ApplicationModule;
import com.mazeed.lms.german.learning.app.domain.modules.PreferencesModule;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenterImp;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Yasser.Ibrahim on 6/28/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, PreferencesModule.class})
public interface ApplicationComponent {
    void inject(UserPresenterImp presenter);
}