package com.example.foodOrderApp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodOrderApp.R;
import com.example.foodOrderApp.activity.cart_page;
import com.example.foodOrderApp.model.PRODUCTS;
import com.example.foodOrderApp.sqlite.productsDAO;
import com.example.foodOrderApp.utils.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CART_ADAPTER extends RecyclerView.Adapter<CART_ADAPTER.cartViewholder>{

    Context context;
    ArrayList<PRODUCTS> data;
    productsDAO productsDAO;

    public CART_ADAPTER(Context context, ArrayList<PRODUCTS> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CART_ADAPTER.cartViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new cartViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CART_ADAPTER.cartViewholder holder, int position) {
        productsDAO = new productsDAO(context.getApplicationContext());
        PRODUCTS sp = data.get(position);
        holder.cart_name.setText(sp.getTensanpham());
        holder.cart_price.setText(sp.getGiasanpham()+"");
        holder.cart_quantity.setText(sp.getSoluong() + "");
        Picasso.get().load(SERVER.imgProduct + sp.getHinhsanpham()).into(holder.cart_img);

        holder.cart_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifications(sp);
            }
        });

        holder.cart_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.setSoluong(sp.getSoluong() + 1);
                holder.cart_quantity.setText(String.valueOf(sp.getSoluong()));
                updateTotal();
                // cập nhật số lượng
                productsDAO.updateProductQuantityById(sp.getMasanpham(), sp.getSoluong());
            }
        });

        holder.cart_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getSoluong() > 1) {
                    sp.setSoluong(sp.getSoluong() - 1);
                    holder.cart_quantity.setText(String.valueOf(sp.getSoluong()));
                    updateTotal();
                    // cập nhật số lượng
                    productsDAO.updateProductQuantityById(sp.getMasanpham(), sp.getSoluong());
                }
            }
        });

        updateTotal();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class cartViewholder extends RecyclerView.ViewHolder {

        ImageView cart_img;
        TextView cart_name, cart_price;
        Button cart_delete;
        TextView cart_quantity, cart_min, cart_max, cart_total;

        public cartViewholder(@NonNull View itemView) {
            super(itemView);
            // ánh xạ
            cart_img = itemView.findViewById(R.id.cart_img);
            cart_name = itemView.findViewById(R.id.cart_name);
            cart_price = itemView.findViewById(R.id.cart_price);
            cart_min = itemView.findViewById(R.id.cart_min);
            cart_quantity = itemView.findViewById(R.id.cart_quantity);
            cart_max = itemView.findViewById(R.id.cart_max);
            cart_delete = itemView.findViewById(R.id.cart_delete);
            cart_total = itemView.findViewById(R.id.cart_total);
        }
    }

    void notifications(PRODUCTS sp) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Bạn có chắc muốn xóa sản phẩm này ?");
        builder1.setCancelable(true);


        builder1.setPositiveButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // get the position of the item to be removed
                        productsDAO productsDAO = new productsDAO(context);
                        productsDAO.deleteProductById(sp.getMasanpham());
                        data.remove(sp);
                        notifyDataSetChanged();
                        updateTotal();

                        Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void updateTotal() {
        double total = 0;
        for (PRODUCTS sp : data) {
            total += sp.getGiasanpham() * sp.getSoluong();
        }
        ((cart_page) context).updateTotal(total);
    }
}
