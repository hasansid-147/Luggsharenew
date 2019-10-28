package com.android.luggshare.presentation.screens.purchaser.fragments;

import android.content.Intent;
import android.os.Bundle;
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

import com.android.luggshare.R;
import com.android.luggshare.business.models.purchasersummary.PurchaserSummaryRequest;
import com.android.luggshare.business.models.purchasersummary.PurchaserSummaryResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.PurchaserRequestBundle;
import com.android.luggshare.common.enums.RequestTypeEnum;
import com.android.luggshare.common.interfaces.onPickDateTimeListener;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.utils.DateTimePicker;
import com.android.luggshare.utils.UiHelper;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class PurchaserPricingFragment extends CoreFragment {

    PurchaserRequestBundle purchaserRequestBundle;

    @BindView(R.id.btnback)
    ImageView btnback;

    @BindView(R.id.txtHeading)
    TextView txtHeading;

    @BindView(R.id.tvServiceCharges )
    TextView tvServiceCharges;

    //@BindView(R.id.txtdeliveryChargescurr)
    // TextView txtdeliveryChargescurr;
    @BindView(R.id.txtServiceChargescurr)
    TextView txtServiceChargescurr;
    @BindView(R.id.edtDeliverychrges)
    EditText edtDeliverychrges;
    @BindView(R.id.edtOk)
    Button edtOk;
    @BindView(R.id.tvItemPrice)
    TextView tvItemPrice;

    private static final String TAG = PurchaserPricingFragment.class.getSimpleName();

    String totprice  ;
    String delvcharges,edtdDelvCharges,tvIP,tvRWD; ;
    String imageFileName1 = "", extension1 = "";
    Double txtHD;
    double estTotal;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_purchaser_pricing;
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

        getPricing();

        return rootview;
    }

    @OnClick({R.id.btnback, R.id.btnSendPackage,R.id.btnedit,R.id.edtOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSendPackage:
                /*Toast.makeText(this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                Intent intTraveler = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intTraveler);*/
                PurchaserRequest();
                break;

            case R.id.btnedit:

               /* Intent intent = new Intent(PricingActivity.this, PricingEdit.class);
                intent.putExtra(KEY_DELVCHARGES_EDT,tvdeliveryCharges.getText().toString());
                startActivity(intent);*/
                edtDeliverychrges.setVisibility(View.VISIBLE);
                edtOk.setVisibility(View.VISIBLE);




                break   ;

            case R.id.edtOk:

                edtdDelvCharges = edtDeliverychrges.getText().toString();
                tvServiceCharges.setText(edtdDelvCharges.toString());
                txtHD = Double.parseDouble(tvIP) + Double.parseDouble(edtdDelvCharges);
                txtHeading.setText(txtHD.toString());
                edtDeliverychrges.setVisibility(View.INVISIBLE);
                edtOk.setVisibility(View.INVISIBLE);

                break;
        }
    }

    private void getPricing() {

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        PurchaserSummaryRequest Summaryequest = new PurchaserSummaryRequest();



        Summaryequest.setProdPrice(Double.parseDouble(purchaserRequestBundle.getPrice()));
        Summaryequest.setQunatity(Integer.parseInt(purchaserRequestBundle.getQuantity()));
        Summaryequest.setCurrency("PKR");

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PurchaserSummaryResponse> call = apiService.getpurchaserPricing(Summaryequest);
        call.enqueue(new Callback<PurchaserSummaryResponse>() {
            @Override
            public void onResponse(Call<PurchaserSummaryResponse> call, Response<PurchaserSummaryResponse> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());

                if (response.body() != null) {

                    tvIP = response.body().getTotalPrice().toString();
                    tvRWD = response.body().getReward().toString();
                    estTotal = Double.parseDouble(tvIP) + Double.parseDouble(tvRWD);


                    txtHeading.setText("PKR " + response.body().getEstimatedTotal().toString());
                    tvServiceCharges.setText(response.body().getReward().toString());
                    tvItemPrice.setText(response.body().getTotalPrice().toString());



                }


                UiHelper.getInstance().hideLoadingIndicator();

            }

            @Override
            public void onFailure(Call<PurchaserSummaryResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });


    }



    private void PurchaserRequest() {

        UiHelper.getInstance().showLoadingIndicator(getActivity());
        totprice =  tvItemPrice.getText().toString();
        delvcharges =  tvServiceCharges.getText().toString();

        /*SenderRequest senderRequest = new SenderRequest();

        int sizeValue = 0;

        if (senderSize.equals("Fits in a bag")) {
            sizeValue = 1;
        } else if (senderSize.equals("Fits in hand carry")) {
            sizeValue = 2;
        } else if (senderSize.equals("Fits in suitcase")) {
            sizeValue = 3;
        }

        HashMap<String, String> userData = session.getUserDetails();
        String uid = userData.get(KEY_UID);

        senderRequest.setUid(Integer.parseInt(uid));
        senderRequest.setReqTyp("Sender");
        senderRequest.setImage(null);
        senderRequest.setName(sendItemData);
        senderRequest.setDescription(dataDescription);
        senderRequest.setWeight(Double.parseDouble(senderWeight));
        senderRequest.setSize(senderSize);
        senderRequest.setFromCity(senderFromCity);
        senderRequest.setFromCountry(senderFromCountry);
        senderRequest.setToCity(senderToCity);
        senderRequest.setToCountry(senderToCountry);
        senderRequest.setDelDate(dataDate);
        senderRequest.setItemCat1(item1);
        senderRequest.setItemCat2(item2);
        senderRequest.setItemCat3(item3);
        senderRequest.setItemCat4(item4);
        senderRequest.setItemCat5(item5);
        senderRequest.setItemCat6(item6);*/

        File file = new File(purchaserRequestBundle.getImg1());
        // Parsing any Media type file
        //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/png"), file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/" + purchaserRequestBundle.getImg1() + ""), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("files", file.getName(), requestBody);



        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part imagenPerfil = MultipartBody.Part.createFormData("img1", file.getName(), requestFile);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        //Call<Object> call = apiService.senderRequest(senderRequest);
        // RequestBody Data
        RequestBody requestUid = RequestBody.create(MediaType.parse("multipart/form-data"), PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "");
        RequestBody requestpurch = RequestBody.create(MediaType.parse("multipart/form-data"), RequestTypeEnum.PURCHASER.getReqType());
        RequestBody reqpurchurl = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getUrl());
        RequestBody requestpurchproddetail = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getProd_detail());
        RequestBody requestpurchname = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getProd_name());
        RequestBody requestpurchprice = RequestBody.create(MediaType.parse("multipart/form-data"), totprice);
        RequestBody requestpurchquant = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getQuantity());
        RequestBody requestpurchFromCountry = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getFrom_country());
        RequestBody requestpurchFromCity = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getFrom_city());
        RequestBody requestpurchToCountry = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getTo_country());
        RequestBody requestpurchToCity = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getTo_city());
        RequestBody requestpurchdelDate = RequestBody.create(MediaType.parse("multipart/form-data"), purchaserRequestBundle.getDel_date());

        RequestBody requestpurchbringerrwd = RequestBody.create(MediaType.parse("multipart/form-data"), delvcharges);


        Call<Object> call = apiService.PurchaserRequest(
                requestUid,
                requestpurch,
                reqpurchurl,
                requestpurchproddetail,
                requestpurchname,
                requestpurchprice,
                requestpurchquant,
                requestpurchFromCountry,
                requestpurchFromCity,
                requestpurchToCountry,
                requestpurchToCity,
                requestpurchdelDate,
                requestpurchbringerrwd,
                imagenPerfil);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());

                UiHelper.getInstance().hideLoadingIndicator();

                if (response.isSuccessful()) {
                    //Toast.makeText(PricingActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new HomeFragment(), false, false, null, false);
                    /*Intent intTraveler = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                    startActivity(intTraveler);*/
                } else {
                    //Toast.makeText(PricingActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                    Log.e("Error", response.errorBody().toString());
                }

                /*if (response != null) {
                    Toast.makeText(PricingActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intTraveler = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                    startActivity(intTraveler);
                } else {
                    Toast.makeText(PricingActivity.this, "Some error occurred.", Toast.LENGTH_SHORT).show();
                    Log.e("Error", response.errorBody().toString());
                }*/

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });


    }

}