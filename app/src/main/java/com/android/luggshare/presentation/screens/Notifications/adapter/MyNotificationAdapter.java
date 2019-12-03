package com.android.luggshare.presentation.screens.Notifications.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.notifications.NotificationReponse;
import com.android.luggshare.business.models.tracking.TrackListResponse;
import com.android.luggshare.presentation.screens.tracking.fragments.adapter.MyTrackingAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyNotificationAdapter extends RecyclerView.Adapter<MyNotificationAdapter.MyViewHolder> {


    private List<NotificationReponse> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvIdate, tvTitle,tvText;

        public MyViewHolder(View view) {
            super(view);

            tvIdate = (TextView) view.findViewById(R.id.tvIdate);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvText = (TextView) view.findViewById(R.id.tvText);

        }
    }

    public MyNotificationAdapter(List<NotificationReponse> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyNotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_my_notifications, parent, false);

        return new MyNotificationAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyNotificationAdapter.MyViewHolder holder, int position) {
        NotificationReponse movie = moviesList.get(position);
        holder.tvIdate.setText(movie.getDate());
        holder.tvTitle.setText(movie.getTopic());
        holder.tvText.setText(movie.getDesc());
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
