package com.android.luggshare.presentation.screens.profile.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.luggshare.R;
import com.android.luggshare.business.models.userprofile.EmailVerification;
import com.android.luggshare.business.models.userprofile.EmailVerificationResponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.EditUserProfileBundle;
import com.android.luggshare.common.bundle.ViewUserPhoneBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.UiHelper;

import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class ViewPhoneFragment extends CoreFragment {


   @BindView(R.id.edtemail)
   EditText edtemail;



    ViewUserPhoneBundle viewUserPhoneBundle ;
    private static final String TAG = ViewPhoneFragment.class.getSimpleName();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_view_phone;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            viewUserPhoneBundle = (ViewUserPhoneBundle) getArguments().getSerializable(BundleKeys.VIEW_USER_PHONE);
        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);
        edtemail.setText(viewUserPhoneBundle.getPhone());
        edtemail.setEnabled(false);

        return rootview;
    }





}
