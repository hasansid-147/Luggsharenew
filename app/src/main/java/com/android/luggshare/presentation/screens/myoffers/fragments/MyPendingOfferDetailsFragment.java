package com.android.luggshare.presentation.screens.myoffers.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.UpdateOfferStatus.UpdateOfferStatus;
import com.android.luggshare.business.models.UpdateOfferStatus.UpdateOfferStatusResponse;
import com.android.luggshare.business.models.acceptoffer.OfferAcceptResponse;
import com.android.luggshare.business.models.editoffer.EditOfferReponse;
import com.android.luggshare.business.models.editoffer.EditOfferRequest;
import com.android.luggshare.business.models.traveleroffer.TravelerOfferResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.EditUserProfileBundle;
import com.android.luggshare.common.bundle.GetUserProfileBundle;
import com.android.luggshare.common.bundle.PendingOfferBundle;
import com.android.luggshare.common.bundle.ReceivedOfferBundle;
import com.android.luggshare.common.constants.IsPreferenceProfile;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.dashboard.fragments.home.HomeFragment;
import com.android.luggshare.presentation.screens.profile.fragments.ProfileFragment;
import com.android.luggshare.utils.UiHelper;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPendingOfferDetailsFragment extends CoreFragment {


    @BindView(R.id.tvUserName)
    TextView tvUserName;


    @BindView(R.id.tvItemname)
    TextView tvItemname;

    @BindView(R.id.ItemPrice)
    TextView ItemPrice;

    @BindView(R.id.tvTravelingFrom)
    TextView tvTravelingFrom;

    @BindView(R.id.tvTravelingTo)
    TextView tvTravelingTo;

    @BindView(R.id.tvUrl)
    TextView tvUrl;
    @BindView(R.id.tvDeliveryDate)
    TextView tvDeliveryDate;

    @BindView(R.id.tvOffer)
    TextView tvOffer;

    @BindView(R.id.btnAcceptOffer)
    Button btnAcceptOffer;

    @BindView(R.id.btnUpdtStatus)
    Button btnUpdtStatus;

    @BindView(R.id.spinner1)
    Spinner spinner1;


    @BindView(R.id.tvOfferstatus)
    TextView tvOfferstatus;
    @BindView(R.id.btnRate)
    Button btnRate;


    @BindView(R.id.btnedit)
    ImageView btnedit;
    String TAG = "DeliveryConfirmation";


    GetUserProfileBundle getUserProfileBundle;
    PendingOfferBundle pendingOfferBundle;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_pending_offer_details;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            pendingOfferBundle = (PendingOfferBundle) getArguments().getSerializable(BundleKeys.PENDING_OFFER_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);
        String[] status = new String[]{"Collected", "Completed"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_item,status);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner1.setAdapter(spinnerArrayAdapter);
        spinner1.setPrompt(pendingOfferBundle.getRequestObj().getOfferStatus());

        initDetails(pendingOfferBundle);

        if(pendingOfferBundle.getRequestObj().getOfferStatus().equalsIgnoreCase("COMPLETED")){

            btnRate.setVisibility(View.VISIBLE);
            btnRate.setEnabled(true);

        }
        else {
            btnRate.setVisibility(View.GONE);
            btnRate.setEnabled(false);
        }


        return rootview;
    }


    private void initDetails(PendingOfferBundle resp) {

        tvUserName.setText(resp.getRequestObj().getRcvName());
        tvItemname.setText(resp.getRequestObj().getRcvPackagName());
        ItemPrice.setText(resp.getRequestObj().getItemPrice().toString());
        tvTravelingFrom.setText(resp.getRequestObj().getRcvFromloc());
        tvTravelingTo.setText(resp.getRequestObj().getRcvToloc());
        tvUrl.setText(resp.getRequestObj().getuRL());
        tvOffer.setText(resp.getRequestObj().getOfferPrice().toString());
        tvDeliveryDate.setText(resp.getRequestObj().getRcvDelvdate());
        tvOfferstatus.setText(resp.getRequestObj().getOfferStatus());



        if (!"CREATED".equalsIgnoreCase(resp.getRequestObj().getOfferStatus())){
            btnedit.setVisibility(View.INVISIBLE);
            btnedit.setEnabled(false);

        }

        if ("CREATED".equalsIgnoreCase(resp.getRequestObj().getOfferStatus())){

            spinner1.setVisibility(View.GONE);


            btnUpdtStatus.setVisibility(View.INVISIBLE);
            btnUpdtStatus.setEnabled(false);

        }



    }

    @OnClick(R.id.btnUpdtStatus)
    public void onUpdateStatus() {


        String spinnervalue = spinner1.getSelectedItem().toString();

        if (spinnervalue.equals("Collected")) {

            updateOffer(null, null);


        } else if (spinnervalue.equals("Completed")) {

            final Dialog offerDialog = new Dialog(getContext());
            offerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            offerDialog.setCancelable(false);
            offerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            offerDialog.setContentView(R.layout.dialog_confirm_offer);

            ImageView dialogButton = (ImageView) offerDialog.findViewById(R.id.imgClose);
            final EditText edtOffer = (EditText) offerDialog.findViewById(R.id.edtOffer);
            Button btnSubmit = (Button) offerDialog.findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (edtOffer.getText().toString().isEmpty()) {

                        return;
                    }

                    updateOffer(Integer.parseInt(edtOffer.getText().toString()), offerDialog);

                }

            });
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    offerDialog.dismiss();
                }
            });


            offerDialog.show();


        }
    }

    @OnClick(R.id.btnedit)
    public void onEditOffer(){

        final Dialog offerDialog = new Dialog(getContext());
        offerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        offerDialog.setCancelable(false);
        offerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        offerDialog.setContentView(R.layout.dialog_make_offer);

        ImageView dialogButton = (ImageView) offerDialog.findViewById(R.id.imgClose);
        final EditText edtOffer = (EditText) offerDialog.findViewById(R.id.edtOffer);
        Button btnSubmit = (Button) offerDialog.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtOffer.getText().toString().isEmpty()) {

                    return;
                }

                editOffer(Integer.parseInt(edtOffer.getText().toString()), offerDialog);

            }

        });
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offerDialog.dismiss();
            }
        });


        offerDialog.show();

    }


    private void updateOffer(Integer Delcode,Dialog offerdialog) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        UpdateOfferStatus update = new UpdateOfferStatus();

        update.setOfferid(pendingOfferBundle.getRequestObj().getOfferid());
        update.setDelcode(Delcode);
        update.setStatus(spinner1.getSelectedItem().toString());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UpdateOfferStatusResponse> call = apiService.deliveryConfirm(update);
        call.enqueue(new Callback<UpdateOfferStatusResponse>() {
            @Override
            public void onResponse(Call<UpdateOfferStatusResponse> call, Response<UpdateOfferStatusResponse> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());


                UiHelper.getInstance().hideLoadingIndicator();

                if (response.isSuccessful()) {


                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if(offerdialog != null){
                    offerdialog.dismiss();}
                    //replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UpdateOfferStatusResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });




    }




    private void editOffer(Integer price,Dialog offerdialog) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        EditOfferRequest update = new EditOfferRequest();

        update.setOfferid(pendingOfferBundle.getRequestObj().getOfferid());
        update.setFromReqid(pendingOfferBundle.getRequestObj().getFromReqid());
        update.setToReqid(pendingOfferBundle.getRequestObj().getRcvReqid());
        update.setPrice(price);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<EditOfferReponse> call = apiService.editOffer(update);
        call.enqueue(new Callback<EditOfferReponse>() {
            @Override
            public void onResponse(Call<EditOfferReponse> call, Response<EditOfferReponse> response) {
                Log.d(TAG, "issuccessfull: " + response.isSuccessful());
                Log.d(TAG, "Status" + response.toString());
                Log.d(TAG, "RESPONSE:" + response.body());


                UiHelper.getInstance().hideLoadingIndicator();

                if (response.isSuccessful()) {


                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    offerdialog.dismiss();
                    navigateBackFragment();
                    //replaceChildFragmentWithDelay(new HomeFragment(), true, false, null, false);
                } else {
                    Toast.makeText(getContext(), getString(R.string.error_something_went_wrong), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EditOfferReponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                UiHelper.getInstance().hideLoadingIndicator();
            }
        });




    }

    @OnClick(R.id.rlUsername)
    public void LoadUserProfile() {

        IsPreferenceProfile isprefuser = IsPreferenceProfile.getInstance();
        isprefuser.setData(false);
        getUserProfileBundle = new GetUserProfileBundle();
        getUserProfileBundle.setUid(pendingOfferBundle.getRequestObj().getRcvId());
        getUserProfileBundle.setEmail(null);

        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleKeys.GET_USER_PROFILE, getUserProfileBundle);
        replaceChildFragmentWithDelay(new ProfileFragment(), true, false, bundle, true);

    }
}
