package com.techmeet.hospitaldoctor.Admin.Medicine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techmeet.common.Utils.Medicine;
import com.techmeet.hospitaldoctor.R;

import java.util.ArrayList;

public class MedicineStockUpdate extends AppCompatActivity {

    RecyclerView medicineRcv;
    FloatingActionButton addMedicine;
    MedicineAdapter adapter;
    ArrayList<Medicine> medicineArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_stock_update);

        _init();

        // Fetch Medicine Data and set Data
        SetRcvData();

        // Add New medicine
        AddNewMedicine();
    }

    private void AddNewMedicine() {
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move to AddEditPage
                Intent addEditIntent = new Intent(MedicineStockUpdate.this, AddEditMedicine.class);
                startActivity(addEditIntent);
            }
        });
    }

    private void SetRcvData() {
        // TODO : Set Recyclerview data
    }

    private void _init() {
        addMedicine = findViewById(R.id.medicine_stock_update_fab);
        medicineRcv = findViewById(R.id.medicine_stock_update_rcv);
        medicineRcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MedicineAdapter(this,medicineArrayList);
        medicineRcv.setAdapter(adapter);
    }
}