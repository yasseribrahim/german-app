package com.mazeed.lms.german.learning.app.presentation.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mazeed.lms.german.learning.app.R;

public class CustomBadge extends FrameLayout {
    private TextView badge;

    public CustomBadge(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextView getBadge() {
        if (badge == null) {
            badge = findViewById(R.id.badge);
        }
        return badge;
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void setCounter(int counter) {
        getBadge().setText((counter >= 100) ? "99+" : (counter + ""));
        setVisibility(counter > 0 ? VISIBLE : GONE);
    }
}
