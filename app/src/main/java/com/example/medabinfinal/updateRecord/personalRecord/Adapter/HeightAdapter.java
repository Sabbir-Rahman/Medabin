package com.example.medabinfinal.updateRecord.personalRecord.Adapter;

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
import com.example.medabinfinal.updateRecord.doctorRecord.showFullPrescriptionImage;
import com.example.medabinfinal.updateRecord.hospitalRecord.Database.HospitalRecordModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;

import java.util.ArrayList;
import java.util.List;

public class HeightAdapter extends RecyclerView.Adapter<HeightAdapter.HeightViewHolder> {

    private Context context;

    List<HeightModel> heightModels = new ArrayList<>();

    public HeightAdapter(Context context, List<HeightModel> heightModels) {
        this.context = context;
        this.heightModels = heightModels;
    }

    @NonNull
    @Override
    public HeightAdapter.HeightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HeightViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personal_record_height,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull HeightViewHolder holder, int position) {

        holder.dateHeight.setText(heightModels.get(position).getDate());
        holder.feet.setText(String.valueOf(heightModels.get(position).getFeet()));
        holder.inch.setText(String.valueOf(heightModels.get(position).getInch()));

    }

    @Override
    public int getItemCount() {
        return heightModels.size();
    }


    public class HeightViewHolder extends  RecyclerView.ViewHolder{

        public TextView dateHeight,feet,inch;


        public HeightViewHolder(@NonNull View itemView) {
            super(itemView);

            dateHeight = itemView.findViewById(R.id.height_textDate);
            feet = itemView.findViewById(R.id.height_feet);
            inch = itemView.findViewById(R.id.height_inch);



        }


    }

}
