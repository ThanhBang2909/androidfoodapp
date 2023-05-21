package com.example.foodappdoan.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodappdoan.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.foodappdoan.utils.SERVER;


public class register_page extends AppCompatActivity {

    private EditText emailReg, passwordReg, fullNameReg, addressReg, phoneReg;
    private String email, password, fullName, address, phone;
    private Button btnReg;
    private TextView  tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        anhxa();
        eventClick();
    }

    void eventClick(){
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    void register(){
        fullName = fullNameReg.getText().toString().trim();
        email = emailReg.getText().toString().trim();
        password = passwordReg.getText().toString().trim();
        address = addressReg.getText().toString().trim();
        phone = phoneReg.getText().toString().trim();

        if (!email.equals("") && !password.equals("") && !fullName.equals("") && !address.equals("") && !phone.equals("")) {
            if (isValidPassword(password)) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.register, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")) {
                            Toast.makeText(register_page.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register_page.this, login_page.class);
                            startActivity(intent);
                            finish();
                        } else if (response.equals("Failure")) {
                            Toast.makeText(register_page.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("email", email);
                        data.put("password", password);
                        data.put("fullName", fullName);
                        data.put("address", address);
                        data.put("phone", phone);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            } else {
                Toast.makeText(register_page.this, "Mật khẩu phải từ 4 kí tự có kí tự thường, hoa, số, và kí tự đặc biệt", Toast.LENGTH_SHORT).show();
            }
        } else {
            emailReg.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            passwordReg.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            fullNameReg.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            addressReg.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            phoneReg.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            Toast.makeText(register_page.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }


    void anhxa(){
        emailReg = findViewById(R.id.emailReg);
        passwordReg = findViewById(R.id.passwordReg);
        fullNameReg = findViewById(R.id.fullNameReg);
        addressReg = findViewById(R.id.addressReg);
        phoneReg = findViewById(R.id.phoneReg);
        btnReg = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);
        email = password = fullName = address = phone = "";
    }

}