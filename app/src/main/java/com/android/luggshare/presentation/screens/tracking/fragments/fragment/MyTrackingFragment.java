package com.android.luggshare.presentation.screens.tracking.fragments.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.TrackingBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class MyTrackingFragment extends CoreFragment {

    @BindView(R.id.btnTrkDelivery)
    Button btnTrkDelivery;


    @BindView(R.id.btnUpdDelivery)
    Button btnUpdDelivery;

    TrackingBundle trackingBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_tracking;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        return rootview;
    }

    @OnClick(R.id.btnTrkDelivery)
    public void onTrckDeliveryclick() {

        trackingBundle = new TrackingBundle();
        trackingBundle.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        trackingBundle.setTrackingas(2);

        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.TRACKING, trackingBundle);
        replaceChildFragmentWithDelay(new TrackDeliveryFragment(), true, false, bundle, true);


    }

    @OnClick(R.id.btnUpdDelivery)
    public void onUpdDeliveryclick() {

        trackingBundle = new TrackingBundle();
        trackingBundle.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        trackingBundle.setTrackingas(1);

        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.TRACKING, trackingBundle);
        replaceChildFragmentWithDelay(new TrackDeliveryFragment(), true, false, bundle, true);


    }


}
