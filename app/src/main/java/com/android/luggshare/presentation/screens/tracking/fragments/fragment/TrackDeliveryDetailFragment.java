package com.android.luggshare.presentation.screens.tracking.fragments.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.tracking.GetLatestTracking;
import com.android.luggshare.business.models.tracking.GetLatestTrackingResp;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.business.models.tracking.UpdateTracking;
import com.android.luggshare.business.models.tracking.UpdateTrackingResp;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.bundle.TrackingListDetailBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.fragments.MapViewFragment;
import com.android.luggshare.presentation.screens.dashboard.activities.DashboardActivity;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.sender.fragments.CategoryFragment;
import com.android.luggshare.utils.UiHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackDeliveryDetailFragment extends CoreFragment implements GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        LocationListener
        {


    @BindView(R.id.edt_pickup)
    TextView edt_pickup;

    @BindView(R.id.edt_comment)
    TextView edt_comment;

    @BindView(R.id.edt_destination)
    TextView edt_destination;

    @BindView(R.id.btn_done)
    Button btn_done;






    /* Map Variables */
    private FusedLocationProviderClient fusedLocationClient;
    boolean isPlayServiceAvailable = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    String[] strSplitGeoLocation;
    String strServiceProviderLat = "", strServiceProviderLng = "";
    Double srcLat = 0.0, srcLng = 0.0;
    private GoogleMap mMap;
    private SupportMapFragment mMapFragment;
    SupportMapFragment mMapView;
    Marker marker;
    private LocationManager locationManager;
    LatLng latLng;
    Double doubleLatitude = 0.0, doubleLongitude = 0.0;
    MapViewFragment mSupportMapFragment;
    public LatLng userLocation;
    public LatLng destination;

    // Places
    String apiKey;
    Geocoder geocoder;
    List<Address> addresses;

    TrackingListDetailBundle trackingListDetailBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_tracking_coordiantes;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            trackingListDetailBundle = (TrackingListDetailBundle) getArguments().getSerializable(BundleKeys.TRACKING_DETAIL);
        }


    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        getTrackData();
        initiateMapData();
        initializePlacesData();
        return rootview;
    }

    @OnClick(R.id.btn_done)
    public void onViewClicked(View view) {
                try {




                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    private void initiateMapData() {
        //mMapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        mSupportMapFragment = (MapViewFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //mMapFragment.getMapAsync(this);
        mSupportMapFragment.getMapAsync(this);


        if (mSupportMapFragment != null)
            mSupportMapFragment.setListener(new MapViewFragment.OnTouchListener() {
                @Override
                public void onTouch() {
                    //scrollView.requestDisallowInterceptTouchEvent(true);
                    ((DashboardActivity) getActivity()).setNestedScroll();
                }
            });


        checkGooglePlayServicesAvailable();

        mMapView = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (isPlayServiceAvailable == true) {

            if (mMapView != null) {
                mMapView.onCreate(null);
                mMapView.onResume();
                mMapView.getMapAsync(this);

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());

            }

        }
    }

    private void initializePlacesData() {

        String apiKey = getString(R.string.google_map_key);
        if (!Places.isInitialized()) {
            Places.initialize(CustomApplication.getContext(), apiKey);
        }

        //onSearchCalled();

    }

    @Override
      public void onMyLocationClick(@NonNull Location location) {
                enableMyLocation();
      }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(CustomApplication.getContext());
        mMap = googleMap;

        checkLocationPermission();

    }


    public void checkLocationPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {

            enableMyLocation();

        }

    }

    @Override
    public void onLocationChanged(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
                mMap.animateCamera(cameraUpdate);
                locationManager.removeUpdates(this);
    }

    @Override
     public void onStatusChanged(String s, int i, Bundle bundle) {

    }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }



            /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(CustomApplication.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
//            PermissionUtils.requestPermission(((DashboardActivity) getActivity()), LOCATION_PERMISSION_REQUEST_CODE,
//                    Manifest.permission.ACCESS_FINE_LOCATION, true);

        } else if (mMap != null) {
            // Access to the location has been granted to the app.


            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);


            fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(((DashboardActivity) getActivity()), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                if(Double.compare(srcLat,0.0) ==0 && Double.compare(srcLng,0.0) ==0 ) {
                                    srcLat = location.getLatitude();
                                    srcLng = location.getLongitude();

                                    latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                }else
                                {
                                    latLng = new LatLng(srcLat, srcLng);
                                }

                                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                                mMap.animateCamera(cameraUpdate);


                            }
                        }
                    });


        }

        geocoder = new Geocoder(getActivity(), Locale.getDefault());




    }

    public void getTrackData(){

        UiHelper.getInstance().hideKeyboard(getActivity());
        UiHelper.getInstance().showLoadingIndicator(getActivity());

        GetLatestTracking getLatestTracking = new GetLatestTracking();

        getLatestTracking.setOfferid(trackingListDetailBundle.getOfferId());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<GetLatestTrackingResp> call = apiService.getLatestTracking(getLatestTracking);
        call.enqueue(new Callback<GetLatestTrackingResp>() {
            @Override
            public void onResponse(Call<GetLatestTrackingResp> call, Response<GetLatestTrackingResp> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                   if (response.body().equals(null)){

                       Toast.makeText(getContext(), getString(R.string.No_Data_Found), Toast.LENGTH_SHORT).show();



                    }else{

                       srcLat = Double.parseDouble(response.body().getLatitude());
                       srcLng = Double.parseDouble(response.body().getLongitutde());
                       edt_pickup.setText(response.body().getStatus());
                       edt_comment.setText(response.body().getComment());
                       edt_destination.setText(response.body().getUpdatedAt());
                    }


                } else {

                    Toast.makeText(getContext(), getString(R.string.No_Data_Found), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetLatestTrackingResp> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });



    }

            @OnClick(R.id.btn_done)
            public void onRequestForLatest() {



                UiHelper.getInstance().hideKeyboard(getActivity());

                UiHelper.getInstance().showLoadingIndicator(getActivity());




                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

                Call<String> call = apiService.requestLatest(trackingListDetailBundle.getOfferId());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Log.d("List", "RESPONSE:" + response.body());
                        UiHelper.getInstance().hideLoadingIndicator();


                        if (response.isSuccessful()) {







                        }




                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("List", t.toString());
                    }
                });


            }



}
