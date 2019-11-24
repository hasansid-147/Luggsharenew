package com.android.luggshare.presentation.screens.login.activities;


import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.android.luggshare.R;
import com.android.luggshare.business.models.loginservice.LoginRequest;
import com.android.luggshare.business.models.loginservice.LoginResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.managers.ApplicationStateManager;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.activities.CoreActivity;
import com.android.luggshare.presentation.screens.dashboard.activities.DashboardActivity;
import com.android.luggshare.presentation.screens.forgetpassword.ForgotPasswordActivity;
import com.android.luggshare.presentation.screens.signup.activities.SignUpActivity;
import com.android.luggshare.utils.LogUtils;
import com.android.luggshare.utils.UiHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_EMAIL;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_STATUS;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_USERLNAME;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_USERNAME;

public class LoginActivity extends CoreActivity implements View.OnClickListener {

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static RelativeLayout rel;
    private static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initViews();
        initListeners();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        emailid = (EditText) findViewById(R.id.login_emailid);
        password = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.loginBtn);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        signUp = (TextView) findViewById(R.id.createAccount);
        show_hide_password = (CheckBox) findViewById(R.id.show_hide_password);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);

    }


    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        signUp.setOnClickListener(this);


        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });


    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                // Loginservice();
                break;

            case R.id.forgot_password:

                navigateToIntent(ForgotPasswordActivity.class, false);

                break;
            case R.id.createAccount:

                navigateToIntent(SignUpActivity.class, false);

                break;
        }
    }


    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {

            rel = findViewById(R.id.relayout);
            Snackbar.make(rel, "Enter both credentials.", Snackbar.LENGTH_LONG).show();

        } else
            Loginservice();

    }


    private void Loginservice() {

        UiHelper.getInstance().hideKeyboard(LoginActivity.this);
        UiHelper.getInstance().showLoadingIndicator(LoginActivity.this);

        LoginRequest login = new LoginRequest();

        login.setEmail(emailid.getText().toString());
        login.setPassword(password.getText().toString());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiService.UserLogin(login);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LogUtils.LOGE(TAG, "issuccessfull: " + response.isSuccessful());
                LogUtils.LOGE(TAG, "Status" + response.toString());
                LogUtils.LOGE(TAG, "RESPONSE:" + response.body());
                rel = findViewById(R.id.relayout);

                UiHelper.getInstance().hideLoadingIndicator();

                if (response.body().getStatus() == 1) {

                    ApplicationStateManager.getInstance().setIsAuthenticated(true);
                    PreferenceManager.getInstance().put(KEY_USERNAME, response.body().getFname());
                    PreferenceManager.getInstance().put(KEY_USERLNAME,response.body().getLname());
                    PreferenceManager.getInstance().put(KEY_EMAIL, response.body().getEmail());
                    PreferenceManager.getInstance().put(KEY_STATUS, response.body().getStatus());
                    PreferenceManager.getInstance().put(KEY_CUSTOMER_ID, response.body().getUid());

                    navigateToIntent(DashboardActivity.class, true);

                } else if (response.body().getStatus() == -2) {
                    Snackbar.make(rel, "Invalid login", Snackbar.LENGTH_LONG).show();
                } else if (response.body().getStatus() == -1) {
                    Snackbar.make(rel, "Invalid login", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(rel, "Something went wrong", Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                LogUtils.LOGE(TAG, t.toString());
            }
        });


    }
}
