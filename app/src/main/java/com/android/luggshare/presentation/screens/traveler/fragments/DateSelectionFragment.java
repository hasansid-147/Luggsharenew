package com.android.luggshare.presentation.screens.traveler.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.TravelerRequestBundle;
import com.android.luggshare.common.interfaces.onPickDateTimeListener;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.sender.fragments.PricingFragment;
import com.android.luggshare.utils.DateFormatter;
import com.android.luggshare.utils.DateTimePicker;

import butterknife.BindView;
import butterknife.OnClick;


public class DateSelectionFragment extends CoreFragment implements onPickDateTimeListener {

    TravelerRequestBundle travelerRequestBundle;

    @BindView(R.id.tvDepDate)
    TextView tvDepDate;

    @BindView(R.id.tvDepTime)
    TextView tvDepTime;

    @BindView(R.id.tvArrDate)
    TextView tvArrDate;

    @BindView(R.id.tvArrTime)
    TextView tvArrTime;

    @BindView(R.id.edtExpCapacity)
    EditText edtExpCapacity;

    @BindView(R.id.btnback)
    ImageView btnback;

    DateTimePicker startDateTimeObj;
    DateTimePicker endDateTimeObj;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_date_selection;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            travelerRequestBundle = (TravelerRequestBundle) getArguments().getSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        startDateTimeObj = new DateTimePicker();
        endDateTimeObj = new DateTimePicker();

        tvDepDate.setText(DateFormatter.getCurrentDateTime());
        tvArrDate.setText(DateFormatter.getCurrentDateTime());

        return rootview;
    }

    @OnClick({R.id.btnback, R.id.tvDepDate, R.id.tvDepTime, R.id.tvArrDate, R.id.tvArrTime, R.id.btnSendPackage})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tvDepDate:

                startDateTimeObj.setListener(this, "departure_date");
                startDateTimeObj.initDatePicker(getActivity(), "departure_date");
                break;

            case R.id.tvDepTime:

                startDateTimeObj.setListener(this, "departure_time");
                startDateTimeObj.initTimePicker(getActivity(), "departure_time");
                break;

            case R.id.tvArrDate:

                endDateTimeObj.setListener(this, "arrival_date");
                endDateTimeObj.initDatePicker(getActivity(), "arrival_date");
                break;

            case R.id.tvArrTime:

                endDateTimeObj.setListener(this, "arrival_time");
                endDateTimeObj.initTimePicker(getActivity(), "arrival_time");
                break;

            case R.id.btnSendPackage:
                /*Intent Intentsender = new Intent(getApplicationContext(), CategoryActivity.class);
                Intentsender.putExtra(KEY_TRAVELER, true);
                Intentsender.putExtra(KEY_SENDER_FROM_COUNTRY, senderFromCountry);
                Intentsender.putExtra(KEY_SENDER_FROM_CITY, senderFromCity);
                Intentsender.putExtra(KEY_SENDER_TO_COUNTRY, senderToCountry);
                Intentsender.putExtra(KEY_SENDER_TO_CITY, senderToCity);
                Intentsender.putExtra(KEY_TRAVELER_DEP_DATE, tvDepDate.getText().toString() + " " + tvDepTime.getText().toString());
                Intentsender.putExtra(KEY_TRAVELER_ARV_DATE, tvArrDate.getText().toString() + " " + tvArrTime.getText().toString());
                Intentsender.putExtra(KEY_TRAVELER_CAPACITY, edtExpCapacity.getText().toString());
                startActivity(Intentsender);*/
                travelerRequestBundle.setDepTime(tvDepDate.getText().toString() + " " + tvDepTime.getText().toString());
                travelerRequestBundle.setExpArrivaltime(tvArrDate.getText().toString() + " " + tvArrTime.getText().toString());

                if (!edtExpCapacity.getText().toString().isEmpty())
                    travelerRequestBundle.setBagCapacity(Double.parseDouble(edtExpCapacity.getText().toString()));

                Bundle bundle = new Bundle();
                bundle.putSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE, travelerRequestBundle);

                replaceChildFragmentWithDelay(new TravelerCategoryFragment(), true, false, bundle, true);
                break;

        }
    }

    @Override
    public void onDateSelect(String date, String formatedDate, String delegate) {
        if (delegate.equals("departure_date")) {

            if (date != null) {

                tvDepDate.setText(date);
            }

        } else if (delegate.equals("arrival_date")) {
            tvArrDate.setText(date);
        }
    }

    @Override
    public void onTimeSelect(String time, String formatedTime, String delegate) {
        if (delegate.equals("departure_time")) {
            tvDepTime.setText(time);
        } else if (delegate.equals("arrival_time")) {
            tvArrTime.setText(time);
        }
    }

}