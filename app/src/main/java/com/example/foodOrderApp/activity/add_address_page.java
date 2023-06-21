package com.example.foodOrderApp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.utils.SERVER;

import java.util.HashMap;
import java.util.Map;

public class add_address_page extends AppCompatActivity {

    private EditText edtFullName, edtAddress, edtPhone;
    private String fullName, address, phone;
    private Button btnSaveAddress;
    private String email;
    private SharedPreferences sharedPreferences;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_address_page);
        anhxa();
        eventClick();
    }

    /**
     *
     * Sự kiện Click
     *
     * */

    private void eventClick(){
        btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_address_delivery(email);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     *
     *
     * Thêm địa chỉ giao hàng theo email được truyền vào.
     * Email được lấy từ SharedPreferences.
     *
     * */

    private void add_address_delivery(String email){
        fullName = edtFullName.getText().toString().trim();
        address = edtAddress.getText().toString().trim();
        phone = edtPhone.getText().toString().trim();

        if(!fullName.equals("") && !address.equals("") && !phone.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.add_address_delivery, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Success")) {
                        finish();
                    } else if (response.equals("Failure")) {
                        Toast.makeText(add_address_page.this, "lõi", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(add_address_page.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", email);
                    data.put("fullName", fullName);
                    data.put("address", address);
                    data.put("phone", phone);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(this, "Các trường k để trống", Toast.LENGTH_SHORT).show();
        }

    }

    void anhxa(){
        edtFullName = findViewById(R.id.add_address_fullName);
        edtAddress = findViewById(R.id.add_address);
        edtPhone = findViewById(R.id.add_address_phone);
        btnSaveAddress = findViewById(R.id.btnSaveAddress);
        btnBack = findViewById(R.id.btnBack);
        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
    }
}