package com.example.foodapp_doan.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp_doan.MainActivity;
import com.example.foodapp_doan.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class login_page extends AppCompatActivity {

    private EditText emailLogin, passwordLogin;
    private String email, password;
    private Button btnLogin, btnBackRegister;
    private String URL = "http://192.168.44.113:8080/csdlfoodapp/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        anhxa();
        checkLogin();
        eventClick();
    }

    void eventClick(){
        btnBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, register_page.class);
                startActivity(intent);
                finish();
            }
        });

    }

    void checkLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs",MODE_PRIVATE);
        boolean isFirtstTimeLogin = sharedPreferences.getBoolean("isFirstTimeLogin", true);

        if (isFirtstTimeLogin){
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = emailLogin.getText().toString().trim();
                    password = passwordLogin.getText().toString().trim();
                    if (!email.equals("") && !password.equals("")) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("Success")) {
                                    Intent intent = new Intent(login_page.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else if (response.equals("Failure")) {
                                    Toast.makeText(login_page.this, "Sai Email hoặc Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(login_page.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> data = new HashMap<>();
                                data.put("email", email);
                                data.put("password", password);
                                return data;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }else{
                        Toast.makeText(login_page.this, "Các trường không thể trống", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstTimeLogin", false);
            editor.apply();
        }else {
            Intent intent = new Intent(login_page.this, MainActivity.class);
            startActivity(intent);
        }
    }

    void anhxa(){
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnBackRegister = findViewById(R.id.btnBackRegister);
        email = password = "";
    }
}