package com.android.luggshare.presentation.screens.signup.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.common.keys.AppConstants;
import com.android.luggshare.presentation.activities.CoreActivity;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;

import java.io.File;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SignUpActivity extends CoreActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    EditText edtFname, edtLname, edtEmail, edtPassword, edtPhone, edtVerifyPhone;
    Button btnContinue;
    TextView TextLoginLink;
    private static RelativeLayout rel;
    private static final int GALLERY_CODE = 147;
    private static final int PERMISSION_REQUEST_CODE = 200;
    String imageFileName = "", extension = "";
    com.makeramen.roundedimageview.RoundedImageView imgProfile;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        edtFname = (EditText) findViewById(R.id.edtFname);
        edtLname = (EditText) findViewById(R.id.edtLname);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        imgProfile = (com.makeramen.roundedimageview.RoundedImageView) findViewById(R.id.imgProfile);


        btnContinue = (Button) findViewById(R.id.btnContinue);

        TextLoginLink = (TextView) findViewById(R.id.TextLoginLink);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!errorInFields()) {

                    Intent intent = new Intent(SignUpActivity.this, VerifyPhoneActivity.class);
                    intent.putExtra(AppConstants.KEY_FIRST_NAME, edtFname.getText().toString());
                    intent.putExtra(AppConstants.KEY_LAST_NAME, edtLname.getText().toString());
                    intent.putExtra(AppConstants.KEY_EMAIL, edtEmail.getText().toString());
                    intent.putExtra(AppConstants.KEY_PASSWORD, edtPassword.getText().toString());
                    intent.putExtra(AppConstants.KEY_PHONE, edtPhone.getText().toString());
                    intent.putExtra(AppConstants.KEY_USER_IMAGE, imageFileName);
                    intent.putExtra(AppConstants.KEY_USER_IMAGE_EXTENSION, extension);
                    startActivity(intent);

                }

            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkPermission()) {
                    requestPermissionAndContinue();
                } else {
                    openGallery();
                }
            }
        });


        TextLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToIntent(LoginActivity.class, false);
            }
        });


    }

    private boolean errorInFields() {


        if (edtFname.getText().toString().matches("")) {
            Toast.makeText(SignUpActivity.this, "Please enter valid first name.", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (edtLname.getText().toString().matches("")) {
            Toast.makeText(SignUpActivity.this, "Please enter valid last name.", Toast.LENGTH_SHORT).show();

            return true;
        }

        if (edtEmail.getText().toString().matches("")) {
            Toast.makeText(SignUpActivity.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (!(edtEmail.getText().toString().matches(getResources().getString(R.string.email_pattern_validation)))) {
            Toast.makeText(SignUpActivity.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (edtPassword.getText().toString().matches("")) {
            Toast.makeText(SignUpActivity.this, "Please enter valid password", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (edtPhone.getText().toString().matches("")) {
            Toast.makeText(SignUpActivity.this, "Please enter valid phone", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (edtPhone.getText().toString().isEmpty() || edtPhone.getText().toString().length() < 13) {
            Toast.makeText(SignUpActivity.this, "Please enter valid phone", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (resultCode == RESULT_OK && requestCode == GALLERY_CODE) {
            if (resultCode == RESULT_OK) {

                if (imageReturnedIntent != null) {

                    Uri selectedImage = imageReturnedIntent.getData();

                    imgProfile.setImageURI(selectedImage);

                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getApplicationContext().getContentResolver().query(selectedImage, filePath,
                            null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    String filePathStr = c.getString(columnIndex);
                    c.close();

                    File actualNameFile = new File(filePathStr);
                    String extension = filePathStr.substring(filePathStr.lastIndexOf(".") + 1);

                    Log.d("image_ext", extension + " : length : " + actualNameFile.length());


                    this.imageFileName = filePathStr;
                    this.extension = extension;

                    /*BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(actualNameFile.getAbsolutePath(), bmOptions);
                    //bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
                    imgProfile.setImageBitmap(bitmap);*/


                }

            }
        }


    }

    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission");
                alertBuilder.setMessage("Permission required!");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(SignUpActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(SignUpActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            openGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults.length > 0) {

                boolean flag = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                    }
                }
                if (flag) {
                    openGallery();
                } else {
                    finish();
                }

            } else {
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void openGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, GALLERY_CODE);
    }

}
