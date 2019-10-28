package com.android.luggshare.presentation.screens.myrequests.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.getsenderlist.ListResponse;

import java.util.List;

public class MyRequestSenderAdapter extends RecyclerView.Adapter<MyRequestSenderAdapter.MyViewHolder> {

    private List<ListResponse> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvProduct, tvLocation;
        public ImageView img_icon;

        public MyViewHolder(View view) {
            super(view);

            tvProduct = (TextView) view.findViewById(R.id.tvProduct);
            tvLocation = (TextView) view.findViewById(R.id.tvLocation);
            img_icon = (ImageView) view.findViewById(R.id.img_icon);
        }
    }


    public MyRequestSenderAdapter(List<ListResponse> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_request_sender, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListResponse movie = moviesList.get(position);
        holder.tvProduct.setText(movie.getName());
        holder.tvLocation.setText(movie.getFromCity() + ", " + movie.getFromCountry() + " To " + movie.getToCity() + ", " + movie.getToCountry());
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
