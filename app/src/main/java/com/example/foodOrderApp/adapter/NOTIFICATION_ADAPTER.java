package com.example.foodOrderApp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodOrderApp.R;
import com.example.foodOrderApp.activity.detail_notification_page;
import com.example.foodOrderApp.model.CATEGORY;
import com.example.foodOrderApp.model.NOTIFICATION;

import java.util.ArrayList;

public class NOTIFICATION_ADAPTER extends RecyclerView.Adapter<NOTIFICATION_ADAPTER.notificationViewholder>{

    private Context context;
    private ArrayList<NOTIFICATION> mListNotification;

    public NOTIFICATION_ADAPTER(Context context, ArrayList<NOTIFICATION> mListNotification) {
        this.context = context;
        this.mListNotification = mListNotification;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notificationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent,false);
        return new NOTIFICATION_ADAPTER.notificationViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notificationViewholder holder, int position) {
        NOTIFICATION data = mListNotification.get(position);
        holder.tvTittle.setText(data.getTittle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detail_notification_page.class);
                intent.putExtra("tittle", data.getTittle());
                intent.putExtra("body", data.getBody());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListNotification.size();
    }

    class notificationViewholder extends RecyclerView.ViewHolder{

        TextView tvTittle;

        public notificationViewholder(@NonNull View itemView) {
            super(itemView);
            tvTittle = itemView.findViewById(R.id.tvTittle);
        }
    }
}
