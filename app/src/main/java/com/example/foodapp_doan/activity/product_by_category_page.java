package com.example.foodapp_doan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp_doan.R;
import com.example.foodapp_doan.adapter.ProductByCategory_ADAPTER;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class product_by_category_page extends AppCompatActivity {

    private ProductByCategory_ADAPTER productByCategory_adapter;
    private ArrayList<PRODUCTS> dataProducts = new ArrayList<>();
    private RecyclerView rvProductByCategory;
    private ImageView btnBack;
    private String macd;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_by_category_page);
        anhxa();
        intent = getIntent();
        macd = intent.getStringExtra("macd");
        loadProduct(macd);
        eventClick();
    }

    public void loadProduct(String macd){
        String url = SERVER.productBycategory+"?macd='"+macd+"'";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataProducts.add(new PRODUCTS(jsonObject.getString("masp"),
                                jsonObject.getString("machude"),
                                jsonObject.getString("tensp"),
                                jsonObject.getString("hinhsp"),
                                jsonObject.getInt("giasp")));
                    } catch (JSONException e) {
                        Toast.makeText(product_by_category_page.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(product_by_category_page.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        productByCategory_adapter = new ProductByCategory_ADAPTER(this, dataProducts);
        rvProductByCategory.setAdapter(productByCategory_adapter);
        rvProductByCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
    }

    void eventClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void anhxa(){
        rvProductByCategory = findViewById(R.id.rvProductByCategory);
        btnBack = findViewById(R.id.btnBack);
    }
}