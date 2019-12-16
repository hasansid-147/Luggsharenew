package com.android.luggshare.presentation.screens.traveler.fragments;

import android.Manifest;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.bundle.TravelerRequestBundle;
import com.android.luggshare.common.constants.IsDashboard;
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
import com.google.android.gms.common.api.Status;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class TravelerSearchFragment extends CoreFragment implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        LocationListener, TextWatcher, View.OnFocusChangeListener {

    TravelerRequestBundle travelerRequestBundle;

    private static final int AUTOCOMPLETE_REQUEST_CODE = 22;

    @BindView(R.id.iv_pickIcon)
    ImageView ivPickIcon;
    @BindView(R.id.edt_pickup)
    EditText edtPickup;
    @BindView(R.id.ll_pickup)
    LinearLayout llPickup;
    @BindView(R.id.iv_destinationIcon)
    ImageView ivDestinationIcon;
    @BindView(R.id.edt_destination)
    EditText edtDestination;
    @BindView(R.id.ll_destination)
    RelativeLayout llDestination;
    @BindView(R.id.container_address)
    LinearLayout containerAddress;
    @BindView(R.id.current_location_button)
    ImageView currentLocationButton;
    @BindView(R.id.search_places_pb)
    ProgressBar searchProgressBar;
    @BindView(R.id.search_places_ll)
    View searchPlacesLL;
    @BindView(R.id.btn_done)
    Button btnDone;
    @BindView(R.id.img_position)
    ImageView imgPosition;

    String pickUpAddress = "";

    private int currentEditableViewId;
    private boolean isPickUp = true;

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

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IsDashboard dsb = IsDashboard.getInstance();
        dsb.setData(0);
        if (getArguments() != null) {


            travelerRequestBundle = (TravelerRequestBundle) getArguments().getSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);
        edtPickup.setOnFocusChangeListener(this);
        edtDestination.setOnFocusChangeListener(this);
        initiateMapData();
        initializePlacesData();
        return rootview;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            switch (requestCode) {

                case LOCATION_PERMISSION_REQUEST_CODE:

                    enableMyLocation();

                    break;

            }

        } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {


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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
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
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(CustomApplication.getContext());

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(((DashboardActivity) getActivity()), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {

                                srcLat = location.getLatitude();
                                srcLng = location.getLongitude();

                                latLng = new LatLng(location.getLatitude(), location.getLongitude());

                                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 16);
                                mMap.animateCamera(cameraUpdate);


                            }
                        }
                    });


        }

        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                if (currentEditableViewId == edtPickup.getId()) {
                    setUserLocation(mMap.getCameraPosition().target);
                    edtPickup.setText(getCurrentAddress(getUserLocation().latitude, getUserLocation().longitude));
                    //edtPickup.setText("Current Location");
                    pickUpAddress = getCurrentAddress(getUserLocation().latitude, getUserLocation().longitude);
                } else {
                    setDestination(mMap.getCameraPosition().target);
                    edtDestination.setText(getCurrentAddress(getDestinationLatLong().latitude, getDestinationLatLong().longitude));

                }
            }
        });


    }

    public void checkLocationPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {

            enableMyLocation();

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

    public void onSearchCalled() {
        // Set the fields to specify which types of place data to return.
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);
        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields).build(getActivity());
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
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

                if (currentEditableViewId == edtPickup.getId()) {
                    edtPickup.setText(getCurrentAddress(place.getLatLng().latitude, place.getLatLng().longitude));
                    pickUpAddress = getCurrentAddress(place.getLatLng().latitude, place.getLatLng().longitude);
                } else {
                    edtDestination.setText(getCurrentAddress(place.getLatLng().latitude, place.getLatLng().longitude));

                }

                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 16));
                CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                        place.getLatLng(), 15);
                mMap.animateCamera(location);

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

    @OnClick(R.id.btn_done)
    public void onViewClicked(View view) {
        try {

            String splitFromLoc[] = edtPickup.getText().toString().split(",");
            String splitToLoc[] = edtDestination.getText().toString().split(",");

            travelerRequestBundle = new TravelerRequestBundle();
            travelerRequestBundle.setDepFromCountry(splitFromLoc[1]);
            travelerRequestBundle.setDepFromCity(splitFromLoc[0]);
            travelerRequestBundle.setArrvToCountry(splitToLoc[1]);
            travelerRequestBundle.setArrvToCity(splitToLoc[0]);

            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE, travelerRequestBundle);

            replaceChildFragmentWithDelay(new DateSelectionFragment(), true, false, bundle, true);

            /*if (getIntent().getExtras() != null && getIntent().hasExtra(AppConstants.KEY_SENDER)) {
                Intent carSelInt = new Intent(SearchActivity.this, CategoryActivity.class);
                carSelInt.putExtra(KEY_SENDER_NAME, sendItemData);
                carSelInt.putExtra(KEY_SENDER_DESC, dataDescription);
                carSelInt.putExtra(KEY_SENDER_DEL_DATE, dataDate);
                carSelInt.putExtra(KEY_SENDER_WEIGHT, senderWeight);
                carSelInt.putExtra(KEY_SENDER_SIZE, senderSize);
                carSelInt.putExtra(KEY_SENDER_FROM_COUNTRY, splitFromLoc[1]);
                carSelInt.putExtra(KEY_SENDER_FROM_CITY, splitFromLoc[0]);
                carSelInt.putExtra(KEY_SENDER_TO_COUNTRY, splitToLoc[1]);
                carSelInt.putExtra(KEY_SENDER_TO_CITY, splitToLoc[0]);
                carSelInt.putExtra(KEY_IMAGE_1, imageFileName1);
                carSelInt.putExtra(KEY_IMAGE_1_EXTENSION, extension1);
                carSelInt.putExtra(KEY_IMAGE_2, imageFileName2);
                carSelInt.putExtra(KEY_IMAGE_2_EXTENSION, extension2);
                startActivity(carSelInt);

            } else if (getIntent().getExtras() != null && getIntent().hasExtra(AppConstants.KEY_TRAVELER)) {
                Intent carSelInt = new Intent(SearchActivity.this, DateSelectionActivity.class);
                carSelInt.putExtra(KEY_SENDER_FROM_COUNTRY, splitFromLoc[1]);
                carSelInt.putExtra(KEY_SENDER_FROM_CITY, splitFromLoc[0]);
                carSelInt.putExtra(KEY_SENDER_TO_COUNTRY, splitToLoc[1]);
                carSelInt.putExtra(KEY_SENDER_TO_CITY, splitToLoc[0]);
                startActivity(carSelInt);

            }*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        switch (view.getId()) {
            case R.id.edt_pickup:
                if (hasFocus) {
                    currentEditableViewId = edtPickup.getId();
                    isPickUp = true;
                    edtPickup.setBackgroundColor(getResources().getColor(R.color.focus_color));
                    edtPickup.setBackground(ContextCompat.getDrawable(CustomApplication.getContext(), R.drawable.rounded_edittext_focus));

                    edtDestination.setBackgroundColor(getResources().getColor(R.color.unfocus_color));
                    edtDestination.setBackground(ContextCompat.getDrawable(CustomApplication.getContext(), R.drawable.rounded_edittext_unfocus));

                    edtDestination.clearFocus();
                    edtPickup.setSelectAllOnFocus(true);
                    edtPickup.requestFocus();
                    edtPickup.selectAll();
                    imgPosition.setVisibility(View.GONE);
                    onSearchCalled();
                }
                break;
            case R.id.edt_destination:
                if (hasFocus) {
                    isPickUp = false;
                    currentEditableViewId = edtDestination.getId();
                    edtPickup.setBackgroundColor(getResources().getColor(R.color.unfocus_color));
                    edtPickup.setBackground(ContextCompat.getDrawable(CustomApplication.getContext(), R.drawable.rounded_edittext_unfocus));
                    edtDestination.setBackgroundColor(getResources().getColor(R.color.focus_color));
                    edtDestination.setBackground(ContextCompat.getDrawable(CustomApplication.getContext(), R.drawable.rounded_edittext_focus));

                    edtPickup.clearFocus();
                    edtDestination.setSelectAllOnFocus(true);
                    edtDestination.requestFocus();
                    edtDestination.selectAll();
                    //placesFragment.showDestinationLater(true);
                    imgPosition.setVisibility(View.GONE);

                    if (edtPickup.getText().toString().trim().equals("")) {
                        edtPickup.setText(pickUpAddress);
                        //pickUpAddress = getCurrentAddress(userLocation.latitude, userLocation.longitude);
                        //setUserLocation(userLocation);
                    }

                    onSearchCalled();
                }
                break;
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

    public LatLng getUserLocation() {
        if (userLocation != null)
            return userLocation;

        return null;
    }

    public void setUserLocation(LatLng latLng) {
        this.userLocation = latLng;
    }

    public LatLng getDestinationLatLong() {
        if (destination != null)
            return destination;

        return null;
    }

    public void setDestination(LatLng latLng) {
        this.destination = latLng;
    }

}