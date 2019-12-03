package com.android.luggshare.presentation.screens.myoffers.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptRequest;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.GetUserProfileBundle;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.constants.IsPreferenceProfile;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.cards.fragments.AddCardFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.profile.fragments.ProfileFragment;
import com.android.luggshare.utils.UiHelper;

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

    @BindView(R.id.btnAcceptOffer)
    Button btnAcceptOffer;

    @BindView(R.id.rlUsername)
    RelativeLayout rlUsername;

    GetUserProfileBundle getUserProfileBundle;
    ReceivedOfferBundle receivedOfferBundle;

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


        return rootview;
    }

    private void initDetails(ReceivedOfferBundle resp) {

        tvUserName.setText(resp.getRequestObj().getTrvName());
        tvRating.setText(resp.getRequestObj().getTravlerRating() + "/5");
        tvTravelingFrom.setText(resp.getRequestObj().getDepartingFrom());
        tvTravelingTo.setText(resp.getRequestObj().getArrivalTo());
        tvArrivalDate.setText(resp.getRequestObj().getArrivDate());
        tvOffer.setText(resp.getRequestObj().getOfferPrice() + " PKR");

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
}