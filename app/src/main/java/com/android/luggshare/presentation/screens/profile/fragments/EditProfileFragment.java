package com.android.luggshare.presentation.screens.profile.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.userprofile.UpdUserProfile;
import com.android.luggshare.business.models.userprofile.UpdUserProfileResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.EditUserProfileBundle;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.UiHelper;
import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_EMAIL;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_USERLNAME;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_USERNAME;

public class EditProfileFragment extends CoreFragment {

    @BindView(R.id.edtfname)
    EditText edtfname;
    @BindView(R.id.edtlname)
    EditText edtlname;
    @BindView(R.id.edtemail)
    EditText edtemail;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.edtcntry)
    EditText edtcntry;
    @BindView(R.id.edtcity)
    EditText edtcity;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.imgProfile)
    com.makeramen.roundedimageview.RoundedImageView imgProfile;

    private static final String TAG = ProfileFragment.class.getSimpleName();

    EditUserProfileBundle editUserProfileBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_user_profile_edit;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            editUserProfileBundle = (EditUserProfileBundle) getArguments().getSerializable(BundleKeys.EDIT_USERPROFILE_BUNDLE);
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        edtfname.setText(editUserProfileBundle.getFname().toString());
        edtlname.setText(editUserProfileBundle.getLname().toString());
        edtemail.setText(editUserProfileBundle.getEmail().toString());

        return rootview;
    }


    @OnClick(R.id.btnUpdate)
    public void onClick(View v) {
        updateUserProfile();
    }


    private void updateUserProfile() {
        UiHelper.getInstance().showLoadingIndicator(getActivity());

        String pass = edtPass.getText().toString();
        String country = edtcntry.getText().toString();
        String city = edtcity.getText().toString();

        if (pass.isEmpty()) {
            pass = null;
        }
        if (country.isEmpty()) {
            country = null;
        }
        if (city.isEmpty()) {
            city = null;
        }

        UpdUserProfile upduserprofRequest = new UpdUserProfile();
        upduserprofRequest.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        upduserprofRequest.setFname(edtfname.getText().toString());
        upduserprofRequest.setLname(edtlname.getText().toString());
        upduserprofRequest.setEmail(edtemail.getText().toString());
        upduserprofRequest.setPass(pass);
        upduserprofRequest.setCountry(country);
        upduserprofRequest.setCity(city);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UpdUserProfileResponse> call = apiService.updUserProfile(upduserprofRequest);
        call.enqueue(new Callback<UpdUserProfileResponse>() {


            @Override
            public void onResponse(Call<UpdUserProfileResponse> call, Response<UpdUserProfileResponse> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());

                if (response.body() != null) {
                    UiHelper.getInstance().hideLoadingIndicator();


                    if (response.body().getStatus().equals("1")) {

                        PreferenceManager.getInstance().put(KEY_USERNAME, edtfname.getText().toString());
                        PreferenceManager.getInstance().put(KEY_USERLNAME,edtlname.getText().toString());
                        PreferenceManager.getInstance().put(KEY_EMAIL, edtlname.getText().toString());

                        Toast.makeText(CustomApplication.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        navigateBackFragment();

                    } else {

                        Toast.makeText(CustomApplication.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }

                UiHelper.getInstance().hideLoadingIndicator();

            }

            @Override
            public void onFailure(Call<UpdUserProfileResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }


        });


    }
}
