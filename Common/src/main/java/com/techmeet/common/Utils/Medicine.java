package com.techmeet.common.Utils;

public class Medicine {
    private String mId;
    private String hId;
    private String medicineName;
    private int stock;

    public Medicine() {
    }

    public Medicine(String mId, String hId, String medicineName, int stock) {
        this.mId = mId;
        this.hId = hId;
        this.medicineName = medicineName;
        this.stock = stock;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
