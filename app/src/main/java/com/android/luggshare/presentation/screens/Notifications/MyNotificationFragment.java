package com.android.luggshare.presentation.screens.Notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.notifications.GetNotifications;
import com.android.luggshare.business.models.notifications.NotificationReponse;
import com.android.luggshare.business.models.tracking.GetTrackList;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.TrackingBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.Notifications.adapter.MyNotificationAdapter;
import com.android.luggshare.presentation.screens.tracking.fragments.adapter.MyTrackingAdapter;
import com.android.luggshare.presentation.screens.tracking.fragments.fragment.UpdateDeliveryDetailFragment;
import com.android.luggshare.utils.RecyclerTouchListener;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class MyNotificationFragment   extends CoreFragment {
    @BindView(R.id.rvTrackDelv)
    RecyclerView rvTrackDelv;

    private MyNotificationAdapter mAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_notifications;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);
        int uid = PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID);

        fetchListData(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));



        return rootview;
    }


    private void fetchListData(int uid) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        GetNotifications getlist = new GetNotifications();

        getlist.setRecvUid(uid);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<NotificationReponse>> call = apiService.fetchNotifications(getlist);
        call.enqueue(new Callback<ArrayList<NotificationReponse>>() {
            @Override
            public void onResponse(Call<ArrayList<NotificationReponse>> call, Response<ArrayList<NotificationReponse>> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {

                        initUpdTrackingView(response.body());



                } else {
                    if (mAdapter != null)
                        mAdapter.clear();

                    Toast.makeText(getContext(), getString(R.string.No_Data_Found), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<NotificationReponse>> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }


    private void initUpdTrackingView(final ArrayList<NotificationReponse> arrayList) {

        rvTrackDelv.setVisibility(View.VISIBLE);



        mAdapter = new MyNotificationAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvTrackDelv.setLayoutManager(mLayoutManager);
        rvTrackDelv.setItemAnimator(new DefaultItemAnimator());
        rvTrackDelv.setAdapter(mAdapter);

        rvTrackDelv.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvTrackDelv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {

                   /* TrackListResponse respObj = arrayList.get(position);

                    trackingListDetailBundle.setOfferId(respObj.getOfferId());
                    trackingListDetailBundle.setReqTyp(respObj.getReqTyp());
                    trackingListDetailBundle.setDeliveryname(respObj.getDeliveryname());
                    trackingListDetailBundle.setTraveleruid(respObj.getTraveleruid());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.TRACKING_DETAIL, trackingListDetailBundle);

                    replaceChildFragmentWithDelay(new UpdateDeliveryDetailFragment(), false, true, bundle, true);
                    */

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
}
