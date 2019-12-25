package com.android.luggshare.presentation.screens.purchaser.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.purchaserdetails.PurchaserDetailsReponse;
import com.android.luggshare.business.models.purchaserdetails.PurchaserDetailsRequest;
import com.android.luggshare.business.models.senderdetails.SenderDetailsResponse;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsRequest;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.RequestTypeBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.purchaser.fragments.adapter.SliderAdapterExample;
import com.android.luggshare.utils.UiHelper;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.AppConstants.BASE_IMG_PATH;
import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class PurchaserDetailFragment extends CoreFragment {
    @BindView(R.id.tvItemName)
    TextView tvItemName;

    @BindView(R.id.tvItemDesc)
    TextView tvItemDesc;

    @BindView(R.id.tvFromLoc)
    TextView tvFromLoc;

    @BindView(R.id.tvToLoc)
    TextView tvToLoc;

    @BindView(R.id.tvUrl)
    TextView tvUrl;

    @BindView(R.id.tvDeliverDate)
    TextView tvDeliverDate;

    @BindView(R.id.tvWeight)
    TextView tvWeight;

    @BindView(R.id.tvSize)
    TextView tvSize;



    @BindView(R.id.tvReward)
    TextView tvReward;


    @BindView(R.id.tvStatus)
    TextView tvStatus;

    //@BindView(R.id.imgPackage)
  //  ImageView imgPackage;

    @BindView(R.id.imageSlider)
    SliderView imageSlider;

    RequestTypeBundle requestTypeBundle;



    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_purchaser_details;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            requestTypeBundle = (RequestTypeBundle) getArguments().getSerializable(BundleKeys.MY_REQUEST_BUNDLE);
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        fetchPurchaserDetails(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) , requestTypeBundle.getRequestObj().getReqId() , requestTypeBundle.getRequestType());

        return rootview;
    }


    private void fetchPurchaserDetails(Integer uid, Integer requestId, String requestType) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        PurchaserDetailsRequest purchaserDetailsRequest = new PurchaserDetailsRequest();

        purchaserDetailsRequest.setUid(uid);
        purchaserDetailsRequest.setReqId(requestId);
        purchaserDetailsRequest.setReqType(requestType);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PurchaserDetailsReponse> call = apiService.fetchPurchaserDetails(purchaserDetailsRequest);
        call.enqueue(new Callback<PurchaserDetailsReponse>() {
            @Override
            public void onResponse(Call<PurchaserDetailsReponse> call, Response<PurchaserDetailsReponse> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    initPurchaserView(response.body());

                } else {

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<PurchaserDetailsReponse> call, Throwable t) {
                UiHelper.getInstance().hideLoadingIndicator();
                Log.e("failure", t.toString());
            }
        });


    }

    private void initPurchaserView(PurchaserDetailsReponse resp) {

        ArrayList<String> images = new ArrayList<String>();

        images.add(BASE_IMG_PATH + resp.getImageLoc() + "?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
        images.add(BASE_IMG_PATH + resp.getImageLoc2() + "?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");


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

        tvItemName.setText(resp.getProdName());
        tvItemDesc.setText(resp.getProdDetail());
        tvFromLoc.setText(resp.getFromCity() + ", " + resp.getFromCountry());
        tvToLoc.setText(resp.getToCity() + ", " + resp.getToCountry());
        tvDeliverDate.setText(resp.getDelDate());
        tvWeight.setText(resp.getPrice() + "");
        tvSize.setText(resp.getQuant() + "");
        tvUrl.setText(resp.getUrl());
        //tvCategory.setText(resp.getCategory());
        tvReward.setText(resp.getReward()+ "");
        //tvFees.setText(resp.getFee() + "");
        tvStatus.setText(resp.getStatus());
        //UiHelper.setImageWithGlide(getActivity(), imgPackage, BASE_IMG_PATH + resp.getImageLoc());

    }
}
