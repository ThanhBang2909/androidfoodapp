package com.example.foodOrderApp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.adapter.CATEGORY_ADAPTER;
import com.example.foodOrderApp.adapter.PRODUCT_ADAPTER;
import com.example.foodOrderApp.adapter.SLIDES_ADAPTER;
import com.example.foodOrderApp.model.CATEGORY;
import com.example.foodOrderApp.model.PRODUCTS;
import com.example.foodOrderApp.model.SLIDES;
import com.example.foodOrderApp.utils.SERVER;

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

    private ImageView btnCart;

    private CATEGORY_ADAPTER category_adapter;
    private ArrayList<CATEGORY> dataCategory = new ArrayList<>();
    private RecyclerView recyclerViewCategory;

    private PRODUCT_ADAPTER product_adapter;
    private ArrayList<PRODUCTS> dataProduct = new ArrayList<>();
    private RecyclerView recyclerViewProduct;

    private EditText edtSeachProducts;

    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;

    int page = 1;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        loadCategory();
        //loadProduct();
        getDataFromAPI(page);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, container, false);
        anhxa(view);
        loadSlides();
        eventClick();
        seachProducts();
        return view;
    }


    /**
     *
     * Load hình ảnh cho slide
     *
     * */


    void loadSlides(){
        dataSlides = new ArrayList<>();
        dataSlides.add(new SLIDES(SERVER.slidepath+"slides2.jpeg", "link"));
        dataSlides.add(new SLIDES(SERVER.slidepath+"slides3.jpeg", "link"));
        dataSlides.add(new SLIDES(SERVER.slidepath+"slides4.png", "link"));
        dataSlides.add(new SLIDES(SERVER.slidepath+"slides5.jpeg", "link"));

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


    /**
     *
     * Load chủ đề sản phẩm
     *
     * */

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
                        category_adapter.notifyDataSetChanged();
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

    }


    /**
     *
     * Load product
     *
     * */

    private void loadProduct(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataProduct.add(new PRODUCTS(jsonObject.getString("masp"),
                                jsonObject.getString("macd"),
                                jsonObject.getString("tensp"),
                                jsonObject.getString("hinhsp"),
                                jsonObject.getInt("giasp"),
                                jsonObject.getString("mota")));
                        product_adapter.notifyDataSetChanged();
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
    }

    /**
     *
     * Sự kiện Click
     *
     * */

    private void eventClick(){
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), cart_page.class);
                startActivity(intent);
            }
        });


        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                    getDataFromAPI(page);
                    product_adapter.notifyDataSetChanged();
                }
            }
        });
    }

    /**
     *
     * Seach sản phẩm
     *
     * */


    private void seachProducts(){
        edtSeachProducts.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String chuoitim = charSequence.toString();
                product_adapter.getFilter().filter(chuoitim);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    /**
     *
     * LOAD PRODUCT THEO PAGE
     *
     * */

    private void getDataFromAPI(int page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, SERVER.test+page, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray dataArray = response.getJSONArray("products");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject jsonObject = dataArray.getJSONObject(i);
                        dataProduct.add(new PRODUCTS(jsonObject.getString("maSanPham"),
                                jsonObject.getString("maChuDe"),
                                jsonObject.getString("tenSanPham"),
                                jsonObject.getString("hinhSanPham"),
                                jsonObject.getInt("giaSanPham"),
                                jsonObject.getString("mota")));
                        product_adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // handling on error listener method.
                Toast.makeText(getContext(), "Fail to get data..", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }



    void anhxa(View view){
        viewPager2 = view.findViewById(R.id.home_viewPager);
        recyclerViewCategory = view.findViewById(R.id.home_category);
        recyclerViewProduct = view.findViewById(R.id.home_products);
        btnCart = view.findViewById(R.id.btnCart);
        edtSeachProducts = view.findViewById(R.id.home_edtSeach);

        loadingPB = view.findViewById(R.id.idPBLoading);
        nestedSV = view.findViewById(R.id.homePage);

        product_adapter = new PRODUCT_ADAPTER(getContext(), dataProduct);
        recyclerViewProduct.setAdapter(product_adapter);
        recyclerViewProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));

        category_adapter = new CATEGORY_ADAPTER(getContext(), dataCategory);
        recyclerViewCategory.setAdapter(category_adapter);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false ));

    }

}


