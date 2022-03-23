package com.techmeet.hospitaldoctor;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.techmeet.hospitaldoctor.Admin.AdminHomeActivity;
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
            Intent homeIntent = new Intent(this, AdminHomeActivity.class);
            startActivity(homeIntent);
        }
        else{
            // Move to login Page
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }
}