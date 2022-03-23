package com.techmeet.hospitaldoctor.LoginRegistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.techmeet.common.Utils.Doctor;
import com.techmeet.common.Utils.Hospital;
import com.techmeet.hospitaldoctor.R;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {


    EditText name, experience, hospitalName, phoneNumber, degree, profession, hospitalAddress;
    Spinner roleSpinner;
    TextView login, register;
    ProgressDialog progressDialog;

    LinearLayout nameLl, hospNameLl, phoneNumLl, degreeLl, profLl, expLl, hosAddLl;

    private int type = -1;

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
        SetupRoleSpinner();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

    }

    private void SetupRoleSpinner() {
        String[] roles = new String[]{Constants.ROLE_ADMIN, Constants.ROLE_DOCTOR};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationActivity.this, android.R.layout.simple_spinner_dropdown_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (l == 0) {
                    // Admin Registration
                    type = Constants.HOSPITAL_ADMIN;

                    // Hiding non useful views
                    nameLl.setVisibility(View.GONE);
                    degreeLl.setVisibility(View.GONE);
                    profLl.setVisibility(View.GONE);
                    expLl.setVisibility(View.GONE);

                } else if (l == 1) {
                    // Doctor Registration
                    type = Constants.HOSPITAL_DOCTOR;

                    // Hiding useful views
                    nameLl.setVisibility(View.VISIBLE);
                    degreeLl.setVisibility(View.VISIBLE);
                    profLl.setVisibility(View.VISIBLE);
                    expLl.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void RegisterUser() {
        // Check user details
        String DoctorName = name.getText().toString().trim();
        String HospitalName = hospitalName.getText().toString().trim();
        String PhoneNumber = phoneNumber.getText().toString().trim();
        String Degree = degree.getText().toString().trim();
        String Profession = profession.getText().toString().trim();
        String Experience = experience.getText().toString().trim();
        String HospitalAddress = hospitalAddress.getText().toString().trim();

        if (type == Constants.HOSPITAL_ADMIN && (HospitalName.isEmpty() || PhoneNumber.isEmpty() || HospitalAddress.isEmpty())) {
            Toast.makeText(getApplicationContext(), "All fields are compulsory", Toast.LENGTH_SHORT).show();
            return;
        } else if (type == Constants.HOSPITAL_DOCTOR && (DoctorName.isEmpty() || HospitalName.isEmpty() || Degree.isEmpty() || Profession.isEmpty() || Experience.isEmpty() || PhoneNumber.isEmpty() || HospitalAddress.isEmpty())) {
            Toast.makeText(getApplicationContext(), "All fields are compulsory", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern numberPattern = Pattern.compile(Constants.NUMBER_PATTERN_REGEX);
        Matcher matcher = numberPattern.matcher(PhoneNumber);

        if (!matcher.matches()) {
            Toast.makeText(getApplicationContext(), "Please enter numeric value only", Toast.LENGTH_SHORT).show();
            return;
        }

        String prefixedMobileNumber = "+91" + PhoneNumber;

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
                        // Check Type and Create model Data
                        if (type == Constants.HOSPITAL_ADMIN) {
                            // Admin Registration
                            Hospital hospital = new Hospital("", HospitalName, "", HospitalAddress, PhoneNumber, 0);
                            String hospitalGson = new Gson().toJson(hospital);
                            Intent otpIntent = new Intent(RegistrationActivity.this, OtpFillActivity.class);
                            otpIntent.putExtra(Constants.PREFIXED_MOBILE_NUMBER, prefixedMobileNumber);
                            otpIntent.putExtra(Constants.VERIFICATION_ID, s);
                            otpIntent.putExtra(Constants.ROLE_ADMIN, hospitalGson);
                            otpIntent.putExtra(Constants.HOSPITAL_TYPE, Constants.TYPE_REGISTER);
                            startActivity(otpIntent);
                        } else if (type == Constants.HOSPITAL_DOCTOR) {
                            // Doctor Registration
                            Doctor doctor = new Doctor("", "", DoctorName, "", Degree, Profession, Integer.parseInt(Experience), HospitalName, PhoneNumber);
                            String doctorGson = new Gson().toJson(doctor);
                            Intent otpIntent = new Intent(RegistrationActivity.this, OtpFillActivity.class);
                            otpIntent.putExtra(Constants.PREFIXED_MOBILE_NUMBER, prefixedMobileNumber);
                            otpIntent.putExtra(Constants.VERIFICATION_ID, s);
                            otpIntent.putExtra(Constants.ROLE_DOCTOR, doctorGson);
                            otpIntent.putExtra(Constants.HOSPITAL_TYPE, Constants.TYPE_REGISTER);
                            startActivity(otpIntent);
                        }
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

        degree = findViewById(R.id.registration_degree);
        profession = findViewById(R.id.registration_profession);
        hospitalAddress = findViewById(R.id.registration_hospital_address);

        nameLl = findViewById(R.id.registration_name_ll);
        hospNameLl = findViewById(R.id.registration_hospital_name_ll);
        phoneNumLl = findViewById(R.id.registration_phoneNumber_ll);
        degreeLl = findViewById(R.id.registration_degree_ll);
        profLl = findViewById(R.id.registration_profession_ll);
        expLl = findViewById(R.id.registration_experience_ll);
        hosAddLl = findViewById(R.id.registration_hospital_address_ll);

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setTitle("Sending Otp");
        progressDialog.setMessage("Please wait...");
    }
}