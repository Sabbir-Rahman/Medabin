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
import com.example.medabinfinal.giveFeedback.Database.RatingHospitalModel;

import java.util.ArrayList;
import java.util.List;

public class HospitalRankAdapter extends RecyclerView.Adapter<HospitalRankAdapter.HospitalRankViewHolder> {

    private Context context;

    List<RatingHospitalModel> ratingHospitalModels = new ArrayList<>();

    public HospitalRankAdapter(Context context, List<RatingHospitalModel> ratingHospitalModels) {
        this.context = context;
        this.ratingHospitalModels = ratingHospitalModels;
    }

    @NonNull
    @Override
    public HospitalRankAdapter.HospitalRankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalRankAdapter.HospitalRankViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hospital_rank_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull HospitalRankAdapter.HospitalRankViewHolder holder, int position) {

        holder.nameHospital.setText(ratingHospitalModels.get(position).getName());
        holder.service.setText(String.valueOf(ratingHospitalModels.get(position).getService()));
        holder.expense.setText(String.valueOf(ratingHospitalModels.get(position).getExpense()));
        holder.infastructure.setText(String.valueOf(ratingHospitalModels.get(position).getInfrastructure()));
        holder.testingQuality.setText(String.valueOf(ratingHospitalModels.get(position).getTestingQuality()));
        holder.total.setText(String.valueOf(ratingHospitalModels.get(position).getTotal()));
    }

    @Override
    public int getItemCount()
    {
        return ratingHospitalModels.size();
    }


    public class HospitalRankViewHolder extends  RecyclerView.ViewHolder{

        public TextView nameHospital,service,expense,infastructure,testingQuality,total;


        public HospitalRankViewHolder(@NonNull View itemView) {
            super(itemView);

            nameHospital = itemView.findViewById(R.id.hospital_rank_view_name);
            service = itemView.findViewById(R.id.hospital_rank_view_service);
            expense = itemView.findViewById(R.id.hospital_rank_view_expense);
            infastructure = itemView.findViewById(R.id.hospital_rank_view_infastructure);
            testingQuality = itemView.findViewById(R.id.hospital_rank_view_test_quality);
            total = itemView.findViewById(R.id.hospital_rank_view_total);


        }


    }
}
