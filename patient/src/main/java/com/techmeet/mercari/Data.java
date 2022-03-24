package com.techmeet.mercari;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class Data {
    @SerializedName("name")
    public String name;

    @SerializedName("age")
    public String age;

    @SerializedName("userImage")
    public String userImage;

    @SerializedName("phoneno")
    public String phoneNo;

    @SerializedName("gender")
    public String gender;

    @SerializedName("address")
    public String address;

    @SerializedName("is_loggedin")
    public String is_loggedin;

    @SerializedName("auth_token")
    public String authToken;

    @SerializedName("firebase_id")
    public String firebase_id;

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

    public String getIs_loggedin() {
        return is_loggedin;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getFirebase_id() {
        return firebase_id;
    }

    public Data(String name,
                String age,
                String userImage,
                String phoneNo,
                String gender,
                String address,
                String is_loggedin,
                String authToken,
                String firebase_id) {
        this.name = name;
        this.age = age;
        this.userImage = userImage;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.address = address;
        this.is_loggedin = is_loggedin;
        this.authToken = authToken;
        this.firebase_id = firebase_id;
    }
}
