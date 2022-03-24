package com.example.nhmadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nhmadmin.databinding.ActivityHome2Binding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHome2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHome2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}