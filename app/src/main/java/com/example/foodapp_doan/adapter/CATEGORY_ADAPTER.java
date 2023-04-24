package com.example.foodapp_doan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp_doan.R;
import com.example.foodapp_doan.model.CATEGORY;
import com.example.foodapp_doan.utils.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CATEGORY_ADAPTER extends RecyclerView.Adapter<CATEGORY_ADAPTER.categoryViewholder>{

    Context context;
    ArrayList<CATEGORY> dataCategory;

    public CATEGORY_ADAPTER(Context context, ArrayList<CATEGORY> dataCategory) {
        this.context = context;
        this.dataCategory = dataCategory;
    }

    @NonNull
    @Override
    public categoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent,false);
        return new categoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewholder holder, int position) {
        CATEGORY category =dataCategory.get(position);
        holder.tvCategory.setText(category.getTenChude());
        Picasso.get().load(SERVER.imgCategory+category.getHinhChude()).into(holder.imgCategory);
    }

    @Override
    public int getItemCount() {
        return dataCategory.size();
    }

    class categoryViewholder extends RecyclerView.ViewHolder{
        ImageView imgCategory;
        TextView tvCategory;

        public categoryViewholder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            tvCategory = itemView.findViewById(R.id.tvCategory);
        }
    }
}
