package com.example.foodappdoan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodappdoan.R;
import com.example.foodappdoan.model.BILL;

import com.example.foodappdoan.activity.detail_order_page;
import java.util.ArrayList;

public class ORDER_PAGE_ADAPTER extends RecyclerView.Adapter<ORDER_PAGE_ADAPTER.orderPageViewholder>{

    private Context context;
    private ArrayList<BILL> data;

    public ORDER_PAGE_ADAPTER(Context context, ArrayList<BILL> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public orderPageViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_page, parent,false);
        return new ORDER_PAGE_ADAPTER.orderPageViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderPageViewholder holder, int position) {
        BILL dataOrder = data.get(position);
        holder.tvMaHD.setText("Mã hóa đơn"+dataOrder.getMaHD());
        holder.tvNameOrder.setText(dataOrder.getNameCus());
        holder.tvPhoneOrder.setText(dataOrder.getPhoneCus());
        holder.tvAddressOrder.setText(dataOrder.getAddressCus());
        holder.tvTotalOrder.setText("Thành tiền : "+dataOrder.getThanhTien());
        if (dataOrder.getStatus() == 0){
            holder.tvStatus.setText("Đã giao hàng");
        }else {
            holder.tvStatus.setText("Chưa giao hàng");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detail_order_page.class);
                intent.putExtra("maHD", dataOrder.getMaHD());
                intent.putExtra("nameOrder", dataOrder.getNameCus());
                intent.putExtra("phoneOrder", dataOrder.getPhoneCus());
                intent.putExtra("addressOrder", dataOrder.getAddressCus());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class orderPageViewholder extends RecyclerView.ViewHolder {

        ImageView btnNavDetail;
        TextView tvStatus, tvMaHD, tvNameOrder, tvPhoneOrder, tvAddressOrder, tvTotalOrder;
        LinearLayout layoutNav;

        public orderPageViewholder(@NonNull View itemView) {
            super(itemView);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvMaHD = itemView.findViewById(R.id.tvMaHD);
            tvNameOrder = itemView.findViewById(R.id.tvNameOrder);
            tvPhoneOrder = itemView.findViewById(R.id.tvPhoneOrder);
            tvAddressOrder = itemView.findViewById(R.id.tvAddressOrder);
            tvTotalOrder = itemView.findViewById(R.id.tvTotalOrder);
        }
    }
}
