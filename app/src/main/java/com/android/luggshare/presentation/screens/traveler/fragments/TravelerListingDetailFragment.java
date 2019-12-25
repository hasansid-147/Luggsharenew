package com.android.luggshare.presentation.screens.traveler.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.RelativeLayout;
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
import com.android.luggshare.common.bundle.GetUserProfileBundle;
import com.android.luggshare.common.bundle.TravelerRequestBundle;
import com.android.luggshare.common.constants.IsDashboard;
import com.android.luggshare.common.constants.IsPreferenceProfile;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.profile.fragments.ProfileFragment;
import com.android.luggshare.presentation.screens.purchaser.fragments.adapter.SliderAdapterExample;
import com.android.luggshare.presentation.screens.traveler.adapters.TravelerListingAdapter;
import com.android.luggshare.utils.UiHelper;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

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

    @BindView(R.id.tvUrl)
    TextView tvUrl;

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

    @BindView(R.id.rlUsername)
    RelativeLayout rlUsername;

    //@BindView(R.id.imgPackage)
    //  ImageView imgPackage;

    @BindView(R.id.imageSlider)
    SliderView imageSlider;

    @BindView(R.id.btnSendOffer)
    Button btnSendOffer;

    String TAG = "TravelerOffer";
    GetUserProfileBundle getUserProfileBundle;


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
        ArrayList<String> images = new ArrayList<String>();

        images.add(BASE_IMG_PATH + travelerObj.getImagename() + "?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        images.add(BASE_IMG_PATH + travelerObj.getImagename2() + "?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");


        final SliderAdapterExample adapter = new SliderAdapterExample(images);

        imageSlider.setSliderAdapter(adapter);

        imageSlider.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        //imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        imageSlider.setIndicatorSelectedColor(Color.WHITE);
        imageSlider.setIndicatorUnselectedColor(Color.GRAY);

        //imageSlider.startAutoCycle();

        imageSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                imageSlider.setCurrentPagePosition(position);
            }
        });

        tvItemName.setText(travelerObj.getProdname());
        tvPickFrom.setText(travelerObj.getFrmlocation());
        tvDeliverTo.setText(travelerObj.getTolocation());
        tvReward.setText("PKR " + travelerObj.getBringersReward() + "");
        tvPrice.setText("PKR " + travelerObj.getPrice() + "");
        tvWeight.setText("KG " + travelerObj.getWeight() + "");
        tvSize.setText(travelerObj.getSize() + "");
        tvUsername.setText(travelerObj.getUsername() + "");
        tvUrl.setText(travelerObj.getUrl()+ "");
        tvRate.setText("0");
        //UiHelper.setImageWithGlide(getActivity(), imgPackage, BASE_IMG_PATH + travelerObj.getImagename());

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

                callSendOfferApi(Integer.parseInt(edtOffer.getText().toString()),offerDialog);

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


    @OnClick(R.id.rlUsername)
    public void LoadUserProfile() {

        IsPreferenceProfile isprefuser = IsPreferenceProfile.getInstance();
        isprefuser.setData(false);
        getUserProfileBundle = new GetUserProfileBundle();
        getUserProfileBundle.setUid(travelerRequestBundle.getTravListRespObj().getUid());
        getUserProfileBundle.setEmail(null);

        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.GET_USER_PROFILE, getUserProfileBundle);
        replaceChildFragmentWithDelay(new ProfileFragment(), true, false, bundle, true);

    }

    private void callSendOfferApi(int offerValue,Dialog offerdialog) {

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        IsDashboard dsb = IsDashboard.getInstance();
        int isdsb = dsb.getData();

        TravelerOfferRequest reqObj = new TravelerOfferRequest();
        reqObj.setFromUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "");
        reqObj.setToUid(travelerRequestBundle.getTravListRespObj().getUid() + "");
        reqObj.setFromReqid(0 + "");
        reqObj.setToReqid(travelerRequestBundle.getTravListRespObj().getUserreq() + "");
        reqObj.setToReqid(travelerRequestBundle.getTravListRespObj().getUserreq() + "");
        reqObj.setPrice(offerValue);
        reqObj.setToReqType(travelerRequestBundle.getTravListRespObj().getReqtype() + "");
        reqObj.setIsDsb(isdsb);
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


                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    offerdialog.dismiss();
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