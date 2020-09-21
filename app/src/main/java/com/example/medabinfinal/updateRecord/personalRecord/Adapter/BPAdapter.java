package com.example.medabinfinal.updateRecord.personalRecord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.BpModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;

import java.util.ArrayList;
import java.util.List;

public class BPAdapter extends RecyclerView.Adapter<BPAdapter.BPViewHolder>{
    private Context context;

    List<BpModel> bpModels = new ArrayList<>();

    public BPAdapter(Context context, List<BpModel> bpModels) {
        this.context = context;
        this.bpModels = bpModels;
    }

    @NonNull
    @Override
    public BPAdapter.BPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BPAdapter.BPViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personal_record_bp_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull BPAdapter.BPViewHolder holder, int position) {

        holder.dateBp.setText(bpModels.get(position).getDate());
        holder.timeBp.setText(String.valueOf(bpModels.get(position).getTime()));
        holder.bpHigh.setText(String.valueOf(bpModels.get(position).getHigh()));
        holder.bpLow.setText(String.valueOf(bpModels.get(position).getLow()));

    }

    @Override
    public int getItemCount() {

        return bpModels.size();
    }


    public class BPViewHolder extends  RecyclerView.ViewHolder{

        public TextView dateBp,timeBp,bpHigh,bpLow;


        public BPViewHolder(@NonNull View itemView) {
            super(itemView);

            dateBp = itemView.findViewById(R.id.bp_textDate);
            timeBp = itemView.findViewById(R.id.bp_textTime);
            bpHigh = itemView.findViewById(R.id.bp_view_high);
            bpLow = itemView.findViewById(R.id.bp_view_low);


        }


    }


}
