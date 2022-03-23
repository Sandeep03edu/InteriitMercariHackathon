package com.techmeet.mercari.Medical;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.techmeet.common.Utils.Hospital;
import com.techmeet.mercari.R;
import com.techmeet.common.Utils.Constants;
import com.techmeet.mercari.databinding.CartHospitalBinding;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {
    
    private Context mContext;
    private ArrayList<Hospital> hospitalArrayList;

    public HospitalAdapter(Context mContext, ArrayList<Hospital> hospitalArrayList) {
        this.mContext = mContext;
        this.hospitalArrayList = hospitalArrayList;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_hospital, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        Hospital hospital = hospitalArrayList.get(position);
        holder.SetData(mContext, hospital);
    }

    @Override
    public int getItemCount() {
        return hospitalArrayList.size();
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder {
        CartHospitalBinding binding;
        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartHospitalBinding.bind(itemView);
        }

        public void SetData(Context mContext, Hospital hospital) {
            binding.cartHospitalName.setText(hospital.getHospitalName());
            binding.cartHospitalAddress.setText(hospital.getAddress());

            String hospitalImage = hospital.getHospitalImage();
            if(hospitalImage==null || hospitalImage.trim().isEmpty()){
                // TODO : Set Local Image
            }
            else{
                // TODO : Set local image in error part
                Picasso.get().load(hospitalImage).into(binding.cartHospitalImage);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Move to Hospital Detail Page
                    Intent intent = new Intent(mContext, DisplayHospitalDetails.class);
                    intent.putExtra(Constants.HOSPITAL_ID, hospital.gethId());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
