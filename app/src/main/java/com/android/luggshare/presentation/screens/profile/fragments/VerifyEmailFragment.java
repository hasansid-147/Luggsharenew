package com.android.luggshare.presentation.screens.profile.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.luggshare.R;
import com.android.luggshare.business.models.userprofile.EmailVerification;
import com.android.luggshare.business.models.userprofile.EmailVerificationResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.EditUserProfileBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.UiHelper;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class VerifyEmailFragment extends CoreFragment {


   @BindView(R.id.edtemail)
   EditText edtemail;

   @BindView(R.id.btnVerify)
   Button btnVerify;


    EditUserProfileBundle verifyUserEmailBundle ;
    private static final String TAG = VerifyEmailFragment.class.getSimpleName();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_verify_email;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            verifyUserEmailBundle = (EditUserProfileBundle) getArguments().getSerializable(BundleKeys.VERIFY_USER_EMAIL);
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);
        edtemail.setText(verifyUserEmailBundle.getEmail());
        edtemail.setEnabled(false);

        return rootview;
    }


    @OnClick(R.id.btnVerify)
    public void onVerify() {
        verifyEmailAddress();
    }


    private void verifyEmailAddress(){
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
