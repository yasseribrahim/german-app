package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.user.User;
import com.mazeed.lms.german.learning.app.domain.utils.UserManager;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.UserCallback;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenter;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements UserCallback {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.containerView)
    LinearLayout containerView;
    @BindView(R.id.input_email)
    EditText emailText;
    @BindView(R.id.input_password)
    EditText passwordText;
    @BindView(R.id.btn_login)
    Button loginButton;
    @BindView(R.id.link_signup)
    TextView signupLink;

    private UserPresenter presenter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new UserPresenterImp(this);
    }

    @OnClick(R.id.link_signup)
    public void onSigUpClicked() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        login();
    }

    @Override
    protected View getSnackBarAnchorView() {
        return containerView;
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        User user = new User(email, password);
        UserManager.getInstance().initializeCurrentUser(email, password);
        presenter.login(user);

        hideKeyboard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onLoginComplete() {
        onLoginSuccess();
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        showInfoSnackBar(R.string.str_login_success);
        loginButton.setEnabled(true);
        startActivity(new Intent(this, HomeActivity.class));
        finishAffinity();
    }

    public void onLoginFailed() {
        showInfoSnackBar(R.string.str_login_fail);
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty()) {
            emailText.setError("enter a valid user name");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
