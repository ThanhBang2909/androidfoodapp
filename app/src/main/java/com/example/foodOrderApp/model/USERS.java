package com.example.foodOrderApp.model;

public class USERS {
    String fullName, address, phone, avartar , password;

    public USERS(String fullName, String address, String phone, String avartar, String password) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.avartar = avartar;
        this.password = password;
    }

    public USERS(String fullName, String address, String phone) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public USERS() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
