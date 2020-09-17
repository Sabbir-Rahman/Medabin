package com.example.medabinfinal.updateRecord.medicalReport.Adapter;

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
import com.example.medabinfinal.updateRecord.medicalReport.Database.MedicalReportModel;
import com.example.medabinfinal.updateRecord.medicalReport.reportFullViewImage;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.reportViewHolder> {


    private Context context;

    List<MedicalReportModel> reportModel = new ArrayList<>();


    public ReportAdapter(Context context, List<MedicalReportModel> reportModel) {
        this.context = context;
        this.reportModel = reportModel;
    }

    @NonNull
    @Override
    public ReportAdapter.reportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new reportViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_report_record_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull reportViewHolder holder, int position) {

        holder.reportName.setText(reportModel.get(position).getReportName());
        holder.reportPrescribed.setText(reportModel.get(position).getPrescribedPeople());
        holder.diagnosticName.setText(reportModel.get(position).getDiagnosticName());
        holder.reportDetails.setText(reportModel.get(position).getReprotDetails());
        holder.testCost.setText(String.valueOf(reportModel.get(position).getTestCost()));
        holder.recordComment.setText(reportModel.get(position).getRecordComment());
        holder.testDate.setText(reportModel.get(position).getDate());

        holder.imageReport.setImageBitmap(reportModel.get(position).getImageReport());


    }



    @Override
    public int getItemCount() {
        return reportModel.size();
    }


    public class reportViewHolder extends  RecyclerView.ViewHolder{

        public TextView reportName,reportPrescribed,diagnosticName,reportDetails,testCost,recordComment,testDate;
        public ImageView imageReport;

        public reportViewHolder(@NonNull View itemView) {
            super(itemView);

            reportName = itemView.findViewById(R.id.layout_name_report);
            reportPrescribed = itemView.findViewById(R.id.layout_report_prescribed);
            diagnosticName = itemView.findViewById(R.id.layout_report_diagnostic_name);
            reportDetails = itemView.findViewById(R.id.report_layout_report_details);
            testCost = itemView.findViewById(R.id.layout_report_test_cost);
            recordComment = itemView.findViewById(R.id.layout_report_comments);
            testDate = itemView.findViewById(R.id.layout_report_test_date);
            imageReport = itemView.findViewById(R.id.layout_reportImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(v.getContext(), reportFullViewImage.class);

                    //passs the data
                    intent.putExtra("ID",reportModel.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);

                }
            });


        }


    }

}
