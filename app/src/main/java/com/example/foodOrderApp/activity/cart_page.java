package com.example.foodOrderApp.activity;

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

import com.example.foodOrderApp.R;
import com.example.foodOrderApp.adapter.CART_ADAPTER;
import com.example.foodOrderApp.model.PRODUCTS;
import com.example.foodOrderApp.sqlite.DBHelper;
import com.example.foodOrderApp.sqlite.productsDAO;

import java.util.ArrayList;

public class cart_page extends AppCompatActivity {

    public static TextView cart_total;
    private Button checkout;
    private RecyclerView rvCart;
    private CART_ADAPTER adapter_cart;
    private Context context;
    public ArrayList<PRODUCTS> dataSP = new ArrayList<>();
    private SQLiteDatabase db;
    private productsDAO productsDAO;
    private ImageView btnBack;
    public double totalPrice = 0;
    private Intent intent;
    private Bundle bundle;

    private String iD, name, image;
    private int quantity,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);
        anhxa();
        eventClick();
        checkData();
        getCart();
        getTotalPrice();
    }

    /**
     *
     * Sự kiện Click.
     *
     * */

    private void eventClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productsDAO.getProducts().size()>=1){
                    Intent intent = new Intent(cart_page.this, check_out_page.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(context, "Gio hang trong", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /**
     *
     * Thêm sản phẩm vào giỏ hàng.
     *
     * */

    private void addProducts(){
        iD = bundle.getString("id");
        name = bundle.getString("name");
        image = bundle.getString("image");
        price = bundle.getInt("price",1);
        quantity = bundle.getInt("quantity", 1);

        productsDAO.insertProduct(iD, name, image, price, quantity);
    }


    void checkData(){
        if (bundle != null && !bundle.isEmpty()){
            addProducts();
        }else {
            getCart();
        }
    }


    /**
     *
     * Hiển thị giỏ hàng lên recycleView.
     *
     * */

    private void getCart(){
        dataSP = productsDAO.getProducts();
        adapter_cart = new CART_ADAPTER(this, dataSP);
        rvCart.setAdapter(adapter_cart);
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        rvCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }


    void getTotalPrice(){
        for (PRODUCTS sp : dataSP) {
            totalPrice += sp.getGiasanpham() * sp.getSoluong();
        }
    }


    public void updateTotal(double total) {
        cart_total.setText(total+"Đ");
    }


    void anhxa(){
        cart_total = findViewById(R.id.cart_total);
        checkout = findViewById(R.id.btnCheckout);
        rvCart = findViewById(R.id.rvCart);
        btnBack = findViewById(R.id.btnBack);
        cart_total.setText(totalPrice + "Đ");

        intent = getIntent();
        bundle = intent.getExtras();

        productsDAO = new productsDAO(this);
        context = this;


        try {
            DBHelper dbHelper = new DBHelper(this);
            db = dbHelper.getWritableDatabase();
        }catch (Exception e){
            Toast.makeText(cart_page.this, "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }
}