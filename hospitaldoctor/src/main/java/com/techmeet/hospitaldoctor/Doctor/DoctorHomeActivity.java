package com.techmeet.hospitaldoctor.Doctor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techmeet.common.Utils.Appointment;
import com.techmeet.hospitaldoctor.R;

import java.util.ArrayList;

public class DoctorHomeActivity extends AppCompatActivity {

    TextView doctorName;
    RecyclerView appointmentRcv;
    AppointmentAdapter appointmentAdapter;
    ArrayList<Appointment> appointmentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        _init();

        // Fetch Doctor Data from SharedPreference
        // TODO : Set Doctor name from SharedPreference

        // Fetch Approved appointments for that doctor
        // TODO: Fetch Appointments
    }

    private void _init() {
        doctorName = findViewById(R.id.doctor_home_name);
        appointmentRcv = findViewById(R.id.doctor_home_rcv);
        appointmentRcv.setLayoutManager(new LinearLayoutManager(this));

        appointmentAdapter = new AppointmentAdapter(this, appointmentArrayList);
        appointmentRcv.setAdapter(appointmentAdapter);
    }
}