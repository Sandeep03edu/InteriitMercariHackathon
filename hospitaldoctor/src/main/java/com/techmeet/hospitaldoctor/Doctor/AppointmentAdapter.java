package com.techmeet.hospitaldoctor.Doctor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Appointment;
import com.techmeet.common.Utils.Constants;
import com.techmeet.hospitaldoctor.R;
import com.techmeet.hospitaldoctor.databinding.CartPatientAppointmentBinding;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>{

    private Context mContext;
    private ArrayList<Appointment> appointmentArrayList;

    public AppointmentAdapter(Context mContext, ArrayList<Appointment> appointmentArrayList) {
        this.mContext = mContext;
        this.appointmentArrayList = appointmentArrayList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_patient_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentArrayList.get(position);
        holder.SetData(mContext, appointment);
    }

    @Override
    public int getItemCount() {
        return appointmentArrayList.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        CartPatientAppointmentBinding binding;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartPatientAppointmentBinding.bind(itemView);
        }

        public void SetData(Context mContext, Appointment appointment) {
            // TODO : Get Patient Details and set name
            String patId = appointment.getPatId();
            String diseaseDesc = appointment.getDiseaseDesc();

            binding.cartPatientAppointmentDesc.setText(diseaseDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Move to Prescription filling page
                    Intent intent = new Intent(mContext, PrescriptionFillingActivity.class);
                    String appointGson = new Gson().toJson(appointment);
                    intent.putExtra(Constants.APPOINTMENT, appointGson);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
