package com.example.foodapp_doan.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.foodapp_doan.utils.SERVER;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class login_page extends AppCompatActivity {

    private EditText emailLogin, passwordLogin;
    private String email, password;
    private Button btnLogin, btnBackRegister;
    public static SharedPreferences sharedPreferences ;
    public static boolean isFirtstTimeLogin;
    private LinearLayout Sign_GG;

    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        anhxa();
        checkLogin();
        eventClick();
    }

    void eventClick(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_page.this, register_page.class);
                startActivity(intent);
                finish();
            }
        });

        Sign_GG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    void checkLogin(){
        isFirtstTimeLogin = sharedPreferences.getBoolean("isFirstTimeLogin", true);

        if (isFirtstTimeLogin){

        }else {
            navigationToMainActivity();
        }
    }

    void login(){
        email = emailLogin.getText().toString().trim();
        password = passwordLogin.getText().toString().trim();
        if (!email.equals("") && !password.equals("")) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.login, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Success")) {
                        Intent intent = new Intent(login_page.this, MainActivity.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putBoolean("isFirstTimeLogin", false);
                        editor.apply();
                        startActivity(intent);
                        finish();
                    } else if (response.equals("Failure")) {
                        Toast.makeText(login_page.this, "Email hoặc password không đúng", Toast.LENGTH_SHORT).show();
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
            emailLogin.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            passwordLogin.setBackground(getResources().getDrawable(R.drawable.bg_edt2));
            Toast.makeText(login_page.this, "Vui lòng nhập đầy đủ email và password", Toast.LENGTH_SHORT).show();
        }
    }

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleResult(task);
            }
        }
        
    });

    private void handleResult(Task<GoogleSignInAccount> task) {
        task.addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
            @Override
            public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                navigationToMainActivity();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login_page.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        launcher.launch(signInIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null){
            Toast.makeText(this, account.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }

    void navigationToMainActivity(){
        Intent intent = new Intent(login_page.this, MainActivity.class);
        startActivity(intent);
    }

    void anhxa(){
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnBackRegister = findViewById(R.id.btnBackRegister);
        Sign_GG = findViewById(R.id.Sign_GG);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        email = password = "";
        sharedPreferences = getSharedPreferences("myPrefs",MODE_PRIVATE);
    }
}