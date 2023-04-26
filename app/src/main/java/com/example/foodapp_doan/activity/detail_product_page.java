package com.example.foodapp_doan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodapp_doan.R;
import com.example.foodapp_doan.utils.SERVER;
import com.squareup.picasso.Picasso;

public class detail_product_page extends AppCompatActivity {

    private ImageView imgProductDetail, btnBack;
    private TextView tvNameProductDetail, tvPriceDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product_page);
        anhxa();
        eventClick();
        loadProductDetail();
    }

    void eventClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void loadProductDetail(){
        Intent intent = getIntent();
        Bundle bundleDetailProduct = intent.getExtras();

        String name = bundleDetailProduct.getString("name");
        Integer gia = bundleDetailProduct.getInt("gia");
        String hinh = bundleDetailProduct.getString("hinh");

        tvNameProductDetail.setText(name);
        tvPriceDetail.setText(gia+"");
        Picasso.get().load(SERVER.imgProduct+hinh).into(imgProductDetail);

    }

    void anhxa(){
        tvNameProductDetail = findViewById(R.id.tvNameProductsDetail);
        tvPriceDetail = findViewById(R.id.tvPirceProductDetail);
        imgProductDetail = findViewById(R.id.imgProductDetail);
        btnBack = findViewById(R.id.btnDetailBack);
    }
}