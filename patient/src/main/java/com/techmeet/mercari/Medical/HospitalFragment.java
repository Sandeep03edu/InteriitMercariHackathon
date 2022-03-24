package com.techmeet.mercari.Medical;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.techmeet.common.Utils.Constants;
import com.techmeet.common.Utils.Hospital;
import com.techmeet.mercari.Data;
import com.techmeet.mercari.R;
import com.techmeet.mercari.retrofit.RetrofitPatientClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        if(isVisible && recyclerView!=null && searchText!=null){
            GetData();
        }
    }

    private void GetData() {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        HospitalAdapter adapter = new HospitalAdapter(getContext(), hospitals);
        recyclerView.setAdapter(adapter);
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("patient", Context.MODE_PRIVATE);

        String patientDetails= sharedPreferences.getString(Constants.PATIENT_REGISTRATION_RESPONSE,"");
        Data data=new Gson().fromJson(patientDetails, Data.class);

        Call<ArrayList<Hospital>> getHospitalByNameCall=RetrofitPatientClient.getService().getHospitalByName(searchText.getText().toString(),data.getAuthToken());
        getHospitalByNameCall.enqueue(new Callback<ArrayList<Hospital>>() {
            @Override
            public void onResponse(Call<ArrayList<Hospital>> call, Response<ArrayList<Hospital>> response) {
                hospitals.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Hospital>> call, Throwable t) {

            }
        });



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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void _init(View v) {
        recyclerView = v.findViewById(R.id.fragment_hospital_rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}