package com.android.luggshare.presentation.screens.tracking.fragments.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.addaccount.AddAccountRequest;
import com.android.luggshare.business.models.addaccount.AddAccountResponse;
import com.android.luggshare.business.models.tracking.UpdateTracking;
import com.android.luggshare.business.models.tracking.UpdateTrackingResp;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.TrackingListDetailBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.fragments.MapViewFragment;
import com.android.luggshare.presentation.screens.dashboard.activities.DashboardActivity;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.utils.DateTimePicker;
import com.android.luggshare.utils.UiHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;
import static com.android.luggshare.presentation.activities.MasterActivity.LOCATION_PERMISSION_REQUEST_CODE;

public class UpdateDeliveryDetailFragment extends CoreFragment  {


    @BindView(R.id.edtCardNo)
    EditText edtCardNo;

    @BindView(R.id.textArea_information)
    EditText textArea_information;


    @BindView(R.id.btnAdd)
    Button btnAdd;

    boolean isPlayServiceAvailable = false;
    private FusedLocationProviderClient fusedLocationClient;
    Double srcLat = 0.0, srcLng = 0.0;

    TrackingListDetailBundle trackingListDetailBundle;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_update_tracking;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            trackingListDetailBundle = (TrackingListDetailBundle) getArguments().getSerializable(BundleKeys.TRACKING_DETAIL);
        }


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        checkLocationPermission();
        initiateMapData();
        initializePlacesData();
        return rootview;


    }

    private void initiateMapData() {


        checkGooglePlayServicesAvailable();



        if (isPlayServiceAvailable == true) {



                fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());



        }
    }

    private void initializePlacesData() {

        String apiKey = getString(R.string.google_map_key);
        if (!Places.isInitialized()) {
            Places.initialize(CustomApplication.getContext(), apiKey);
        }

        //onSearchCalled();

    }
    private void checkGooglePlayServicesAvailable() {

        final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(CustomApplication.getContext());
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(((DashboardActivity) getActivity()), resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {

                isPlayServiceAvailable = false;
                Log.d("AllServiceProvidersMap", "This device is not supported.");
                //UIHelper.showToast(CustomApplication.getContext(), "This device doesn't support Google Play Services");
                UiHelper.showToast(CustomApplication.getContext(), "This device doesn't support Google Play Services");
                replaceChildFragmentWithDelay(new HomeFragment(), false, true, null, true);

            }
        }

        isPlayServiceAvailable = true;
        Log.d("AllServiceProvidersMap", "This device is supported.");

    }
    public void checkLocationPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {

            enableMyLocation();

        }

    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(CustomApplication.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(((DashboardActivity) getActivity()), LOCATION_PERMISSION_REQUEST_CODE,
//                    Manifest.permission.ACCESS_FINE_LOCATION, true);

        } else {
            // Access to the location has been granted to the app.


            fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(((DashboardActivity) getActivity()), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {


                                srcLat = location.getLatitude();
                                srcLng = location.getLongitude();


                            }
                        }
                    });


        }
    }

    @OnClick(R.id.btnAdd)
    public void onUpdateTrackingStatus() {



        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        UpdateTracking update = new UpdateTracking();

        update.setOfferId(trackingListDetailBundle.getOfferId());
        update.setLongitude(String.valueOf(srcLng));
        update.setLatitude(String.valueOf(srcLat));
        update.setStatus(edtCardNo.getText().toString());
        update.setComment(textArea_information.getText().toString());


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<UpdateTrackingResp> call = apiService.updateTracking(update);
        call.enqueue(new Callback<UpdateTrackingResp>() {
            @Override
            public void onResponse(Call<UpdateTrackingResp> call, Response<UpdateTrackingResp> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    if (response.body().equals(null)){

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        replaceChildFragmentWithDelay(new MyTrackingFragment(), true, false, null, true);


                    }


                }




            }

            @Override
            public void onFailure(Call<UpdateTrackingResp> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }






}
