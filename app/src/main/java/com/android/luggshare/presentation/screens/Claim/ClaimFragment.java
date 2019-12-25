package com.android.luggshare.presentation.screens.Claim;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.addcard.AddCardRequest;
import com.android.luggshare.business.models.addcard.AddCardResponse;
import com.android.luggshare.business.models.claim.ClaimRequest;
import com.android.luggshare.business.models.tracking.GetTrackList;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.ClaimBundle;
import com.android.luggshare.common.bundle.TrackingBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;



public class ClaimFragment extends CoreFragment {
    ClaimBundle claimBundle;

    @BindView(R.id.edtCardName)
    EditText edtCardName;

    @BindView(R.id.edtCardNo)
    EditText edtCardNo;

    @BindView(R.id.edtSwift)
    EditText edtSwift;

    @BindView(R.id.textArea_information)
    EditText textArea_information;

    @BindView(R.id.btnAdd)
    Button btnAdd;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_add_claim;
    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            claimBundle = (ClaimBundle) getArguments().getSerializable(BundleKeys.CLAIM);
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        edtCardName.setText(claimBundle.getItem().toString());
        edtCardNo.setText(claimBundle.getDate().toString());





        return rootview;
    }

    @OnClick(R.id.btnAdd)
    public void onAddCardClicked() {


        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        ClaimRequest claim = new ClaimRequest();

        claim.setUsername("");
        claim.setItemname(claimBundle.getItem().toString());
        claim.setRequesttype("Delivery");
        claim.setOfferid(claimBundle.getOfferid());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<String> call = apiService.raiseClaim(claim);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {
                    //onAcceptOfferClicked();
                    Toast.makeText(getContext(), "Successfull", Toast.LENGTH_SHORT).show();
                    replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }




}
