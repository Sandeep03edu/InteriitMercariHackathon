package com.techmeet.common.Utils;

public class User {
    private String id;
    private String name;
    private String userImage;
    private String mobileNumber;
    private String gender;
    private String age;
    private String address;

    public User() {
    }

    public User(String id, String name, String userImage, String mobileNumber, String gender, String age, String address) {
        this.id = id;
        this.name = name;
        this.userImage = userImage;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.age = age;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
