package com.mazeed.lms.german.learning.app.domain.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mazeed.lms.german.learning.app.GermanApplication;
import com.mazeed.lms.german.learning.app.domain.models.Language;
import com.mazeed.lms.german.learning.app.domain.models.user.Authorization;
import com.mazeed.lms.german.learning.app.domain.models.user.User;

/**
 * Created by interactive on 7/23/18.
 */

public class UserManager {
    public static final String KEY_USER_LOGGED_OBJECT = "USER_LOGGED";
    private static final UserManager MANAGER = new UserManager();
    private final SharedPreferences PREFERENCES;
    private volatile User user;

    private UserManager() {
        PREFERENCES = PreferenceManager.getDefaultSharedPreferences(GermanApplication.getApplication());
        buildUser();
    }

    public static UserManager getInstance() {
        return MANAGER;
    }

    /**
     * Returns singleton class instance
     */
    public User getCurrentUser() {
        if (user == null) {
            synchronized (User.class) {
                if (user == null) {
                    buildUser();
                }
            }
        }
        return user;
    }

    private void buildUser() {
        if (PREFERENCES != null) {
            try {
                String json = PREFERENCES.getString(KEY_USER_LOGGED_OBJECT, null);
                user = new Gson().fromJson(json, new TypeToken<User>() {
                }.getType());
            } catch (Exception ex) {
                ex.printStackTrace();
                user = null;
            }
        }
    }

    public User initializeCurrentUser(String username, String password) {
        User olderUser = getCurrentUser();
        user = new User(username, password);
        user.setLanguage((olderUser != null) ? olderUser.getLanguage() : Language.getDefaultLanguage());
        saveUser(user);
        return user;
    }

    public void saveUser(User user) {
        SharedPreferences.Editor editor = PREFERENCES.edit();
        editor.putString(KEY_USER_LOGGED_OBJECT, new Gson().toJson(user));
        editor.apply();
        buildUser();
    }

    public void prepareAndStoreCurrentUser(Authorization authorization) {
        user.setAuthorization(authorization.getAuthorizationDetails());
        saveUser(user);
    }

    public void prepareAndStoreCurrentUser(User user) {
        user.setPassword(getCurrentUser().getPassword());
        user.setAuthorization(getCurrentUser().getAuthorization());
        user.setLanguage(getCurrentUser().getLanguage());
        saveUser(user);
    }

    public void logout() {
        user.setAuthorization(null);
        saveUser(user);
        Preferences.clear();
        user = null;
    }

    public boolean isExistUserLoggedIn() {
        return (getCurrentUser() != null) && user.isLogged();
    }

    public boolean isDifferentLanguage(Language language) {
        return user != null && user.getLanguage() != null && !user.getLanguage().equals(language);
    }

    public void changeLanguage(Language language) {
        user = getCurrentUser();
        if (user != null) {
            user.setLanguage(language);
            saveUser(user);
        }
    }
}
