package com.techmeet.common.Utils;

public class Doctor {
    private String dId;
    private String hId;
    private String doctorName;
    private String doctorImage;
    private String degree;
    private String medicalProfession;
    private int experience;
    private String hospital;
    private String phoneNumber;

    public Doctor() {
    }

    public Doctor(String dId, String hId, String doctorName, String doctorImage, String degree, String medicalProfession, int experience, String hospital, String phoneNumber) {
        this.dId = dId;
        this.hId = hId;
        this.doctorName = doctorName;
        this.doctorImage = doctorImage;
        this.degree = degree;
        this.medicalProfession = medicalProfession;
        this.experience = experience;
        this.hospital = hospital;
        this.phoneNumber = phoneNumber;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorImage() {
        return doctorImage;
    }

    public void setDoctorImage(String doctorImage) {
        this.doctorImage = doctorImage;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMedicalProfession() {
        return medicalProfession;
    }

    public void setMedicalProfession(String medicalProfession) {
        this.medicalProfession = medicalProfession;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}