package com.mazeed.lms.german.learning.app.presentation.ui.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mazeed.lms.german.learning.app.GermanApplication;

/**
 * Created by Yasser.Ibrahim on 6/28/2018.
 */

public class Utils {

    public static boolean isConnectingToInternet() {
        if (GermanApplication.getApplication().getApplicationContext() != null) {

            ConnectivityManager manager = (ConnectivityManager) GermanApplication.getApplication().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
