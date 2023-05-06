package com.example.foodapp_doan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodapp_doan.R;
import com.example.foodapp_doan.adapter.CART_ADAPTER;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.sqlite.DBHelper;
import com.example.foodapp_doan.sqlite.productsDAO;

import java.util.ArrayList;

public class cart_page extends AppCompatActivity {

    public TextView cart_total;
    private Button checkout;
    private RecyclerView rvCart;
    private CART_ADAPTER adapter_cart;
    private Context context;
    private ArrayList<PRODUCTS> dataSP = new ArrayList<>();
    private SQLiteDatabase db;
    private productsDAO productsDAO;
    private ImageView btnBack;

    private String iD, name, image;
    private int quantity,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        anhxa();
        eventClick();
        getCart();
    }

    private void eventClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getCart(){
        Intent intent = getIntent();
        iD = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        image = intent.getStringExtra("image");
        price = intent.getIntExtra("price",1);
        quantity = intent.getIntExtra("quantity", 1);

        productsDAO = new productsDAO(this);
        productsDAO.insertProduct(iD, name, image, price, quantity);
        dataSP = productsDAO.getProducts();

        adapter_cart = new CART_ADAPTER(this, dataSP);
        rvCart.setAdapter(adapter_cart);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    public void updateTotal(double total) {
        cart_total.setText(total+"");
    }

    void anhxa(){
        cart_total = findViewById(R.id.cart_total);
        checkout = findViewById(R.id.btnCheckout);
        rvCart = findViewById(R.id.rvCart);
        btnBack = findViewById(R.id.btnBack);
        context = this;

        try {
            DBHelper dbHelper = new DBHelper(this);
            db = dbHelper.getWritableDatabase();
        }catch (Exception e){
            Toast.makeText(cart_page.this, "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}