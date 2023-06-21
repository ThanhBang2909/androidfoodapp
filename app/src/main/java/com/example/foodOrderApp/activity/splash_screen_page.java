package com.example.foodOrderApp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.foodOrderApp.R;
import com.example.foodOrderApp.utils.CheckInternetConnection;

public class splash_screen_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_page);

        if (CheckInternetConnection.isNetworkAvailable(this)){
            Thread myThread = new Thread(){
                @Override
                public void run(){
                    try {
                        sleep(2000);
                        Intent intent = new Intent(getApplicationContext(), login_page.class);
                        startActivity(intent);
                        finish ();
                    } catch (InterruptedException e)
                    { e.printStackTrace();}
                }
            };
            myThread.start();
        }else {
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}