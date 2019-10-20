package com.android.luggshare.presentation.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.luggshare.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MasterActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int LOCATION_PERMISSION_REQUEST_CODE = 858;
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 100;

    protected GoogleMap mMap;
    public LatLng userLocation;
    public LatLng destination;
    public FusedLocationProviderClient mFusedLocationClient;


    public static final String TAG = MasterActivity.class.getSimpleName();

    private boolean isOnCreateAlreadyCalled = false;

    private ProgressDialog pd;
    private AlertDialog alert;

    private int LocationResultCode = 1001;
    private int WifiResultCode = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (!isOnCreateAlreadyCalled) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            isOnCreateAlreadyCalled = true;
            //register a Broadcast Listener
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void dismissProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void showProgressDialog(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                        pd = new ProgressDialog(MasterActivity.this, R.style.ProgressDialogKitkat);
                    } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        pd = new ProgressDialog(MasterActivity.this);
                    }

                    pd.setMessage(msg);
                    pd.setCancelable(false);
                    pd.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMaxZoomPreference(20);




        /*try {
            if (checkPermission()) {
                ((DashboardActivity) this).containerLocation.setVisibility(View.GONE);
                isConnected(getApplicationContext());
                onLocationPermissionGranted();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            //Ask for the permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            Toast.makeText(this, "Please give location permission", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    protected String getCurrentAddress(double lat, double lng) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.ENGLISH);
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

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void onLocationPermissionGranted() {
        if (checkPermission()) {

            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mMap.setMyLocationEnabled(true);
            moveToUserLocation(R.id.edt_pickup);
        }
    }

    public void setBlueIconOnMap(boolean set) {
        if (checkPermission()) {
            if (set) {
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.setMyLocationEnabled(true);
            } else {
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mMap.setMyLocationEnabled(false);
            }
        }
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    protected void moveToUserLocation(final int currentFoucusID) {
        if (!checkPermission()) return;
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {
                            if (currentFoucusID == R.id.edt_pickup) {
                                userLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                CameraPosition cameraPosition = new CameraPosition.Builder()
                                        .target(userLocation)
                                        .zoom(15)
                                        .build();

                                //addOverlay(userLocation);
                                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                //getDriversWithDelay(10000);
                            } else {

                                destination = new LatLng(location.getLatitude(), location.getLongitude());
                                CameraPosition cameraPosition = new CameraPosition.Builder()
                                        .target(destination)
                                        .zoom(15)
                                        .build();

                                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                            }

                        } else {
                            userLocation = null;
                            if (checkPermission()) {
                                moveToUserLocation(currentFoucusID);
                            }
                        }
                    }
                });
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfoMob = cm.getNetworkInfo(cm.TYPE_MOBILE);
        NetworkInfo netInfoWifi = cm.getNetworkInfo(cm.TYPE_WIFI);
        if (netInfoMob != null && netInfoMob.isConnectedOrConnecting()) {
            Log.v("TAG", "Mobile Internet connected");
            turnLocationOn(null);
            return true;
        }
        if (netInfoWifi != null && netInfoWifi.isConnectedOrConnecting()) {
            Log.v("TAG", "Wifi Internet connected");
            turnLocationOn(null);
            return true;
        }
        buildAlertMessageNoGps();
        return false;

    }

    public void turnLocationOn(GoogleApiClient apiClient) {
        if (apiClient == null) {
            apiClient = new GoogleApiClient.Builder(getApplicationContext())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(@Nullable Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {

                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        }
                    }).build();
            apiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            //**************************
            builder.setAlwaysShow(true); //this is the key ingredient
            //**************************

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(apiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result.getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            if (checkPermission()) {
                                onLocationPermissionGranted();
                            }
                            // All location settings are satisfied. The client can initialize location
                            // requests here.
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(
                                        MasterActivity.this, LocationResultCode);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        }
    }

    @SuppressLint("RestrictedApi")
    private void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MasterActivity.this, R.style.myDialog));
        builder
                .setMessage("No Network found  Do you want to enable Wifi ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                        Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(i, WifiResultCode);
                        dialog.dismiss();

                        // startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),LocationResultCode);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.dismiss();
                        dialog.cancel();
                        finish();

                    }
                });
        if (alert == null) {
            alert = builder.create();

        }

        if (!alert.isShowing())
            alert.show();

    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels,
                displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(),
                view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
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

    public void clearGoogleMap() {
        mMap.clear();
    }

    public void checkLocationEnabled() {

        LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
            dialog.setMessage("Location services not enabled. Please enable it.");
            dialog.setPositiveButton("Open locations", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getApplicationContext().startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }

    }

}

