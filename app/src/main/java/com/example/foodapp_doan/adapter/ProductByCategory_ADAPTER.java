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
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.utils.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductByCategory_ADAPTER extends RecyclerView.Adapter<ProductByCategory_ADAPTER.ProductByCategoryViewholder>{

    Context context;
    ArrayList<PRODUCTS> dataProducts;

    public ProductByCategory_ADAPTER(Context context, ArrayList<PRODUCTS> dataProducts) {
        this.context = context;
        this.dataProducts = dataProducts;
    }

    @NonNull
    @Override
    public ProductByCategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_by_category, parent,false);
        return new ProductByCategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductByCategoryViewholder holder, int position) {
        PRODUCTS products = dataProducts.get(position);
        holder.tvNameProduct.setText(products.getTensanpham());
        holder.tvPriceProduct.setText(products.getGiasanpham()+"");
        Picasso.get().load(SERVER.imgProduct+products.getHinhsanpham()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return dataProducts.size();
    }

    class ProductByCategoryViewholder extends RecyclerView.ViewHolder {

        ImageView imgProduct;
        TextView tvNameProduct, tvPriceProduct;

        public ProductByCategoryViewholder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPirceProduct);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
