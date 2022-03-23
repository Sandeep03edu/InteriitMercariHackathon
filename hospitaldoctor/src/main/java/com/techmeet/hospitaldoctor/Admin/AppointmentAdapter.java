package com.techmeet.hospitaldoctor.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techmeet.common.Utils.Appointment;
import com.techmeet.hospitaldoctor.R;

import java.util.ArrayList;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder>{
    private ArrayList<Appointment> appointmentArrayList;
    private Context context;
    private String status;

    public AppointmentAdapter(ArrayList<Appointment> appointmentArrayList, Context context, String status) {
        this.appointmentArrayList = appointmentArrayList;
        this.context = context;
        this.status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.appointment_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment appointment=appointmentArrayList.get(position);
        String patientID= appointment.getPatId();
        String doctorID= appointment.getDocId();
        holder.description.setText(appointment.getDiseaseDesc());
        if (status.toLowerCase().equals("pending")){
            holder.status.setVisibility(View.GONE);
            holder.approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.decline.setEnabled(false);
                }
            });
            holder.decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.approve.setEnabled(false);
                }
            });
        }
        else {
            holder.status.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {

        return appointmentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView patientName,doctorName,description,status;
        ImageView approve,decline;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientName=itemView.findViewById(R.id.patient_name);
            doctorName=itemView.findViewById(R.id.doctor_name);
            description=itemView.findViewById(R.id.description);
            status=itemView.findViewById(R.id.status);
            approve=itemView.findViewById(R.id.appointment_approved);
            decline=itemView.findViewById(R.id.appointment_declined);
        }
    }
}
