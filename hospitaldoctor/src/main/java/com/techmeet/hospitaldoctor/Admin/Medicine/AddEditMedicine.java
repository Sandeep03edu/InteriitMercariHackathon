package com.techmeet.hospitaldoctor.Admin.Medicine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Constants;
import com.techmeet.common.Utils.Medicine;
import com.techmeet.hospitaldoctor.R;

public class AddEditMedicine extends AppCompatActivity {

    Medicine medicine = new Medicine();
    EditText medicineName, medicineStock;
    TextView submit, delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_medicine);

        _init();

        delete.setVisibility(View.GONE);
        submit.setText("Add");

        if(getIntent()!=null && getIntent().hasExtra(Constants.MEDICINE_EDIT)){
            String medicineGson = getIntent().getStringExtra(Constants.MEDICINE_EDIT);
            medicine = new Gson().fromJson(medicineGson, Medicine.class);
            medicineName.setText(medicine.getMedicineName());
            medicineStock.setText(medicine.getStock() +"");
            delete.setVisibility(View.VISIBLE);
            submit.setText("Submit");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(medicine.getmId()!=null && !medicine.getmId().trim().isEmpty()){
                    // TODO : Update Medicine Data
                }
                else{
                    // TODO : Add new Medicine
                }
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(medicine.getmId()!=null && !medicine.getmId().trim().isEmpty()){
                    // TODO : Delete Medicine Data
                }
            }
        });
    }

    private void _init() {
        medicineName = findViewById(R.id.add_edit_medicine_name);
        medicineStock = findViewById(R.id.add_edit_medicine_stock);
        submit = findViewById(R.id.add_edit_medicine_submit);
        delete = findViewById(R.id.add_edit_medicine_delete);
    }
}