package com.example.medabinfinal.updateRecord.personalRecord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.FoodModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;

    List<FoodModel> foodModels = new ArrayList<>();

    public FoodAdapter(Context context, List<FoodModel> foodModels) {
        this.context = context;
        this.foodModels = foodModels;
    }

    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodAdapter.FoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personal_record_food_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {

        holder.dateFood.setText(foodModels.get(position).getDate());
        holder.carbohydrate.setText(String.valueOf(foodModels.get(position).getCarbohydratesPercentage()));
        holder.protein.setText(String.valueOf(foodModels.get(position).getProteinPercentage()));
        holder.fat.setText(String.valueOf(foodModels.get(position).getFatPercetage()));
        holder.vitamin.setText(String.valueOf(foodModels.get(position).getVitaminPercentage()));
        holder.mineral.setText(String.valueOf(foodModels.get(position).getMineralPercentage()));
        holder.water.setText(String.valueOf(foodModels.get(position).getWaterPercentage()));
        holder.unknown.setText(String.valueOf(foodModels.get(position).getUnknownPercentage()));

    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }


    public class FoodViewHolder extends  RecyclerView.ViewHolder{

        public TextView dateFood,carbohydrate,protein,fat,vitamin,mineral,water,unknown;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            dateFood = itemView.findViewById(R.id.food_textDate);
            carbohydrate = itemView.findViewById(R.id.food_view_carbohydrate);
            protein = itemView.findViewById(R.id.food_view_protein);
            fat = itemView.findViewById(R.id.food_view_fat);
            vitamin = itemView.findViewById(R.id.food_view_vitamin);
            mineral = itemView.findViewById(R.id.food_view_mineral);
            water = itemView.findViewById(R.id.food_view_water);
            unknown = itemView.findViewById(R.id.food_view_unknown);


        }


    }
}
