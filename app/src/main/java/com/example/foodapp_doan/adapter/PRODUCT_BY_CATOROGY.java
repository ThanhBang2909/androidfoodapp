package com.example.foodapp_doan.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp_doan.R;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.utils.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PRODUCT_BY_CATOROGY extends RecyclerView.Adapter<PRODUCT_BY_CATOROGY.ProductByCategoryViewholder> implements Filterable {

    Context context;
    ArrayList<PRODUCTS> dataProducts;
    ArrayList<PRODUCTS> dataOrigin;

    public PRODUCT_BY_CATOROGY(Context context, ArrayList<PRODUCTS> dataProducts) {
        this.context = context;
        this.dataProducts = dataProducts;
        this.dataOrigin = dataProducts;
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

    @Override
    public Filter getFilter() {
        return new ItemFilter();
    }


    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String chuoitim = charSequence.toString().toLowerCase().trim();
            FilterResults filterResults = new FilterResults();
            if (!TextUtils.isEmpty(chuoitim)) {
                ArrayList<PRODUCTS> tam = new ArrayList<>();
                for (PRODUCTS sp : dataOrigin) {
                    if (sp.getTensanpham().toLowerCase().toString().contains(chuoitim))
                        tam.add(sp);
                }
                filterResults.values = tam;
                filterResults.count = tam.size();

            } else {
                filterResults.values = dataOrigin;
                filterResults.count = dataOrigin.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults != null && filterResults.count > 0) {
                dataProducts = (ArrayList<PRODUCTS>) filterResults.values;
                notifyDataSetChanged();
            }
        }
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
