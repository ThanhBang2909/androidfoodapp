package com.example.foodapp_doan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp_doan.R;
import com.example.foodapp_doan.adapter.ADDRESS_DELIVERY_ADAPTER;
import com.example.foodapp_doan.model.USERS;
import com.example.foodapp_doan.utils.SERVER;
import com.squareup.picasso.Picasso;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_delivery_page);
        anhxa();
        email = "abc";
        getAddressDelivery(email);
        eventClick();
    }

    public void getAddressDelivery(String email){
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

        adapter = new ADDRESS_DELIVERY_ADAPTER(this, data);
        rvAddressDelivery.setAdapter(adapter);
        rvAddressDelivery.setLayoutManager(new LinearLayoutManager(this));
        rvAddressDelivery.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    void eventClick(){

    }

    void anhxa(){
        rvAddressDelivery = findViewById(R.id.rvAddressDelivery);
        sharedPreferences =getApplicationContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        //email = sharedPreferences.getString("email","");
    }
}