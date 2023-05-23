package com.example.foodOrderApp.activity;

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
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.utils.SERVER;

import java.util.HashMap;
import java.util.Map;

public class changePassword_page extends AppCompatActivity {

    private EditText edtCurrentPassword, edtNewPassword, edtConfirmPassword;
    private String password, email;
    private String _currentPassword, _newPassword, _confirmPassword;
    private Button btnChangePassword;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_page);
        anhxa();
        eventClick();
    }


    /**
     *
     * Sự kiện Click.
     *
     * */

    void eventClick(){
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
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
     * Thay đổi mật khẩu
     *
     * */

    void changePassword(){
        _currentPassword = edtCurrentPassword.getText().toString().trim();
        _newPassword = edtNewPassword.getText().toString().trim();
        _confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (checkIsEmpty(_currentPassword, _newPassword, _confirmPassword)){
            if (checkCurrentPassword(_currentPassword)){
                if (checkConfirmPassword(_newPassword, _confirmPassword)){
                    updatePassword(_newPassword);
                }else {
                    Toast.makeText(this, "Mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            }
        }else {
            edtCurrentPassword.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            edtNewPassword.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            edtConfirmPassword.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     *
     * Gọi API thay đổi mật khẩu.
     *
     * **/

    void updatePassword(String newPassword){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.changePasswordPath, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    finish();
                } else if (response.equals("Failure")) {
                    Toast.makeText(changePassword_page.this, "lõi", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(changePassword_page.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("email", email);
                data.put("newPassword", newPassword);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }



    boolean checkIsEmpty(String currentPassword, String newPassword, String confirmPassword){
        if (currentPassword.equals("") && newPassword.equals("") && confirmPassword.equals("")){
            return false;
        }
        return true;
    }

    boolean checkCurrentPassword(String currentPassword){
        if (currentPassword.equals(password)){
            return true;
        }
        return false;
    }

    boolean checkConfirmPassword(String newPassword, String confirmPassword){
        if (newPassword.equals(confirmPassword)){
            return true;
        }
        return false;
    }



    void anhxa(){
        edtCurrentPassword = findViewById(R.id.currentPassword);
        edtNewPassword = findViewById(R.id.newPassword);
        edtConfirmPassword = findViewById(R.id.confirmPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        btnBack = findViewById(R.id.btnBack);
        Intent intent = getIntent();
        password = intent.getStringExtra("password");
        email = intent.getStringExtra("email");
    }
}