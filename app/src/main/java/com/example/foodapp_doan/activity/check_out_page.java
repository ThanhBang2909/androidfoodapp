package com.example.foodapp_doan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.foodapp_doan.R;
import com.example.foodapp_doan.adapter.CART_ADAPTER;
import com.example.foodapp_doan.adapter.PRODUCT_CHECKOUT_ADAPTER;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.model.USERS;
import com.example.foodapp_doan.sqlite.productsDAO;

import java.util.ArrayList;

public class check_out_page extends AppCompatActivity {

    private RecyclerView rvDataCart;
    public ArrayList<PRODUCTS> dataSP = new ArrayList<>();
    private productsDAO productsDAO;
    private PRODUCT_CHECKOUT_ADAPTER product_checkout_adapter;
    private ImageView btnBack;
    private String cart_total;
    private TextView tvCartTotal, namePay, phonePay,addressPay;
    private LinearLayout layoutAddressDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out_page);
        anhxa();
        eventClick();
        getAddress();
        getProductCart();
    }

    private void eventClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        layoutAddressDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(check_out_page.this, address_delivery_page.class);
                startActivity(intent);
            }
        });
    }

    private void getProductCart(){
        productsDAO = new productsDAO(this);
        dataSP = productsDAO.getProducts();

        product_checkout_adapter = new PRODUCT_CHECKOUT_ADAPTER(this, dataSP);
        rvDataCart.setAdapter(product_checkout_adapter);
        rvDataCart.setLayoutManager(new LinearLayoutManager(this));
        rvDataCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void getAddress(){
        Intent intent = getIntent();
        namePay.setText(intent.getStringExtra("nameDelivery"));
        addressPay.setText(intent.getStringExtra("addressDelivery"));
        phonePay.setText(intent.getStringExtra("phoneDelivery"));
    }

    void anhxa(){
        rvDataCart = findViewById(R.id.rvCart);
        btnBack = findViewById(R.id.btnBack);
        tvCartTotal = findViewById(R.id.moneyPay);
        namePay = findViewById(R.id.namePay);
        phonePay = findViewById(R.id.phonePay);
        addressPay = findViewById(R.id.addressPay);
        layoutAddressDelivery = findViewById(R.id.layoutAddressDelivery);

        cart_total = cart_page.cart_total.getText().toString().trim();
        tvCartTotal.setText(cart_total);
    }
}