package com.android.luggshare.presentation.screens.profile.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.luggshare.R;
import com.android.luggshare.presentation.fragments.CoreFragment;

import androidx.annotation.NonNull;

public class VerifyEmail extends CoreFragment {




    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_verify_email;
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


        return rootview;
    }
}
