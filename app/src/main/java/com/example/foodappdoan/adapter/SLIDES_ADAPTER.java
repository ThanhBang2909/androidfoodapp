package com.example.foodappdoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappdoan.R;
import com.example.foodappdoan.model.SLIDES;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SLIDES_ADAPTER extends RecyclerView.Adapter<SLIDES_ADAPTER.slidesAdapterViewHolder>{

    private List<SLIDES> imgSlides;
    private Context context;

    public SLIDES_ADAPTER(List<SLIDES> imgSlides, Context context) {
        this.imgSlides = imgSlides;
        this.context = context;
    }

    @NonNull
    @Override
    public slidesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slides, parent, false);

        return new slidesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull slidesAdapterViewHolder holder, int position) {
        SLIDES image = imgSlides.get(position);
        Picasso.get().load(image.getUrl()).into(holder.image_slide);
    }

    @Override
    public int getItemCount() {
        if (imgSlides != null) {
            return imgSlides.size();
        }
        return 0;
    }

    public class  slidesAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView image_slide;

        public slidesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            image_slide = itemView.findViewById(R.id.image_slide);
        }
    }
}
