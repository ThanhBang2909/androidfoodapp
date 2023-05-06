package com.example.foodapp_doan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp_doan.R;
import com.example.foodapp_doan.utils.SERVER;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class profile_page extends Fragment {

    private LinearLayout editProfile;
    private String email;
    private Intent intent;
    private ImageView imgAvartar;
    private String fullName, phone, Address, avartar, password;
    private TextView tvFullNames;
    private TextView changePassword;

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
                Intent intent = new Intent(getActivity(), changePassword_page.class);
                startActivity(intent);
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
                        avartar = jsonObject.getString("avarta");

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

    void anhxa(View view){
        editProfile = view.findViewById(R.id.editProfile);
        imgAvartar = view.findViewById(R.id.imgAvartar);
        tvFullNames = view.findViewById(R.id.tvNameCus);
        changePassword = view.findViewById(R.id.changePassword);
        email = login_page.emailUser;
    }
}
