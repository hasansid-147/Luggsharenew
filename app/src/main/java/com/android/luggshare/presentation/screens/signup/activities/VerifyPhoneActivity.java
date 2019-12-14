package com.android.luggshare.presentation.screens.signup.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.android.luggshare.R;
import com.android.luggshare.business.models.registrationservice.SignUpResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.keys.AppConstants;
import com.android.luggshare.common.managers.ApplicationStateManager;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.activities.CoreActivity;
import com.android.luggshare.presentation.screens.dashboard.activities.DashboardActivity;
import com.android.luggshare.utils.UiHelper;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_EMAIL;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_STATUS;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_USERNAME;

public class VerifyPhoneActivity extends CoreActivity {

    //These are the objects needed
    //It is the verification id that will be sent to the user
    private String mVerificationId;

    //The edittext to input the code
    private EditText editTextCode;

    //firebase auth object
    private FirebaseAuth mAuth;

    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private static final String TAG = "SignUp";

    String fname, lname, emailAddress, password, phone;
    String imageFileName = "", extension = "";
    private static RelativeLayout rel;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_verify_phone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Git initialize check
		// Git change 2

        FirebaseApp.initializeApp(VerifyPhoneActivity.this);
        //initializing objects
        //mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);

        rel = findViewById(R.id.relayout);

        Intent intent = getIntent();
        phone = intent.getStringExtra(AppConstants.KEY_PHONE);
        fname = intent.getStringExtra(AppConstants.KEY_FIRST_NAME);
        lname = intent.getStringExtra(AppConstants.KEY_LAST_NAME);
        emailAddress = intent.getStringExtra(AppConstants.KEY_EMAIL);
        password = intent.getStringExtra(AppConstants.KEY_PASSWORD);
        imageFileName = intent.getStringExtra(AppConstants.KEY_USER_IMAGE);
        extension = intent.getStringExtra(AppConstants.KEY_USER_IMAGE_EXTENSION);

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                //Log.d(TAG, "onVerificationCompleted:" + credential);
                //signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

                Snackbar.make(findViewById(android.R.id.content), Log.getStackTraceString(e), Snackbar.LENGTH_SHORT).show();

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    //edtPhone.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };

        startPhoneNumberVerification(phone);


        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }

                //verifying the code entered manually
                //verifyVerificationCode(code);
                UiHelper.getInstance().hideKeyboard(VerifyPhoneActivity.this);
                UiHelper.getInstance().showLoadingIndicator(VerifyPhoneActivity.this);
                verifyPhoneNumberWithCode(mVerificationId, code);
            }
        });


        findViewById(R.id.buttonresendotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendVerificationCode(phone,mResendToken);
            }
        });

    }

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+92" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            signUpService();

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Something is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Snackbar snackbar = Snackbar.make(findViewById(R.id.relayout), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();
                        }
                    }
                });
    }

    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }



    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        try {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
            signInWithPhoneAuthCredential(credential);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private void signUpService() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        RequestBody requestFname = RequestBody.create(MediaType.parse("multipart/form-data"), fname);
        RequestBody requestLname = RequestBody.create(MediaType.parse("multipart/form-data"), lname);
        RequestBody requestEmailAddress = RequestBody.create(MediaType.parse("multipart/form-data"), emailAddress);
        RequestBody requestPassword = RequestBody.create(MediaType.parse("multipart/form-data"), password);
        RequestBody requestPhone = RequestBody.create(MediaType.parse("multipart/form-data"), phone);
        RequestBody requestIsValidPhone = RequestBody.create(MediaType.parse("multipart/form-data"), "true");

        File file = new File(imageFileName);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part userImage = MultipartBody.Part.createFormData("img", file.getName(), requestFile);

        /*RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/" + extension + ""), file);
        MultipartBody.Part userImage = MultipartBody.Part.createFormData("files", file.getName(), requestBody);*/


        Call<SignUpResponse> call = apiService.userRegistration(
                requestFname,
                requestLname,
                requestEmailAddress,
                requestPassword,
                requestPhone,
                requestIsValidPhone,
                userImage
        );
        call.enqueue(new Callback<SignUpResponse>() {

            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                Log.d(TAG, "Response_status: " + response.isSuccessful());
                Log.d(TAG, "ListResponse" + response.body());
                //rel = findViewById(R.id.relayout);

                UiHelper.getInstance().hideLoadingIndicator();

                if (response.body().getStatus() == 1) {

                    ApplicationStateManager.getInstance().setIsAuthenticated(true);
                    PreferenceManager.getInstance().put(KEY_USERNAME, response.body().getFname());
                    PreferenceManager.getInstance().put(KEY_EMAIL, response.body().getEmail());
                    PreferenceManager.getInstance().put(KEY_STATUS, response.body().getStatus());
                    PreferenceManager.getInstance().put(KEY_CUSTOMER_ID, response.body().getUid());

                    navigateToIntent(DashboardActivity.class, true);

                } else if (response.body().getStatus() == -2) {
                    Snackbar.make(rel, "Invalid login", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(rel, "Something went wrong.", Snackbar.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                UiHelper.getInstance().hideLoadingIndicator();
                // Log error here since request failed
                Toast.makeText(getApplicationContext(), "Network failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
