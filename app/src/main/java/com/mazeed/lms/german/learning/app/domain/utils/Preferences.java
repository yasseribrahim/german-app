package com.mazeed.lms.german.learning.app.domain.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mazeed.lms.german.learning.app.GermanApplication;

import java.util.List;

/**
 * Created by Yasser.Ibrahim on 6/28/2018.
 */

public class Preferences {
    public static final String KEY_DEVICE_TOKEN = "DEVICE_TOKEN";
    public static final String KEY_DIRECTION_PATH = "DIRECTION_PATH";
    private static final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(GermanApplication.getApplication());

    public static boolean clear() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public static synchronized void storeDeviceToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_DEVICE_TOKEN, token);
        editor.apply();
    }

    public static synchronized String getDeviceToken() {
        return preferences.getString(KEY_DEVICE_TOKEN, null);
    }

    public static synchronized void storeDirectionPath(List<LatLng> points) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_DIRECTION_PATH, new Gson().toJson(points));
        editor.apply();
    }

    public static synchronized List<LatLng> getDirectionPath() {
        String points = preferences.getString(KEY_DIRECTION_PATH, null);
        try {
            return new Gson().fromJson(points, new TypeToken<List<LatLng>>() {
            }.getType());
        } catch (Exception ex) {
        }
        return null;
    }
}
