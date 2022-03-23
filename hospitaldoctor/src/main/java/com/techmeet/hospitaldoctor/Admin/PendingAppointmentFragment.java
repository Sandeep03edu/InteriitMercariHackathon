package com.techmeet.hospitaldoctor.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techmeet.hospitaldoctor.R;
import com.techmeet.hospitaldoctor.databinding.FragmentPendingAppointmentBinding;

public class PendingAppointmentFragment extends Fragment {
    private FragmentPendingAppointmentBinding binding;


    public PendingAppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentPendingAppointmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}