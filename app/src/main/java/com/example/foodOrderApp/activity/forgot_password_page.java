package com.example.foodOrderApp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class forgot_password_page extends AppCompatActivity {

    private EditText edtMail, edtNewPass;
    private Button btnSent;
    private String mail, newPass;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_page);
        anhxa();
        eventClick();
    }

    /**
     *
     * Gọi API chức năng quên mật khẩu
     *
     * */

    private void forgotPassword(){
        mail = edtMail.getText().toString().trim();
        newPass = edtNewPass.getText().toString().trim();

        if (!mail.equals("") && !newPass.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.changePasswordPath, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Success")) {
                        finish();
                    } else if (response.equals("Failure")) {
                        Toast.makeText(forgot_password_page.this, "Email sai", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(forgot_password_page.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email", mail);
                    data.put("newPassword", newPass);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else {
            Toast.makeText(this, "Mời nhập đủ các trường", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     *
     * Sự kiện Click.
     *
     * */

    private void eventClick(){
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void anhxa(){
        edtMail = findViewById(R.id.emailForgot);
        edtNewPass = findViewById(R.id.newPassForgot);
        btnSent = findViewById(R.id.btnSendMail);
        btnBack = findViewById(R.id.btnBack);
    }
}