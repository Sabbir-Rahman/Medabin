package com.example.medabinfinal.updateRecord.personalRecord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.GlucoseModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;

import java.util.ArrayList;
import java.util.List;

public class GlucoseAdapter extends RecyclerView.Adapter<GlucoseAdapter.GlucoseViewHolder> {

    private Context context;

    List<GlucoseModel> glucoseModels = new ArrayList<>();

    public GlucoseAdapter(Context context, List<GlucoseModel> glucoseModels) {
        this.context = context;
        this.glucoseModels = glucoseModels;
    }

    @NonNull
    @Override
    public GlucoseAdapter.GlucoseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GlucoseAdapter.GlucoseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personal_record_glucose_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull GlucoseAdapter.GlucoseViewHolder holder, int position) {

        holder.dateGlucose.setText(glucoseModels.get(position).getDate());
        holder.beforeFast.setText(String.valueOf(glucoseModels.get(position).getBeforeFast()));
        holder.afterFast.setText(String.valueOf(glucoseModels.get(position).getAfterFast()));

    }

    @Override
    public int getItemCount()
    {
        return glucoseModels.size();
    }


    public class GlucoseViewHolder extends  RecyclerView.ViewHolder{

        public TextView dateGlucose,beforeFast,afterFast;


        public GlucoseViewHolder(@NonNull View itemView) {
            super(itemView);

            dateGlucose = itemView.findViewById(R.id.glucose_textDate);
            beforeFast = itemView.findViewById(R.id.glucose_before_fast);
            afterFast = itemView.findViewById(R.id.glucose_after_fast);



        }


    }

}
