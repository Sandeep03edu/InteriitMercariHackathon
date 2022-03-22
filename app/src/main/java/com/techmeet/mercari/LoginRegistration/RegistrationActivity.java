package com.techmeet.mercari.LoginRegistration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.techmeet.mercari.Model.User;
import com.techmeet.mercari.R;
import com.techmeet.mercari.Utils.Constants;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    EditText name, age, address, phoneNumber;
    Spinner genderSpinner;
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


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });

    }

    private void RegisterUser() {
        // Check user details
        String userName = name.getText().toString().trim();
        String userAge = age.getText().toString().trim();
        String userAddress = address.getText().toString().trim();
        String userPhoneNumber = phoneNumber.getText().toString().trim();
        String userGender = genderSpinner.getSelectedItem().toString();

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
                        User user = new User("", userName, "", prefixedMobileNumber , userGender, userAge, userAddress);
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
        age = findViewById(R.id.registration_age);
        address = findViewById(R.id.registration_address);
        phoneNumber = findViewById(R.id.registration_phoneNumber);
        login = findViewById(R.id.register_login);
        register = findViewById(R.id.register_register);
        genderSpinner = findViewById(R.id.registration_gender_spinner);

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setTitle("Sending Otp");
        progressDialog.setMessage("Please wait...");
    }
}