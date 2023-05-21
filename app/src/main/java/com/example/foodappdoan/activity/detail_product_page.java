package com.example.foodappdoan.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodappdoan.R;
import com.example.foodappdoan.utils.SERVER;
import com.squareup.picasso.Picasso;

public class detail_product_page extends AppCompatActivity {

    private ImageView imgProductDetail, btnBack, btnMax, btnMin;
    private TextView tvNameProductDetail, tvPriceDetail, tvQuality;
    private Button btnAddtocart;
    private String maSanPham, name, hinh;
    private Integer gia;
    private Integer totalQuality = 1;


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


        btnAddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detail_product_page.this, cart_page.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", maSanPham);
                bundle.putString("name", name);
                bundle.putString("image", hinh);
                bundle.putInt("price", gia);
                bundle.putInt("quantity", totalQuality);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        btnMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuality<10){
                    totalQuality ++;
                    tvQuality.setText(totalQuality+"");
                }
            }
        });


        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuality>1){
                    totalQuality--;
                    tvQuality.setText(totalQuality+"");
                }
            }
        });
    }

    void loadProductDetail(){
        Intent intent = getIntent();
        Bundle bundleDetailProduct = intent.getExtras();

        maSanPham = bundleDetailProduct.getString("maSanPham");
        name = bundleDetailProduct.getString("name");
        gia = bundleDetailProduct.getInt("gia");
        hinh = bundleDetailProduct.getString("hinh");

        tvNameProductDetail.setText(name);
        tvPriceDetail.setText(gia+"");
        Picasso.get().load(SERVER.imgProduct+hinh).into(imgProductDetail);

    }

    void anhxa(){
        tvNameProductDetail = findViewById(R.id.tvNameProductsDetail);
        tvPriceDetail = findViewById(R.id.tvPirceProductDetail);
        imgProductDetail = findViewById(R.id.imgProductDetail);
        btnBack = findViewById(R.id.btnDetailBack);
        btnAddtocart = findViewById(R.id.btnAddCard);
        btnMax = findViewById(R.id.btnMax);
        btnMin = findViewById(R.id.btnMin);
        tvQuality = findViewById(R.id.tvQuantity);
        tvQuality.setText(totalQuality+"");
    }
}