package com.techmeet.mercari.retrofit;

import com.techmeet.common.Utils.Doctor;
import com.techmeet.common.Utils.Hospital;
import com.techmeet.mercari.PatientRegisterResponse;
import com.techmeet.mercari.RegisterPatient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserCallApi {

    @PUT("api/patientlogin")
    Call<String> patientLogin(@Field("phoneno") String PatientPhoneNo,
                              @Field("firebase_id") String patientFirebaseUID);

    @POST("api/patient")
    Call<PatientRegisterResponse> registerPatient(@Body RegisterPatient registerPatient);

    @GET("api/GetAllHospitals")
    Call<ArrayList<Hospital>> getAllHospital();

    @POST("api/GetHospitalbyName")
    Call<ArrayList<Hospital>> getHospitalByName(@Field("name") String doctorName, @Header("AuthToken") String AuthToken);

    @POST("api/searchDegree")
    Call<ArrayList<Doctor>> getDoctorByDegree(@Field("degree") String degree,@Header("AuthToken") String authToken);
}
