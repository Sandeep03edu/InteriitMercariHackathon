package com.techmeet.mercari;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class PatientRegisterResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private Data data;

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public PatientRegisterResponse(String message, Data data) {
        this.message = message;
        this.data = data;
    }
}

