package com.android.luggshare.presentation.screens.forgetpassword;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.presentation.activities.CoreActivity;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;


public class ForgotPasswordActivity extends CoreActivity implements View.OnClickListener{

    private static EditText emailId;
    private static TextView submit, back;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_forgot_pass;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
        setListeners();
    }
    // Initialize the views
    private void initViews() {
        emailId = (EditText) findViewById(R.id.registered_emailid);
        submit = (TextView) findViewById(R.id.forgot_button);
        back = (TextView) findViewById(R.id.backToLoginBtn);

    }

    private void setListeners() {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToLoginBtn:
                navigateToIntent(LoginActivity.class, true);
                break;

            case R.id.forgot_button:

                // Call Submit button task
                //submitButtonTask();
                break;

        }
    }



}
