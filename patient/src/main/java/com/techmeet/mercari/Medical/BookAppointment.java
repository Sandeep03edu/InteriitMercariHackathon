package com.techmeet.mercari.Medical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techmeet.mercari.R;
import com.techmeet.mercari.databinding.ActivityBookAppointmentBinding;

public class BookAppointment extends AppCompatActivity {
    private ActivityBookAppointmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBookAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}