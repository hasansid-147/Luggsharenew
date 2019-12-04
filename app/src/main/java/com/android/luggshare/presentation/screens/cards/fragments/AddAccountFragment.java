package com.android.luggshare.presentation.screens.cards.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.addaccount.AddAccountRequest;
import com.android.luggshare.business.models.addaccount.AddAccountResponse;
import com.android.luggshare.business.models.addcard.AddCardRequest;
import com.android.luggshare.business.models.addcard.AddCardResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.DateTimePicker;
import com.android.luggshare.utils.UiHelper;


import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class AddAccountFragment extends CoreFragment {
    DateTimePicker expiryDateObj;

    @BindView(R.id.edtCardName)
    EditText edtCardName;

    @BindView(R.id.edtCardNo)
    EditText edtCardNo;

    @BindView(R.id.edtSwift)
    EditText edtSwift;

    @BindView(R.id.edtCVV)
    EditText edtCVV;



    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_add_accounts;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

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

        AddAccountRequest login = new AddAccountRequest();

        login.setUid(PreferenceManager.getInstance().getInt(KEY_CUSTOMER_ID));
        login.setAccNumber(edtCardNo.getText().toString());
        login.setAcctHolderName(edtCardName.getText().toString());
        login.setSwiftCode(edtSwift.getText().toString());
        login.setCurrency("PKR");
        login.setIsvalid(1);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<AddAccountResponse> call = apiService.addAccount(login);
        call.enqueue(new Callback<AddAccountResponse>() {
            @Override
            public void onResponse(Call<AddAccountResponse> call, Response<AddAccountResponse> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();




                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<AddAccountResponse> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    private boolean errorInFields() {

        boolean hasError = true;

        if (edtCardName.getText().toString().isEmpty()){
            Toast.makeText(CustomApplication.getContext(), "Please enter Account holder name.", Toast.LENGTH_SHORT).show();
            hasError = false;
        }

        if (edtCardNo.getText().toString().isEmpty()){
            Toast.makeText(CustomApplication.getContext(), "Please enter account number.", Toast.LENGTH_SHORT).show();
            hasError = false;
        }




        return hasError;

    }
}
