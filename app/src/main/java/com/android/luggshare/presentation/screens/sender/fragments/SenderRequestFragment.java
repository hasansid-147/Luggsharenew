package com.android.luggshare.presentation.screens.sender.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.interfaces.onPickDateTimeListener;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.utils.DateTimePicker;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


public class SenderRequestFragment extends CoreFragment implements onPickDateTimeListener {

    ImageView imgThumbnail, imgPackage, imgThumbnail2, imgPackage2;
    EditText edtItemSend, edtItemDescription;
    Button btnSendPackage ;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String imageFileName1 = "", extension1 = "";
    String imageFileName2 = "", extension2 = "";

    @BindView(R.id.tvDepDate)
    TextView tvDepDate;

    @BindView(R.id.tvDepTime)
    TextView tvDepTime;

    boolean firstImage = false, secondImage = false;

    DateTimePicker startDateTimeObj;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_sender_request;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        startDateTimeObj = new DateTimePicker();

        imgThumbnail = (ImageView) rootview.findViewById(R.id.imgThumbnail);
        imgPackage = (ImageView) rootview.findViewById(R.id.imgPackage);
        imgThumbnail2 = (ImageView) rootview.findViewById(R.id.imgThumbnail2);
        imgPackage2 = (ImageView) rootview.findViewById(R.id.imgPackage2);
        edtItemSend = (EditText) rootview.findViewById(R.id.edtItemSend);
        edtItemDescription = (EditText) rootview.findViewById(R.id.edtItemDescription);
        btnSendPackage = (Button) rootview.findViewById(R.id.btnSendPackage);


        imgThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

                    if (Build.VERSION.SDK_INT >= 23) {
                        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    }

                    firstImage = true;
                    secondImage = false;
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, CAMERA_REQUEST);

                }

            }
        });

        imgThumbnail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    /*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

                    if (Build.VERSION.SDK_INT >= 23) {
                        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    }

                    firstImage = false;
                    secondImage = true;
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, CAMERA_REQUEST);
                }
            }
        });


        return rootview;
    }

    @OnClick({R.id.tvDepDate, R.id.tvDepTime, R.id.btnSendPackage})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tvDepDate:

                startDateTimeObj.setListener(this, "departure_date");
                startDateTimeObj.initDatePicker(getActivity(), "departure_date");
                break;

            case R.id.tvDepTime:

                startDateTimeObj.setListener(this, "departure_time");
                startDateTimeObj.initTimePicker(getActivity(), "departure_time");
                break;

            case R.id.btnSendPackage:
                if (edtItemSend.getText().toString().equals("")) {
                    Toast.makeText(CustomApplication.getContext(), "Enter valid item data", Toast.LENGTH_SHORT).show();
                } else if (edtItemDescription.getText().toString().equals("")) {
                    Toast.makeText(CustomApplication.getContext(), "Enter valid item description", Toast.LENGTH_SHORT).show();
                } else {

                    SenderRequestBundle senderRequestBundle = new SenderRequestBundle();
                    senderRequestBundle.setName(edtItemSend.getText().toString());
                    senderRequestBundle.setDescription(edtItemDescription.getText().toString());
                    senderRequestBundle.setDelivery_date(tvDepDate.getText().toString() + " " + tvDepTime.getText().toString());
                    senderRequestBundle.setImg1(imageFileName1);
                    senderRequestBundle.setImg_1_extension(extension1);
                    senderRequestBundle.setImg2(imageFileName2);
                    senderRequestBundle.setImg_2_extension(extension2);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.SENDER_REQUEST_BUNDLE, senderRequestBundle);

                    replaceChildFragmentWithDelay(new SenderRequestSizeFragment(), true, false, bundle, true);

                }
                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(CustomApplication.getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(CustomApplication.getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            /*Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgPackage.setImageBitmap(photo);*/

            String filePathStr = null;

            if (data != null) {


                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = CustomApplication.getContext().getContentResolver().query(selectedImage, filePath,
                        null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                filePathStr = c.getString(columnIndex);
                c.close();

                File actualNameFile = new File(filePathStr);
                String extension = filePathStr.substring(filePathStr.lastIndexOf(".") + 1);

                Log.d("image_ext", extension + " : length : " + actualNameFile.length());

                if (firstImage) {

                    imageFileName1 = filePathStr;
                    extension1 = extension;

                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(actualNameFile.getAbsolutePath(), bmOptions);
                    //bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
                    imgPackage.setImageBitmap(bitmap);

                    firstImage = false;

                } else if (secondImage) {

                    imageFileName2 = filePathStr;
                    extension2 = extension;

                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(actualNameFile.getAbsolutePath(), bmOptions);
                    //bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
                    imgPackage2.setImageBitmap(bitmap);

                    secondImage = false;

                }
            }
        }
    }

    @Override
    public void onDateSelect(String date, String formatedDate, String delegate) {
        if (delegate.equals("departure_date")) {

            if (date != null) {

                tvDepDate.setText(date);
            }

        }
    }

    @Override
    public void onTimeSelect(String time, String formatedTime, String delegate) {
        if (delegate.equals("departure_time")) {
            tvDepTime.setText(time);
        }
    }
}