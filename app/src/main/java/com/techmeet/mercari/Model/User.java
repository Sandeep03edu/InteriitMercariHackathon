package com.techmeet.mercari.Model;

public class User {
    private String id;
    private String name;
    private String userImage;
    private String prefMobileNumber;
    private String gender;
    private String age;
    private String address;

    public User() {
    }

    public User(String id, String name, String userImage, String prefMobileNumber, String gender, String age, String address) {
        this.id = id;
        this.name = name;
        this.userImage = userImage;
        this.prefMobileNumber = prefMobileNumber;
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

    public String getPrefMobileNumber() {
        return prefMobileNumber;
    }

    public void setPrefMobileNumber(String prefMobileNumber) {
        this.prefMobileNumber = prefMobileNumber;
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
}
