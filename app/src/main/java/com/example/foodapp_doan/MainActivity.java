package com.example.foodapp_doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodapp_doan.activity.edit_profile_page;
import com.example.foodapp_doan.activity.home_page;
import com.example.foodapp_doan.activity.notification_page;
import com.example.foodapp_doan.activity.profile_page;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        OpenFragment(new home_page());
        eventClick();
    }

    void eventClick(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.mnuHome:
                        fragment = new home_page();
                        break;
                    case R.id.mnuNotifications:
                        fragment = new notification_page();
                        break;
                    case R.id.mnuProfile:
                        fragment = new profile_page();
                        break;
                }
                OpenFragment(fragment);
                return true;
            }
        });
    }

    void OpenFragment(Fragment f) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.bottom_nav_framelayout, f);
        transaction.commit();
    }

    void anhxa(){
        bottomNavigationView = findViewById(R.id.bottomnavigation);
    }
}