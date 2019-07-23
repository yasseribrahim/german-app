package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.utils.Preferences;
import com.mazeed.lms.german.learning.app.domain.utils.UserManager;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.UserCallback;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenter;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements UserCallback {

    private static final int SPLASH_DELAY_MILLIS = 2000;

    @BindView(R.id.containerView)
    RelativeLayout containerView;

    private UserPresenter presenter;
    private Handler handler;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (UserManager.getInstance().isExistUserLoggedIn()) {
                presenter.login(UserManager.getInstance().getCurrentUser());
            } else {
                openLogin();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        presenter = new UserPresenterImp(this);
        handler = new Handler();

        gotoApp();
    }

    private void gotoApp() {
        if (isGooglePlayServicesAvailable(this)) {
            handler.postDelayed(runnable, SPLASH_DELAY_MILLIS);
        } else {
            showRetrySnackBar(getString(R.string.message_general_error), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoApp();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return containerView;
    }

    @Override
    public void onUnAuthorized() {
        UserManager.getInstance().logout();
        openLogin();
    }

    @Override
    public void onHideProgress() {
    }

    @Override
    public void onShowProgress() {
    }

    @Override
    public void onLoginComplete() {
        openHome();
    }

    private void openHome() {
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }

    private void openLogin() {
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
