package com.example.foodapp_doan.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp_doan.R;
import com.example.foodapp_doan.adapter.CATEGORY_ADAPTER;
import com.example.foodapp_doan.adapter.PRODUCT_ADAPTER;
import com.example.foodapp_doan.adapter.SLIDES_ADAPTER;
import com.example.foodapp_doan.model.CATEGORY;
import com.example.foodapp_doan.model.PRODUCTS;
import com.example.foodapp_doan.model.SLIDES;
import com.example.foodapp_doan.utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class home_page extends Fragment {

    private ViewPager2 viewPager2;
    private List<SLIDES> dataSlides;
    private Handler handler = new Handler();
    private SLIDES_ADAPTER slides_adapter;

    private CATEGORY_ADAPTER category_adapter;
    private ArrayList<CATEGORY> dataCategory = new ArrayList<>();
    private RecyclerView recyclerViewCategory;

    private PRODUCT_ADAPTER product_adapter;
    private ArrayList<PRODUCTS> dataProduct = new ArrayList<>();
    private RecyclerView recyclerViewProduct;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        loadCategory();
        loadProduct();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, container, false);
        anhxa(view);
        loadSlides();

        return view;
    }

    void loadSlides(){
        dataSlides = new ArrayList<>();
        dataSlides.add(new SLIDES(SERVER.slidepath+"slides01.png", "link"));
        dataSlides.add(new SLIDES(SERVER.slidepath+"slides02.jpeg", "link"));
        dataSlides.add(new SLIDES(SERVER.slidepath+"slides03.jpeg", "link"));

        slides_adapter = new SLIDES_ADAPTER(dataSlides, getContext());
        viewPager2.setAdapter(slides_adapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2.getCurrentItem() == dataSlides.size() - 1){
                viewPager2.setCurrentItem(0);
            }else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }
        }
    };

    void loadCategory() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataCategory.add(new CATEGORY(jsonObject.getString("macd"),
                                jsonObject.getString("tencd"),
                                jsonObject.getString("hinhcd")));
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
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.category, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        category_adapter = new CATEGORY_ADAPTER(getContext(), dataCategory);
        recyclerViewCategory.setAdapter(category_adapter);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false ));
    }

    void loadProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataProduct.add(new PRODUCTS(jsonObject.getString("masp"),
                                jsonObject.getString("machude"),
                                jsonObject.getString("tensp"),
                                jsonObject.getString("hinhsp"),
                                jsonObject.getInt("giasp")));
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), "Lỗi ", Toast.LENGTH_LONG).show();
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
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.productpath, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);

        product_adapter = new PRODUCT_ADAPTER(getContext(), dataProduct);
        recyclerViewProduct.setAdapter(product_adapter);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    void anhxa(View view){
        viewPager2 = view.findViewById(R.id.home_viewPager);
        recyclerViewCategory = view.findViewById(R.id.home_category);
        recyclerViewProduct = view.findViewById(R.id.home_products);
    }
}