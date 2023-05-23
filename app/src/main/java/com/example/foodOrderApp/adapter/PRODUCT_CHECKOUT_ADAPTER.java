package com.example.foodOrderApp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodOrderApp.R;
import com.example.foodOrderApp.model.PRODUCTS;
import com.example.foodOrderApp.sqlite.productsDAO;
import com.example.foodOrderApp.utils.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PRODUCT_CHECKOUT_ADAPTER extends RecyclerView.Adapter<PRODUCT_CHECKOUT_ADAPTER.PRODUCT_CHECKOUT_ADAPTER_ViewHolder>{

    Context context;
    ArrayList<PRODUCTS> data;
    productsDAO productsDAO;

    public PRODUCT_CHECKOUT_ADAPTER(Context context, ArrayList<PRODUCTS> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public PRODUCT_CHECKOUT_ADAPTER_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkout, parent, false);
        return new PRODUCT_CHECKOUT_ADAPTER.PRODUCT_CHECKOUT_ADAPTER_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PRODUCT_CHECKOUT_ADAPTER_ViewHolder holder, int position) {
        productsDAO = new productsDAO(context.getApplicationContext());
        PRODUCTS sp = data.get(position);
        holder.cart_name.setText(sp.getTensanpham());
        holder.cart_price.setText(sp.getGiasanpham()+"");
        holder.cart_quantity.setText(sp.getSoluong() + "");
        Picasso.get().load(SERVER.imgProduct + sp.getHinhsanpham()).into(holder.cart_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PRODUCT_CHECKOUT_ADAPTER_ViewHolder extends RecyclerView.ViewHolder{

        ImageView cart_img;
        TextView cart_name, cart_price, cart_quantity;

        public PRODUCT_CHECKOUT_ADAPTER_ViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_img = itemView.findViewById(R.id.cart_img);
            cart_name = itemView.findViewById(R.id.cart_name);
            cart_price = itemView.findViewById(R.id.cart_price);
            cart_quantity = itemView.findViewById(R.id.cart_quantity);
        }
    }
}
