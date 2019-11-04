package com.android.luggshare.presentation.screens.traveler.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.business.models.sendersummary.SenderSummaryRequest;
import com.android.luggshare.business.models.sendersummary.SenderSummaryResponse;
import com.android.luggshare.business.models.traveler.TravelerRequest;
import com.android.luggshare.business.models.traveler.TravelerResponse;
import com.android.luggshare.business.models.travelerlisting.TravListingRequest;
import com.android.luggshare.business.models.travelerlisting.TravListingResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.bundle.TravelerRequestBundle;
import com.android.luggshare.common.enums.RequestTypeEnum;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.activities.DashboardActivity;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.traveler.adapters.TravelerListingAdapter;
import com.android.luggshare.utils.UiHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class TravelerListingFragment extends CoreFragment {

    TravelerRequestBundle travelerRequestBundle;
    ListView list;
    Button btnPostData;
    TextView txtNoDataFound;
    TravelerListingAdapter adapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_traveler_listing;
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

        list = (ListView) rootview.findViewById(R.id.list);
        btnPostData = (Button) rootview.findViewById(R.id.btnPostData);
        txtNoDataFound = (TextView) rootview.findViewById(R.id.txtNoDataFound);

        txtNoDataFound.setVisibility(View.GONE);

        if (travelerRequestBundle.getFromDetailsScreen())
            btnPostData.setVisibility(View.GONE);
        else
            btnPostData.setVisibility(View.VISIBLE);

        getTravelerListing();

        btnPostData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestTraveler();

            }
        });

        return rootview;
    }

    private void getTravelerListing() {

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        TravListingRequest travelerRequest = new TravListingRequest();
        travelerRequest.setUid("" + PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        travelerRequest.setReqtype("SENDER");
        travelerRequest.setFromcountry(travelerRequestBundle.getDepFromCountry());
        travelerRequest.setFrmcity(travelerRequestBundle.getDepFromCity());
        travelerRequest.setTocountry(travelerRequestBundle.getArrvToCountry());
        travelerRequest.setTocity(travelerRequestBundle.getArrvToCity());
        travelerRequest.setDeptime(travelerRequestBundle.getDepTime());
        travelerRequest.setArrvtime(travelerRequestBundle.getExpArrivaltime());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<TravListingResponse>> call = apiService.travelerList(travelerRequest);
        call.enqueue(new Callback<ArrayList<TravListingResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TravListingResponse>> call, Response<ArrayList<TravListingResponse>> response) {

                if (response.body() != null) {
                    adapter = new TravelerListingAdapter(getActivity(), response.body());
                    list.setAdapter(adapter);

                    // Click event for single list row
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            TravListingResponse travelerObj = (TravListingResponse)parent.getAdapter().getItem(position);

                            travelerRequestBundle.setTravListRespObj(travelerObj);

                            Bundle bundle = new Bundle();
                            bundle.putSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE, travelerRequestBundle);

                            replaceChildFragmentWithDelay(new TravelerListingDetailFragment(), true, false, bundle, true);

                            /*TravListingResponse travelerObj = (TravListingResponse)parent.getAdapter().getItem(position);
                            Intent intTraveler = new Intent(getApplicationContext(), Traveler_Listing_details.class);
                            intTraveler.putExtra(KEY_TRAVELER_OBJECT, travelerObj);
                            intTraveler.putExtra(KEY_SENDER_FROM_COUNTRY, senderFromCountry);
                            intTraveler.putExtra(KEY_SENDER_FROM_CITY, senderFromCity);
                            intTraveler.putExtra(KEY_SENDER_TO_COUNTRY, senderToCountry);
                            intTraveler.putExtra(KEY_SENDER_TO_CITY, senderToCity);
                            intTraveler.putExtra(KEY_SENDER_ITEM_1, item1);
                            intTraveler.putExtra(KEY_SENDER_ITEM_2, item2);
                            intTraveler.putExtra(KEY_SENDER_ITEM_3, item3);
                            intTraveler.putExtra(KEY_SENDER_ITEM_4, item4);
                            intTraveler.putExtra(KEY_SENDER_ITEM_5, item4);
                            intTraveler.putExtra(KEY_SENDER_ITEM_6, item6);
                            intTraveler.putExtra(KEY_TRAVELER_DEP_DATE, departureDate);
                            intTraveler.putExtra(KEY_TRAVELER_ARV_DATE, arrivalDate);
                            intTraveler.putExtra(KEY_TRAVELER_CAPACITY, travelerCapacity);
                            startActivity(intTraveler);*/

                        }
                    });
                } else {
                    txtNoDataFound.setVisibility(View.VISIBLE);
                }

                UiHelper.getInstance().hideLoadingIndicator();

            }

            @Override
            public void onFailure(Call<ArrayList<TravListingResponse>> call, Throwable t) {
                Log.e("failure", t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });


    }

    private void requestTraveler() {

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        TravelerRequest travelerRequest = new TravelerRequest();
        travelerRequest.setUid(Integer.parseInt("" + PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID)));
        travelerRequest.setReqTyp("TRAVELER");
        travelerRequest.setDepFromCountry(travelerRequestBundle.getDepFromCountry());
        travelerRequest.setDepFromCity(travelerRequestBundle.getDepFromCity());
        travelerRequest.setArrvToCountry(travelerRequestBundle.getArrvToCountry());
        travelerRequest.setArrvToCity(travelerRequestBundle.getArrvToCity());
        travelerRequest.setBagCapacity(travelerRequestBundle.getBagCapacity());
        travelerRequest.setPrefItem1(travelerRequestBundle.getPrefItem1());
        travelerRequest.setPrefItem2(travelerRequestBundle.getPrefItem2());
        travelerRequest.setPrefItem3(travelerRequestBundle.getPrefItem3());
        travelerRequest.setDepTime(travelerRequestBundle.getDepTime());
        travelerRequest.setExpArrivaltime(travelerRequestBundle.getExpArrivaltime());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TravelerResponse> call = apiService.travelerRequest(travelerRequest);
        call.enqueue(new Callback<TravelerResponse>() {
            @Override
            public void onResponse(Call<TravelerResponse> call, Response<TravelerResponse> response) {
                Log.d("response", "issuccessfull: " + response.isSuccessful());
                Log.d("response", "Status" + response.toString());
                Log.d("response", "RESPONSE:" + response.body());


                UiHelper.getInstance().hideLoadingIndicator();

                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new HomeFragment(), false, false, null, false);


                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TravelerResponse> call, Throwable t) {
                Log.e("failure", t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });


    }

}