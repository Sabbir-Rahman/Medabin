package com.example.medabinfinal.giveFeedback.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.giveFeedback.Database.RatingDoctorModel;
import com.example.medabinfinal.updateRecord.personalRecord.Adapter.DailyRoutineAdapter;
import com.example.medabinfinal.updateRecord.personalRecord.Database.DailyTimeModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorRankAdapter extends RecyclerView.Adapter<DoctorRankAdapter.DoctorRankViewHolder> {

    private Context context;

    List<RatingDoctorModel> ratingDoctorModels = new ArrayList<>();

    public DoctorRankAdapter(Context context, List<RatingDoctorModel> ratingDoctorModels) {
        this.context = context;
        this.ratingDoctorModels = ratingDoctorModels;
    }

    @NonNull
    @Override
    public DoctorRankAdapter.DoctorRankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoctorRankAdapter.DoctorRankViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_doctor_rank_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull DoctorRankAdapter.DoctorRankViewHolder holder, int position) {

        holder.nameDr.setText(ratingDoctorModels.get(position).getName());
        holder.behaviour.setText(String.valueOf(ratingDoctorModels.get(position).getBehaviour()));
        holder.fee.setText(String.valueOf(ratingDoctorModels.get(position).getFee()));
        holder.prescription.setText(String.valueOf(ratingDoctorModels.get(position).getPrescription()));
        holder.diagnosis.setText(String.valueOf(ratingDoctorModels.get(position).getDiagnosis()));
        holder.total.setText(String.valueOf(ratingDoctorModels.get(position).getTotal()));
    }

    @Override
    public int getItemCount()
    {
        return ratingDoctorModels.size();
    }


    public class DoctorRankViewHolder extends  RecyclerView.ViewHolder{

        public TextView nameDr,behaviour,fee,prescription,diagnosis,total;


        public DoctorRankViewHolder(@NonNull View itemView) {
            super(itemView);

            nameDr = itemView.findViewById(R.id.dr_rank_view_name);
            behaviour = itemView.findViewById(R.id.dr_rank_view_behaviour);
            fee = itemView.findViewById(R.id.dr_rank_view_fee);
            prescription = itemView.findViewById(R.id.dr_rank_view_prescription);
            diagnosis = itemView.findViewById(R.id.dr_rank_view_diagnosis);
            total = itemView.findViewById(R.id.dr_rank_view_total);

        }


    }

}




