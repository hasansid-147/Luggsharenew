package com.android.luggshare.presentation.screens.myoffers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.getmyoffersreceived.MyOffersReceivedListResponseModel;

import java.util.List;

public class MyReceivedOffersAdapter extends RecyclerView.Adapter<MyReceivedOffersAdapter.MyViewHolder> {

    private List<MyOffersReceivedListResponseModel> offersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUsername, tvArrival, tvDeparture, tvByDate, tvStatus;

        public MyViewHolder(View view) {
            super(view);

            tvUsername = (TextView) view.findViewById(R.id.tvUsername);
            tvArrival = (TextView) view.findViewById(R.id.tvArrival);
            tvDeparture = (TextView) view.findViewById(R.id.tvDeparture);
            tvByDate = (TextView) view.findViewById(R.id.tvByDate);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        }
    }


    public MyReceivedOffersAdapter(List<MyOffersReceivedListResponseModel> moviesList) {
        this.offersList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_offers_recieved, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyOffersReceivedListResponseModel offerObj = offersList.get(position);
        holder.tvUsername.setText(offerObj.getTrvName());
        holder.tvArrival.setText(offerObj.getArrivalTo());
        holder.tvDeparture.setText(offerObj.getDepartingFrom());
        holder.tvByDate.setText(offerObj.getDelvDate());
        holder.tvStatus.setText(offerObj.getOfferStatus());
    }

    @Override
    public int getItemCount() {
        return offersList.size();
    }

    public void clear() {
        int size = offersList.size();
        offersList.clear();
        notifyItemRangeRemoved(0, size);
    }
}
