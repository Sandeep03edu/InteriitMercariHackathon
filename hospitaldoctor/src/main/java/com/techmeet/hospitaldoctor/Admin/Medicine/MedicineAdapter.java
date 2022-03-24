package com.techmeet.hospitaldoctor.Admin.Medicine;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Constants;
import com.techmeet.common.Utils.Medicine;
import com.techmeet.hospitaldoctor.R;
import com.techmeet.hospitaldoctor.databinding.CartMedicineBinding;

import java.util.ArrayList;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>{

    private Context mContext;
    private ArrayList<Medicine> medicineArrayList;

    public MedicineAdapter(Context mContext, ArrayList<Medicine> medicineArrayList) {
        this.mContext = mContext;
        this.medicineArrayList = medicineArrayList;
    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_medicine, parent, false);
        return new MedicineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineViewHolder holder, int position) {
        Medicine medicine = medicineArrayList.get(position);
        holder.SetData(mContext, medicine);
    }

    @Override
    public int getItemCount() {
        return medicineArrayList.size();
    }

    public class MedicineViewHolder extends RecyclerView.ViewHolder {
        CartMedicineBinding binding;
        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartMedicineBinding.bind(itemView);
        }

        public void SetData(Context mContext, Medicine medicine) {
            binding.cartMedicineName.setText(medicine.getMedicineName());
            binding.cartMedicineStock.setText(medicine.getStock() +"");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Move to Edit Section
                    Intent intent = new Intent(mContext, AddEditMedicine.class);
                    String medicineGson = new Gson().toJson(medicine);
                    intent.putExtra(Constants.MEDICINE_EDIT, medicineGson);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
