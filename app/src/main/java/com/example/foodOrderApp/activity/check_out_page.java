package com.example.foodOrderApp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodOrderApp.MainActivity;
import com.example.foodOrderApp.R;
import com.example.foodOrderApp.adapter.PRODUCT_CHECKOUT_ADAPTER;
import com.example.foodOrderApp.model.PRODUCTS;
import com.example.foodOrderApp.sqlite.productsDAO;
import com.example.foodOrderApp.utils.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class check_out_page extends AppCompatActivity {

    private RecyclerView rvDataCart;
    public static ArrayList<PRODUCTS> dataSP = new ArrayList<>();
    private productsDAO productsDAO;
    private PRODUCT_CHECKOUT_ADAPTER product_checkout_adapter;
    private ImageView btnBack;
    private String cart_total;
    private TextView tvCartTotal, namePay, phonePay,addressPay;
    private LinearLayout layoutAddressDelivery;
    private Button btnCheckOut;
    public SharedPreferences sharedPreferences;
    private String email;
    private String message ;
    private String subject = "Đặt hàng thành công.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_out_page);
        anhxa();
        eventClick();
        getAddress();
        getProductCart();
    }


    /**
     *
     * Sự kiện Click.
     *
     * */

    private void eventClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        layoutAddressDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(check_out_page.this, address_delivery_page.class);
                startActivity(intent);
            }
        });


        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkout();
            }
        });


    }

    /**
     *
     * Hiển thị sản phẩm có trong giỏ hàng.
     *
     * */

    private void getProductCart(){
        productsDAO = new productsDAO(this);
        dataSP = productsDAO.getProducts();

        product_checkout_adapter = new PRODUCT_CHECKOUT_ADAPTER(this, dataSP);
        rvDataCart.setAdapter(product_checkout_adapter);
        rvDataCart.setLayoutManager(new LinearLayoutManager(this));
        rvDataCart.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    /**
     *
     * Hiển thị thông tin giao hàng
     *
     * */

    private void getAddress(){
        Intent intent = getIntent();
        namePay.setText(intent.getStringExtra("nameDelivery"));
        addressPay.setText(intent.getStringExtra("addressDelivery"));
        phonePay.setText(intent.getStringExtra("phoneDelivery"));
    }

    /**
     *
     * Thanh toán .
     * Gọi API đưa giỏ hàng lên server.
     *
     * */


    private void checkout(){
        final String name = namePay.getText().toString().trim();
        final String address = addressPay.getText().toString().trim();
        final String phone = phonePay.getText().toString().trim();
        if(name.length()>0&& address.length()>0&&phone.length()>0){
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.billPath, new Response.Listener<String>() {
                @Override
                public void onResponse(final String madonhang) {
                    if(Integer.parseInt(madonhang)>0){
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest request = new StringRequest(Request.Method.POST, SERVER.billdetailPath, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("1")) {
                                    dataSP.clear();
                                    productsDAO.reloadcart();
                                    message = "Đặt hàng thành công.\nMã đơn hàng là: "+madonhang+
                                            "\nTên người nhận là: "+name+
                                            "\nSố điện thoại người nhận là : "+phone+
                                            "\nĐịa chỉ giao hàng là: "+address+
                                            "\nSố tiền thanh toán là: "+cart_total;
                                    sentMail(email,message,subject);
                                    showDialog();
                                }else{
                                    Toast.makeText(check_out_page.this, "Du lieu gio hang da bi loi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                JSONArray jsonArray = new JSONArray();
                                for(int i=0;i<dataSP.size();i++)
                                {
                                    JSONObject jsonObject = new JSONObject();
                                    try {
                                        jsonObject.put("maHD",madonhang);
                                        jsonObject.put("maSanPham",dataSP.get(i).getMasanpham());
                                        jsonObject.put("tenSanPham",dataSP.get(i).getTensanpham());
                                        jsonObject.put("giaSanPham",dataSP.get(i).getGiasanpham());
                                        jsonObject.put("hinhSanPham",dataSP.get(i).getHinhsanpham());
                                        jsonObject.put("soLuong",dataSP.get(i).getSoluong());
                                        jsonObject.put("thanhTien",dataSP.get(i).getGiasanpham() * dataSP.get(i).getSoluong());

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    jsonArray.put(jsonObject);
                                }
                                HashMap<String,String> hashMap = new HashMap<String, String>();
                                hashMap.put("json",jsonArray.toString());
                                return hashMap;
                            }
                        };
                        queue.add(request);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String,String> hashMap= new HashMap<String,String>();
                    hashMap.put("customername",name);
                    hashMap.put("phonenumber",phone);
                    hashMap.put("customeraddress",address);
                    hashMap.put("customertotalprice",cart_total);
                    hashMap.put("customertolstatus","0");
                    hashMap.put("email",email);
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Kiem Tra Lai Du Lieu", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     *
     * Gửi mail xác nhận khi đặt hàng thành công.
     *
     * */

    private void sentMail(String email, String message, String subject){
        String url = SERVER.sendMail+"?email="+email+"&&message="+message+"&&subject="+subject;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Tạo một StringRequest để gửi email
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Xử lý phản hồi từ server nếu cần
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi nếu có
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("subject", subject);
                params.put("message", message);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


    /**
     *
     * Show Dialog khi mua hàng thành công.
     *
     * */

    private void showDialog(){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_congratulations);
        Window window = dialog.getWindow();

        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribue = window.getAttributes();
        windowAttribue.gravity = Gravity.TOP;
        window.setAttributes(windowAttribue);
        dialog.setCancelable(true);


        Button btnContinue = dialog.findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        dialog.show();
    }



    void anhxa(){
        rvDataCart = findViewById(R.id.rvCart);
        btnBack = findViewById(R.id.btnBack);
        tvCartTotal = findViewById(R.id.moneyPay);
        namePay = findViewById(R.id.namePay);
        phonePay = findViewById(R.id.phonePay);
        addressPay = findViewById(R.id.addressPay);
        layoutAddressDelivery = findViewById(R.id.layoutAddressDelivery);
        btnCheckOut = findViewById(R.id.btnCheckOut);

        cart_total = cart_page.cart_total.getText().toString().trim();
        tvCartTotal.setText(cart_total);

        sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email","");

    }
}