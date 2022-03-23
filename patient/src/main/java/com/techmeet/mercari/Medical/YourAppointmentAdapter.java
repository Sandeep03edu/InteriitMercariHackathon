package com.techmeet.mercari.Medical;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techmeet.common.Utils.Appointment;
import com.techmeet.mercari.R;

import java.util.ArrayList;

public class YourAppointmentAdapter extends RecyclerView.Adapter<YourAppointmentAdapter.ViewHolder> {
    ArrayList<Appointment> doctorAppointmentArrayList;
    Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.your_appoitment_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Appointment doctorAppointment= doctorAppointmentArrayList.get(position);
        // TODO : Fetch hospital Name using Id
        String hospitalId = doctorAppointment.getHosId();

        // TODO : Fetch Doctor name using Id
        String docId = doctorAppointment.getDocId();

        String status = doctorAppointment.getStatus();
        holder.status.setText(status);

    }

    @Override
    public int getItemCount() {
        return doctorAppointmentArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView doctor,hospital,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctor=itemView.findViewById(R.id.doctor_name_list_item);
            hospital=itemView.findViewById(R.id.hospital_name_list_item);
            status=itemView.findViewById(R.id.appointment_status);
        }
    }
}
