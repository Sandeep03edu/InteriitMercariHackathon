package com.techmeet.common.Utils;

import java.util.ArrayList;

public class Appointment {
    private String appId;
    private String patId;
    private String docId;
    private String hosId;

    private long time;
    private String diseaseDesc;
    private String status;
    private ArrayList<String> presImages;
    private ArrayList<String> references;

    public Appointment() {
    }

    public Appointment(String appId, String patId, String docId, String hosId, long time, String diseaseDesc, String status, ArrayList<String> presImages, ArrayList<String> references) {
        this.appId = appId;
        this.patId = patId;
        this.docId = docId;
        this.hosId = hosId;
        this.time = time;
        this.diseaseDesc = diseaseDesc;
        this.status = status;
        this.presImages = presImages;
        this.references = references;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getHosId() {
        return hosId;
    }

    public void setHosId(String hosId) {
        this.hosId = hosId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDiseaseDesc() {
        return diseaseDesc;
    }

    public void setDiseaseDesc(String diseaseDesc) {
        this.diseaseDesc = diseaseDesc;
    }

    public ArrayList<String> getPresImages() {
        return presImages;
    }

    public void setPresImages(ArrayList<String> presImages) {
        this.presImages = presImages;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
