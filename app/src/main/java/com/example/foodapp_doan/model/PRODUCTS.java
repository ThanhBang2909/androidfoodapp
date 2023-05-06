package com.example.foodapp_doan.model;

import java.io.Serializable;

public class PRODUCTS implements Serializable {

    private String masanpham, machude, tensanpham, hinhsanpham;
    private int giasanpham, soluong;

    public PRODUCTS(String masanpham, String machude, String tensanpham, String hinhsanpham, int giasanpham) {
        this.masanpham = masanpham;
        this.machude = machude;
        this.tensanpham = tensanpham;
        this.hinhsanpham = hinhsanpham;
        this.giasanpham = giasanpham;
    }

    public PRODUCTS(String tensanpham, String hinhsanpham, int giasanpham) {
        this.tensanpham = tensanpham;
        this.hinhsanpham = hinhsanpham;
        this.giasanpham = giasanpham;
    }

    public PRODUCTS(String masanpham,  String tensanpham, String hinhsanpham, int giasanpham, int soluong) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.hinhsanpham = hinhsanpham;
        this.giasanpham = giasanpham;
        this.soluong = soluong;
    }

    public PRODUCTS(String masanpham, String machude, String tensanpham, String hinhsanpham, int giasanpham, int soluong) {
        this.masanpham = masanpham;
        this.machude = machude;
        this.tensanpham = tensanpham;
        this.hinhsanpham = hinhsanpham;
        this.giasanpham = giasanpham;
        this.soluong = soluong;
    }

    public PRODUCTS(String masanpham, String tensanpham, String hinhsanpham, int giasanpham) {
        this.masanpham = masanpham;
        this.tensanpham = tensanpham;
        this.hinhsanpham = hinhsanpham;
        this.giasanpham = giasanpham;
    }

    public PRODUCTS() {

    }


    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(String masanpham) {
        this.masanpham = masanpham;
    }

    public String getMachude() {
        return machude;
    }

    public void setMachude(String machude) {
        this.machude = machude;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }

    public String getHinhsanpham() {
        return hinhsanpham;
    }

    public void setHinhsanpham(String hinhsanpham) {
        this.hinhsanpham = hinhsanpham;
    }

    public int getGiasanpham() {
        return giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        this.giasanpham = giasanpham;
    }
}
