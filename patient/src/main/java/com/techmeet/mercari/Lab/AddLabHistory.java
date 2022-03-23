package com.techmeet.mercari.Lab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.techmeet.common.Utils.Constants;
import com.techmeet.mercari.databinding.ActivityAddLabHistoryBinding;

import java.util.ArrayList;

public class AddLabHistory extends AppCompatActivity {
    private ActivityAddLabHistoryBinding binding;
    private ArrayList<String> imageUriList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddLabHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.uploadImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(AddLabHistory.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    imagePicker();
                }
                else  ActivityCompat.requestPermissions(AddLabHistory.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Constants.READ_EXTERNAL_STORAGE);

            }
        });
    }


    public void imagePicker(){
        Intent intent= new Intent();
        intent.setType("images/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Images"), Constants.PICK_IMAGES_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constants.PICK_IMAGES_CODE && resultCode==RESULT_OK && data!=null){
            if (data.getClipData()!=null){
                int count=data.getClipData().getItemCount();
                for (int i=0;i<count;i++){
                    Uri imageUri=data.getClipData().getItemAt(i).getUri();
                    imageUriList.add(imageUri.toString());
                }
            }else {
                imageUriList.add(data.getData().toString());
            }
        }
    }
}