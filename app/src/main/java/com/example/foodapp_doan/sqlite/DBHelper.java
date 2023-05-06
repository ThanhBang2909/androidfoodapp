package com.example.foodapp_doan.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static String TenCSDL="QLProducts";
    public static int Version = 1;

    public DBHelper(@Nullable Context context) {
        super(context, TenCSDL, null, Version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE CART (maSanPham TEXT PRIMARY KEY, tenSanPham TEXT, hinhSanPham TEXT, giaSanPham INTEGER, soLuong INTEGER);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
