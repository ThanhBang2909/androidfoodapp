package com.example.foodapp_doan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp_doan.R;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.model.USERS;
import com.example.foodapp_doan.sqlite.productsDAO;
import com.example.foodapp_doan.activity.check_out_page;

import java.util.ArrayList;

public class ADDRESS_DELIVERY_ADAPTER extends RecyclerView.Adapter<ADDRESS_DELIVERY_ADAPTER.address_delivery_viewHolder>{

    Context context;
    ArrayList<USERS> data;

    public ADDRESS_DELIVERY_ADAPTER(Context context, ArrayList<USERS> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public address_delivery_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address_delivery, parent, false);
        return new ADDRESS_DELIVERY_ADAPTER.address_delivery_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull address_delivery_viewHolder holder, int position) {
        USERS users = data.get(position);
        holder.tvName.setText(users.getFullName());
        holder.tvAddress.setText(users.getAddress());
        holder.tvPhone.setText(users.getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, check_out_page.class);
                intent.putExtra("nameDelivery", users.getFullName());
                intent.putExtra("phoneDelivery", users.getPhone());
                intent.putExtra("addressDelivery", users.getAddress());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class address_delivery_viewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPhone, tvAddress;

        public address_delivery_viewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameDelivery);
            tvPhone = itemView.findViewById(R.id.tvPhoneDelivery);
            tvAddress = itemView.findViewById(R.id.tvAddressDelivery);
        }
    }
}
