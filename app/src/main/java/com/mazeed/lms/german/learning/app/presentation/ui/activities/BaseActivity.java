package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.Language;
import com.mazeed.lms.german.learning.app.domain.models.user.User;
import com.mazeed.lms.german.learning.app.domain.utils.UserManager;
import com.mazeed.lms.german.learning.app.presentation.ui.dialogs.ProgressDialogFragment;
import com.mazeed.lms.german.learning.app.presentation.ui.helper.LocaleHelper;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

import retrofit2.HttpException;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocaleHelper.setLocale(this, getCurrentLanguage().getLocale().getLanguage());
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.onAttach(context, getCurrentLanguage().getLocale().getLanguage()));
    }

    protected Language getCurrentLanguage() {
        User user = UserManager.getInstance().getCurrentUser();
        if (user != null && user.getLanguage() != null) {
            return user.getLanguage();
        }

        return Language.getDefaultLanguage();
    }

    protected abstract View getSnackBarAnchorView();

    public Snackbar showRetrySnackBar(String message, View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.label_retry, clickListener);
        snackbar.show();
        return snackbar;
    }

    protected void setActionBarTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    protected void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    protected void setupSupportedActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    protected void setupSupportedActionBarWithHome(Toolbar toolbar) {
    }

    protected void hideKeyboard() {
        try {
            if (getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    protected void hideKeyboard(Dialog dialog) {
        try {
            if (dialog.getCurrentFocus() != null) {
                InputMethodManager imm = (InputMethodManager) this.getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(dialog.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception ignored) {
        }
    }

    protected void setupSupportedActionBarWithHome(Toolbar toolbar, int homeUpIndicatorId) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(homeUpIndicatorId);
    }

    public Snackbar showConnectionSnackBar(View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), R.string.message_error_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.label_retry, clickListener);
        snackbar.show();
        return snackbar;
    }

    public Snackbar showInfoSnackBar(@StringRes int message) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
        return snackbar;
    }

    public Snackbar showHoldErrorSnackBar(@StringRes int message) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), message, Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, getTheme()));
        snackbar.show();
        return snackbar;
    }

    public Snackbar showInfoSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(getSnackBarAnchorView(), message, Snackbar.LENGTH_LONG);
        snackbar.show();
        return snackbar;
    }

    public boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    public void onShowError(String message, View.OnClickListener onClickListener) {
        showRetrySnackBar(message, onClickListener);
    }

    public void onShowConnectionError(View.OnClickListener onClickListener) {
        showConnectionSnackBar(onClickListener);
    }

    public void onUnAuthorized() {
        UserManager.getInstance().logout();
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    public String onGetErrorMessage(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            if (exception.response().code() == HttpsURLConnection.HTTP_UNAUTHORIZED) {
                return getString(R.string.error_no_authorization);
            } else if (exception.response().code() == HttpsURLConnection.HTTP_BAD_REQUEST ||
                    exception.response().code() == HttpsURLConnection.HTTP_NOT_FOUND ||
                    exception.response().code() == HttpsURLConnection.HTTP_UNAVAILABLE ||
                    exception.response().code() == HttpsURLConnection.HTTP_INTERNAL_ERROR ||
                    exception.response().code() == HttpsURLConnection.HTTP_BAD_GATEWAY) {
                return getString(R.string.error_service_unavailable);
            } else {
                return getString(R.string.message_error_connection);
            }
        } else if (throwable instanceof SocketTimeoutException) {
            return getString(R.string.error_service_unavailable);
        } else if (throwable instanceof UnknownHostException) {
            return getString(R.string.message_error_connection);
        } else if (throwable instanceof ConnectException) {
            return getString(R.string.message_error_connection);
        } else if (throwable instanceof SSLHandshakeException) {
            return getString(R.string.message_error_connection);
        }
        return null;
    }

    public void onShowProgress() {
        ProgressDialogFragment.show(getSupportFragmentManager());
    }

    public void onHideProgress() {
        ProgressDialogFragment.hide(getSupportFragmentManager());
    }

    protected void hideKeyBoard() {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null)
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

