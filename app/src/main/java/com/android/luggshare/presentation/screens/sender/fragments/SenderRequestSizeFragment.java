package com.android.luggshare.presentation.screens.sender.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.DateTimePicker;

import java.io.File;

import butterknife.OnClick;


public class SenderRequestSizeFragment extends CoreFragment {

    SenderRequestBundle senderRequestBundle;

    Button btnSendPackage;
    RadioButton rbBag, rbHandCarry, rbSuitcase;
    EditText edtItemWeight;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_sender_request_size;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            senderRequestBundle = (SenderRequestBundle) getArguments().getSerializable(BundleKeys.SENDER_REQUEST_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        btnSendPackage = (Button) rootview.findViewById(R.id.btnSendPackage);
        rbBag = (RadioButton) rootview.findViewById(R.id.rbBag);
        rbHandCarry = (RadioButton) rootview.findViewById(R.id.rbHandCarry);
        rbSuitcase = (RadioButton) rootview.findViewById(R.id.rbSuitcase);
        edtItemWeight = (EditText) rootview.findViewById(R.id.edtItemWeight);

        rbBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbHandCarry.setChecked(false);
                rbSuitcase.setChecked(false);
                rbBag.setChecked(true);
            }
        });

        rbHandCarry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbBag.setChecked(false);
                rbSuitcase.setChecked(false);
                rbHandCarry.setChecked(true);
            }
        });


        rbSuitcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbBag.setChecked(false);
                rbHandCarry.setChecked(false);
                rbSuitcase.setChecked(true);
            }
        });

        btnSendPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedData = "";
                if (rbBag.isChecked()) {
                    selectedData = "Fits in a bag";
                } else if (rbHandCarry.isChecked()) {
                    selectedData = "Fits in hand carry";
                } else if (rbSuitcase.isChecked()) {
                    selectedData = "Fits in suitcase";
                } else {
                    Toast.makeText(CustomApplication.getContext(), "Please select size to fit", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (edtItemWeight.getText().toString().equals("")) {
                    return;
                }


                senderRequestBundle.setWeight(Double.parseDouble(edtItemWeight.getText().toString()));
                senderRequestBundle.setSize(selectedData);

                Bundle bundle = new Bundle();
                bundle.putSerializable(BundleKeys.SENDER_REQUEST_BUNDLE, senderRequestBundle);

                replaceChildFragmentWithDelay(new SearchFragment(), true, false, bundle, true);

            }
        });

        return rootview;
    }
}