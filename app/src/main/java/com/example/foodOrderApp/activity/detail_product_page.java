package com.example.foodOrderApp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodOrderApp.R;
import com.example.foodOrderApp.utils.SERVER;
import com.squareup.picasso.Picasso;

public class detail_product_page extends AppCompatActivity {

    private ImageView imgProductDetail, btnBack;
    private TextView tvNameProductDetail, tvPriceDetail, tvQuality,tvDetail;
    private Button btnAddtocart;
    private String maSanPham, name, hinh, detail;
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

    /**
     *
     * Sự kiện CLick.
     *
     * */

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
                bundle.putInt("quantity", 1);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    /**
     *
     * Load thông tin chi tiết sản phẩm.
     *
     * */

    void loadProductDetail(){
        Intent intent = getIntent();
        Bundle bundleDetailProduct = intent.getExtras();

        maSanPham = bundleDetailProduct.getString("maSanPham");
        name = bundleDetailProduct.getString("name");
        gia = bundleDetailProduct.getInt("gia");
        hinh = bundleDetailProduct.getString("hinh");
        detail = bundleDetailProduct.getString("mota");

        tvNameProductDetail.setText(name);
        tvPriceDetail.setText(gia+"");
        tvDetail.setText(detail);
        Picasso.get().load(SERVER.imgProduct+hinh).into(imgProductDetail);

    }



    void anhxa(){
        tvNameProductDetail = findViewById(R.id.tvNameProductsDetail);
        tvPriceDetail = findViewById(R.id.tvPirceProductDetail);
        imgProductDetail = findViewById(R.id.imgProductDetail);
        btnBack = findViewById(R.id.btnDetailBack);
        btnAddtocart = findViewById(R.id.btnAddCard);
        tvDetail = findViewById(R.id.tvDetail);
    }
}