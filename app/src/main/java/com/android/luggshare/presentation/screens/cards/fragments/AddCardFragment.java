package com.android.luggshare.presentation.screens.cards.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.luggshare.R;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptRequest;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptResponse;
import com.android.luggshare.business.models.addcard.AddCardRequest;
import com.android.luggshare.business.models.addcard.AddCardResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.utils.DateTimePicker;
import com.android.luggshare.utils.UiHelper;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;


public class AddCardFragment extends CoreFragment {

    @BindView(R.id.edtCardName)
    EditText edtCardName;

    @BindView(R.id.edtCardNo)
    EditText edtCardNo;

    @BindView(R.id.edtExpDatet)
    EditText edtExpDatet;

    @BindView(R.id.edtCVV)
    EditText edtCVV;

    @BindView(R.id.btnAdd)
    Button btnAdd;

    DateTimePicker expiryDateObj;

    ReceivedOfferBundle receivedOfferBundle;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_add_card;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            receivedOfferBundle = (ReceivedOfferBundle) getArguments().getSerializable(BundleKeys.RECEIVED_OFFER_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        expiryDateObj = new DateTimePicker();

        return rootview;
    }

    @OnClick(R.id.btnAdd)
    public void onAddCardClicked() {

        if (!errorInFields()) {

        }

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        AddCardRequest login = new AddCardRequest();

        login.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID) + "");
        login.setCreditCard(edtCardNo.getText().toString());
        login.setCardName(edtCardName.getText().toString());
        login.setExpiryDate(edtExpDatet.getText().toString());
        login.setBillAddress("XYZ");
        login.setCity("Karachi");
        login.setCvv(edtCVV.getText().toString());
        login.setIsvalid(1);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<AddCardResponse> call = apiService.addCard(login);
        call.enqueue(new Callback<AddCardResponse>() {
            @Override
            public void onResponse(Call<AddCardResponse> call, Response<AddCardResponse> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    //onAcceptOfferClicked();
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddCardResponse> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    private boolean errorInFields() {

        boolean hasError = true;

        if (edtCardName.getText().toString().isEmpty()){
            Toast.makeText(CustomApplication.getContext(), "Please enter valid card name.", Toast.LENGTH_SHORT).show();
            hasError = false;
        }

        if (edtCardNo.getText().toString().isEmpty()){
            Toast.makeText(CustomApplication.getContext(), "Please enter valid card number.", Toast.LENGTH_SHORT).show();
            hasError = false;
        }

        if (edtExpDatet.getText().toString().isEmpty()){
            Toast.makeText(CustomApplication.getContext(), "Please enter valid expiry date.", Toast.LENGTH_SHORT).show();
            hasError = false;
        }

        if (edtCVV.getText().toString().isEmpty()){
            Toast.makeText(CustomApplication.getContext(), "Please enter valid CVV.", Toast.LENGTH_SHORT).show();
            hasError = false;
        }

        return hasError;

    }

    public void onAcceptOfferClicked() {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        OfferAcceptRequest login = new OfferAcceptRequest();

        login.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        login.setTravelerUid(receivedOfferBundle.getRequestObj().getTrvId());
        login.setSendPurchaserreqid(receivedOfferBundle.getRequestObj().getSenderPurchaserid());
        login.setTravelerreqid(receivedOfferBundle.getRequestObj().getTrvReqId());
        login.setReqtype(receivedOfferBundle.getRequestObj().getRequest_type());
        login.setStatus("ACCEPTED");

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<OfferAcceptResponse> call = apiService.acceptOffer(login);
        call.enqueue(new Callback<OfferAcceptResponse>() {
            @Override
            public void onResponse(Call<OfferAcceptResponse> call, Response<OfferAcceptResponse> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OfferAcceptResponse> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

}