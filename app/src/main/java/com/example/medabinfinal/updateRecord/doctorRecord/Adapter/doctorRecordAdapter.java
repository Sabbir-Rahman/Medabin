package com.example.medabinfinal.updateRecord.doctorRecord.Adapter;

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
import com.example.medabinfinal.updateRecord.doctorRecord.Database.updateRecordDoctorModel;
import com.example.medabinfinal.updateRecord.doctorRecord.showFullPrescriptionImage;

import java.util.ArrayList;
import java.util.List;



public class doctorRecordAdapter extends RecyclerView.Adapter<doctorRecordAdapter.doctorRecordViewHolder> {


    private Context context;

    List<updateRecordDoctorModel> doctorModel = new ArrayList<>();


    public doctorRecordAdapter(Context context, List<updateRecordDoctorModel> doctorModel) {
        this.context = context;
        this.doctorModel = doctorModel;
    }

    @NonNull
    @Override
    public doctorRecordAdapter.doctorRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new doctorRecordViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_doctor_record_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull doctorRecordViewHolder holder, int position) {

        holder.doctorName.setText(doctorModel.get(position).getDoctorName());
        holder.doctorSpeciality.setText(doctorModel.get(position).getSpeciality());
        holder.doctorChamberLocation.setText(doctorModel.get(position).getChamberLocation());
        holder.patientSymptom.setText(doctorModel.get(position).getSymtomps());
        holder.doctorFee.setText(String.valueOf(doctorModel.get(position).getConsultantFee()));
        holder.doctorComment.setText(doctorModel.get(position).getComments());
        holder.doctorVisitDate.setText(doctorModel.get(position).getVisitDate());

        holder.imagePrescription.setImageBitmap(doctorModel.get(position).getImage());

    }



    @Override
    public int getItemCount() {
        return doctorModel.size();
    }


    public class doctorRecordViewHolder extends  RecyclerView.ViewHolder{

        public TextView doctorName,doctorSpeciality,doctorChamberLocation,patientSymptom,doctorFee,doctorComment,doctorVisitDate;
        public ImageView imagePrescription;

        public doctorRecordViewHolder(@NonNull View itemView) {
            super(itemView);

            doctorName = itemView.findViewById(R.id.layout_name_doctor);
            doctorSpeciality = itemView.findViewById(R.id.layout_doctor_speciality);
            doctorChamberLocation = itemView.findViewById(R.id.layout_doctorChamberLocation);
            patientSymptom = itemView.findViewById(R.id.doctor_record_layout_patient_symptom);
            doctorFee = itemView.findViewById(R.id.layout_doctorRecordDoctorFees);
            doctorComment = itemView.findViewById(R.id.layout_doctorRecordDoctorComments);
            doctorVisitDate = itemView.findViewById(R.id.layout_doctorRecordDoctorComments);
            imagePrescription = itemView.findViewById(R.id.layout_doctorRecordPrescriptionImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), showFullPrescriptionImage.class);

                    //passs the data
                    intent.putExtra("ID",doctorModel.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }
            });


        }


    }


}

