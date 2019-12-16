package com.android.luggshare.presentation.screens.sender.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.business.models.sendersummary.SenderSummaryRequest;
import com.android.luggshare.business.models.sendersummary.SenderSummaryResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.enums.RequestTypeEnum;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
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


public class PricingFragment extends CoreFragment {

    SenderRequestBundle senderRequestBundle;

    @BindView(R.id.txtHeading)
    TextView txtHeading;

    @BindView(R.id.tvServiceCharges )
    TextView tvServiceCharges;
    @BindView(R.id.tvdeliveryCharges )
    TextView tvdeliveryCharges;
    @BindView(R.id.txtdeliveryChargescurr)
    TextView txtdeliveryChargescurr;
    @BindView(R.id.txtServiceChargescurr)
    TextView txtServiceChargescurr;
    @BindView(R.id.edtDeliverychrges)
    EditText edtDeliverychrges;

    @BindView(R.id.btnedit)
    ImageView btnedit;

    @BindView(R.id.edtOk)
    Button edtOk;

    String delvcharges, edtdDelvCharges, tvSC,tvDC ;
    String servicefee ;
    Double txtHD;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_sender_pricing;
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

        getPricing();


        return rootview;
    }

    @OnClick({R.id.btnSendPackage,R.id.btnedit,R.id.edtOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnSendPackage:
                /*Toast.makeText(this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                Intent intTraveler = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intTraveler);*/
                senderRequest();
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
                tvdeliveryCharges.setText(edtdDelvCharges.toString());
                txtHD = Double.parseDouble(tvSC) + Double.parseDouble(edtdDelvCharges);
                txtHeading.setText(txtHD.toString());
                edtDeliverychrges.setVisibility(View.INVISIBLE);
                edtOk.setVisibility(View.INVISIBLE);

                break;
        }
    }

    private void getPricing() {

        UiHelper.getInstance().showLoadingIndicator(getContext());

        SenderSummaryRequest senderRequest = new SenderSummaryRequest();

        int sizeValue = 0;

        if (senderRequestBundle.getSize().equals("Fits in a bag")) {
            sizeValue = 1;
        } else if (senderRequestBundle.getSize().equals("Fits in hand carry")) {
            sizeValue = 2;
        } else if (senderRequestBundle.getSize().equals("Fits in suitcase")) {
            sizeValue = 3;
        }

        senderRequest.setProdWeight(senderRequestBundle.getWeight());
        senderRequest.setSize(sizeValue);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<SenderSummaryResponse> call = apiService.getPricing(senderRequest);
        call.enqueue(new Callback<SenderSummaryResponse>() {
            @Override
            public void onResponse(Call<SenderSummaryResponse> call, Response<SenderSummaryResponse> response) {
                Log.d("response", "issuccessfull: " + response.isSuccessful());
                Log.d("response", "Status" + response.toString());
                Log.d("response", "RESPONSE:" + response.body());

                if (response.body() != null) {
                    tvSC = response.body().getServiceFee().toString();
                    tvDC = response.body().getDeliveryReward().toString();
                    txtHeading.setText("PKR " + response.body().getTotCost().toString());
                    tvServiceCharges.setText(response.body().getServiceFee().toString());
                    tvdeliveryCharges.setText(response.body().getDeliveryReward().toString());
                    edtDeliverychrges.setText(response.body().getDeliveryReward().toString());
                } else {
                    tvSC = "0.0";
                    tvDC = "0.0";
                }


                UiHelper.getInstance().hideLoadingIndicator();

            }

            @Override
            public void onFailure(Call<SenderSummaryResponse> call, Throwable t) {
                Log.e("response", t.toString());
                tvSC = "0.0";
                tvDC = "0.0";
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });


    }

    private void senderRequest() {

        UiHelper.getInstance().showLoadingIndicator(getContext());
        delvcharges =  tvdeliveryCharges.getText().toString();
        servicefee =  tvServiceCharges.getText().toString();

        File file = new File(senderRequestBundle.getImg1());
        // Parsing any Media type file
        //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/png"), file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/" + senderRequestBundle.getImg_1_extension() + ""), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("files", file.getName(), requestBody);

        File file2 = new File(senderRequestBundle.getImg2());
        // Parsing any Media type file
        //RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/png"), file);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/" + senderRequestBundle.getImg_2_extension() + ""), file2);
        MultipartBody.Part fileToUpload2 = MultipartBody.Part.createFormData("files", file2.getName(), requestBody2);

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part imagenPerfil = MultipartBody.Part.createFormData("img1", file.getName(), requestFile);

        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part imagenPerfil2 = MultipartBody.Part.createFormData("img2", file2.getName(), requestFile2);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        //Call<Object> call = apiService.senderRequest(senderRequest);
        // RequestBody Data
        RequestBody requestUid = RequestBody.create(MediaType.parse("multipart/form-data"), PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "");
        RequestBody requestSender = RequestBody.create(MediaType.parse("multipart/form-data"), RequestTypeEnum.SENDER.getReqType());
        RequestBody requestImage = RequestBody.create(MediaType.parse("multipart/form-data"), "");
        RequestBody requestsendItemData = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getName());
        RequestBody requestdataDescription = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getDescription());
        RequestBody requestsenderWeight = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getWeight() + "");
        RequestBody requestsenderSize = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getSize() + "");
        RequestBody requestsenderFromCountry = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getFrom_country());
        RequestBody requestsenderFromCity = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getFrom_city());
        RequestBody requestsenderToCountry = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getTo_country());
        RequestBody requestsenderToCity = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getTo_city());
        RequestBody requestdataDate = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getDelivery_date());
        RequestBody requestitem1 = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getItem_1());
        RequestBody requestitem2 = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getItem_2());
        RequestBody requestitem3 = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getItem_3());
        RequestBody requestitem4 = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getItem_4());
        RequestBody requestitem5 = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getItem_5());
        RequestBody requestitem6 = RequestBody.create(MediaType.parse("multipart/form-data"), senderRequestBundle.getItem_6());
        RequestBody requestbringerrwd = RequestBody.create(MediaType.parse("multipart/form-data"), delvcharges);
        RequestBody requestservicefee = RequestBody.create(MediaType.parse("multipart/form-data"), servicefee);

        Call<Object> call = apiService.senderRequestNew(
                requestUid,
                requestSender,
                requestImage,
                requestsendItemData,
                requestdataDescription,
                requestsenderWeight,
                requestsenderSize,
                requestsenderFromCountry,
                requestsenderFromCity,
                requestsenderToCountry,
                requestsenderToCity,
                requestdataDate,
                requestbringerrwd,
                requestservicefee,
                requestitem1,
                requestitem2,
                requestitem3,
                requestitem4,
                requestitem5,
                requestitem6,
                imagenPerfil,
                imagenPerfil2);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("response", "issuccessfull: " + response.isSuccessful());
                Log.d("response", "Status" + response.toString());
                Log.d("response", "RESPONSE:" + response.body());

                UiHelper.getInstance().hideLoadingIndicator();

                if (response.isSuccessful()) {

                    Toast.makeText(getContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();

                    replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
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
                Log.e("fail", t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });


    }

}