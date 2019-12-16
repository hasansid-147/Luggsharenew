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
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.RequestTypeBundle;
import com.android.luggshare.common.keys.AppConstants;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.myrequests.adapters.MyRequestSenderAdapter;
import com.android.luggshare.presentation.screens.myrequests.adapters.MyRequestTravelrAdapter;
import com.android.luggshare.presentation.screens.purchaser.fragments.PurchaserDetailFragment;
import com.android.luggshare.presentation.screens.sender.fragments.SenderDetailsFragment;
import com.android.luggshare.presentation.screens.traveler.fragments.TravelerDetailsFragment;
import com.android.luggshare.utils.RecyclerTouchListener;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class MyRequestFragment extends CoreFragment implements View.OnClickListener {

    private List<ListResponse> dataList = new ArrayList<>();
    @BindView(R.id.rvSender)
    RecyclerView rvSender;
    @BindView(R.id.rvTraveler)
    RecyclerView rvTraveler;
    @BindView(R.id.rvPurchaser)
    RecyclerView rvPurchaser;
    private MyRequestSenderAdapter mAdapter;
    private MyRequestTravelrAdapter mTravAdapter;
    TextView tvSender, tvTraveler, tvPurchaser;
    RequestTypeBundle requestTypeBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_request;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        requestTypeBundle = new RequestTypeBundle();

        tvSender = (TextView) rootview.findViewById(R.id.tvSender);
        tvPurchaser = (TextView) rootview.findViewById(R.id.tvPurchaser);
        tvTraveler = (TextView) rootview.findViewById(R.id.tvTraveler);

        tvSender.setBackgroundResource(R.drawable.border_curved_selected);

        tvSender.setOnClickListener(this);
        tvTraveler.setOnClickListener(this);
        tvPurchaser.setOnClickListener(this);

        fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID), "Sender");

        return rootview;
    }

    private void fetchListData(int uid, String dataRequest) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        RequestSenderList login = new RequestSenderList();

        login.setUid(uid);
        login.setReqType(dataRequest);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ListResponse>> call = apiService.fetchListData(login);
        call.enqueue(new Callback<ArrayList<ListResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ListResponse>> call, Response<ArrayList<ListResponse>> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    if (dataRequest.toLowerCase().equals(AppConstants.KEY_SENDER)) {
                        initSenderView(response.body());
                    } else if (dataRequest.toLowerCase().equals(AppConstants.KEY_TRAVELER)) {
                        initTravelerView(response.body());
                    } else if (dataRequest.toLowerCase().equals(AppConstants.KEY_PURCHASER)) {
                        initPurchaserView(response.body());
                    }

                } else {
                    if (mAdapter != null)
                        mAdapter.clear();

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ListResponse>> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    private void initSenderView(final ArrayList<ListResponse> arrayList) {

        rvSender.setVisibility(View.VISIBLE);
        rvTraveler.setVisibility(View.GONE);
        rvPurchaser.setVisibility(View.GONE);

        mAdapter = new MyRequestSenderAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvSender.setLayoutManager(mLayoutManager);
        rvSender.setItemAnimator(new DefaultItemAnimator());
        rvSender.setAdapter(mAdapter);

        rvSender.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvSender, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    ListResponse respObj = arrayList.get(position);

                    requestTypeBundle.setRequestType(respObj.getReqType());
                    requestTypeBundle.setRequestObj(respObj);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.MY_REQUEST_BUNDLE, requestTypeBundle);

                    replaceChildFragmentWithDelay(new SenderDetailsFragment(), false, true, bundle, true);


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

    private void initTravelerView(final ArrayList<ListResponse> arrayList) {

        rvSender.setVisibility(View.GONE);
        rvTraveler.setVisibility(View.VISIBLE);
        rvPurchaser.setVisibility(View.GONE);

        mTravAdapter = new MyRequestTravelrAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvTraveler.setLayoutManager(mLayoutManager);
        rvTraveler.setItemAnimator(new DefaultItemAnimator());
        rvTraveler.setAdapter(mTravAdapter);

        rvTraveler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvTraveler, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    ListResponse respObj = arrayList.get(position);

                    requestTypeBundle.setRequestType(respObj.getReqType());
                    requestTypeBundle.setRequestObj(respObj);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.MY_REQUEST_BUNDLE, requestTypeBundle);

                    if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_SENDER)) {
                        replaceChildFragmentWithDelay(new SenderDetailsFragment(), false, true, bundle, true);

                    } else if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_TRAVELER)) {
                        replaceChildFragmentWithDelay(new TravelerDetailsFragment(), false, true, bundle, true);
                    }
                    if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_PURCHASER)) {

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), getString(R.string.No_Data_Found), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void initPurchaserView(final ArrayList<ListResponse> arrayList) {

        rvSender.setVisibility(View.GONE);
        rvTraveler.setVisibility(View.GONE);
        rvPurchaser.setVisibility(View.VISIBLE);

        mAdapter = new MyRequestSenderAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvPurchaser.setLayoutManager(mLayoutManager);
        rvPurchaser.setItemAnimator(new DefaultItemAnimator());
        rvPurchaser.setAdapter(mAdapter);

        rvPurchaser.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvPurchaser, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    ListResponse respObj = arrayList.get(position);

                    requestTypeBundle.setRequestType(respObj.getReqType());
                    requestTypeBundle.setRequestObj(respObj);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.MY_REQUEST_BUNDLE, requestTypeBundle);

                    if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_SENDER)) {
                        replaceChildFragmentWithDelay(new SenderDetailsFragment(), false, true, bundle, true);

                    } else if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_TRAVELER)) {
                        replaceChildFragmentWithDelay(new TravelerDetailsFragment(), false, true, bundle, true);
                    }
                    if (requestTypeBundle.getRequestType().toLowerCase().equals(AppConstants.KEY_PURCHASER)) {
                        replaceChildFragmentWithDelay(new PurchaserDetailFragment(), false, true, bundle, true);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvSender:
                tvSender.setBackgroundResource(R.drawable.border_curved_selected);
                tvTraveler.setBackgroundResource(R.drawable.border_curved);
                tvPurchaser.setBackgroundResource(R.drawable.border_curved);
                fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID), "Sender");
                break;

            case R.id.tvTraveler:
                tvSender.setBackgroundResource(R.drawable.border_curved);
                tvTraveler.setBackgroundResource(R.drawable.border_curved_selected);
                tvPurchaser.setBackgroundResource(R.drawable.border_curved);
                fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID), "Traveler");
                break;

            case R.id.tvPurchaser:
                tvSender.setBackgroundResource(R.drawable.border_curved);
                tvTraveler.setBackgroundResource(R.drawable.border_curved);
                tvPurchaser.setBackgroundResource(R.drawable.border_curved_selected);
                fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID), "Purchaser");
                break;

        }

    }

}