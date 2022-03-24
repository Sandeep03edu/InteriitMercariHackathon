package com.techmeet.hospitaldoctor.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.techmeet.hospitaldoctor.Admin.BedStats.BedUpdate;
import com.techmeet.hospitaldoctor.Admin.DoctorsList.DoctorsListActivity;
import com.techmeet.hospitaldoctor.Admin.Medicine.MedicineStockUpdate;
import com.techmeet.hospitaldoctor.R;

public class AdminHomeActivity extends AppCompatActivity {
    LinearLayout manageAppointments;
    LinearLayout doctorList, medicineStock, bedUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        manageAppointments = findViewById(R.id.manage_appointment);
        doctorList = findViewById(R.id.doctor_list);
        medicineStock = findViewById(R.id.home_medicine_stock);
        bedUpdate = findViewById(R.id.home_bed_status);

        manageAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, AppointmentActivity.class));
            }
        });

        medicineStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, MedicineStockUpdate.class);
                startActivity(intent);
            }
        });

        bedUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, BedUpdate.class);
                startActivity(intent);
            }
        });

        doctorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, DoctorsListActivity.class);
                startActivity(intent);
            }
        });


    }
}