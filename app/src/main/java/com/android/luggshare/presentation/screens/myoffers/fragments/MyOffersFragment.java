package com.android.luggshare.presentation.screens.myoffers.fragments;

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
import com.android.luggshare.business.models.getmyoffersreceived.RequestMyOffersReceivedList;
import com.android.luggshare.business.models.getmyoffersreceived.ResponseMyOffersReceivedList;
import com.android.luggshare.business.models.getsenderlist.ListResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.bundle.RequestTypeBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.myoffers.adapters.MyOffersAdapter;
import com.android.luggshare.presentation.screens.sender.fragments.SenderDetailsFragment;
import com.android.luggshare.utils.RecyclerTouchListener;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class MyOffersFragment extends CoreFragment {

    private List<ListResponse> dataList = new ArrayList<>();
    @BindView(R.id.rvReceivedOffers)
    RecyclerView rvReceivedOffers;
    @BindView(R.id.rvPendingOffers)
    RecyclerView rvPendingOffers;
    @BindView(R.id.tvReceived)
    TextView tvReceived;
    @BindView(R.id.tvPending)
    TextView tvPending;
    private MyOffersAdapter mAdapter;
    ReceivedOfferBundle receivedOfferBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_offers;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        receivedOfferBundle = new ReceivedOfferBundle();

        tvReceived.setBackgroundResource(R.drawable.border_curved_selected);

        //fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        fetchListData(21);

        return rootview;
    }

    private void fetchListData(int uid) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        RequestMyOffersReceivedList login = new RequestMyOffersReceivedList();

        login.setUid(uid);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ResponseMyOffersReceivedList>> call = apiService.fetchOffersData(login);
        call.enqueue(new Callback<ArrayList<ResponseMyOffersReceivedList>>() {
            @Override
            public void onResponse(Call<ArrayList<ResponseMyOffersReceivedList>> call, Response<ArrayList<ResponseMyOffersReceivedList>> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
//                    if (dataRequest.toLowerCase().equals(AppConstants.KEY_SENDER)) {
                        initReceivedOffers(response.body());
                    /*} else if (dataRequest.toLowerCase().equals(AppConstants.KEY_TRAVELER)) {
                        initPendingOffers(response.body());
                    }*/

                } else {
                    if (mAdapter != null)
                        mAdapter.clear();

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ResponseMyOffersReceivedList>> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    private void initReceivedOffers(final ArrayList<ResponseMyOffersReceivedList> arrayList) {

        rvReceivedOffers.setVisibility(View.VISIBLE);
        rvPendingOffers.setVisibility(View.GONE);

        mAdapter = new MyOffersAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvReceivedOffers.setLayoutManager(mLayoutManager);
        rvReceivedOffers.setItemAnimator(new DefaultItemAnimator());
        rvReceivedOffers.setAdapter(mAdapter);

        rvReceivedOffers.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvReceivedOffers, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    ResponseMyOffersReceivedList respObj = arrayList.get(position);

                    if (respObj.getOfferStatus().toLowerCase().equals("expired")) {
                        Toast.makeText(getContext(), "Offer is expired.", Toast.LENGTH_SHORT).show();
                    } else {

                        receivedOfferBundle.setRequestObj(respObj);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(BundleKeys.RECEIVED_OFFER_BUNDLE, receivedOfferBundle);

                        replaceChildFragmentWithDelay(new MyOfferDetailsFragment(), false, true, bundle, true);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void initPendingOffers(final ArrayList<ResponseMyOffersReceivedList> arrayList) {

        rvReceivedOffers.setVisibility(View.GONE);
        rvPendingOffers.setVisibility(View.VISIBLE);

        mAdapter = new MyOffersAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvPendingOffers.setLayoutManager(mLayoutManager);
        rvPendingOffers.setItemAnimator(new DefaultItemAnimator());
        rvPendingOffers.setAdapter(mAdapter);

        rvPendingOffers.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvPendingOffers, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    /*ResponseMyOffersReceivedList respObj = arrayList.get(position);

                    receivedOfferBundle.setRequestType(respObj.getReqType());
                    receivedOfferBundle.setRequestObj(respObj);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.MY_REQUEST_BUNDLE, receivedOfferBundle);

                    if (receivedOfferBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_SENDER)) {
                        replaceChildFragmentWithDelay(new SenderDetailsFragment(), false, true, bundle, true);

                    } else if (receivedOfferBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_TRAVELER)) {
                        replaceChildFragmentWithDelay(new TravelerDetailsFragment(), false, true, bundle, true);
                    }
                    if (receivedOfferBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_PURCHASER)) {

                    }*/

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    @OnClick({R.id.tvReceived, R.id.tvPending})
    public void onClickedListener(View v) {

        switch (v.getId()) {

            case R.id.tvReceived:
                tvReceived.setBackgroundResource(R.drawable.border_curved_selected);
                tvPending.setBackgroundResource(R.drawable.border_curved);
                fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
                break;

            case R.id.tvPending:
                tvReceived.setBackgroundResource(R.drawable.border_curved);
                tvPending.setBackgroundResource(R.drawable.border_curved_selected);
                fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
                break;

        }

    }

}