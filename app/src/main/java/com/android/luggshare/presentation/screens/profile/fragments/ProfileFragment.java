package com.android.luggshare.presentation.screens.profile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptRequest;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptResponse;
import com.android.luggshare.business.models.userprofile.UpdUserProfile;
import com.android.luggshare.business.models.userprofile.UserProfileGet;
import com.android.luggshare.business.models.userprofile.UserProfileResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.ApplicationStateManager;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.cards.fragments.AddCardFragment;
import com.android.luggshare.presentation.screens.dashboard.activities.DashboardActivity;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;
import com.android.luggshare.presentation.screens.sender.fragments.SenderRequestSizeFragment;
import com.android.luggshare.utils.UiHelper;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.AppConstants.BASE_IMG_PATH;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_EMAIL;


public class ProfileFragment extends CoreFragment {

    @BindView(R.id.imgTickMobile)
    ImageView imgTickMobile;
    @BindView(R.id.imgCancelMobile)
    ImageView imgCancelMobile;


    @BindView(R.id.imgTickProfile)
    ImageView imgTickProfile;
    @BindView(R.id.imgCancelProfile)
    ImageView imgCancelProfile;

    @BindView(R.id.imgTickEmail)
    ImageView imgTickEmail;
    @BindView(R.id.imgCancelEmail)
    ImageView imgCancelEmail;

    @BindView(R.id.imgTickpayment)
    ImageView imgTickpayment;
    @BindView(R.id.imgCancelpayment)
    ImageView imgCancelpayment;

    @BindView(R.id.imgTicksocial)
    ImageView imgTicksocial;
    @BindView(R.id.imgCancelsocial)
    ImageView imgCancelsocial;

    @BindView(R.id.imgTickidentity)
    ImageView imgTickidentity;
    @BindView(R.id.imgCancelidentity)
    ImageView imgCancelidentity;

    @BindView(R.id.tvUsername)
    TextView tvUsername;
    @BindView(R.id.tvRating)
    TextView tvRating;
    @BindView(R.id.imgProfile)
    com.makeramen.roundedimageview.RoundedImageView imgProfile;

    @BindView(R.id.btnLogout)
    Button btnLogout;

    @BindView(R.id.btnedit)
    ImageView btnedit;

    @BindView(R.id.txtIsEmail)
    RelativeLayout txtIsEmail;


    String rspUid,rspFname,rspLname,rspEmail,rspImage,rspImapth,rspPhone,rspDesc;
    public Integer rspIslogin=0,rspIsphone=0,rspIsemail=0,rspIsimage=0,rspIssocial=0,rspIsCC=0,rspIscnic=0,rating=0,rspreturn=0;


    private static final String TAG = ProfileFragment.class.getSimpleName();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_user_profile;
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

        getUserProfile();

        return rootview;
    }


    @OnClick(R.id.btnLogout)
    public void onLoginClicked() {

        PreferenceManager.getInstance().clear();

        ApplicationStateManager.getInstance().setIsAuthenticated(false);

        // After logout redirect user to Loing Activity
        Intent i = new Intent(CustomApplication.getContext(), LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        CustomApplication.getContext().startActivity(i);



    }


    @OnClick(R.id.btnedit)
    public void onEdit() {


        replaceChildFragmentWithDelay(new ProfileFragmentedit(), true, false, null, true);

        // After logout redirect user to Loing Activity
       // Intent i = new Intent(CustomApplication.getContext(), ProfileFragmentedit.class);

        // Staring Login Activity
      //  CustomApplication.getContext().startActivity(i);



    }


    @OnClick(R.id.txtIsEmail)
    public void onclicktxtIsEmail(){

        if(imgCancelEmail.getVisibility() == View.VISIBLE){
            replaceChildFragmentWithDelay(new VerifyEmail(), true, false, null, true);
        }



    }

    private void getUserProfile(){
        UiHelper.getInstance().showLoadingIndicator(getActivity());


        UserProfileGet userprofRequest = new UserProfileGet();
        userprofRequest.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        userprofRequest.setEmail(PreferenceManager.getInstance().getString(KEY_EMAIL));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UserProfileResponse> call = apiService.getUserProfile(userprofRequest);
        call.enqueue(new Callback<UserProfileResponse>() {


            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());

                if (response.body() != null) {

                    try {

                        rspUid = response.body().getUid().toString();
                        rspFname = response.body().getFname().toString();
                        rspLname = response.body().getLname().toString();
                        rspEmail = response.body().getEmail().toString();
                        rspImage = response.body().getImage().toString();
                        rspImapth = response.body().getImagePth().toString();
                        rspPhone = response.body().getPhone().toString();
                        rspDesc = response.body().getDesc().toString();

                        rspIslogin = response.body().getIslogin();
                        rspIsphone = response.body().getIsphone();
                        rspIsemail = response.body().getIsemail();
                        rspIsimage = response.body().getIsimage();
                        rspIssocial = response.body().getIssocial();
                        rspIsCC = response.body().getIscc();
                        rspIscnic = response.body().getIscnic();
                        rating = response.body().getRating();
                        rspreturn = response.body().getResponse();

                        tvUsername.setText(rspFname.toString() + " " + rspLname.toString());
                        tvRating.setText(rating.toString());
                        //Picasso.with(UserProfile_view.this).load(BASE_IMG_PATH + rspImage ).placeholder(R.drawable.icon_profile).into(imgProfile);
                        UiHelper.setImageWithGlide(getActivity(), imgProfile, BASE_IMG_PATH + rspImage);
                        //UIHelper.ge.setImageWithGlide(mContext, imageView, venueGalleryList.get(position).getUrl());
                        if (rspIsphone == 1) {
                            imgCancelMobile.setVisibility(View.INVISIBLE);
                            imgTickMobile.setVisibility(View.VISIBLE);
                        }

                        if (rspIsimage == 1) {
                            imgCancelProfile.setVisibility(View.INVISIBLE);
                            imgTickProfile.setVisibility(View.VISIBLE);

                        }
                        //**change it to rspIsemail after email validation work
                        if (rspIsemail == 1) {
                            imgCancelEmail.setVisibility(View.INVISIBLE);
                            imgTickEmail.setVisibility(View.VISIBLE);
                        }
                        if (rspIsCC == 1) {
                            imgCancelpayment.setVisibility(View.INVISIBLE);
                            imgTickpayment.setVisibility(View.VISIBLE);
                        }
                        if (rspIssocial == 1) {
                            imgCancelsocial.setVisibility(View.INVISIBLE);
                            imgTicksocial.setVisibility(View.VISIBLE);
                        }
                    /*if(rsp == 1){     for identityverification
                        imgCancelMobile.setVisibility(View.INVISIBLE);
                        imgTickMobile.setVisibility(View.VISIBLE);
                    }*/

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                UiHelper.getInstance().hideLoadingIndicator();

            }

            @Override
            public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }




        });







    }

}