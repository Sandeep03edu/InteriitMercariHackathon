package com.techmeet.common.Utils;

public class Bed {
    private String bedId;
    private String type;
    private int available;
    private int total;

    public Bed() {
    }

    public Bed(String bedId, String type, int available, int total) {
        this.bedId = bedId;
        this.type = type;
        this.available = available;
        this.total = total;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
