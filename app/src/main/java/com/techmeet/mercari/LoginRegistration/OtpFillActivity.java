package com.techmeet.mercari.LoginRegistration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.techmeet.mercari.R;
import com.techmeet.mercari.Utils.Constants;

public class OtpFillActivity extends AppCompatActivity {


    String prefixedMobileNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_fill);

        if(getIntent()==null || !getIntent().hasExtra(Constants.PREFIXED_MOBILE_NUMBER) || !getIntent().hasExtra(Constants.VERIFICATION_ID)){
            return;
        }

        prefixedMobileNum = getIntent().getStringExtra(Constants.PREFIXED_MOBILE_NUMBER);

    }
}