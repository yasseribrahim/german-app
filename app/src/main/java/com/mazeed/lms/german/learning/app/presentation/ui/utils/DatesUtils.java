package com.mazeed.lms.german.learning.app.presentation.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by interactive on 7/30/18.
 */

public class DatesUtils {
    public static final String SERVER_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm a";//"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat serverDatetimeFormatter;
    private static final SimpleDateFormat dateFormatter;

    static {
        serverDatetimeFormatter = new SimpleDateFormat(SERVER_DATE_TIME_FORMAT);
        dateFormatter = new SimpleDateFormat(DATE_FORMAT);
    }

    public static String formatDateOnly(Date date) {
        return dateFormatter.format(date);
    }

    public static Date parseDate(String date) throws Exception {
        return serverDatetimeFormatter.parse(date);
    }
}
