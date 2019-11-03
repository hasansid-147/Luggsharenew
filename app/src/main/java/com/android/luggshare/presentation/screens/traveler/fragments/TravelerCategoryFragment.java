package com.android.luggshare.presentation.screens.traveler.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.common.bundle.SenderRequestBundle;
import com.android.luggshare.common.bundle.TravelerRequestBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.presentation.application.CustomApplication;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.sender.fragments.PricingFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class TravelerCategoryFragment extends CoreFragment {

    TravelerRequestBundle travelerRequestBundle;

    @BindView(R.id.rlGeneralItems)
    RelativeLayout rlGeneralItems;

    @BindView(R.id.rlFragileItems)
    RelativeLayout rlFragileItems;

    @BindView(R.id.rlFoodItems)
    RelativeLayout rlFoodItems;

    @BindView(R.id.rlDocumentItems)
    RelativeLayout rlDocumentItems;

    @BindView(R.id.rlElectronicItems)
    RelativeLayout rlElectronicItems;

    @BindView(R.id.rlTextileItems)
    RelativeLayout rlTextileItems;

    boolean generalItem = false;
    boolean fragileItem = false;
    boolean foodItem = false;
    boolean documentItem = false;
    boolean electronicItem = false;
    boolean textileItem = false;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_traveler_category;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            travelerRequestBundle = (TravelerRequestBundle) getArguments().getSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootview = super.onCreateView(inflater, container, savedInstanceState);



        return rootview;
    }

    @OnClick({R.id.rlGeneralItems, R.id.rlFragileItems, R.id.rlFoodItems, R.id.rlDocumentItems, R.id.rlElectronicItems, R.id.rlTextileItems, R.id.btnSendPackage})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.rlGeneralItems:
                if (generalItem) {
                    rlGeneralItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorGrey));
                    generalItem = false;
                } else {
                    rlGeneralItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorPrimary));
                    generalItem = true;
                }

                break;
            case R.id.rlFoodItems:
                if (foodItem) {
                    rlFoodItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorGrey));
                    foodItem = false;
                } else {
                    rlFoodItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorPrimary));
                    foodItem = true;
                }

                break;
            case R.id.rlFragileItems:
                if (fragileItem) {
                    rlFragileItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorGrey));
                    fragileItem = false;
                } else {
                    rlFragileItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorPrimary));
                    fragileItem = true;
                }

                break;
            case R.id.rlDocumentItems:
                if (documentItem) {
                    rlDocumentItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorGrey));
                    documentItem = false;
                } else {
                    rlDocumentItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorPrimary));
                    documentItem = true;
                }

                break;
            case R.id.rlElectronicItems:
                if (electronicItem) {
                    rlElectronicItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorGrey));
                    electronicItem = false;
                } else {
                    rlElectronicItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorPrimary));
                    electronicItem = true;
                }

                break;
            case R.id.rlTextileItems:
                if (textileItem) {
                    rlTextileItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorGrey));
                    textileItem = false;
                } else {
                    rlTextileItems.setBackgroundColor(ContextCompat.getColor(CustomApplication.getContext(), R.color.colorPrimary));
                    textileItem = true;
                }

                break;

            case R.id.btnSendPackage:

                String generalItems = "";
                String fragileItems = "";
                String foodItems = "";
                String documentation = "";
                String electronicItems = "";
                String clothingItems = "";

                if (generalItem) {
                    generalItems = "General Items";
                }

                if (fragileItem) {
                    fragileItems = "Fragile Items";
                }

                if (foodItem) {
                    foodItems = "Food Items";
                }

                if (documentItem) {
                    documentation = "Documents";
                }

                if (electronicItem) {
                    electronicItems = "Electronic Items";
                }

                if (textileItem) {
                    clothingItems = "Clothing";
                }

                travelerRequestBundle.setPrefItem1(generalItems);
                travelerRequestBundle.setPrefItem2(fragileItems);
                travelerRequestBundle.setPrefItem3(foodItems);
                travelerRequestBundle.setFromDetailsScreen(false);

                Bundle bundle = new Bundle();
                bundle.putSerializable(BundleKeys.TRAVELER_REQUEST_BUNDLE, travelerRequestBundle);

                replaceChildFragmentWithDelay(new TravelerListingFragment(), true, false, bundle, true);

                /*if (getIntent().getBooleanExtra(KEY_TRAVELER, false)) {
                    Intent intTraveler = new Intent(getApplicationContext(), TravelerListingActivity.class);
                    intTraveler.putExtra(KEY_SENDER_FROM_COUNTRY, senderFromCountry);
                    intTraveler.putExtra(KEY_SENDER_FROM_CITY, senderFromCity);
                    intTraveler.putExtra(KEY_SENDER_TO_COUNTRY, senderToCountry);
                    intTraveler.putExtra(KEY_SENDER_TO_CITY, senderToCity);
                    intTraveler.putExtra(KEY_SENDER_ITEM_1, generalItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_2, fragileItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_3, foodItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_4, documentation);
                    intTraveler.putExtra(KEY_SENDER_ITEM_5, electronicItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_6, clothingItems);
                    intTraveler.putExtra(KEY_TRAVELER_DEP_DATE, departureDate);
                    intTraveler.putExtra(KEY_TRAVELER_ARV_DATE, arrivalDate);
                    intTraveler.putExtra(KEY_TRAVELER_CAPACITY, travelerCapacity);
                    startActivity(intTraveler);
                } else {
                    Intent intTraveler = new Intent(getApplicationContext(), PricingActivity.class);
                    intTraveler.putExtra(KEY_SENDER_NAME, sendItemData);
                    intTraveler.putExtra(KEY_SENDER_DESC, dataDescription);
                    intTraveler.putExtra(KEY_SENDER_DEL_DATE, dataDate);
                    intTraveler.putExtra(KEY_SENDER_WEIGHT, senderWeight);
                    intTraveler.putExtra(KEY_SENDER_SIZE, senderSize);
                    intTraveler.putExtra(KEY_SENDER_FROM_COUNTRY, senderFromCountry);
                    intTraveler.putExtra(KEY_SENDER_FROM_CITY, senderFromCity);
                    intTraveler.putExtra(KEY_SENDER_TO_COUNTRY, senderToCountry);
                    intTraveler.putExtra(KEY_SENDER_TO_CITY, senderToCity);
                    intTraveler.putExtra(KEY_SENDER_ITEM_1, generalItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_2, fragileItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_3, foodItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_4, documentation);
                    intTraveler.putExtra(KEY_SENDER_ITEM_5, electronicItems);
                    intTraveler.putExtra(KEY_SENDER_ITEM_6, clothingItems);
                    intTraveler.putExtra(KEY_IMAGE_1, imageFileName1);
                    intTraveler.putExtra(KEY_IMAGE_1_EXTENSION, extension1);
                    intTraveler.putExtra(KEY_IMAGE_2, imageFileName2);
                    intTraveler.putExtra(KEY_IMAGE_2_EXTENSION, extension2);
                    startActivity(intTraveler);
                }*/
                break;
        }
    }

}