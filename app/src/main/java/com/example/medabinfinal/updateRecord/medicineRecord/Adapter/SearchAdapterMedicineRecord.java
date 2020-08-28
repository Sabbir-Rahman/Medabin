package com.example.medabinfinal.updateRecord.medicineRecord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.medicineRecord.Database.updateRecordMedicineModel;

import java.util.List;

public class SearchAdapterMedicineRecord extends RecyclerView.Adapter<SearchAdapterMedicineRecord.ViewHolder> {

    LayoutInflater layoutInflater;
    List<updateRecordMedicineModel> allRecords;

    public SearchAdapterMedicineRecord(Context context, List<updateRecordMedicineModel>records){

        this.layoutInflater = LayoutInflater.from(context);
        this.allRecords = records;

    }


    @NonNull
    @Override
    public SearchAdapterMedicineRecord.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.medicine_record_recycler_view,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapterMedicineRecord.ViewHolder holder, int position) {

        String name = allRecords.get(position).getMedicineName();
        String type = allRecords.get(position).getMedicineType();
        Float cosume = allRecords.get(position).getTimes();
        Integer schedule = allRecords.get(position).getDays();
        String morningDb = allRecords.get(position).getMorning();
        String noonDb = allRecords.get(position).getNoon();
        String nightDb = allRecords.get(position).getNight();
        String othersDb = allRecords.get(position).getOthers();
        String reasonMedicine = allRecords.get(position).getReasonToTakeMedicine();
        String prescribeBy = allRecords.get(position).getPrescribedBy();
        String startDateMedicine = allRecords.get(position).getStartDate();
        String endDateMedicine = allRecords.get(position).getEndDate();


        //placing data to recycler view by holder
        holder.medicineName.setText(name);
        holder.medicineType.setText(type);
        holder.times.setText(cosume.toString());
        holder.days.setText(schedule.toString());
        holder.startDate.setText(startDateMedicine);
        holder.endDate.setText(endDateMedicine);
        holder.prescribe.setText(prescribeBy);
        holder.reason.setText(reasonMedicine);

        if(morningDb.equals("Yes")){
            holder.morning.setVisibility(View.VISIBLE);
        }
        if(noonDb.equals("Yes")){
            holder.noon.setVisibility(View.VISIBLE);
        }
        if(nightDb.equals("Yes")){
            holder.night.setVisibility(View.VISIBLE);
        }
        if(othersDb.equals("Yes")){
            holder.others.setVisibility(View.VISIBLE);
        }



    }



    @Override
    public int getItemCount() {

        return allRecords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView medicineName,medicineType,times,days,startDate,endDate,reason,prescribe;
        public TextView morning,noon,night,others;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            medicineName = itemView.findViewById(R.id.name_medicine);
            medicineType = itemView.findViewById(R.id.textType);
            times = itemView.findViewById(R.id.textTime);
            days = itemView.findViewById(R.id.days);
            morning = itemView.findViewById(R.id.view_checkbox_morning);
            noon = itemView.findViewById(R.id.view_checkbox_noon);
            night = itemView.findViewById(R.id.view_checkbox_night);
            others = itemView.findViewById(R.id.view_checkbox_others);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            reason = itemView.findViewById(R.id.reason);
            prescribe = itemView.findViewById(R.id.prescribe);






        }
    }
}
