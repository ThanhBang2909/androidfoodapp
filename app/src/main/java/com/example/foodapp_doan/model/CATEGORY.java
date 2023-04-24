package com.example.foodapp_doan.model;

public class CATEGORY {

    private String maChude, tenChude;
    private String hinhChude;

    public CATEGORY(String maChude, String tenChude, String hinhChude) {
        this.maChude = maChude;
        this.tenChude = tenChude;
        this.hinhChude = hinhChude;
    }

    public String getMaChude() {
        return maChude;
    }

    public void setMaChude(String maChude) {
        this.maChude = maChude;
    }

    public String getTenChude() {
        return tenChude;
    }

    public void setTenChude(String tenChude) {
        this.tenChude = tenChude;
    }

    public String getHinhChude() {
        return hinhChude;
    }

    public void setHinhChude(String hinhChude) {
        this.hinhChude = hinhChude;
    }
}
