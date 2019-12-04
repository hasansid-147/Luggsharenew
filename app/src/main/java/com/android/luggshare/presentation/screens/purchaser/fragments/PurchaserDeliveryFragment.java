package com.android.luggshare.presentation.screens.purchaser.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.PurchaserRequestBundle;
import com.android.luggshare.common.interfaces.onPickDateTimeListener;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.DateTimePicker;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class PurchaserDeliveryFragment extends CoreFragment implements onPickDateTimeListener, View.OnFocusChangeListener {

    @BindView(R.id.edtDeliverFrom)
    EditText edtDeliverFrom;

    @BindView(R.id.edtDeliverTo)
    EditText edtDeliverTo;

    @BindView(R.id.tvDeliverDate)
    TextView tvDeliverDate;

    @BindView(R.id.btnSendPackage)
    Button btnSendPackage;

    DateTimePicker startDateTimeObj;
    PurchaserRequestBundle purchaserRequestBundle;

    private static final int AUTOCOMPLETE_REQUEST_CODE = 23;

    private int currentEditableViewId;


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
        initializePlacesData();
        edtDeliverFrom.setOnFocusChangeListener(this);
        edtDeliverTo.setOnFocusChangeListener(this);

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

                    purchaserRequestBundle.setFrom_city(splitFromLoc[0]);
                    purchaserRequestBundle.setFrom_country(splitFromLoc[1]);
                    purchaserRequestBundle.setTo_country(splitToLoc[1]);
                    purchaserRequestBundle.setTo_city(splitToLoc[0]);
                    purchaserRequestBundle.setDel_date(tvDeliverDate.getText().toString());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.PURCHASER_REQUEST_BUNDLE, purchaserRequestBundle);

                    replaceChildFragmentWithDelay(new PurchaserPricingFragment(), true, false, bundle, true);
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

    public void onSearchCalled() {
        // Set the fields to specify which types of place data to return.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(getActivity());
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @Override
    public void onFocusChange(View view, boolean b) {

        switch (view.getId()) {
            case R.id.edtDeliverFrom:
                currentEditableViewId = edtDeliverFrom.getId();
                onSearchCalled();
                break;

            /*case R.id.edtDeliverTo:
                currentEditableViewId = edtDeliverTo.getId();
                onSearchCalled();
                break;*/
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.i("places", "Place: " + place.getName() + ", " + place.getId() + ", " + place.getAddress());
                //Toast.makeText(CustomApplication.getContext(), "ID: " + place.getId() + "address:" + place.getAddress() + "Name:" + place.getName() + " latlong: " + place.getLatLng(), Toast.LENGTH_LONG).show();
                String address = place.getAddress();

                if (currentEditableViewId == edtDeliverFrom.getId()) {
                    edtDeliverFrom.setText(getCurrentAddress(place.getLatLng().latitude, place.getLatLng().longitude));
                } else if (currentEditableViewId == edtDeliverTo.getId())  {
                    edtDeliverTo.setText(getCurrentAddress(place.getLatLng().latitude, place.getLatLng().longitude));

                }


            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Toast.makeText(CustomApplication.getContext(), "Error: " + status.getStatusMessage(), Toast.LENGTH_LONG).show();
                Log.i("places", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    protected String getCurrentAddress(double lat, double lng) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(CustomApplication.getContext(), Locale.ENGLISH);
            addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0) {
                String address = addresses.get(0).getAddressLine(0);

                String cityName = addresses.get(0).getLocality();
                String countryName = addresses.get(0).getCountryName();

                /* String Province = addresses.get(0).getAddressLine(1);
                String country = addresses.get(0).getCountryName();*/

                Log.e("city_country_name", cityName + " : " + "" + " : " + countryName);

                //return address;// + ", " + Province + ", " + country;
                return cityName + ", " + countryName;// + ", " + Province + ", " + country;
            } else {

                return "Address Not Available";
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void initializePlacesData() {
        String apiKey = getString(R.string.google_map_key);
        if (!Places.isInitialized()) {
            Places.initialize(CustomApplication.getContext(), apiKey);
        }
    }
}