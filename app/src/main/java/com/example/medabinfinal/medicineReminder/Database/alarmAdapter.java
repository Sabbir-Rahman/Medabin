package com.example.medabinfinal.medicineReminder.Database;

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
import com.example.medabinfinal.medicineReminder.alarmEditActivity;
import com.example.medabinfinal.updateRecord.medicalReport.Adapter.ReportAdapter;
import com.example.medabinfinal.updateRecord.medicalReport.Database.MedicalReportModel;
import com.example.medabinfinal.updateRecord.medicalReport.reportFullViewImage;

import java.util.ArrayList;
import java.util.List;

public class alarmAdapter extends RecyclerView.Adapter<alarmAdapter.alarmViewHolder> {

    private Context context;

    List<AlarmModel> alarmModel = new ArrayList<>();


    public alarmAdapter(Context context, List<AlarmModel> alarmModel) {
        this.context = context;
        this.alarmModel = alarmModel;
    }

    @NonNull
    @Override
    public alarmAdapter.alarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new alarmAdapter.alarmViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_alarm_record_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull alarmAdapter.alarmViewHolder holder, int position) {

        holder.nameMedicine.setText(alarmModel.get(position).getMedicineName());
        holder.remain.setText(String.valueOf(alarmModel.get(position).getRemain()));
        holder.time1.setText(alarmModel.get(position).getTime1());
        holder.time2.setText(alarmModel.get(position).getTime2());
        holder.time3.setText(alarmModel.get(position).getTime3());
        holder.isActive.setText(alarmModel.get(position).getIsAlarm());

    }



    @Override
    public int getItemCount() {
        return alarmModel.size();
    }


    public class alarmViewHolder extends  RecyclerView.ViewHolder{

        public TextView nameMedicine,time1,time2,time3,isActive,remain;


        public alarmViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMedicine = itemView.findViewById(R.id.medicine_reminder_view_medicine_name);
            time1 = itemView.findViewById(R.id.medicine_reminder_view_medicine_time1);
            time2 = itemView.findViewById(R.id.medicine_reminder_view_medicine_time2);
            time3 = itemView.findViewById(R.id.medicine_reminder_view_medicine_time_3);
            remain = itemView.findViewById(R.id.medicine_reminder_view_medicine_remain);
            isActive = itemView.findViewById(R.id.medicine_reminder_view_medicine_current_status);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item Clicked id "+alarmModel.get(getAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), alarmEditActivity.class);

                    //passs the data
                    intent.putExtra("IDEDIT",alarmModel.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }
            });


        }


    }

}
