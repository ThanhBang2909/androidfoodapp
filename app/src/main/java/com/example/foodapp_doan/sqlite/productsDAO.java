package com.example.foodapp_doan.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foodapp_doan.model.PRODUCTS;

import java.util.ArrayList;

public class productsDAO {
    SQLiteDatabase db;

    public productsDAO(Context context){
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<PRODUCTS> getProducts() {
        ArrayList<PRODUCTS> listProducts = new ArrayList<>();
        Cursor cursor = db.query("CART", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            PRODUCTS sp = new PRODUCTS();
            sp.setMasanpham(cursor.getString(0));
            sp.setTensanpham(cursor.getString(1));
            sp.setHinhsanpham(cursor.getString(2));
            sp.setGiasanpham(cursor.getInt(3));
            sp.setSoluong(cursor.getInt(4));
            listProducts.add(sp);
        }
        cursor.close();
        return listProducts;
    }

    public void insertProduct(String id, String name, String image, int price, int quantity) {
        Cursor cursor = db.query("CART", null, "maSanPham=?", new String[]{id}, null, null, null);

        if (cursor.moveToFirst()) {
            // Nếu sản phẩm tồn tại trong giỏ hàng, cập nhật số lượng + 1
            int currentQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));
            int newQuantity = currentQuantity + quantity;

            ContentValues values = new ContentValues();
            values.put("soLuong", newQuantity);

            db.update("CART", values, "maSanPham=?", new String[]{id});

        } else {
            // Nếu Sản phẩm không tồn tại trong giỏ hàng, thêm hàng mới
            ContentValues values = new ContentValues();
            values.put("maSanPham", id);
            values.put("tenSanPham", name);
            values.put("hinhSanPham", image);
            values.put("giaSanPham", price);
            values.put("soLuong", quantity);

            db.insert("CART", null, values);
        }

        cursor.close();
    }

    public void deleteProductById(String id) {
        db.delete("CART", "maSanPham=?", new String[]{id});
        db.close();
    }

    public void updateProductQuantityById(String id, int quantity) {
        ContentValues values = new ContentValues();
        values.put("soLuong", quantity);
        db.update("CART", values, "maSanPham=?", new String[]{id});
    }
}
