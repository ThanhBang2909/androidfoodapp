package com.example.foodOrderApp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodOrderApp.R;

public class detail_notification_page extends AppCompatActivity {

    private ImageView btnBack;
    private TextView tvTittle, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_notification_page);

        anhxa();
        eventClick();
        getNotification();

    }

    /**
     *
     * SỰ KIỆN CLICK
     *
     * */


    private void eventClick(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    /**
     *
     * HIỂN THỊ NỘI DUNG THÔNG BÁO LÊN VIEW
     *
     * */

    private void getNotification(){
        Intent intent = getIntent();

        tvTittle.setText(intent.getStringExtra("tittle"));
        body.setText(intent.getStringExtra("body"));
    }


    private void anhxa(){
        btnBack = findViewById(R.id.btnBack);
        tvTittle = findViewById(R.id.tvTittle);
        body = findViewById(R.id.bodyNotification);
    }
}