package com.techmeet.mercari;

import com.google.gson.annotations.SerializedName;

public class RegisterPatient {

    @SerializedName("firebase_uid")
    private String firebaseUID;

    @SerializedName("name")
    private String name;

    @SerializedName("age")
    private String age;

    @SerializedName("userImgae")
    private String userImage;

    @SerializedName("phoneno")
    private String phoneNo;

    @SerializedName("gender")
    private String gender;

    @SerializedName("address")
    private String address;

    public String getFirebaseUID() {
        return firebaseUID;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public RegisterPatient(String firebaseUID, String name, String age, String userImage, String phoneNo, String gender, String address) {
        this.firebaseUID = firebaseUID;
        this.name = name;
        this.age = age;
        this.userImage = userImage;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.address = address;
    }
}
