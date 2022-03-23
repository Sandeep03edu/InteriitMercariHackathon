package com.techmeet.mercari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.techmeet.mercari.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity {
    private ActivityUserProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUserProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pastPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPastPrescription();
            }
        });

        binding.pastLabReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPastLabReports();
            }
        });
    }

    private void showPastLabReports() {

    }

    private void showPastPrescription() {

    }
}