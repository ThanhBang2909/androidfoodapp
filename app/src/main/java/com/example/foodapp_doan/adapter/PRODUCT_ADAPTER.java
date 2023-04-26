package com.example.foodapp_doan.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp_doan.R;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.utils.SERVER;
import com.squareup.picasso.Picasso;

import com.example.foodapp_doan.activity.detail_product_page;

import java.util.ArrayList;

public class PRODUCT_ADAPTER extends RecyclerView.Adapter<PRODUCT_ADAPTER.productsViewholder>{

    Context context;
    ArrayList<PRODUCTS> dataProducts;
    ArrayList<PRODUCTS> dataOrigin;

    public PRODUCT_ADAPTER(Context context, ArrayList<PRODUCTS> dataProducts) {
        this.context = context;
        this.dataProducts = dataProducts;
        this.dataOrigin = dataOrigin;
    }

    @NonNull
    @Override
    public productsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent,false);
        return new productsViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productsViewholder holder, int position) {
        PRODUCTS products = dataProducts.get(position);
        holder.tvNameProduct.setText(products.getTensanpham());
        holder.tvPriceProduct.setText(products.getGiasanpham()+"");
        Picasso.get().load(SERVER.imgProduct+products.getHinhsanpham()).into(holder.imgProduct);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detail_product_page.class);
                Bundle bundleDetailProduct = new Bundle();
                bundleDetailProduct.putString("name",products.getTensanpham());
                bundleDetailProduct.putInt("gia",products.getGiasanpham());
                bundleDetailProduct.putString("hinh",products.getHinhsanpham());
                intent.putExtras(bundleDetailProduct);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataProducts.size();
    }

    class productsViewholder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvNameProduct, tvPriceProduct;

        public productsViewholder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPirceProduct);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
