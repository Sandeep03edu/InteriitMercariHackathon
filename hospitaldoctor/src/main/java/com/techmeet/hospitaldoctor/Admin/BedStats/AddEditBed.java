package com.techmeet.hospitaldoctor.Admin.BedStats;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Bed;
import com.techmeet.common.Utils.Constants;
import com.techmeet.hospitaldoctor.R;

public class AddEditBed extends AppCompatActivity {

    Bed bed = new Bed();
    EditText bedType, bedAvailable, bedTotal;
    TextView submit, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_bed);

        _init();

        delete.setVisibility(View.GONE);
        submit.setText("Add");

        if(getIntent()!=null && getIntent().hasExtra(Constants.BED_EDIT)){
            String bedGson = getIntent().getStringExtra(Constants.BED_EDIT);
            bed = new Gson().fromJson(bedGson, Bed.class);
            bedType.setText(bed.getType());
            bedAvailable.setText(bed.getAvailable() +"");
            bedTotal.setText(bed.getTotal()+ "");
            delete.setVisibility(View.VISIBLE);
            submit.setText("Submit");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bed.getBedId()!=null && !bed.getBedId().trim().isEmpty()){
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
                if(bed.getBedId()!=null && !bed.getBedId().trim().isEmpty()){
                    // TODO : Delete Medicine Data
                }
            }
        });

    }

    private void _init() {
        bedType = findViewById(R.id.add_edit_bed_type);
        bedAvailable = findViewById(R.id.add_edit_bed_available);
        bedTotal = findViewById(R.id.add_edit_bed_total);
        submit = findViewById(R.id.add_edit_bed_submit);
        delete = findViewById(R.id.add_edit_bed_delete);
    }
}