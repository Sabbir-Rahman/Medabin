package com.example.medabinfinal.giveFeedback.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.giveFeedback.Database.RatingHospitalModel;
import com.example.medabinfinal.giveFeedback.Database.RatingMedicineModel;

import java.util.ArrayList;
import java.util.List;

public class MedicineRankAdapter extends RecyclerView.Adapter<MedicineRankAdapter.MedicineRankViewHolder>{

    private Context context;

    List<RatingMedicineModel> ratingMedicineModels = new ArrayList<>();

    public MedicineRankAdapter(Context context, List<RatingMedicineModel> ratingMedicineModels) {
        this.context = context;
        this.ratingMedicineModels = ratingMedicineModels;
    }

    @NonNull
    @Override
    public MedicineRankAdapter.MedicineRankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineRankAdapter.MedicineRankViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_medicine_rank_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MedicineRankAdapter.MedicineRankViewHolder holder, int position) {

        holder.nameMedicine.setText(ratingMedicineModels.get(position).getName());
        holder.priceMedicine.setText(String.valueOf(ratingMedicineModels.get(position).getPrice()));
        holder.packaging.setText(String.valueOf(ratingMedicineModels.get(position).getPackaging()));
        holder.effectiveness.setText(String.valueOf(ratingMedicineModels.get(position).getEffectiveness()));
        holder.sideEffects.setText(String.valueOf(ratingMedicineModels.get(position).getSide()));
        holder.total.setText(String.valueOf(ratingMedicineModels.get(position).getTotal()));
    }

    @Override
    public int getItemCount()
    {
        return ratingMedicineModels.size();
    }


    public class MedicineRankViewHolder extends  RecyclerView.ViewHolder{

        public TextView nameMedicine,priceMedicine,packaging,effectiveness,sideEffects,total;


        public MedicineRankViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMedicine = itemView.findViewById(R.id.medicine_rank_view_name);
            priceMedicine = itemView.findViewById(R.id.medicine_rank_view_pricing);
            packaging = itemView.findViewById(R.id.medicine_rank_view_packaging);
            effectiveness = itemView.findViewById(R.id.medicine_rank_view_effectiveness);
            sideEffects = itemView.findViewById(R.id.medicine_rank_view_side_effects);
            total = itemView.findViewById(R.id.medicine_rank_view_total);


        }


    }
}
