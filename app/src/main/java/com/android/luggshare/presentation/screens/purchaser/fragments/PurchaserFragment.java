package com.android.luggshare.presentation.screens.purchaser.fragments;

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
import com.android.luggshare.common.bundle.PurchaserRequestBundle;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.interfaces.onPickDateTimeListener;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.sender.fragments.SenderRequestSizeFragment;
import com.android.luggshare.presentation.screens.traveler.fragments.TravelerListingDetailFragment;
import com.android.luggshare.utils.DateTimePicker;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;


public class PurchaserFragment extends CoreFragment {

    PurchaserRequestBundle purchaserRequestBundle;

    @BindView(R.id.tvQuantity)
    TextView tvQuantity;

    ImageView imgThumbnail, imgPackage, imgPlus, imgMinus;
    Button btnSendPackage;
    //ABULHASAN codeadd  -START
    EditText edtItemUrl, edtItemProduct, edtProductDetails, edtProductPrice;

    boolean firstImage = false, secondImage = false;

    int minteger = 0;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String imageFileName1 = "", extension1 = "";

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_purchaser;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootview = super.onCreateView(inflater, container, savedInstanceState);

        purchaserRequestBundle = new PurchaserRequestBundle();

        imgThumbnail = (ImageView) rootview.findViewById(R.id.imgThumbnail);
        imgPackage = (ImageView) rootview.findViewById(R.id.imgPackage);
        btnSendPackage = (Button) rootview.findViewById(R.id.btnSendPackage);
        //ABULHASAN codeadd -START
        imgPlus = (ImageView) rootview.findViewById(R.id.imgPlus);
        imgMinus = (ImageView) rootview.findViewById(R.id.imgMinus);
        edtItemUrl = (EditText) rootview.findViewById(R.id.edtItemUrl);
        edtItemProduct = (EditText) rootview.findViewById(R.id.edtItemProduct);
        edtProductDetails = (EditText) rootview.findViewById(R.id.edtProductDetails);
        edtProductPrice = (EditText) rootview.findViewById(R.id.edtProductPrice);
        tvQuantity = (TextView) rootview.findViewById(R.id.tvQuantity);

        //ABULHASAN codeadd INC / DEC  -START


        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        imgThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                   /* Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);*/

                    if (Build.VERSION.SDK_INT >= 23) {
                        int permissionCheck = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        }
                    }

                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, CAMERA_REQUEST);

                }
            }
        });

        return rootview;
    }

    @OnClick({R.id.btnSendPackage, R.id.imgPlus, R.id.imgMinus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
/*            case R.id.btnback:
                onBackPressed();
                break;*/
            //ABULHASAN codeadd -START
            case R.id.imgPlus:
                minteger = minteger + 1;
                display(minteger);
                break;
            case R.id.imgMinus:
                minteger = minteger - 1;
                display(minteger);
                break;
            case R.id.btnSendPackage:

                if (edtItemUrl.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter valid item URL", Toast.LENGTH_SHORT).show();
                } else if (edtItemProduct.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter valid item name", Toast.LENGTH_SHORT).show();
                } else if (edtProductDetails.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter valid item description", Toast.LENGTH_SHORT).show();
                } else if (edtProductPrice.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter valid item price", Toast.LENGTH_SHORT).show();
                } else if (tvQuantity.getText().toString().equals("0")) {
                    Toast.makeText(getContext(), "Enter valid item quantity", Toast.LENGTH_SHORT).show();
                } else {

                    purchaserRequestBundle.setUrl(edtItemUrl.getText().toString());
                    purchaserRequestBundle.setProd_name(edtItemProduct.getText().toString());
                    purchaserRequestBundle.setProd_detail(edtProductDetails.getText().toString());
                    purchaserRequestBundle.setPrice(edtProductPrice.getText().toString());
                    purchaserRequestBundle.setQuantity(tvQuantity.getText().toString());
                    purchaserRequestBundle.setImg1(imageFileName1);
                    purchaserRequestBundle.setExtension1(extension1);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleKeys.PURCHASER_REQUEST_BUNDLE, purchaserRequestBundle);

                    replaceChildFragmentWithDelay(new PurchaserDeliveryFragment(), true, false, bundle, true);

                    /*Intent intentSecond = new Intent(getApplicationContext(), PurchaserSecondActivity.class);
                    intentSecond.putExtra(KEY_PURCHASER_URL, );
                    intentSecond.putExtra(KEY_PURCHASER_NAME, );
                    intentSecond.putExtra(KEY_PURCHASER_DESC, );
                    intentSecond.putExtra(KEY_PURCHASER_PRICE,);
                    intentSecond.putExtra(KEY_PURCHASER_QUANT,);
                    intentSecond.putExtra(KEY_PURCH_IMAGE_1, );
                    intentSecond.putExtra(KEY_PURCH_IMAGE_1_EXTENSION, );
                    //intentSecond.putExtra(KEY_IMAGE_2, );
                    //intentSecond.putExtra(KEY_IMAGE_2_EXTENSION, extension2);
                    startActivity(intentSecond);*/

                }
                break;
            //ABULHASAN codeadd -END


            // Intent intentSecond = new Intent(getApplicationContext(), PurchaserSecondActivity.class);
            // startActivity(intentSecond);
            // break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            // imgPackage.setImageBitmap(photo);

            //ABULHASAN codeadd  -START
            String filePathStr = null;
            if (data != null) {


                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContext().getContentResolver().query(selectedImage, filePath,
                        null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                filePathStr = c.getString(columnIndex);
                c.close();

                File actualNameFile = new File(filePathStr);
                String extension = filePathStr.substring(filePathStr.lastIndexOf(".") + 1);

                Log.d("image_ext", extension + " : length : " + actualNameFile.length());


                imageFileName1 = filePathStr;
                extension1 = extension;

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(actualNameFile.getAbsolutePath(), bmOptions);
                //bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
                imgPackage.setImageBitmap(bitmap);

                firstImage = false;

                /*else if (secondImage) {

                    imageFileName2 = filePathStr;
                    extension2 = extension;

                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(actualNameFile.getAbsolutePath(), bmOptions);
                    //bitmap = Bitmap.createScaledBitmap(bitmap,parent.getWidth(),parent.getHeight(),true);
                    imgPackage2.setImageBitmap(bitmap);

                    secondImage = false;

                }*/
            }
            //ABULHASAN codeadd  -END

        }
    }

    //ABULHASAN codeadd  -START
    private void display(int number) {
        tvQuantity.setText("" + number);
    }

}