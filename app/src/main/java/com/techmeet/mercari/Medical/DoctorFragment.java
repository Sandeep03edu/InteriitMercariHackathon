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

import com.techmeet.mercari.Model.Doctor;
import com.techmeet.mercari.R;

import java.util.ArrayList;

public class DoctorFragment extends Fragment {

    public DoctorFragment() {
        // Required empty public constructor
    }


    boolean isVisible = false;
    EditText searchText;
    RecyclerView recyclerView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisible) {
            GetData();
        }
    }

    private void GetData() {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        DoctorAdapter adapter = new DoctorAdapter(getContext(), doctorArrayList);
        recyclerView.setAdapter(adapter);
        // TODO: Implement API and adapter
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_doctor, container, false);

        _init(v);

        AppointmentSchedulerActivity activity = (AppointmentSchedulerActivity) getActivity();
        if (activity != null) {
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
                    if (isVisible) {
                        // Refresh Data if visible to user
                        GetData();
                    }
                }
            });
        }
        return v;
    }

    private void _init(View v) {
        recyclerView = v.findViewById(R.id.fragment_doctor_rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}