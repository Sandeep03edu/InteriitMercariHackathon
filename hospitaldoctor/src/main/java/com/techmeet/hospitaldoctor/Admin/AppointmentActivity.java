package com.techmeet.hospitaldoctor.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techmeet.common.Utils.ViewPagerAdapter;
import com.techmeet.hospitaldoctor.databinding.ActivityAppointmentBinding;

public class AppointmentActivity extends AppCompatActivity {
    private ActivityAppointmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SetViewPager();
    }

    private void SetViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendingAppointmentFragment(), "Pending Appointments");
        adapter.addFragment(new ApprovedAppointmentFragment(), "Approved Appointments");
        binding.manageAppointmentViewPager.setAdapter(adapter);
        binding.manageAppointmentViewPager.setOffscreenPageLimit(2);
        binding.manageAppointmentTabLayout.setupWithViewPager(binding.manageAppointmentViewPager);
    }
}