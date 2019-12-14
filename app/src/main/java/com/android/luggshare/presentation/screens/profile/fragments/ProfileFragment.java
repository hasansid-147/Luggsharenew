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

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.business.models.userprofile.UserProfileGet;
import com.android.luggshare.business.models.userprofile.UserProfileResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.EditUserProfileBundle;
import com.android.luggshare.common.bundle.GetUserProfileBundle;
import com.android.luggshare.common.bundle.ViewReviewsBundle;
import com.android.luggshare.common.bundle.ViewUserPhoneBundle;
import com.android.luggshare.common.constants.IsPreferenceProfile;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.ApplicationStateManager;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;
import com.android.luggshare.utils.UiHelper;

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

    @BindView(R.id.btnReviews)
    Button btnReviews;

    @BindView(R.id.btnedit)
    ImageView btnedit;

    @BindView(R.id.txtIsEmail)
    RelativeLayout txtIsEmail;

    @BindView(R.id.txtIsSocial)
    RelativeLayout txtIsSocial;

    GetUserProfileBundle getUserProfileBundle;


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
            getUserProfileBundle = (GetUserProfileBundle) getArguments().getSerializable(BundleKeys.GET_USER_PROFILE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        getUserProfile();

        IsPreferenceProfile isprefuser = IsPreferenceProfile.getInstance();
        boolean isPrefUser = isprefuser.getData();
        if(isPrefUser == false){


            btnedit.setVisibility(View.INVISIBLE);
            btnedit.setEnabled(false);

            btnLogout.setVisibility(View.INVISIBLE);
            btnLogout.setEnabled(false);

            txtIsEmail.setClickable(false);

            txtIsSocial.setClickable(false);

        }

        return rootview;
    }


    @OnClick(R.id.btnLogout)
    public void onLoginClicked() {

        /*PreferenceManager.getInstance().clear();

        ApplicationStateManager.getInstance().setIsAuthenticated(false);

        // After logout redirect user to Loing Activity
        Intent i = new Intent(CustomApplication.getContext(), LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        CustomApplication.getContext().startActivity(i);*/



    }


    @OnClick(R.id.btnedit)
    public void onEdit() {


        EditUserProfileBundle editUserProfileBundle = new EditUserProfileBundle();
        editUserProfileBundle.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        editUserProfileBundle.setFname(rspFname);
        editUserProfileBundle.setLname(rspLname);
        editUserProfileBundle.setEmail(rspEmail);

        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.EDIT_USERPROFILE_BUNDLE, editUserProfileBundle);

        replaceChildFragmentWithDelay(new EditProfileFragment(), true, false, bundle, true);
    }


    @OnClick(R.id.txtIsMobile)
    public void onClickIsMobile(){
        if(imgTickMobile.getVisibility() == View.VISIBLE){


            ViewUserPhoneBundle viewUserPhoneBundle = new ViewUserPhoneBundle();
            viewUserPhoneBundle.setPhone(rspPhone.toString());


            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.VIEW_USER_PHONE, viewUserPhoneBundle);

            replaceChildFragmentWithDelay(new ViewPhoneFragment(), true, false, bundle, true);
        }

    }


    @OnClick(R.id.txtIsEmail)
    public void onClickIsEmail(){
        if(imgCancelEmail.getVisibility() == View.VISIBLE){

            int uid = getUserProfileBundle.getUid();
            EditUserProfileBundle verifyUserEmailBundle = new EditUserProfileBundle();
            verifyUserEmailBundle.setUid(uid);
            verifyUserEmailBundle.setEmail(rspEmail);

            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.VERIFY_USER_EMAIL, verifyUserEmailBundle);

            replaceChildFragmentWithDelay(new VerifyEmailFragment(), true, false, bundle, true);
        }

    }

    @OnClick(R.id.txtIsSocial)
    public void onClickIsSocial(){
        if(imgCancelsocial.getVisibility() == View.VISIBLE){



            replaceChildFragmentWithDelay(new SocialConnectFragment(), true, false, null, true);
        }

    }

    @OnClick(R.id.btnReviews)
    public void onClickReviews(){

            int uid = getUserProfileBundle.getUid();
            ViewReviewsBundle viewReviewsBundle = new ViewReviewsBundle();
            viewReviewsBundle.setUid(uid);


            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.VIEW_USER_REVIEWS, viewReviewsBundle);

            replaceChildFragmentWithDelay(new UserReviewsFragment(), true, false, bundle, true);


    }

    private void getUserProfile(){
        UiHelper.getInstance().showLoadingIndicator(getActivity());

        int uid = getUserProfileBundle.getUid();
        //String email = getUserProfileBundle.getEmail().toString();

        UserProfileGet userProfRequest = new UserProfileGet();
        userProfRequest.setUid(uid);
        //userProfRequest.setEmail(email);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UserProfileResponse> call = apiService.getUserProfile(userProfRequest);
        call.enqueue(new Callback<UserProfileResponse>() {


            @Override
            public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {

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