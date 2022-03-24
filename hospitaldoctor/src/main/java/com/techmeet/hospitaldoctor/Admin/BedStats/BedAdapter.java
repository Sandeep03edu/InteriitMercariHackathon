package com.techmeet.hospitaldoctor.Admin.BedStats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Bed;
import com.techmeet.common.Utils.Constants;
import com.techmeet.hospitaldoctor.R;
import com.techmeet.hospitaldoctor.databinding.CartBedBinding;

import java.util.ArrayList;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.BedViewHolder>{

    private Context mContext;
    private ArrayList<Bed> bedArrayList;

    public BedAdapter(Context mContext, ArrayList<Bed> bedArrayList) {
        this.mContext = mContext;
        this.bedArrayList = bedArrayList;
    }

    @NonNull
    @Override
    public BedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_bed, parent, false);
        return new BedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BedViewHolder holder, int position) {
        Bed bed = bedArrayList.get(position);
        holder.SetData(mContext, bed);
    }

    @Override
    public int getItemCount() {
        return bedArrayList.size();
    }

    public class BedViewHolder extends RecyclerView.ViewHolder {
        CartBedBinding binding;
        public BedViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartBedBinding.bind(itemView);
        }

        public void SetData(Context mContext, Bed bed) {
            binding.cartBedType.setText(bed.getType());
            binding.cartBedStock.setText(bed.getAvailable() +"/" + bed.getTotal());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent addEditBedIntent = new Intent(mContext, AddEditBed.class);
                    String bedGson = new Gson().toJson(bed);
                    addEditBedIntent.putExtra(Constants.BED_EDIT, bedGson);
                    mContext.startActivity(addEditBedIntent);
                }
            });
        }
    }
}
