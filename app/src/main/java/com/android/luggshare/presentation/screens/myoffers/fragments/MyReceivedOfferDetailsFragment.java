package com.android.luggshare.presentation.screens.myoffers.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.business.models.UpdateOfferStatus.UpdateOfferStatusResponse;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptRequest;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptResponse;
import com.android.luggshare.business.models.userreviews.AddReviews;
import com.android.luggshare.business.models.userreviews.AddReviewsReponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.ClaimBundle;
import com.android.luggshare.common.bundle.GetUserProfileBundle;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.bundle.TrackingBundle;
import com.android.luggshare.common.constants.IsPreferenceProfile;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.Claim.ClaimFragment;
import com.android.luggshare.presentation.screens.cards.fragments.AddCardFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.profile.fragments.ProfileFragment;
import com.android.luggshare.presentation.screens.tracking.fragments.fragment.TrackDeliveryFragment;
import com.android.luggshare.utils.UiHelper;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class MyReceivedOfferDetailsFragment extends CoreFragment {

    @BindView(R.id.tvUserName)
    TextView tvUserName;

    @BindView(R.id.tvRating)
    TextView tvRating;

    @BindView(R.id.tvTravelingFrom)
    TextView tvTravelingFrom;

    @BindView(R.id.tvTravelingTo)
    TextView tvTravelingTo;

    @BindView(R.id.tvArrivalDate)
    TextView tvArrivalDate;

    @BindView(R.id.tvOffer)
    TextView tvOffer;

    @BindView(R.id.tvOfferstatus)
    TextView tvOfferstatus;

    @BindView(R.id.btnAcceptOffer)
    Button btnAcceptOffer;

    @BindView(R.id.btnCancelOffer)
    Button btnCancelOffer;

    @BindView(R.id.btnRate)
    Button btnRate;

    @BindView(R.id.rlUsername)
    RelativeLayout rlUsername;

    GetUserProfileBundle getUserProfileBundle;
    ReceivedOfferBundle receivedOfferBundle;
    ClaimBundle claimBundle;

    String TAG_RATE = "UserRatingNew";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_offer_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            receivedOfferBundle = (ReceivedOfferBundle) getArguments().getSerializable(BundleKeys.RECEIVED_OFFER_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        initDetails(receivedOfferBundle);

        if(receivedOfferBundle.getRequestObj().getOfferStatus().equalsIgnoreCase("COMPLETED")){

            btnRate.setVisibility(View.VISIBLE);
            btnRate.setEnabled(true);

        }

        if(receivedOfferBundle.getRequestObj().getOfferStatus().equalsIgnoreCase("COMPLETED")){

            btnCancelOffer.setVisibility(View.INVISIBLE);
            btnRate.setEnabled(false);

        }
        else {
            btnRate.setVisibility(View.GONE);
            btnRate.setEnabled(false);
        }

        if(receivedOfferBundle.getRequestObj().getOfferStatus().equalsIgnoreCase("COMPLETED") && receivedOfferBundle.getRequestObj().getIsRated() ==0 ){

            initRateDialog();

        }

        if(!receivedOfferBundle.getRequestObj().getOfferStatus().equalsIgnoreCase("ACTIVE")){


            btnAcceptOffer.setEnabled(false);
            btnAcceptOffer.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));

        }


        return rootview;
    }

    private void initDetails(ReceivedOfferBundle resp) {

        tvUserName.setText(resp.getRequestObj().getTrvName());
        tvRating.setText(resp.getRequestObj().getTravlerRating() + "/5");
        tvTravelingFrom.setText(resp.getRequestObj().getDepartingFrom());
        tvTravelingTo.setText(resp.getRequestObj().getArrivalTo());
        tvArrivalDate.setText(resp.getRequestObj().getArrivDate());
        tvOffer.setText(resp.getRequestObj().getOfferPrice() + " PKR");
        tvOfferstatus.setText(resp.getRequestObj().getOfferStatus());

    }

    @OnClick(R.id.btnAcceptOffer)
    public void onAcceptOfferClicked() {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        OfferAcceptRequest login = new OfferAcceptRequest();

        login.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        login.setTravelerUid(receivedOfferBundle.getRequestObj().getTrvId());
        login.setSendPurchaserreqid(receivedOfferBundle.getRequestObj().getSendReqId());
        login.setTravelerreqid(receivedOfferBundle.getRequestObj().getTrvReqId());
        login.setReqtype(receivedOfferBundle.getRequestObj().getRequest_type());
        login.setStatus("ACCEPTED");

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<OfferAcceptResponse> call = apiService.acceptOffer(login);
        call.enqueue(new Callback<OfferAcceptResponse>() {
            @Override
            public void onResponse(Call<OfferAcceptResponse> call, Response<OfferAcceptResponse> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful() && response.body().getStatus() == 0) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.RECEIVED_OFFER_BUNDLE, receivedOfferBundle);

                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new AddCardFragment(), true, false, bundle, false);
                } else if (response.isSuccessful() && response.body().getStatus() == 1) {
                    Toast.makeText(getContext(), response.body().getMessage() + "", Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OfferAcceptResponse> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    @OnClick(R.id.btnCancelOffer)
    public void onClaimRiased() {

        claimBundle =  new ClaimBundle();
        claimBundle.setUserid(receivedOfferBundle.getRequestObj().getSenderPurchaserid());
        claimBundle.setOfferid(receivedOfferBundle.getRequestObj().getOfferId());
        claimBundle.setItem(receivedOfferBundle.getRequestObj().getItemName());
        claimBundle.setDate(receivedOfferBundle.getRequestObj().getDelvDate());




        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.CLAIM, claimBundle);
        replaceChildFragmentWithDelay(new ClaimFragment(), true, false, bundle, true);



    }

    @OnClick(R.id.rlUsername)
    public void LoadUserProfile() {

        IsPreferenceProfile isprefuser = IsPreferenceProfile.getInstance();
        isprefuser.setData(false);
        getUserProfileBundle = new GetUserProfileBundle();
        getUserProfileBundle.setUid(receivedOfferBundle.getRequestObj().getTrvId());
        getUserProfileBundle.setEmail(null);

        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.GET_USER_PROFILE, getUserProfileBundle);
        replaceChildFragmentWithDelay(new ProfileFragment(), true, false, bundle, true);

    }


    public void initRateDialog(){

        final Dialog offerDialog = new Dialog(getContext());
        offerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        offerDialog.setCancelable(false);
        offerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        offerDialog.setContentView(R.layout.dialog_rate_user);

        ImageView dialogButton = (ImageView) offerDialog.findViewById(R.id.imgClose);
        final EditText edtOffer = (EditText) offerDialog.findViewById(R.id.edtOffer);
        final RatingBar ratingBar = (RatingBar) offerDialog.findViewById(R.id.ratingBar);
        Button btnSubmit = (Button) offerDialog.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ratingBar.getRating() == 0.0) {

                    return;
                }else{

                    int rate = ((int)ratingBar.getRating());
                    String text = edtOffer.getText().toString();

                    rateUser(rate,text);
                    offerDialog.dismiss();

                }

            }

        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offerDialog.dismiss();
            }
        });


        offerDialog.show();


    }


    private void rateUser(int rating ,String text){

        AddReviews addReviews = new AddReviews();

        addReviews.setUid(receivedOfferBundle.getRequestObj().getTrvId());
        addReviews.setFromuid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        addReviews.setRating(rating);
        addReviews.setComments(text);
        addReviews.setOfferid(receivedOfferBundle.getRequestObj().getOfferId());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AddReviewsReponse> call = apiService.AddReviews(addReviews);
        call.enqueue(new Callback<AddReviewsReponse>() {
            @Override
            public void onResponse(Call<AddReviewsReponse> call, Response<AddReviewsReponse> response) {
                Log.d(TAG_RATE, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG_RATE, "Status" + response.toString());
                Log.d(TAG_RATE, "RESPONSE:" + response.body());


                UiHelper.getInstance().hideLoadingIndicator();

                if (response.isSuccessful()) {

                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {

                }

            }

            @Override
            public void onFailure(Call<AddReviewsReponse> call, Throwable t) {
                Log.e(TAG_RATE, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });





    }


}