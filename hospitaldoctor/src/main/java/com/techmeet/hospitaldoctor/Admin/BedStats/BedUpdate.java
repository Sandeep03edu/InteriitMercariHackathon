package com.techmeet.hospitaldoctor.Admin.BedStats;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techmeet.common.Utils.Bed;
import com.techmeet.hospitaldoctor.R;

import java.util.ArrayList;

public class BedUpdate extends AppCompatActivity {

    RecyclerView bedRcv;
    FloatingActionButton addBed;
    ArrayList<Bed> bedArrayList = new ArrayList<>();
    BedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_update);

        _init();

        // Add New Bet option
        AddBedOption();
    }

    private void AddBedOption() {
        addBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addBedIntent = new Intent(BedUpdate.this, AddEditBed.class);
                startActivity(addBedIntent);
            }
        });
    }

    private void _init() {
        addBed = findViewById(R.id.bed_stock_update_fab);
        bedRcv = findViewById(R.id.bed_stock_update_rcv);
        bedRcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BedAdapter(this, bedArrayList);
        bedRcv.setAdapter(adapter);
    }
}