package com.techmeet.hospitaldoctor;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            // Already logged In
        }
        else{
            // Move to login Page
        }
    }
}