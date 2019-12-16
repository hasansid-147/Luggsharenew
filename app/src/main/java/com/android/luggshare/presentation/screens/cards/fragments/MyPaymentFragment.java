package com.android.luggshare.presentation.screens.cards.fragments;

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
import com.android.luggshare.presentation.screens.tracking.fragments.fragment.TrackDeliveryFragment;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class MyPaymentFragment extends CoreFragment {

    @BindView(R.id.btnAddcard)
    Button btnAddcard;


    @BindView(R.id.btnAddAccnt)
    Button btnAddAccnt;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_payment;
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


    @OnClick(R.id.btnAddcard)
    public void onTrckDeliveryclick(){




        replaceChildFragmentWithDelay( new AddCardFragment(), true, false, null, true);


    }

    @OnClick(R.id.btnAddAccnt)
    public void onUpdDeliveryclick(){


        replaceChildFragmentWithDelay( new AddAccountFragment(), true, false, null, true);


    }


}
