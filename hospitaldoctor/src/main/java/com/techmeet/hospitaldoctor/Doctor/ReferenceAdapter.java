package com.techmeet.hospitaldoctor.Doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techmeet.hospitaldoctor.R;
import com.techmeet.hospitaldoctor.databinding.CartReferenceBinding;

import java.util.ArrayList;

public class ReferenceAdapter extends RecyclerView.Adapter<ReferenceAdapter.ReferenceViewHolder>{

    private Context mContext;
    private ArrayList<String> referenceIds;

    public ReferenceAdapter(Context mContext, ArrayList<String> referenceIds) {
        this.mContext = mContext;
        this.referenceIds = referenceIds;
    }

    @NonNull
    @Override
    public ReferenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_reference, parent, false);
        return new ReferenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferenceViewHolder holder, int position) {
        String refId = referenceIds.get(position);
        holder.SetData(mContext, refId);
    }

    @Override
    public int getItemCount() {
        return referenceIds.size();
    }

    public class ReferenceViewHolder extends RecyclerView.ViewHolder {
        CartReferenceBinding binding;
        public ReferenceViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CartReferenceBinding.bind(itemView);
        }

        public void SetData(Context mContext, String refId) {
            // TODO : Fetch Prescription Details from refId
        }
    }
}
