package com.android.luggshare.presentation.screens.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.presentation.fragments.CoreFragment;

import androidx.annotation.NonNull;
import butterknife.BindFont;
import butterknife.BindView;

public class FragmentPolicies extends CoreFragment {

    @BindView(R.id.edtCardName)
    TextView edtCardName;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_policies;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        String para =
                "1.Luggshare is a matching service,and it does not serve as delivery transportation solution.\n" + "\n" +
                "2.Luggshare allow Traveler to be reimbursed in part of their cost of travelling to another location.\n" + "\n" +
                "3.Sender should avoid delivery of any item that is illegal to carry while travelling to other locations.\n" +  "\n" +
                "4.Purchaser should avoid for the purchase and delivery of any item that is illegal to carry while travelling to other locations.\n" +  "\n" +

                "5.Traveler should consider well before accepting any item , about the legality of the item. " +
                "for eg.weapons, firearms or material for firearms, corrosive or dangerous substances, drugs etc.\n" +  "\n" +
                "7.Before accepting offer from Traveler. Sender or Purchaser should verify the persons authenticity by\n" +
                "viewing the profile and verification checks. For eg.(Verified phone, verified email, Profile image,Social\n" +
                "identity)";






        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        edtCardName.setText(para);

        return rootview;
    }

}
