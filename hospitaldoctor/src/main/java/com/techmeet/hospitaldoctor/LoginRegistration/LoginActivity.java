package com.techmeet.hospitaldoctor.LoginRegistration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.techmeet.common.Utils.Constants;
import com.techmeet.hospitaldoctor.R;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivityTag";
    TextView signIn, register;
    EditText mobileNumber;
    Spinner roleSpinner;
    String verificationCode = "";
    ProgressDialog progressDialog;
    int type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _init();

        SetupRoleSpinner();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check Mobile Number and Send OTP
                SendOtp();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Move user to Registration Page
                Intent regIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(regIntent);
            }
        });

    }

    private void SetupRoleSpinner() {
        String[] roles = new String[]{Constants.ROLE_ADMIN, Constants.ROLE_DOCTOR};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpinner.setAdapter(adapter);

        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (l == 0) {
                    // Admin Registration
                    type = Constants.HOSPITAL_ADMIN;
                } else if (l == 1) {
                    // Doctor Registration
                    type = Constants.HOSPITAL_DOCTOR;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void SendOtp() {
        String number = mobileNumber.getText().toString().trim();
        if (number.length() != 10) {
            Toast.makeText(getApplicationContext(), "Enter a valid 10 digit mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern numberPattern = Pattern.compile(Constants.NUMBER_PATTERN_REGEX);
        Matcher matcher = numberPattern.matcher(number);

        if (!matcher.matches()) {
            Toast.makeText(getApplicationContext(), "Please enter numeric value only", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();

        // TODO : Check from API whether user exist or not and then send OTP

        String prefixedPhoneNumber = "+91" + number;
        FirebaseAuth auth = FirebaseAuth.getInstance();

        PhoneAuthOptions options = PhoneAuthOptions
                .newBuilder(auth)
                .setPhoneNumber(prefixedPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(LoginActivity.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressDialog.dismiss();
                    }


                    @Override
                    public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                        super.onCodeAutoRetrievalTimeOut(s);
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "An error Occurred!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onCodeAutoRetrievalTimeOut: ");
                    }

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Otp sent on " + prefixedPhoneNumber, Toast.LENGTH_SHORT).show();
                        // Move to Otp enter activity to enter Otp
                        Intent otpIntent = new Intent(LoginActivity.this, OtpFillActivity.class);
                        otpIntent.putExtra(Constants.PREFIXED_MOBILE_NUMBER, prefixedPhoneNumber);
                        otpIntent.putExtra(Constants.VERIFICATION_ID, verificationCode);
                        otpIntent.putExtra(Constants.HOSPITAL_TYPE, Constants.TYPE_LOGIN);
                        if(type==Constants.HOSPITAL_ADMIN){
                            otpIntent.putExtra(Constants.ROLE_ADMIN, "");
                        }
                        else if(type==Constants.HOSPITAL_DOCTOR){
                            otpIntent.putExtra(Constants.ROLE_DOCTOR, "");
                        }
                        startActivity(otpIntent);
                    }
                })
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void _init() {
        signIn = findViewById(R.id.login_sign_in);
        register = findViewById(R.id.login_register);
        mobileNumber = findViewById(R.id.login_mobile_number);
        roleSpinner = findViewById(R.id.login_role_spinner);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Sending Otp");
        progressDialog.setMessage("Please wait...");
    }
}