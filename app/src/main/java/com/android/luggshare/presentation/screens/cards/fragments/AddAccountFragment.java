package com.android.luggshare.presentation.screens.cards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.DateTimePicker;



import androidx.annotation.NonNull;

public class AddAccountFragment extends CoreFragment {
    DateTimePicker expiryDateObj;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_add_card;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        expiryDateObj = new DateTimePicker();

        return rootview;
    }
}
