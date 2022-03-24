package com.techmeet.mercari.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Constants;
import com.techmeet.mercari.Data;
import com.techmeet.mercari.Lab.LabBookingActivity;
import com.techmeet.mercari.Lab.ViewLabHistory;
import com.techmeet.mercari.LoginRegistration.LoginActivity;
import com.techmeet.mercari.Medical.AppointmentSchedulerActivity;
import com.techmeet.mercari.Medical.ViewAppointmentHistory;
import com.techmeet.mercari.UserProfileActivity;
import com.techmeet.mercari.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, AppointmentSchedulerActivity.class));
            }
        });
        binding.pastAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ViewAppointmentHistory.class));
            }
        });
        binding.bookLabTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LabBookingActivity.class));
            }
        });
        binding.addYourPastDiseaseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ViewLabHistory.class));
            }
        });

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, UserProfileActivity.class));
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("patient", Context.MODE_PRIVATE);
        String patientDetails= sharedPreferences.getString(Constants.PATIENT_REGISTRATION_RESPONSE,"");
        Data data=new Gson().fromJson(patientDetails, Data.class);

//        if (data.getAuthToken().isEmpty()){
//            startActivity(new Intent(this, LoginActivity.class));
//        }
    }
}