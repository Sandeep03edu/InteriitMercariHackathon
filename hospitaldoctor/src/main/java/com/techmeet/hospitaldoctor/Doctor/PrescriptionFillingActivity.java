package com.techmeet.hospitaldoctor.Doctor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Appointment;
import com.techmeet.common.Utils.Constants;
import com.techmeet.common.Utils.Prescription;
import com.techmeet.common.Utils.SaveImage;
import com.techmeet.common.Utils.User;
import com.techmeet.hospitaldoctor.DisplayUploadImageAdapter;
import com.techmeet.hospitaldoctor.R;

import java.util.ArrayList;

public class PrescriptionFillingActivity extends AppCompatActivity {

    TextView patientName, patientDesc, attachedImages, attachedReferences, addImages, submit;
    EditText diseaseName, diseasePrescription;
    ViewPager viewPager;

    ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();
    ArrayList<String> uriArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_filling);

        if (getIntent() == null || !getIntent().hasExtra(Constants.APPOINTMENT)) {
            Toast.makeText(getApplicationContext(), "Failed to fetch data!!", Toast.LENGTH_SHORT).show();
            return;
        }

        _init();

        String appointmentGson = getIntent().getStringExtra(Constants.APPOINTMENT);
        Appointment appointment = new Gson().fromJson(appointmentGson, Appointment.class);

        // Setting patient data
        SetPatientData(appointment);

        // Setting attachedImages View
        PopupAttachedImages(appointment);

        // Setting Prescription View
        PopupReferenceView(appointment);

        // Get Images Command
        GetCameraImage();

        // Set Submit Action
        SetSubmitAction(appointment);
    }

    private void SetSubmitAction(Appointment appointment) {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO : Create prescription and set into database
                String disease = diseaseName.getText().toString().trim();
                String pres = diseasePrescription.getText().toString().trim();
                // TODO : Fetch Names from Doctor profile
                String docName = "", hospName = "";
                Prescription prescription = new Prescription("", appointment.getPatId(), appointment.getDocId(), docName, hospName, disease, pres, uriArrayList);

                // TODO : Set Appoint status to be Completed
            }
        });
    }

    private void GetCameraImage() {
        addImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetImage();
            }
        });
    }

    private void GetImage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, Constants.CAMERA_REQUEST);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.EXTERNAL_STORAGE_PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CAMERA_REQUEST && resultCode == RESULT_OK && data != null && data.getExtras() != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Uri imageUri = SaveImage.addToFav(PrescriptionFillingActivity.this, "Prescription", System.currentTimeMillis() + "", photo);
            String imageStr = String.valueOf(imageUri);
            uriArrayList.add(imageStr);

            if (uriArrayList.size() > 0) {
                viewPager.setVisibility(View.VISIBLE);

                int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
                viewPager.getLayoutParams().width = screenWidth;
                viewPager.getLayoutParams().height = screenWidth;

                DisplayUploadImageAdapter adapter = new DisplayUploadImageAdapter(PrescriptionFillingActivity.this, uriArrayList);
                viewPager.setAdapter(adapter);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                GetImage();
            }
        }
    }

    private void PopupReferenceView(Appointment appointment) {
        attachedReferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View refView = ((LayoutInflater) PrescriptionFillingActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.activity_reference_list, null);

                int width = Resources.getSystem().getDisplayMetrics().widthPixels - 60;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;

                PopupWindow popupWindow = new PopupWindow(refView, width, height, focusable);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                RecyclerView recyclerView = refView.findViewById(R.id.reference_list_rcv);
                ReferenceAdapter adapter = new ReferenceAdapter(PrescriptionFillingActivity.this, appointment.getReferences());
                recyclerView.setLayoutManager(new LinearLayoutManager(PrescriptionFillingActivity.this));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void PopupAttachedImages(Appointment appointment) {
        attachedImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View viewPagerView = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.images_view_pager, null);

                int width = Resources.getSystem().getDisplayMetrics().widthPixels - 60;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;

                PopupWindow popupWindow = new PopupWindow(viewPagerView, width, height, focusable);
                popupWindow.setElevation(20);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                ViewPager pager = viewPagerView.findViewById(R.id.images_view_pager_pager);
                DisplayUploadImageAdapter adapter = new DisplayUploadImageAdapter(PrescriptionFillingActivity.this, appointment.getPresImages());
                pager.setAdapter(adapter);
            }
        });
    }

    private void SetPatientData(Appointment appointment) {
        String patId = appointment.getPatId();
        // TODO : Fetch patient details
        User user = new User();
        patientName.setText(user.getName());
        patientDesc.setText(appointment.getDiseaseDesc());
    }

    private void _init() {
        patientName = findViewById(R.id.prescription_filling_name);
        patientDesc = findViewById(R.id.prescription_filling_desc);
        attachedImages = findViewById(R.id.prescription_filling_attached_images);
        attachedReferences = findViewById(R.id.prescription_filling_attached_references);

        diseaseName = findViewById(R.id.prescription_filling_disease_name);
        diseasePrescription = findViewById(R.id.prescription_filling_disease_prescription);
        addImages = findViewById(R.id.prescription_filling_add_images);
        viewPager = findViewById(R.id.prescription_filling_viewpager);
        submit = findViewById(R.id.prescription_filling_submit);
    }
}