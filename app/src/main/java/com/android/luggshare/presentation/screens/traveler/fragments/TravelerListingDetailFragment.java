package com.android.luggshare.presentation.screens.traveler.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.business.models.traveler.TravelerRequest;
import com.android.luggshare.business.models.traveler.TravelerResponse;
import com.android.luggshare.business.models.travelerlisting.TravListingRequest;
import com.android.luggshare.business.models.travelerlisting.TravListingResponse;
import com.android.luggshare.business.models.traveleroffer.TravelerOfferRequest;
import com.android.luggshare.business.models.traveleroffer.TravelerOfferResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.TravelerRequestBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.traveler.adapters.TravelerListingAdapter;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.AppConstants.BASE_IMG_PATH;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class TravelerListingDetailFragment extends CoreFragment {

    TravelerRequestBundle travelerRequestBundle;

    @BindView(R.id.tvItemName)
    TextView tvItemName;

    @BindView(R.id.tvPickFrom)
    TextView tvPickFrom;

    @BindView(R.id.tvDeliverTo)
    TextView tvDeliverTo;

    @BindView(R.id.tvReward)
    TextView tvReward;

    @BindView(R.id.tvPrice)
    TextView tvPrice;

    @BindView(R.id.tvWeight)
    TextView tvWeight;

    @BindView(R.id.tvSize)
    TextView tvSize;

    @BindView(R.id.tvUsername)
    TextView tvUsername;

    @BindView(R.id.tvRate)
    TextView tvRate;

    @BindView(R.id.imgPackage)
    ImageView imgPackage;

    @BindView(R.id.btnSendOffer)
    Button btnSendOffer;

    String TAG = "TravelerOffer";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_traveler_listing_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            travelerRequestBundle = (TravelerRequestBundle) getArguments().getSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        populateData(travelerRequestBundle.getTravListRespObj());

        return rootview;
    }

    private void populateData(TravListingResponse travelerObj) {

        tvItemName.setText(travelerObj.getProdname());
        tvPickFrom.setText(travelerObj.getFrmlocation());
        tvDeliverTo.setText(travelerObj.getTolocation());
        tvReward.setText("PKR " + travelerObj.getBringersReward() + "");
        tvPrice.setText("PKR " + travelerObj.getPrice() + "");
        tvWeight.setText("KG " + travelerObj.getWeight() + "");
        tvSize.setText(travelerObj.getSize() + "");
        tvUsername.setText(travelerObj.getUsername() + "");
        tvRate.setText("0");
        UiHelper.setImageWithGlide(getActivity(), imgPackage, BASE_IMG_PATH + travelerObj.getImagename());

    }

    @OnClick(R.id.btnSendOffer)
    public void onSendOfferClicked() {

        final Dialog offerDialog = new Dialog(getContext());
        offerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        offerDialog.setCancelable(false);
        offerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        offerDialog.setContentView(R.layout.dialog_make_offer);

        ImageView dialogButton = (ImageView) offerDialog.findViewById(R.id.imgClose);
        final EditText edtOffer = (EditText) offerDialog.findViewById(R.id.edtOffer);
        Button btnSubmit = (Button) offerDialog.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtOffer.getText().toString().isEmpty()) {

                    return;
                }

                callSendOfferApi(Integer.parseInt(edtOffer.getText().toString()));

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

    private void callSendOfferApi(int offerValue) {

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        TravelerOfferRequest reqObj = new TravelerOfferRequest();
        reqObj.setFromUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "");
        reqObj.setToUid(travelerRequestBundle.getTravListRespObj().getUid() + "");
        reqObj.setFromReqid(0 + "");
        reqObj.setToReqid(travelerRequestBundle.getTravListRespObj().getUserreq() + "");
        reqObj.setToReqid(travelerRequestBundle.getTravListRespObj().getUserreq() + "");
        reqObj.setPrice(offerValue);
        reqObj.setToReqType("SENDER");
        reqObj.setIsDsb(0);
        reqObj.setReqTyp("TRAVELER");

        reqObj.setDepFromCountry(travelerRequestBundle.getDepFromCountry());
        reqObj.setDepFromCity(travelerRequestBundle.getDepFromCity());
        reqObj.setArrvToCountry(travelerRequestBundle.getArrvToCountry());
        reqObj.setArrvToCity(travelerRequestBundle.getArrvToCity());
        reqObj.setBagCapacity(travelerRequestBundle.getBagCapacity());
        reqObj.setPrefItem1(travelerRequestBundle.getPrefItem1());
        reqObj.setPrefItem2(travelerRequestBundle.getPrefItem2());
        reqObj.setPrefItem3(travelerRequestBundle.getPrefItem3());
        reqObj.setDepTime(travelerRequestBundle.getDepTime());
        reqObj.setExpArrivaltime(travelerRequestBundle.getExpArrivaltime());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TravelerOfferResponse> call = apiService.sendTravelerOffer(reqObj);
        call.enqueue(new Callback<TravelerOfferResponse>() {
            @Override
            public void onResponse(Call<TravelerOfferResponse> call, Response<TravelerOfferResponse> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());


                UiHelper.getInstance().hideLoadingIndicator();

                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TravelerOfferResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });


    }

}