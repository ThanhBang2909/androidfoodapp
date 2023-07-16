package com.example.foodOrderApp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.model.USERS;
import com.example.foodOrderApp.sqlite.productsDAO;
import com.example.foodOrderApp.utils.SERVER;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class profile_page extends Fragment {

    private LinearLayout editProfile ;
    public static String email;
    private ImageView imgAvartar;
    private String fullName, phone, Address, avartar, password;
    private TextView tvFullNames;
    private TextView changePassword , Transaction_history;
    private Button btnSignOut;
    public SharedPreferences sharedPreferences;
    private productsDAO productsDAO;
    public static ArrayList<USERS> users = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_page, container, false);
        anhxa(view);
        eventClick();
        GetUserByEmail(email);
        return view;
    }



    void eventClick(){
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), edit_profile_page.class);
                Bundle bundleProfile = new Bundle();
                bundleProfile.putString("avarta", avartar);
                bundleProfile.putString("name", fullName);
                bundleProfile.putString("address", Address);
                bundleProfile.putString("phone", phone);
                bundleProfile.putString("email", email);
                intent.putExtras(bundleProfile);
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), changePassword_page.class);
                intent.putExtra("password", password);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        Transaction_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), get_order_page.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();

            }
        });

    }

    void GetUserByEmail(String email){
        String url = SERVER.loadUser+"?email='"+email+"'";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        fullName = jsonObject.getString("fullName");
                        password = jsonObject.getString("password");
                        Address = jsonObject.getString("address");
                        phone = jsonObject.getString("phone");
                        avartar = jsonObject.getString("avartar");

                        Picasso.get().load(SERVER.imgAvartar+avartar).into(imgAvartar);
                        tvFullNames.setText(fullName);

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }

    void notification(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setMessage("Bạn có muốn đăng xuất ứng dụng không");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), login_page.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isFirstTimeLogin", true);
                        editor.clear();
                        editor.apply();
                        startActivity(intent);
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    void anhxa(View view){
        editProfile = view.findViewById(R.id.editProfile);
        imgAvartar = view.findViewById(R.id.imgAvartar);
        tvFullNames = view.findViewById(R.id.tvNameCus);
        changePassword = view.findViewById(R.id.changePassword);
        btnSignOut = view.findViewById(R.id.btnSignOut);
        Transaction_history = view.findViewById(R.id.Transaction_history);

        sharedPreferences = getContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        users.add(new USERS(fullName,Address,phone,avartar,password));
    }
}
