package com.android.luggshare.presentation.screens.dashboard.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.sender.fragments.SenderRequestFragment;

import butterknife.OnClick;


public class HomeFragment extends CoreFragment {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_dashboard;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        return rootview;
    }

    @OnClick(R.id.btnSender)
    public void senderButtonClicked() {
        replaceChildFragmentWithDelay(new SenderRequestFragment(), true, true, null, false);
    }


}