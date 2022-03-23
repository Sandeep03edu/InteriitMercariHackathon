package com.techmeet.mercari.Medical;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techmeet.common.Utils.Doctor;
import com.techmeet.mercari.R;
import com.techmeet.common.Utils.Constants;
import com.techmeet.mercari.databinding.CartDoctorBinding;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context mContext;
    private ArrayList<Doctor> doctorArrayList;

    public DoctorAdapter(Context mContext, ArrayList<Doctor> doctorArrayList) {
        this.mContext = mContext;
        this.doctorArrayList = doctorArrayList;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorArrayList.get(position);
        holder.SetData(mContext, doctor);
    }

    @Override
    public int getItemCount() {
        return doctorArrayList.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        CartDoctorBinding binding;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartDoctorBinding.bind(itemView);
        }

        public void SetData(Context mContext, Doctor doctor) {
            binding.cartDoctorName.setText(doctor.getDoctorName());
            binding.cartDoctorProfession.setText(doctor.getMedicalProfession());

            String doctorImage = doctor.getDoctorImage();
            if (doctorImage == null || doctorImage.trim().isEmpty()) {
                // TODO : Set Local Image
            } else {
                // TODO : Set local image in error part
                Picasso.get().load(doctorImage).into(binding.cartDoctorImage);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Move to Doctor Appointment Page
                    Intent intent = new Intent(mContext, BookAppointment.class);
                    intent.putExtra(Constants.DOCTOR_ID, doctor.getdId());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
