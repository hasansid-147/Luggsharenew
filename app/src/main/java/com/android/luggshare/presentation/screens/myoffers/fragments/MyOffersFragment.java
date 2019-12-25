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
import com.android.luggshare.business.models.getmyofferspending.MyOffersPendingListRequestModel;
import com.android.luggshare.business.models.getmyofferspending.MyOffersPendingListResponseModel;
import com.android.luggshare.business.models.getmyoffersreceived.MyOffersReceivedListRequestModel;
import com.android.luggshare.business.models.getmyoffersreceived.MyOffersReceivedListResponseModel;
import com.android.luggshare.business.models.getsenderlist.ListResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.PendingOfferBundle;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.myoffers.adapters.MyPendingOffersAdapter;
import com.android.luggshare.presentation.screens.myoffers.adapters.MyReceivedOffersAdapter;
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
    private MyReceivedOffersAdapter mReceivedAdapter;
    private MyPendingOffersAdapter mPendingAdapter;
    ReceivedOfferBundle receivedOfferBundle;
    PendingOfferBundle pendingOfferBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_offers;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        receivedOfferBundle = new ReceivedOfferBundle();
        pendingOfferBundle =  new PendingOfferBundle();

        tvReceived.setBackgroundResource(R.drawable.border_curved_selected);

        fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));

        return rootview;
    }

    private void fetchListData(int uid) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        MyOffersReceivedListRequestModel login = new MyOffersReceivedListRequestModel();

        login.setUid(uid);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<MyOffersReceivedListResponseModel>> call = apiService.fetchReceivedOffersData(login);
        call.enqueue(new Callback<ArrayList<MyOffersReceivedListResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MyOffersReceivedListResponseModel>> call, Response<ArrayList<MyOffersReceivedListResponseModel>> response) {

                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
//                    if (dataRequest.toLowerCase().equals(AppConstants.KEY_SENDER)) {
                        initReceivedOffers(response.body());
                    /*} else if (dataRequest.toLowerCase().equals(AppConstants.KEY_TRAVELER)) {
                        initPendingOffers(response.body());
                    }*/

                } else {
                    if (mReceivedAdapter != null)
                        mReceivedAdapter.clear();

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MyOffersReceivedListResponseModel>> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    private void fetchPendingListData(int uid) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        MyOffersPendingListRequestModel login = new MyOffersPendingListRequestModel();

        login.setUid(uid);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<MyOffersPendingListResponseModel>> call = apiService.fetchPendingOffersData(login);
        call.enqueue(new Callback<ArrayList<MyOffersPendingListResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<MyOffersPendingListResponseModel>> call, Response<ArrayList<MyOffersPendingListResponseModel>> response) {

                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    initPendingOffers(response.body());
                } else {
                    if (mPendingAdapter != null)
                        mPendingAdapter.clear();

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<MyOffersPendingListResponseModel>> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    private void initReceivedOffers(final ArrayList<MyOffersReceivedListResponseModel> arrayList) {

        rvReceivedOffers.setVisibility(View.VISIBLE);
        rvPendingOffers.setVisibility(View.GONE);




        mReceivedAdapter = new MyReceivedOffersAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvReceivedOffers.setLayoutManager(mLayoutManager);
        rvReceivedOffers.setItemAnimator(new DefaultItemAnimator());
        rvReceivedOffers.setAdapter(mReceivedAdapter);

        rvReceivedOffers.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvReceivedOffers, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    MyOffersReceivedListResponseModel respObj = arrayList.get(position);

                    if (respObj.getOfferStatus().toLowerCase().equals("expired")) {
                        Toast.makeText(getContext(), "Offer is expired.", Toast.LENGTH_SHORT).show();
                    } else {

                        receivedOfferBundle.setRequestObj(respObj);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(BundleKeys.RECEIVED_OFFER_BUNDLE, receivedOfferBundle);

                        replaceChildFragmentWithDelay(new MyReceivedOfferDetailsFragment(), false, true, bundle, true);
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

    private void initPendingOffers(final ArrayList<MyOffersPendingListResponseModel> arrayList) {

        rvPendingOffers.setVisibility(View.VISIBLE);
        rvReceivedOffers.setVisibility(View.GONE);


        mPendingAdapter = new MyPendingOffersAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvPendingOffers.setLayoutManager(mLayoutManager);
        rvPendingOffers.setItemAnimator(new DefaultItemAnimator());
        rvPendingOffers.setAdapter(mPendingAdapter);

        rvPendingOffers.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvPendingOffers, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    MyOffersPendingListResponseModel respObj = arrayList.get(position);

                    if (respObj.getOfferStatus().toLowerCase().equals("expired")) {
                        Toast.makeText(getContext(), "Offer is not valid any more!.", Toast.LENGTH_SHORT).show();
                    } else {

                        pendingOfferBundle.setRequestObj(respObj);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(BundleKeys.PENDING_OFFER_BUNDLE, pendingOfferBundle);

                        replaceChildFragmentWithDelay(new MyPendingOfferDetailsFragment(), false, true, bundle, true);
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


    @OnClick({R.id.tvReceived, R.id.tvPending})
    public void onClickedListener(View v) {

        switch (v.getId()) {

            case R.id.tvReceived:
                tvReceived.setBackgroundResource(R.drawable.border_curved_selected);
                tvPending.setBackgroundResource(R.drawable.border_curved);
                rvPendingOffers.setVisibility(View.GONE);
                fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));

                break;

            case R.id.tvPending:
                tvReceived.setBackgroundResource(R.drawable.border_curved);
                tvPending.setBackgroundResource(R.drawable.border_curved_selected);
                rvReceivedOffers.setVisibility(View.GONE);
                fetchPendingListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
                break;

        }

    }

}