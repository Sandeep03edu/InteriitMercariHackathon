package com.techmeet.common.Utils;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class PatientLogin {

    @SerializedName("phoneno")
    private String patientNo;

    public String getPatientNo() {
        return patientNo;
    }

    public String getPatientFirebaseUID() {
        return patientFirebaseUID;
    }

    @SerializedName("firebase_id")
    private String patientFirebaseUID;

    public PatientLogin(String patientNo, String patientFirebaseUID) {
        this.patientNo = patientNo;
        this.patientFirebaseUID = patientFirebaseUID;
    }
}
