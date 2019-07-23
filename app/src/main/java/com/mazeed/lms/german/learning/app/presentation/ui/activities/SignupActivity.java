package com.mazeed.lms.german.learning.app.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mazeed.lms.german.learning.app.R;
import com.mazeed.lms.german.learning.app.domain.models.user.User;
import com.mazeed.lms.german.learning.app.presentation.presenters.callbacks.UserCallback;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenter;
import com.mazeed.lms.german.learning.app.presentation.presenters.user.UserPresenterImp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements UserCallback {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.containerView)
    LinearLayout containerView;
    @BindView(R.id.input_name)
    EditText nameText;
    @BindView(R.id.input_address)
    EditText addressText;
    @BindView(R.id.input_email)
    EditText emailText;
    @BindView(R.id.input_mobile)
    EditText mobileText;
    @BindView(R.id.input_password)
    EditText passwordText;
    @BindView(R.id.input_reEnterPassword)
    EditText reEnterPasswordText;

    private UserPresenter presenter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        presenter = new UserPresenterImp(this);
    }

    @Override
    protected View getSnackBarAnchorView() {
        return containerView;
    }

    @OnClick(R.id.link_login)
    public void onLoginClicked() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void onRegisterComplete() {
        onSignupSuccess();
    }

    @OnClick(R.id.btn_signup)
    public void onSignUpClicked() {
        signup();
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        String name = nameText.getText().toString();
        String address = addressText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = mobileText.getText().toString();
        String password = passwordText.getText().toString();

        User user = new User(email, password);
        user.setEmail(email);
        user.setAddress(address);
        user.setFirstname(name);
        user.setMobile(mobile);
        presenter.register(user);

        hideKeyboard();
    }

    public void onSignupSuccess() {
        showInfoSnackBar(R.string.str_register_success);
        setResult(RESULT_OK, null);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void onSignupFailed() {
        showInfoSnackBar(R.string.str_register_fail);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String address = addressText.getText().toString();
        String email = emailText.getText().toString();
        String mobile = mobileText.getText().toString();
        String password = passwordText.getText().toString();
        String reEnterPassword = reEnterPasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (address.isEmpty()) {
            addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            addressText.setError(null);
        }


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (mobile.isEmpty()) {
            mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            mobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            reEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            reEnterPasswordText.setError(null);
        }

        return valid;
    }
}