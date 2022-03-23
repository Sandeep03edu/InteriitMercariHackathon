package com.techmeet.mercari.Medical;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techmeet.common.Utils.Doctor;
import com.techmeet.mercari.R;
import com.techmeet.common.Utils.Constants;

import java.util.ArrayList;

public class DisplayHospitalDetails extends AppCompatActivity {

    ImageView hospitalImage;
    TextView hospitalName, hospitalAddress, hospitalPhone, hospitalRating;
    RecyclerView doctorRcv;
    ArrayList<Doctor> doctorArrayList;
    DoctorAdapter doctorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_hospital_details);

        _init();

        SetHospitalData();
    }

    private void SetHospitalData() {
        if(getIntent()==null || !getIntent().hasExtra(Constants.HOSPITAL_ID)){
            return;
        }
        String hospitalId = getIntent().getStringExtra(Constants.HOSPITAL_ID);
        if(hospitalId==null){
            return;
        }

        // TODO : Fetched Hospital Details from API and set Hospitals Data
        // TODO : Fetch Doctor list from API and Set Doctors data
    }

    private void _init() {
        hospitalImage = findViewById(R.id.display_hospital_details_image);
        hospitalName = findViewById(R.id.display_hospital_details_name);
        hospitalAddress = findViewById(R.id.display_hospital_details_address);
        hospitalPhone = findViewById(R.id.display_hospital_details_phone);
        hospitalRating = findViewById(R.id.display_hospital_details_rating);
        doctorRcv = findViewById(R.id.display_hospital_details_rcv);

        doctorRcv.setLayoutManager(new LinearLayoutManager(this));
        doctorArrayList = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(this, doctorArrayList);
        doctorRcv.setAdapter(doctorAdapter);
        doctorRcv.setNestedScrollingEnabled(false);
    }
}