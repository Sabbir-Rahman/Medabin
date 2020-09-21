package com.example.medabinfinal.updateRecord.personalRecord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.WeightModel;

import java.util.ArrayList;
import java.util.List;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.WeightViewHolder> {

    private Context context;

    List<WeightModel> weightModels = new ArrayList<>();

    public WeightAdapter(Context context, List<WeightModel> weightModels) {
        this.context = context;
        this.weightModels = weightModels;
    }

    @NonNull
    @Override
    public WeightAdapter.WeightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WeightAdapter.WeightViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personal_record_weight_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull WeightAdapter.WeightViewHolder holder, int position) {

        holder.dateWeight.setText(weightModels.get(position).getDate());
        holder.kg.setText(String.valueOf(weightModels.get(position).getWeightKg()));

    }

    @Override
    public int getItemCount() {
        return weightModels.size();
    }


    public class WeightViewHolder extends  RecyclerView.ViewHolder{

        public TextView dateWeight,kg;


        public WeightViewHolder(@NonNull View itemView) {
            super(itemView);

            dateWeight = itemView.findViewById(R.id.weight_textDate);
            kg = itemView.findViewById(R.id.weight_kg);

        }


    }

}
