package com.techmeet.mercari.LoginRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.techmeet.common.Utils.Constants;
import com.techmeet.mercari.Home.HomeActivity;
import com.techmeet.mercari.databinding.ActivityOtpFillBinding;

public class OtpFillActivity extends AppCompatActivity {

    private ActivityOtpFillBinding binding;
    String prefixedMobileNum = "";
    String storedVerificationId;
    private String TAG="OtpFillActivity";
    private FirebaseAuth mAuth;

    private int type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOtpFillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent()==null || !getIntent().hasExtra(Constants.PREFIXED_MOBILE_NUMBER) || !getIntent().hasExtra(Constants.VERIFICATION_ID)){
            return;
        }

        if(getIntent().hasExtra(Constants.USER_DETAILS)){
            type = Constants.TYPE_REGISTER;
        }
        else{
            type = Constants.TYPE_LOGIN;
        }

        otpInputs();
        mAuth=FirebaseAuth.getInstance();
        prefixedMobileNum = getIntent().getStringExtra(Constants.PREFIXED_MOBILE_NUMBER);

        storedVerificationId = getIntent().getStringExtra(Constants.VERIFICATION_ID);

        binding.phoneNoShow.setText(prefixedMobileNum);
        binding.verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.otp1.getText().toString().trim().isEmpty() ||
                        binding.otp2.getText().toString().trim().isEmpty() ||
                        binding.otp3.getText().toString().trim().isEmpty() ||
                        binding.otp4.getText().toString().trim().isEmpty() ||
                        binding.otp5.getText().toString().trim().isEmpty() ||
                        binding.otp6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(OtpFillActivity.this, "Please Enter Valid Otp", Toast.LENGTH_SHORT).show();
                } else {
                    String otp = binding.otp1.getText().toString().trim() +
                            binding.otp2.getText().toString().trim() +
                            binding.otp3.getText().toString().trim() +
                            binding.otp4.getText().toString().trim() +
                            binding.otp5.getText().toString().trim() +
                            binding.otp6.getText().toString().trim();
                    if (storedVerificationId != null) {
                        //verifyPhoneNumberWithCode(storedVerificationId, otp);
                        binding.progressCircular.setVisibility(View.VISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(storedVerificationId, otp);
                        Log.d(TAG+"credential",phoneAuthCredential.toString());
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                }
            }
        });

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            if(type==Constants.TYPE_REGISTER){
                                // Save user details to database
                                // TODO : Set user data to Backend and then proceed to next page
                            }
                            else if(type==Constants.TYPE_LOGIN){
                                // TODO : Logged in user
                                binding.progressCircular.setVisibility(View.GONE);
                                startActivity(new Intent(OtpFillActivity.this, HomeActivity.class));
                                finish();
                            }


                            // Update UI
                            //startActivity(new Intent(MainActivity.this,BuildProfileActivity.class));
                            Toast.makeText(OtpFillActivity.this, "Succeeded", Toast.LENGTH_SHORT).show();
                        } else {
                            // Sign in failed, display a message and update the UI
                            binding.progressCircular.setVisibility(View.GONE);
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void otpInputs() {
        binding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    binding.otp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    binding.otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    binding.otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    binding.otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    binding.otp6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}