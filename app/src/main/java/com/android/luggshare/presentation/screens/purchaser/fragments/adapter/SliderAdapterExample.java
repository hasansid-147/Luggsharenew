package com.android.luggshare.presentation.screens.purchaser.fragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.luggshare.R;
import com.android.luggshare.business.models.userreviews.ReviewsListReponse;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;

    private List<String> images;


   /* public SliderAdapterExample(Context context) {
        this.context = context;
    }*/

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    public SliderAdapterExample(List<String> images) {
        this.images = images;
    }
    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        //viewHolder.textViewDescription.setText("This is slider item " + position);
        //String image = images.get(position);
        //Glide.with(viewHolder.itemView)
                //.load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
               // .load(image)
              //  .into(viewHolder.imageViewBackground);

        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        //.load("https://images.pexels.com/photos/218983/pexels-photo-218983.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                        .load(images.get(position))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                Glide.with(viewHolder.itemView)
                        //.load("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                        .load(images.get(position))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

            default:
                Glide.with(viewHolder.itemView)
                        .load("http://luggshare-001-site1.htempurl.com/images/gray-background.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
