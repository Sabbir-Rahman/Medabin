package com.example.medabinfinal.updateRecord.personalRecord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.DiceaseModel;

import java.util.ArrayList;
import java.util.List;

public class CommonDiseaseAdapter extends RecyclerView.Adapter<CommonDiseaseAdapter.CommonDiceaseViewHolder> {

    private Context context;

    List<DiceaseModel> diceaseModels = new ArrayList<>();

    public CommonDiseaseAdapter(Context context, List<DiceaseModel> diceaseModels) {
        this.context = context;
        this.diceaseModels = diceaseModels;
    }

    @NonNull
    @Override
    public CommonDiseaseAdapter.CommonDiceaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommonDiseaseAdapter.CommonDiceaseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personal_record_dicease_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CommonDiseaseAdapter.CommonDiceaseViewHolder holder, int position) {

        holder.dateSymptom.setText(diceaseModels.get(position).getDate());
        holder.timeSymptom.setText(String.valueOf(diceaseModels.get(position).getTime()));
        holder.fever.setText(String.valueOf(diceaseModels.get(position).getFeelFever()));
        holder.stomachPain.setText(String.valueOf(diceaseModels.get(position).getFeelStomachPain()));
        holder.bodyPain.setText(String.valueOf(diceaseModels.get(position).getFeelBodyPain()));
        holder.headPain.setText(String.valueOf(diceaseModels.get(position).getFeelHeadPain()));


    }

    @Override
    public int getItemCount() {
        return diceaseModels.size();
    }


    public class CommonDiceaseViewHolder extends  RecyclerView.ViewHolder{

        public TextView dateSymptom,timeSymptom,fever,stomachPain,bodyPain,headPain;


        public CommonDiceaseViewHolder(@NonNull View itemView) {
            super(itemView);

            dateSymptom = itemView.findViewById(R.id.Symptoms_textDate);
            timeSymptom = itemView.findViewById(R.id.Symptoms_textTime);
            fever = itemView.findViewById(R.id.symptoms_view_fever);
            stomachPain = itemView.findViewById(R.id.symptoms_view_stomach);
            bodyPain = itemView.findViewById(R.id.Symptoms_view_body_pain);
            headPain = itemView.findViewById(R.id.Symptoms_view_head);



        }


    }

}
