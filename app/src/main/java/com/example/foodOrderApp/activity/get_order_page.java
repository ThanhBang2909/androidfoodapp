package com.example.foodOrderApp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.adapter.ORDER_PAGE_ADAPTER;
import com.example.foodOrderApp.model.BILL;
import com.example.foodOrderApp.utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class get_order_page extends AppCompatActivity {

    private ArrayList<BILL> dataOrder = new ArrayList<>();
    private ORDER_PAGE_ADAPTER order_page_adapter;
    private RecyclerView rvOrderPage;
    public SharedPreferences sharedPreferences;
    private String email;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_order_page);
        anhxa();
        eventClick();
        getDataOrder(email);
    }

    /**
     *
     * Load lịch sử mua hàng.
     *
     * */

    private void getDataOrder(String email){
        String url = SERVER.orderPath+"?email='"+email+"'";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataOrder.add(new BILL(jsonObject.getInt("maHD"),
                                jsonObject.getString("name"),
                                jsonObject.getString("phone"),
                                jsonObject.getString("address"),
                                jsonObject.getInt("thanhTien"),
                                jsonObject.getInt("status")));
                        order_page_adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(get_order_page.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(get_order_page.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
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
    }


    void anhxa(){
        rvOrderPage = findViewById(R.id.rvOrderPage);
        btnBack = findViewById(R.id.btnBack);

        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");

        order_page_adapter = new ORDER_PAGE_ADAPTER(this, dataOrder);
        rvOrderPage.setAdapter(order_page_adapter);
        rvOrderPage.setLayoutManager(new LinearLayoutManager(this));
        rvOrderPage.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}