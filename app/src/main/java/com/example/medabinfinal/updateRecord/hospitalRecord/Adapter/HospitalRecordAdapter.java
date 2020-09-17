package com.example.medabinfinal.updateRecord.hospitalRecord.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.hospitalRecord.Database.HospitalRecordModel;
import com.example.medabinfinal.updateRecord.medicalReport.Adapter.ReportAdapter;
import com.example.medabinfinal.updateRecord.medicalReport.reportFullViewImage;

import java.util.ArrayList;
import java.util.List;

public class HospitalRecordAdapter extends RecyclerView.Adapter<HospitalRecordAdapter.hospitalViewHolder> {


    private Context context;
    List<HospitalRecordModel> hospitalRecordModels = new ArrayList<>();

    public HospitalRecordAdapter(Context context, List<HospitalRecordModel> hospitalRecordModels) {
        this.context = context;
        this.hospitalRecordModels = hospitalRecordModels;
    }

    @NonNull
    @Override
    public hospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalRecordAdapter.hospitalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hospital_record_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull hospitalViewHolder holder, int position) {

        holder.hospitalName.setText(hospitalRecordModels.get(position).getHospitalName());
        holder.admitReason.setText(hospitalRecordModels.get(position).getAdmitReason());
        holder.admitUnder.setText(hospitalRecordModels.get(position).getAdmitUnder());
        holder.aboutCabinWard.setText(hospitalRecordModels.get(position).getAboutCabinWard());
        holder.totalCost.setText(String.valueOf(hospitalRecordModels.get(position).getTotalCost()));
        holder.admitDate.setText(hospitalRecordModels.get(position).getAdmitDate());
        holder.releaseDate.setText(hospitalRecordModels.get(position).getReleaseDate());
        holder.hospitalComment.setText(hospitalRecordModels.get(position).getComments());


    }

    @Override
    public int getItemCount() {
        return hospitalRecordModels.size();
    }

    public class hospitalViewHolder extends  RecyclerView.ViewHolder{

        public TextView hospitalName,admitReason,admitUnder,aboutCabinWard,totalCost,admitDate,releaseDate,hospitalComment;


        public hospitalViewHolder(@NonNull View itemView) {
            super(itemView);

            hospitalName = itemView.findViewById(R.id.layout_name_hospital);
            admitReason = itemView.findViewById(R.id.layout_hospital_record_admit_reason);
            admitUnder = itemView.findViewById(R.id.layout_hospital_record_admit_under);
            aboutCabinWard = itemView.findViewById(R.id.layout_hospital_record_about_cabin);
            totalCost = itemView.findViewById(R.id.layout_hospital_record_cost);
            admitDate = itemView.findViewById(R.id.layout_hospital_record_admit_date);
            releaseDate = itemView.findViewById(R.id.layout_hospital_record_release_date);
            hospitalComment = itemView.findViewById(R.id.layout_hospital_record_comments);




        }


    }


}
