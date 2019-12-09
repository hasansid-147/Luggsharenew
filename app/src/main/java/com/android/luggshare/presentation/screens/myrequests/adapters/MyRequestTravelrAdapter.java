package com.android.luggshare.presentation.screens.myrequests.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.getsenderlist.ListResponse;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyRequestTravelrAdapter extends RecyclerView.Adapter<MyRequestTravelrAdapter.MyViewHolder> {

    private List<ListResponse> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProduct, tvLocation,tvCapacity,txtCapacitylabl;
        public ImageView img_icon;

        public MyViewHolder(View view) {
            super(view);

            tvProduct = (TextView) view.findViewById(R.id.tvProduct);
            tvLocation = (TextView) view.findViewById(R.id.tvLocation);
            img_icon = (ImageView) view.findViewById(R.id.img_icon);
            tvCapacity = (TextView) view.findViewById(R.id.tvCapacity);
            txtCapacitylabl = (TextView) view.findViewById(R.id.txtCapacitylabl);
        }
    }


    public MyRequestTravelrAdapter(List<ListResponse> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyRequestTravelrAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_request_traveler, parent, false);

        return new MyRequestTravelrAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyRequestTravelrAdapter.MyViewHolder holder, int position) {
        ListResponse movie = moviesList.get(position);

        holder.tvProduct.setText(movie.getDepTime().toString());
        holder.tvLocation.setText(movie.getFromCity() + ", " + movie.getFromCountry() + " To " + movie.getToCity() + ", " + movie.getToCountry());
        holder.tvCapacity.setText(movie.getCapacity().toString());
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
