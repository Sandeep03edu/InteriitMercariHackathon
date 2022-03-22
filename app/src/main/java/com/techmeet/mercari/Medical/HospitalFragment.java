package com.techmeet.mercari.Medical;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.techmeet.mercari.Model.Hospital;
import com.techmeet.mercari.R;

import java.util.ArrayList;


public class HospitalFragment extends Fragment {
    public HospitalFragment() {
        // Required empty public constructor
    }

    boolean isVisible = false;
    EditText searchText;
    RecyclerView recyclerView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if(isVisible){
            GetData();
        }
    }

    private void GetData() {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        HospitalAdapter adapter = new HospitalAdapter(getContext(), hospitals);
        recyclerView.setAdapter(adapter);

        // TODO: Implement API and adapter

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);

        _init(view);

        AppointmentSchedulerActivity activity = (AppointmentSchedulerActivity) getActivity();
        if(activity!=null) {
            searchText = activity.editText;
            searchText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(isVisible){
                        // Refresh Data if visible to user
                        GetData();
                    }
                }
            });
        }

        return view;
    }

    private void _init(View v) {
        recyclerView = v.findViewById(R.id.fragment_hospital_rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}