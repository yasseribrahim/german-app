package com.mazeed.lms.german.learning.app.domain.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mazeed.lms.german.learning.app.GermanApplication;
import com.mazeed.lms.german.learning.app.domain.models.video.Video;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataSet {
    public static List<Video> getData() {
        try {
            InputStream open = GermanApplication.getApplication().getAssets().open("sample.json");
            byte[] buf = new byte[open.available()];
            open.read(buf);

            return new Gson().fromJson(new String(buf, "UTF-8"), new TypeToken<List<Video>>() {
            }.getType());
        } catch (IOException ex) {
            try {
                throw ex;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {

        }
        return new ArrayList<>();
    }
}
