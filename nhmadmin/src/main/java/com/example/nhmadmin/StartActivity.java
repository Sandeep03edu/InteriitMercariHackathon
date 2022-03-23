package com.example.nhmadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nhmadmin.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    private ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}