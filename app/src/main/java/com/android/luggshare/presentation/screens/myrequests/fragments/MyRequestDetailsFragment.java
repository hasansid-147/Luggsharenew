package com.android.luggshare.presentation.screens.myrequests.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.getsenderlist.ListResponse;
import com.android.luggshare.business.models.getsenderlist.RequestSenderList;
import com.android.luggshare.business.models.senderdetails.SenderDetailsRequest;
import com.android.luggshare.business.models.senderdetails.SenderDetailsResponse;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsRequest;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.RequestTypeBundle;
import com.android.luggshare.common.keys.AppConstants;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.myrequests.adapters.MyRequestSenderAdapter;
import com.android.luggshare.utils.RecyclerTouchListener;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class MyRequestDetailsFragment extends CoreFragment implements View.OnClickListener {

    @BindView(R.id.tvItemName)
    TextView tvItemName;

    @BindView(R.id.tvItemDesc)
    TextView tvItemDesc;

    @BindView(R.id.tvFromLoc)
    TextView tvFromLoc;

    @BindView(R.id.tvToLoc)
    TextView tvToLoc;

    @BindView(R.id.tvDeliverDate)
    TextView tvDeliverDate;

    @BindView(R.id.tvWeight)
    TextView tvWeight;

    @BindView(R.id.tvSize)
    TextView tvSize;

    @BindView(R.id.tvCategory)
    TextView tvCategory;

    @BindView(R.id.tvReward)
    TextView tvReward;

    @BindView(R.id.tvFees)
    TextView tvFees;

    @BindView(R.id.tvStatus)
    TextView tvStatus;

    RequestTypeBundle requestTypeBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_request_details;
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

        if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_SENDER)) {
            fetchSenderDetails(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "", requestTypeBundle.getRequestObj().getReqId() + "", requestTypeBundle.getRequestType());

        } else if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_TRAVELER)) {
            fetchTravelerDetails(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "", requestTypeBundle.getRequestObj().getReqId() + "", requestTypeBundle.getRequestType());
        }
        if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_PURCHASER)) {

        }

        return rootview;
    }

    private void fetchSenderDetails(String uid, String requestId, String requestType) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        SenderDetailsRequest senderDetailsRequest = new SenderDetailsRequest();

        senderDetailsRequest.setUid(uid);
        senderDetailsRequest.setReqId(requestId);
        senderDetailsRequest.setReqType(requestType);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SenderDetailsResponse> call = apiService.fetchSenderDetails(senderDetailsRequest);
        call.enqueue(new Callback<SenderDetailsResponse>() {
            @Override
            public void onResponse(Call<SenderDetailsResponse> call, Response<SenderDetailsResponse> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    initSenderView(response.body());

                } else {

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SenderDetailsResponse> call, Throwable t) {
                UiHelper.getInstance().hideLoadingIndicator();
                Log.e("failure", t.toString());
            }
        });


    }

    private void fetchTravelerDetails(String uid, String requestId, String requestType) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        TravelerDetailsRequest travelerDetailsRequest = new TravelerDetailsRequest();

        travelerDetailsRequest.setUid(uid);
        travelerDetailsRequest.setReqId(requestId);
        travelerDetailsRequest.setReqType(requestType);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<TravelerDetailsResponse> call = apiService.fetchTravelerDetails(travelerDetailsRequest);
        call.enqueue(new Callback<TravelerDetailsResponse>() {
            @Override
            public void onResponse(Call<TravelerDetailsResponse> call, Response<TravelerDetailsResponse> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    //initViews(response.body());

                } else {

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<TravelerDetailsResponse> call, Throwable t) {
                UiHelper.getInstance().hideLoadingIndicator();
                Log.e("failure", t.toString());
            }
        });


    }

    private void initSenderView(SenderDetailsResponse resp) {

        tvItemName.setText(resp.getName());
        tvItemDesc.setText(resp.getDetail());
        tvFromLoc.setText(resp.getFromCity() + ", " + resp.getFromCountry());
        tvToLoc.setText(resp.getToCity() + ", " + resp.getToCountry());
        tvDeliverDate.setText(resp.getDelDate());
        tvWeight.setText(resp.getWeight() + "");
        tvSize.setText(resp.getSize());
        tvCategory.setText(resp.getCategory());
        tvReward.setText(resp.getReward() + "");
        tvFees.setText(resp.getFee() + "");
        tvStatus.setText(resp.getStatus());

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

        }

    }

}