package com.techmeet.hospitaldoctor.Admin.DoctorsList;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techmeet.common.Utils.Doctor;
import com.techmeet.hospitaldoctor.R;

import java.util.ArrayList;

public class DoctorsListActivity extends AppCompatActivity {

    RecyclerView doctorRcv;
    ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    DoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        doctorRcv = findViewById(R.id.doctors_list_rcv);
        doctorRcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DoctorAdapter(this, doctorArrayList);
        doctorRcv.setAdapter(adapter);
    }
}