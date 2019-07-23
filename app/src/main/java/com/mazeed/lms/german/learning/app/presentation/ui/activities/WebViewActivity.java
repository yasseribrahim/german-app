package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.presentation.ui.fragments.WebViewFragment;
import com.mazeed.lms.german.learning.app.presentation.ui.utils.Constants;

import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {
    private String TAG = "WebViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        String url = getIntent().getExtras().getString(Constants.KEY_URL, "");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_container, WebViewFragment.newInstance(url), TAG).commit();
    }

    @Override
    protected View getSnackBarAnchorView() {
        return null;
    }
}
