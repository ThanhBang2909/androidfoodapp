package com.example.foodOrderApp.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.adapter.NOTIFICATION_ADAPTER;
import com.example.foodOrderApp.adapter.PRODUCT_CHECKOUT_ADAPTER;
import com.example.foodOrderApp.model.CATEGORY;
import com.example.foodOrderApp.model.NOTIFICATION;
import com.example.foodOrderApp.utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class notification_page extends Fragment {

    private RecyclerView rvNotification;
    private ArrayList<NOTIFICATION> mListNotification = new ArrayList<>();
    private NOTIFICATION_ADAPTER notification_adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        getListNotification();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_page, container, false);

        return view;
    }


    /**
     *
     * GỌI API LẤY THÔNG BÁO TỪ DATABASE
     *
     * */

    private void getListNotification(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        mListNotification.add(new NOTIFICATION(jsonObject.getString("tittle"),
                                jsonObject.getString("body")));
                        notification_adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "Lỗi", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.getNotification, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }



    private void anhxa(View view){
        rvNotification = view.findViewById(R.id.rvNotification);


        notification_adapter = new NOTIFICATION_ADAPTER(getContext(), mListNotification);
        rvNotification.setAdapter(notification_adapter);
        rvNotification.setLayoutManager(new LinearLayoutManager(getContext()));
        rvNotification.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }
}
