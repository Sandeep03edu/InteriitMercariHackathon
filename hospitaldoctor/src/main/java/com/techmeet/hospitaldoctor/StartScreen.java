package com.techmeet.hospitaldoctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.techmeet.common.Utils.Constants;
import com.techmeet.hospitaldoctor.Admin.AdminHomeActivity;
import com.techmeet.hospitaldoctor.Doctor.DoctorHomeActivity;
import com.techmeet.hospitaldoctor.LoginRegistration.LoginActivity;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            // Already logged In
            // Check whether doctor or Admin
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Constants.PREFERENCE_HOSPITAL_DOCTOR, MODE_PRIVATE);
            if(sharedPreferences!=null && sharedPreferences.contains(Constants.ROLE_ADMIN)){
                // Admin Login
                Intent homeIntent = new Intent(this, AdminHomeActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
                finish();
            }
            else if(sharedPreferences!=null && sharedPreferences.contains(Constants.ROLE_DOCTOR)){
                // Doctor Login
                Intent homeIntent = new Intent(this, DoctorHomeActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(homeIntent);
                finish();
            }
            else{
                // Move to login Page
                Intent loginIntent = new Intent(this, LoginActivity.class);
                loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginIntent);
                finish();
            }
        }
        else{
            // Move to login Page
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(loginIntent);
            finish();
        }
    }
}