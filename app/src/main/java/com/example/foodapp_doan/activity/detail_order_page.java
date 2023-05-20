package com.example.foodapp_doan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp_doan.R;
import com.example.foodapp_doan.adapter.PRODUCT_CHECKOUT_ADAPTER;
import com.example.foodapp_doan.model.BILL;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detail_order_page extends AppCompatActivity {

    private RecyclerView rvCartOrder;
    private ArrayList<PRODUCTS> dataOrder = new ArrayList<>();
    private PRODUCT_CHECKOUT_ADAPTER adapter_detail_order;
    private String name, phone, address;
    private int maHD;
    private Intent intent ;
    private TextView namePay, phonePay, addressPay, moneyPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_page);
        anhxa();
        eventClick();
        getCustomer();
        getCartOrder(maHD);
    }


    private void getCustomer(){
        name = intent.getStringExtra("nameOrder");
        phone = intent.getStringExtra("phoneOrder");
        address = intent.getStringExtra("addressOrder");

        namePay.setText(name);
        phonePay.setText(phone);
        addressPay.setText(address);
    }


    private void getCartOrder(int maHD){
        String url = SERVER.orderDetailPath+"?maHD='"+maHD+"'";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataOrder.add(new PRODUCTS(jsonObject.getString("maSanPham"),
                                jsonObject.getString("tenSanPham"),
                                jsonObject.getInt("giaSanPham"),
                                jsonObject.getString("hinhSanPham"),
                                jsonObject.getInt("soLuong"),
                                jsonObject.getInt("thanhTien")));
                        moneyPay.setText(jsonObject.getString("thanhTien")+"");
                        adapter_detail_order.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(detail_order_page.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(detail_order_page.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }


    private void eventClick(){

    }


    void anhxa(){
        rvCartOrder = findViewById(R.id.rvCartOrder);
        namePay = findViewById(R.id.namePay);
        phonePay = findViewById(R.id.phonePay);
        addressPay = findViewById(R.id.addressPay);
        moneyPay = findViewById(R.id.moneyPay);
        intent = getIntent();
        maHD = intent.getIntExtra("maHD",1);

        adapter_detail_order = new PRODUCT_CHECKOUT_ADAPTER(this, dataOrder);
        rvCartOrder.setAdapter(adapter_detail_order);
        rvCartOrder.setLayoutManager(new LinearLayoutManager(this));
        rvCartOrder.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}