package com.example.foodappdoan.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.foodappdoan.R;
import com.example.foodappdoan.utils.SERVER;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class edit_profile_page extends AppCompatActivity {

    private ImageView btnBackProfilePage, imgAvartar;
    private EditText profile_fullName, profile_email, profile_phone, profileAddress;
    private Button btnSaveProfile;
    private String email, fullName, Address, phone, avartar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_page);
        anhxa();
        eventClick();
        getProfile();

    }


    void eventClick(){
        btnBackProfilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

    }


    void getProfile(){
        intent = getIntent();
        Bundle bundleProfile = intent.getExtras();
        email = bundleProfile.getString("email");
        fullName = bundleProfile.getString("name");
        Address = bundleProfile.getString("address");
        phone = bundleProfile.getString("phone");
        avartar = bundleProfile.getString("avarta");

        profile_fullName.setText(fullName);
        profileAddress.setText(Address);
        profile_email.setText(email);
        profile_phone.setText(phone);
        Picasso.get().load(SERVER.imgAvartar+avartar).into(imgAvartar);
    }


    void updateProfile(){
        email = profile_email.getText().toString();
        fullName = profile_fullName.getText().toString();
        Address = profileAddress.getText().toString();
        phone = profile_phone.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.updateProfilePath, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    finish();
                } else if (response.equals("Failure")) {
                    Toast.makeText(edit_profile_page.this, "lõi", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(edit_profile_page.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                data.put("fullName", fullName);
                data.put("address", Address);
                data.put("phone", phone);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    void anhxa(){
        btnBackProfilePage = findViewById(R.id.btnBackProfiePage);
        imgAvartar = findViewById(R.id.profile_avartar);
        profile_email = findViewById(R.id.profile_Email);
        profile_fullName = findViewById(R.id.profile_fullName);
        profileAddress = findViewById(R.id.profile_Address);
        profile_phone = findViewById(R.id.profile_phone);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
    }
}