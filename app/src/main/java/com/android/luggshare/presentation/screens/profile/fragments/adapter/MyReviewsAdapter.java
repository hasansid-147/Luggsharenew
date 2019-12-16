package com.android.luggshare.presentation.screens.profile.fragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.business.models.userreviews.ReviewsListReponse;
import com.android.luggshare.presentation.screens.tracking.fragments.adapter.MyTrackingAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyReviewsAdapter extends RecyclerView.Adapter<MyReviewsAdapter.MyViewHolder> {

    private List<ReviewsListReponse> reviewsList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvRating, tvComment,tvUsername,tvdate;


        public MyViewHolder(View view) {
            super(view);

            tvRating = (TextView) view.findViewById(R.id.tvRating);
            tvComment = (TextView) view.findViewById(R.id.tvComment);
            tvUsername = (TextView) view.findViewById(R.id.tvUsername);
            tvdate = (TextView) view.findViewById(R.id.tvdate);

        }
    }

    public MyReviewsAdapter(List<ReviewsListReponse> reviewsList) {
        this.reviewsList = reviewsList;
    }

    @Override
    public MyReviewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_reviews, parent, false);

        return new MyReviewsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyReviewsAdapter.MyViewHolder holder, int position) {
        ReviewsListReponse reviews = reviewsList.get(position);
        holder.tvRating.setText(reviews.getRating().toString());
        holder.tvUsername.setText(reviews.getUsername());
        holder.tvComment.setText(reviews.getComment());
        holder.tvdate.setText(reviews.getCommentdate());
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public void clear() {
        int size = reviewsList.size();
        reviewsList.clear();
        notifyItemRangeRemoved(0, size);
    }

}
