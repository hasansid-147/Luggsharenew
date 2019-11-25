package com.android.luggshare.presentation.screens.tracking.fragments.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.getsenderlist.ListResponse;
import com.android.luggshare.business.models.getsenderlist.RequestSenderList;
import com.android.luggshare.business.models.tracking.GetTrackList;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.RequestTypeBundle;
import com.android.luggshare.common.bundle.TrackingBundle;
import com.android.luggshare.common.bundle.TrackingListDetailBundle;
import com.android.luggshare.common.keys.AppConstants;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.myrequests.adapters.MyRequestSenderAdapter;
import com.android.luggshare.presentation.screens.sender.fragments.SenderDetailsFragment;
import com.android.luggshare.presentation.screens.tracking.fragments.adapter.MyTrackingAdapter;
import com.android.luggshare.utils.RecyclerTouchListener;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class TrackDeliveryFragment extends CoreFragment {

    private List<ListResponse> dataList = new ArrayList<>();
    @BindView(R.id.rvTrackDelv)
    RecyclerView rvTrackDelv;
    @BindView(R.id.rvUpdDelv)
    RecyclerView rvUpdDelv;
    TrackingBundle trackingBundle;
    private MyTrackingAdapter mAdapter;
    TrackingListDetailBundle trackingListDetailBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_tracking;
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        trackingBundle =  new TrackingBundle();
        int uid = trackingBundle.getUid();
        int trackingas  = trackingBundle.getTrackingas();


        fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID), trackingas);

        return rootview;
    }


    private void fetchListData(int uid, int trackingAs) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        GetTrackList getlist = new GetTrackList();

        getlist.setUid(uid);
        getlist.setTrackingas(trackingAs);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<TrackListResponse>> call = apiService.fetchTrackListData(getlist);
        call.enqueue(new Callback<ArrayList<TrackListResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TrackListResponse>> call, Response<ArrayList<TrackListResponse>> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    if (trackingAs == 1) {
                        initUpdTrackingView(response.body());
                    } else if (trackingAs == 2) {
                        initTracking(response.body());
                    }


                } else {
                    if (mAdapter != null)
                        mAdapter.clear();

                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<TrackListResponse>> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }


    private void initUpdTrackingView(final ArrayList<TrackListResponse> arrayList) {

        rvUpdDelv.setVisibility(View.VISIBLE);
        rvTrackDelv.setVisibility(View.GONE);


        mAdapter = new MyTrackingAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvUpdDelv.setLayoutManager(mLayoutManager);
        rvUpdDelv.setItemAnimator(new DefaultItemAnimator());
        rvUpdDelv.setAdapter(mAdapter);

        rvUpdDelv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvUpdDelv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    TrackListResponse respObj = arrayList.get(position);

                    trackingListDetailBundle.setOfferId(respObj.getOfferId());
                    trackingListDetailBundle.setReqTyp(respObj.getReqTyp());
                    trackingListDetailBundle.setDeliveryname(respObj.getDeliveryname());
                    trackingListDetailBundle.setTraveleruid(respObj.getTraveleruid());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.TRACKING_DETAIL, trackingListDetailBundle);

                    replaceChildFragmentWithDelay(new UpdateDeliveryDetailFragment(), false, true, bundle, true);


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


    private void initTracking(final ArrayList<TrackListResponse> arrayList) {

        rvUpdDelv.setVisibility(View.GONE);
        rvTrackDelv.setVisibility(View.VISIBLE);


        mAdapter = new MyTrackingAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvTrackDelv.setLayoutManager(mLayoutManager);
        rvTrackDelv.setItemAnimator(new DefaultItemAnimator());
        rvTrackDelv.setAdapter(mAdapter);

        rvUpdDelv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvTrackDelv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                    TrackListResponse respObj = arrayList.get(position);

                    trackingListDetailBundle.setOfferId(respObj.getOfferId());
                    trackingListDetailBundle.setReqTyp(respObj.getReqTyp());
                    trackingListDetailBundle.setDeliveryname(respObj.getDeliveryname());
                    trackingListDetailBundle.setTraveleruid(respObj.getTraveleruid());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.TRACKING_DETAIL, trackingListDetailBundle);

                    replaceChildFragmentWithDelay(new TrackDeliveryDetailFragment(), false, true, bundle, true);


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
}
