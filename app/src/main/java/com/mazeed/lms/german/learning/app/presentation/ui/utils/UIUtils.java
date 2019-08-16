package com.mazeed.lms.german.learning.app.presentation.ui.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.ImageView;

import com.mazeed.lms.german.learning.app.R;

public class UIUtils {
    public static void setColor(ImageView image, int color) {
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        image.setColorFilter(porterDuffColorFilter);
    }

    public static synchronized Integer getColor(Context context, int index) {

        switch (index % 24) {
            case 0:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_0, null);
            case 1:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_1, null);
            case 2:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_2, null);
            case 3:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_3, null);
            case 4:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_4, null);
            case 5:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_5, null);
            case 6:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_6, null);
            case 7:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_7, null);
            case 8:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_8, null);
            case 9:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_9, null);
            case 10:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_10, null);
            case 11:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_11, null);
            case 12:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_12, null);
            case 13:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_13, null);
            case 14:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_14, null);
            case 15:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_15, null);
            case 16:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_16, null);
            case 17:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_17, null);
            case 18:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_18, null);
            case 19:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_19, null);
            case 20:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_20, null);
            case 21:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_21, null);
            case 22:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_22, null);
            case 23:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_23, null);
            default:
                return ResourcesCompat.getColor(context.getResources(), R.color.background_0, null);
        }
    }
}
