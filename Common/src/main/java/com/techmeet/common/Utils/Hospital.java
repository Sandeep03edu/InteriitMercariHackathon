package com.techmeet.common.Utils;

public class Hospital {
    private String hId;
    private String hospitalName;
    private String hospitalImage;
    private String address;
    private String phoneNumber;
    private float rating;

    public Hospital() {
    }

    public Hospital(String hId, String hospitalName, String hospitalImage, String address, String phoneNumber, float rating) {
        this.hId = hId;
        this.hospitalName = hospitalName;
        this.hospitalImage = hospitalImage;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalImage() {
        return hospitalImage;
    }

    public void setHospitalImage(String hospitalImage) {
        this.hospitalImage = hospitalImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}