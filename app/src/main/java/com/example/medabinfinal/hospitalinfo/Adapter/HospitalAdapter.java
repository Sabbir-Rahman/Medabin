package com.example.medabinfinal.hospitalinfo.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.hospitalinfo.Model.hospitalModel;

import java.util.List;




public class HospitalAdapter extends RecyclerView.Adapter<HospitalViewHolder> {

     Context context;
     List<hospitalModel> hospitals;



    public HospitalAdapter(Context context, List<hospitalModel> hospitals){

        this.context = context;
        this.hospitals = hospitals;

    }


    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.layout_hospital_info_view,parent,false);

        return new HospitalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        holder.hospitalName.setText(hospitals.get(position).getName());
        holder.hospitalLocation.setText(hospitals.get(position).getLocation());
        holder.hospitalPhone.setText(hospitals.get(position).getPhone());
        holder.emergencyUnit.setText(hospitals.get(position).getEmergencyUnit());
        holder.hotlineAmbulance.setText(hospitals.get(position).getHotlineAmbulance());
        holder.hospitalSpeciality.setText(hospitals.get(position).getSpeciality());
        holder.hospitalCabin.setText(String.valueOf(hospitals.get(position).getCebinNo()));
        holder.hospitalWard.setText(String.valueOf(hospitals.get(position).getWardNo()));
        holder.hospitalOthers.setText(hospitals.get(position).getOthers());
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }
}

