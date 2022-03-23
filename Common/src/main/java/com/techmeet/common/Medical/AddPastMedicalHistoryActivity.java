package com.techmeet.mercari.Medical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techmeet.mercari.R;
import com.techmeet.mercari.databinding.ActivityAddPastMedicalHistoryBinding;

public class AddPastMedicalHistoryActivity extends AppCompatActivity {
    private ActivityAddPastMedicalHistoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddPastMedicalHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}