package com.android.luggshare.presentation.screens.tracking.fragments.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.getsenderlist.ListResponse;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.presentation.screens.myrequests.adapters.MyRequestSenderAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyTrackingAdapter extends RecyclerView.Adapter<MyTrackingAdapter.MyViewHolder> {


    private List<TrackListResponse> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvItemNamevalue, tvItemTypvalue,tvItemname,tvItemTyp;
        public ImageView img_icon;

        public MyViewHolder(View view) {
            super(view);

            tvItemNamevalue = (TextView) view.findViewById(R.id.tvItemNamevalue);
            tvItemTypvalue = (TextView) view.findViewById(R.id.tvItemTypvalue);
            tvItemname = (TextView) view.findViewById(R.id.tvItemname);
            tvItemTyp = (TextView) view.findViewById(R.id.tvItemTyp);


            img_icon = (ImageView) view.findViewById(R.id.img_icon);
        }
    }

    public MyTrackingAdapter(List<TrackListResponse> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyTrackingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_tracking, parent, false);

        return new MyTrackingAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyTrackingAdapter.MyViewHolder holder, int position) {
        TrackListResponse movie = moviesList.get(position);
        holder.tvItemNamevalue.setText(movie.getDeliveryname());
        holder.tvItemTypvalue.setText(movie.getReqTyp());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void clear() {
        int size = moviesList.size();
        moviesList.clear();
        notifyItemRangeRemoved(0, size);
    }
}
