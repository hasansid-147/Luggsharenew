package com.android.luggshare.presentation.screens.profile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.userprofile.EmailVerification;
import com.android.luggshare.business.models.userprofile.EmailVerificationResponse;
import com.android.luggshare.business.models.userprofile.UpdUserProfile;
import com.android.luggshare.business.models.userprofile.UpdUserProfileResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.managers.ApplicationStateManager;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;
import com.android.luggshare.utils.UiHelper;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class VerifyEmail extends CoreFragment {


   @BindView(R.id.edtemail)
   EditText edtemail;

   @BindView(R.id.btnVerify)
   Button btnVerify;

    private static final String TAG = VerifyEmail.class.getSimpleName();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_verify_email;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);
        edtemail.setEnabled(false);


        return rootview;
    }


    @OnClick(R.id.btnVerify)
    public void onVerify() {

        VerifyEmailAddress();


    }


    private void VerifyEmailAddress(){
        UiHelper.getInstance().showLoadingIndicator(getActivity());

        String email = edtemail.getText().toString();


        EmailVerification emailverify = new EmailVerification();
        emailverify.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        emailverify.setEmail(email);




        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<EmailVerificationResponse> call = apiService.verifyEmailaddress(emailverify);
        call.enqueue(new Callback<EmailVerificationResponse>() {


            @Override
            public void onResponse(Call<EmailVerificationResponse> call, Response<EmailVerificationResponse> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());

                if (response.body() != null) {
                    UiHelper.getInstance().hideLoadingIndicator();








                }

                UiHelper.getInstance().hideLoadingIndicator();

            }

            @Override
            public void onFailure(Call<EmailVerificationResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }




        });







    }
}
