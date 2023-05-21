package com.example.foodappdoan.model;

public class BILL {
    private int maHD, thanhTien, status;
    private String nameCus, phoneCus , addressCus;

    public BILL(int maHD, String nameCus, String phoneCus, String addressCus, int thanhTien, int status) {
        this.maHD = maHD;
        this.nameCus = nameCus;
        this.phoneCus = phoneCus;
        this.addressCus = addressCus;
        this.thanhTien = thanhTien;
        this.status = status;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getPhoneCus() {
        return phoneCus;
    }

    public void setPhoneCus(String phoneCus) {
        this.phoneCus = phoneCus;
    }

    public String getAddressCus() {
        return addressCus;
    }

    public void setAddressCus(String addressCus) {
        this.addressCus = addressCus;
    }
}
