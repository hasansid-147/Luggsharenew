package com.android.luggshare.presentation.screens.traveler.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.travelerlisting.TravListingResponse;

import java.util.ArrayList;

public class TravelerListingAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<TravListingResponse> data;
    private static LayoutInflater inflater=null;

    public TravelerListingAdapter(Activity a, ArrayList<TravListingResponse> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        return data.size();
    }

    public TravListingResponse getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.traveler_listing_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView artist = (TextView)vi.findViewById(R.id.artist); // artist name
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        data.get(position);

        // Setting all values in listview
        title.setText(data.get(position).getProdname());
        artist.setText(data.get(position).getReqtype());
        duration.setText(data.get(position).getReqtype());
        return vi;
    }
}