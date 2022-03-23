package com.techmeet.common.Utils;

import java.util.ArrayList;

public class Prescription {
    private String presId;
    private String patId;
    private String docId; // Optional

    private String disease; // Optional
    private String prescription;

    private ArrayList<String> images;

    public Prescription() {
    }

    public Prescription(String presId, String patId, String docId, String disease, String prescription, ArrayList<String> images) {
        this.presId = presId;
        this.patId = patId;
        this.docId = docId;
        this.disease = disease;
        this.prescription = prescription;
        this.images = images;
    }

    public String getPresId() {
        return presId;
    }

    public void setPresId(String presId) {
        this.presId = presId;
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

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
