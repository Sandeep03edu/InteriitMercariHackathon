package com.techmeet.mercari.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.techmeet.mercari.Lab.LabBookingActivity;
import com.techmeet.mercari.Lab.ViewLabHistory;
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
    }
}