package com.mazeed.lms.german.learning.app.presentation.presenters.callbacks;

/**
 * Created by Yasser.Ibrahim on 6/12/2018.
 */

public interface UserCallback extends BaseCallback {
    default void onGetTokenComplete() {
    }

    default void onGetUserInfoComplete() {
    }

    default void onLogoutComplete() {
    }

    default void onSetDeviceTokenComplete() {
    }

    default void ontUserNotSupportCallback() {
    }
}
