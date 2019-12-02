package com.android.luggshare.presentation.screens.profile.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.luggshare.R;
import com.android.luggshare.business.models.tracking.GetTrackList;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.business.models.userreviews.GetReviews;
import com.android.luggshare.business.models.userreviews.ReviewsListReponse;
import com.android.luggshare.business.services.ApiClient;
import com.android.luggshare.business.services.ApiInterface;
import com.android.luggshare.common.bundle.TrackingBundle;
import com.android.luggshare.common.bundle.ViewReviewsBundle;
import com.android.luggshare.common.keys.BundleKeys;
import com.android.luggshare.common.managers.PreferenceManager;
import com.android.luggshare.presentation.fragments.CoreFragment;
import com.android.luggshare.presentation.screens.profile.fragments.adapter.MyReviewsAdapter;
import com.android.luggshare.presentation.screens.tracking.fragments.adapter.MyTrackingAdapter;
import com.android.luggshare.presentation.screens.tracking.fragments.fragment.UpdateDeliveryDetailFragment;
import com.android.luggshare.utils.RecyclerTouchListener;
import com.android.luggshare.utils.UiHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.luggshare.common.keys.PreferenceKeys.KEY_CUSTOMER_ID;

public class UserReviewsFragment extends CoreFragment {

    ViewReviewsBundle viewReviewsBundle;
    private MyReviewsAdapter mAdapter;
    @BindView(R.id.rvListreview)
    RecyclerView rvListreview;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_all_reviews;


    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            viewReviewsBundle = (ViewReviewsBundle) getArguments().getSerializable(BundleKeys.VIEW_USER_REVIEWS);
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootview = super.onCreateView(inflater, container, savedInstanceState);



        //trackingBundle =  new TrackingBundle();
        int uid = viewReviewsBundle.getUid();
        fetchListData(uid);

        return rootview;
    }


    private void fetchListData(int uid) {

        UiHelper.getInstance().hideKeyboard(getActivity());

        UiHelper.getInstance().showLoadingIndicator(getActivity());

        GetReviews getlist = new GetReviews();

        getlist.setUid(uid);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ReviewsListReponse>> call = apiService.fetchAllReviews(getlist);
        call.enqueue(new Callback<ArrayList<ReviewsListReponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ReviewsListReponse>> call, Response<ArrayList<ReviewsListReponse>> response) {

                Log.d("List", "RESPONSE:" + response.body());
                UiHelper.getInstance().hideLoadingIndicator();


                if (response.isSuccessful()) {

                    initReviewsList(response.body());



                } else {
                    if (mAdapter != null)
                        mAdapter.clear();

                    Toast.makeText(getContext(), getString(R.string.No_Data_Found), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<ReviewsListReponse>> call, Throwable t) {
                Log.e("List", t.toString());
            }
        });


    }

    private void initReviewsList(final ArrayList<ReviewsListReponse> arrayList) {

        rvListreview.setVisibility(View.VISIBLE);



        mAdapter = new MyReviewsAdapter(arrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvListreview.setLayoutManager(mLayoutManager);
        rvListreview.setItemAnimator(new DefaultItemAnimator());
        rvListreview.setAdapter(mAdapter);

        rvListreview.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvListreview, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
