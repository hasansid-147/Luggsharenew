package com.android.luggshare.presentation.screens.profile.fragments;

import android.widget.EditText;

import com.android.luggshare.R;
import com.android.luggshare.presentation.fragments.CoreFragment;

import butterknife.BindView;

public class ProfileFragmentedit extends CoreFragment {


    @BindView(R.id.edtfname)
    EditText edtfname;

    @BindView(R.id.edtlname)
    EditText edtlname;
    @BindView(R.id.edtemail)
    EditText edtemail;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.edtcntry)
    EditText edtcntry;

    @BindView(R.id.imgProfile)
    com.makeramen.roundedimageview.RoundedImageView imgProfile;



    String userFname,userLname,userEmail;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_user_profile_edit;
    }
}
