package com.android.luggshare.presentation.screens.purchaser.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.PurchaserRequestBundle;
import com.android.luggshare.common.interfaces.onPickDateTimeListener;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.DateTimePicker;

import butterknife.BindView;
import butterknife.OnClick;


public class PurchaserPricingFragment extends CoreFragment implements onPickDateTimeListener {

    PurchaserRequestBundle purchaserRequestBundle;

    @BindView(R.id.edtDeliverFrom)
    EditText edtDeliverFrom;

    @BindView(R.id.edtDeliverTo)
    EditText edtDeliverTo;

    @BindView(R.id.tvDeliverDate)
    TextView tvDeliverDate;

    @BindView(R.id.btnSendPackage)
    Button btnSendPackage;

    DateTimePicker startDateTimeObj;

    String purch_url,purch_name,purch_description,purch_price,purch_quantity,purch_img,purch_imgext;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_purchaser_delivery;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            purchaserRequestBundle = (PurchaserRequestBundle) getArguments().getSerializable(BundleKeys.PURCHASER_REQUEST_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        startDateTimeObj = new DateTimePicker();

        return rootview;
    }


    @OnClick({R.id.tvDeliverDate, R.id.btnSendPackage})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tvDeliverDate:

                startDateTimeObj.setListener(this, "departure_date");
                startDateTimeObj.initDatePicker(getActivity(), "departure_date");
                break;

            case R.id.btnSendPackage:

                if(edtDeliverFrom.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Enter valid pickup location", Toast.LENGTH_SHORT).show();
                }
                if(edtDeliverTo.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Enter valid destination location", Toast.LENGTH_SHORT).show();
                }else {


                    String splitFromLoc[] = edtDeliverFrom.getText().toString().split(",");
                    String splitToLoc[] = edtDeliverTo.getText().toString().split(",");

                    /*Intent intentpricing = new Intent(getApplicationContext(), PurchaserPricingActivity.class);

                    intentpricing.putExtra(KEY_PURCHASER_URL,purch_url );
                    intentpricing.putExtra(KEY_PURCHASER_NAME, purch_name);
                    intentpricing.putExtra(KEY_PURCHASER_DESC, purch_description);
                    intentpricing.putExtra(KEY_PURCHASER_PRICE,purch_price);
                    intentpricing.putExtra(KEY_PURCHASER_QUANT,purch_quantity);
                    intentpricing.putExtra(KEY_PURCH_IMAGE_1, purch_img);
                    intentpricing.putExtra(KEY_PURCH_IMAGE_1_EXTENSION, purch_imgext);
                    intentpricing.putExtra(KEY_PURCH_FROMCOUNTRY, splitFromLoc[1]);
                    intentpricing.putExtra(KEY_PURCH_FROMCITY, splitFromLoc[0]);
                    intentpricing.putExtra(KEY_PURCH_TOCOUNTRY, splitToLoc[1]);
                    intentpricing.putExtra(KEY_PURCH_TOCITY, splitToLoc[0]);
                    intentpricing.putExtra(KEY_PURCH_DELDATE, tvDeliverDate.getText().toString());

                    startActivity(intentpricing);*/





                }



                break;


        }
    }



    @Override
    public void onDateSelect(String date, String formatedDate, String delegate) {
        if (delegate.equals("departure_date")) {

            if (date != null) {

                tvDeliverDate.setText(date);
            }

        }
    }

    @Override
    public void onTimeSelect(String time, String formatedTime, String delegate) {

    }
}