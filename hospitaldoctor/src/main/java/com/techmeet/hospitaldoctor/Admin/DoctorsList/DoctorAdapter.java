package com.techmeet.hospitaldoctor.Admin.DoctorsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techmeet.common.Utils.Doctor;
import com.techmeet.hospitaldoctor.R;
import com.techmeet.hospitaldoctor.databinding.CartDoctorBinding;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>{

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
            String doctorImage = doctor.getDoctorImage();
            binding.cartDoctorName.setText(doctor.getDoctorName());

            if(doctorImage!=null && !doctorImage.trim().isEmpty()){
                Picasso.get().load(doctorImage).error(R.drawable.ic_baseline_person_24).placeholder(R.drawable.ic_baseline_person_24).into(binding.cartDoctorProfileImage);
            }
            else{
                binding.cartDoctorProfileImage.setImageResource(R.drawable.ic_baseline_person_24);
            }
        }
    }
}
