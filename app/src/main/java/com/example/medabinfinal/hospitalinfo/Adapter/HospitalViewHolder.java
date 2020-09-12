package com.example.medabinfinal.hospitalinfo.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;

public class HospitalViewHolder extends RecyclerView.ViewHolder
{
    public TextView hospitalName,hospitalLocation,hospitalPhone,emergencyUnit,hotlineAmbulance,hospitalSpeciality,hospitalCabin,hospitalWard,hospitalOthers;


    public HospitalViewHolder(@NonNull View itemView) {
        super(itemView);
        hospitalName = (TextView)itemView.findViewById(R.id.name_hospital);
        hospitalLocation = (TextView)itemView.findViewById(R.id.location_hospital);
        hospitalPhone = (TextView)itemView.findViewById(R.id.hospitalPhone);
        emergencyUnit = (TextView)itemView.findViewById(R.id.hospitalEmergencyUnit);
        hotlineAmbulance = (TextView)itemView.findViewById(R.id.hospitalHotlineAmbulance);
        hospitalSpeciality = (TextView)itemView.findViewById(R.id.hospital_speciality);
        hospitalCabin = (TextView)itemView.findViewById(R.id.hospitalCebin);
        hospitalWard = (TextView)itemView.findViewById(R.id.hospitalWard);
        hospitalOthers = (TextView)itemView.findViewById(R.id.hospitalOthers);

    }
}
