package com.example.medabinfinal.giveFeedback.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.giveFeedback.Database.RatingMedicineModel;
import com.example.medabinfinal.giveFeedback.Database.RatingPharmacyModel;

import java.util.ArrayList;
import java.util.List;

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.PharmacyRankViewHolder>{

    private Context context;

    List<RatingPharmacyModel> ratingPharmacyModels = new ArrayList<>();

    public PharmacyAdapter(Context context, List<RatingPharmacyModel> ratingPharmacyModels) {
        this.context = context;
        this.ratingPharmacyModels = ratingPharmacyModels;
    }

    @NonNull
    @Override
    public PharmacyAdapter.PharmacyRankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PharmacyAdapter.PharmacyRankViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_pharmacy_rank_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapter.PharmacyRankViewHolder holder, int position) {

        holder.namePharmacy.setText(ratingPharmacyModels.get(position).getName());
        holder.servicepharmacy.setText(String.valueOf(ratingPharmacyModels.get(position).getService()));
        holder.pricingPharmacy.setText(String.valueOf(ratingPharmacyModels.get(position).getPricing()));
        holder.wellOrganised.setText(String.valueOf(ratingPharmacyModels.get(position).getWellOrganised()));
        holder.medicineAvailability.setText(String.valueOf(ratingPharmacyModels.get(position).getMedicineAvailability()));
        holder.total.setText(String.valueOf(ratingPharmacyModels.get(position).getTotal()));
    }

    @Override
    public int getItemCount()
    {
        return ratingPharmacyModels.size();
    }


    public class PharmacyRankViewHolder extends  RecyclerView.ViewHolder{

        public TextView namePharmacy,servicepharmacy,pricingPharmacy,wellOrganised,medicineAvailability,total;


        public PharmacyRankViewHolder(@NonNull View itemView) {
            super(itemView);

            namePharmacy = itemView.findViewById(R.id.pharmacy_rank_view_name);
            servicepharmacy = itemView.findViewById(R.id.pharmacy_rank_view_service);
            pricingPharmacy = itemView.findViewById(R.id.pharmacy_rank_view_pricing);
            wellOrganised = itemView.findViewById(R.id.pharmacy_rank_view_well_organised);
            medicineAvailability = itemView.findViewById(R.id.pharmacy_rank_view_medicine_availability);
            total = itemView.findViewById(R.id.pharmacy_rank_view_total);


        }


    }
}
