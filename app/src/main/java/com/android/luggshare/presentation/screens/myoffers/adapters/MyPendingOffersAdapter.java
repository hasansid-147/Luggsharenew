package com.android.luggshare.presentation.screens.myoffers.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.getmyofferspending.MyOffersPendingListResponseModel;

import java.util.List;

public class MyPendingOffersAdapter extends RecyclerView.Adapter<MyPendingOffersAdapter.MyViewHolder> {

    private List<MyOffersPendingListResponseModel> offersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUsername, tvArrival, tvDeparture, tvByDate, tvStatus, tvItem;

        public MyViewHolder(View view) {
            super(view);

            tvUsername = (TextView) view.findViewById(R.id.tvUsername);
            tvArrival = (TextView) view.findViewById(R.id.tvArrival);
            tvDeparture = (TextView) view.findViewById(R.id.tvDeparture);
            tvItem = (TextView) view.findViewById(R.id.tvItem);
            tvByDate = (TextView) view.findViewById(R.id.tvByDate);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        }
    }


    public MyPendingOffersAdapter(List<MyOffersPendingListResponseModel> moviesList) {
        this.offersList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_offers_pending, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try {
            MyOffersPendingListResponseModel offerObj = offersList.get(position);
            holder.tvUsername.setText(offerObj.getRcvName());
            holder.tvArrival.setText(offerObj.getRcvToloc());
            holder.tvDeparture.setText(offerObj.getRcvFromloc());
            if (offerObj.getRcvPackagName() != null)
                holder.tvItem.setText(offerObj.getRcvPackagName());

            if (offerObj.getRcvDelvdate() != null)
                holder.tvByDate.setText(offerObj.getRcvDelvdate());

            if (offerObj.getOfferStatus() != null)
                holder.tvStatus.setText(offerObj.getOfferStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
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
