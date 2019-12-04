package com.android.luggshare.presentation.screens.traveler.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.business.models.senderdetails.SenderDetailsRequest;
import com.android.luggshare.business.models.senderdetails.SenderDetailsResponse;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsRequest;
import com.android.luggshare.business.models.travelerdetails.TravelerDetailsResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.RequestTypeBundle;
import com.android.luggshare.common.bundle.TravelerRequestBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.UiHelper;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class TravelerDetailsFragment extends CoreFragment {

    @BindView(R.id.tvDeparture)
    TextView tvDeparture;

    @BindView(R.id.tvArrival)
    TextView tvArrival;

    @BindView(R.id.tvDepDate)
    TextView tvDepDate;

    @BindView(R.id.tvExpArrival)
    TextView tvExpArrival;

    @BindView(R.id.tvBaggage)
    TextView tvBaggage;

    @BindView(R.id.tvBagcap)
    TextView tvBagcap;

    @BindView(R.id.tvStatus)
    TextView tvStatus;

    @BindView(R.id.btnSearch)
    TextView btnSearch;

    RequestTypeBundle requestTypeBundle;
    TravelerDetailsResponse responseDetails;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_traveler_details;
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

        fetchTravelerDetails(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "", requestTypeBundle.getRequestObj().getReqId() + "", requestTypeBundle.getRequestType());

        return rootview;
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
                    initView(response.body());

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

    private void initView(TravelerDetailsResponse resp) {

        try {
            tvDeparture.setText(resp.getDepFromCity() + ", " + resp.getDepFromCountry());
            tvArrival.setText(resp.getArrvToCity() + ", " + resp.getArrvToCountry());
            tvDepDate.setText(resp.getDepTime());
            tvExpArrival.setText(resp.getExpArrvTime());
            tvBaggage.setText(resp.getCategories() + "");
            tvBagcap.setText(resp.getBagCap() + "");
            tvStatus.setText(resp.getReqstatus());

            if (Integer.parseInt(resp.getOfferstatus()) == 1) {
                btnSearch.setEnabled(false);
                btnSearch.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));
            }

            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TravelerRequestBundle travelerRequestBundle = new TravelerRequestBundle();
                    travelerRequestBundle.setUid(resp.getUid());
                    travelerRequestBundle.setReqTyp(requestTypeBundle.getRequestType());
                    travelerRequestBundle.setDepTime(resp.getDepTime());
                    travelerRequestBundle.setDepFromCity(resp.getDepFromCity());
                    travelerRequestBundle.setDepFromCountry(resp.getDepFromCountry());
                    travelerRequestBundle.setExpArrivaltime(resp.getExpArrvTime());
                    travelerRequestBundle.setArrvToCity(resp.getArrvToCity());
                    travelerRequestBundle.setArrvToCountry(resp.getArrvToCountry());
                    travelerRequestBundle.setFromDetailsScreen(true);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE, travelerRequestBundle);

                    replaceChildFragmentWithDelay(new TravelerListingFragment(), true, true, bundle, true);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}