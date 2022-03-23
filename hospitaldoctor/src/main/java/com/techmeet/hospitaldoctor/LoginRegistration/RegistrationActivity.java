package com.techmeet.hospitaldoctor.LoginRegistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.techmeet.common.Utils.Constants;
import com.techmeet.common.Utils.User;
import com.techmeet.hospitaldoctor.R;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {


    EditText name, experience, hospitalName, phoneNumber;
    Spinner roleSpinner;
    TextView login, register;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        _init();

        // login Action
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                logIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logIntent);
                finish();
            }
        });

        // Setup gender spinner
        SetupGenderSpinner();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

    }

    private void SetupGenderSpinner() {
        String[] genders = new String[]{Constants.GENDER_MALE, Constants.GENDER_FEMALE, Constants.GENDER_OTHER};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);
    }

    private void RegisterUser() {
        // Check user details
        String userName = name.getText().toString().trim();
        String userAge = experience.getText().toString().trim();
        String userAddress = hospitalName.getText().toString().trim();
        String userPhoneNumber = phoneNumber.getText().toString().trim();
        String userGender = roleSpinner.getSelectedItem().toString();

        if(userName.isEmpty() || userAge.isEmpty() || userAddress.isEmpty() || userPhoneNumber.isEmpty() || userGender.isEmpty()){
            Toast.makeText(getApplicationContext(), "All fields are compulsory", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPhoneNumber.length() != 10) {
            Toast.makeText(getApplicationContext(), "Enter a valid 10 digit mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern numberPattern = Pattern.compile(Constants.NUMBER_PATTERN_REGEX);
        Matcher matcher = numberPattern.matcher(userPhoneNumber);

        if (!matcher.matches()) {
            Toast.makeText(getApplicationContext(), "Please enter numeric value only", Toast.LENGTH_SHORT).show();
            return;
        }

        String prefixedMobileNumber = "+91" + userPhoneNumber;

        progressDialog.show();
        // Getting Otp
        FirebaseAuth auth = FirebaseAuth.getInstance();
        PhoneAuthOptions options = PhoneAuthOptions
                .newBuilder(auth)
                .setPhoneNumber(prefixedMobileNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(RegistrationActivity.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                        super.onCodeAutoRetrievalTimeOut(s);
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "An error Occurred!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        progressDialog.dismiss();
                        // User details filled
                        // Moving to Otp View Page
                        User user = new User("", userName, "", userPhoneNumber , userGender, userAge, userAddress);
                        Gson gson = new Gson();
                        String userGson = gson.toJson(user);

                        // Move to Otp view
                        Intent otpIntent = new Intent(RegistrationActivity.this, OtpFillActivity.class);
                        otpIntent.putExtra(Constants.USER_DETAILS, userGson);
                        otpIntent.putExtra(Constants.PREFIXED_MOBILE_NUMBER, prefixedMobileNumber);
                        otpIntent.putExtra(Constants.VERIFICATION_ID, s);
                        startActivity(otpIntent);
                    }
                }).build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void _init() {
        name = findViewById(R.id.registration_name);
        experience = findViewById(R.id.registration_experience);
        hospitalName = findViewById(R.id.registration_hospital_name);
        phoneNumber = findViewById(R.id.registration_phoneNumber);
        login = findViewById(R.id.register_login);
        register = findViewById(R.id.register_register);
        roleSpinner = findViewById(R.id.registration_role_spinner);

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setTitle("Sending Otp");
        progressDialog.setMessage("Please wait...");
    }
}