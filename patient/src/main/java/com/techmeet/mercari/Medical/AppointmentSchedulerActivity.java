package com.techmeet.mercari.Medical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.tabs.TabLayout;
import com.techmeet.mercari.R;
import com.techmeet.common.Utils.ViewPagerAdapter;

public class AppointmentSchedulerActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_scheduler);

        _init();

        // Setting ViewPager
        SetViewPager();

    }

    public String getEdittextText(){
        if(editText==null){
            return "";
        }
        return editText.getText().toString().trim();
    }

    private void SetViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HospitalFragment(), "Hospital");
        adapter.addFragment(new DoctorFragment(), "Doctor");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void _init() {
        tabLayout = findViewById(R.id.appointment_scheduler_tab_layout);
        viewPager = findViewById(R.id.appointment_scheduler_view_pager);
        editText =findViewById(R.id.appointment_scheduler_edittext);
    }
}