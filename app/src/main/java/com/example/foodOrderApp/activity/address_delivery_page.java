package com.example.foodOrderApp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.adapter.ADDRESS_DELIVERY_ADAPTER;
import com.example.foodOrderApp.model.USERS;
import com.example.foodOrderApp.utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class address_delivery_page extends AppCompatActivity {

    private RecyclerView rvAddressDelivery;
    private String email, name, phone, address;
    private ArrayList<USERS> data = new ArrayList<>();
    private ADDRESS_DELIVERY_ADAPTER adapter;
    public SharedPreferences sharedPreferences;
    private TextView add_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_delivery_page);
        anhxa();
        getAddressDelivery(email);
        eventClick();
    }


    /**
     *
     * Hiển thị các thông tin địa chỉ giao hàng dựa vào email truyền vào.
     * Email được lấy từ SharedPreferences.
     *
     * */


    void getAddressDelivery(String email){
        String url = SERVER.getAddressDeliveryPath+"?email='"+email+"'";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        data.add(new USERS(jsonObject.getString("fullName"),
                                jsonObject.getString("address"),
                                jsonObject.getString("phone")));
                        adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(address_delivery_page.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(address_delivery_page.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

    }


    /**
     *
     * Sự kiện Click
     *
     * */

    void eventClick(){
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(address_delivery_page.this, add_address_page.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void anhxa(){
        rvAddressDelivery = findViewById(R.id.rvAddressDelivery);
        add_address = findViewById(R.id.insertAddress);

        adapter = new ADDRESS_DELIVERY_ADAPTER(this, data);
        rvAddressDelivery.setAdapter(adapter);
        rvAddressDelivery.setLayoutManager(new LinearLayoutManager(this));
        rvAddressDelivery.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        sharedPreferences =getApplicationContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
    }
}