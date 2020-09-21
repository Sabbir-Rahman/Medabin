package com.example.medabinfinal.updateRecord.dasboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.doctorRecord.doctor_record_dashboard;
import com.example.medabinfinal.updateRecord.hospitalRecord.hospital_record_dashboard;
import com.example.medabinfinal.updateRecord.medicalReport.medical_report_dashboard;
import com.example.medabinfinal.updateRecord.medicineRecord.update_record_medicine_record_menu;
import com.example.medabinfinal.updateRecord.personalRecord.Dashboard.personal_record_dashboard;

public class updaterecordDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_dashboard);
    }

    public void enterMedicineRecord(View v)
    {
        sendUserToMedicienRecord();
        Toast.makeText(this, "Going to medicine record dashboard", Toast.LENGTH_SHORT).show();
    }

    public void enterDoctorRecord(View v){
        sendUserToDoctorRecord();
        Toast.makeText(this, "Going to Doctor Record", Toast.LENGTH_SHORT).show();
    }


    public void enterPersonalRecord(View v){
        sendUserToPersonalRecord();
        Toast.makeText(this, "Going to personal record", Toast.LENGTH_SHORT).show();
    }

    public void enterMedicalReport(View v){
        sendUserToMedicalReport();
        Toast.makeText(this, "Going to medical report", Toast.LENGTH_SHORT).show();

    }

    public void enterHospitalRecord(View v){
        sendUserToHospitalRecord();
        Toast.makeText(this, "Going to hospital record", Toast.LENGTH_SHORT).show();

    }



    public void sendUserToMedicienRecord()
    {
        Intent intent = new Intent(this, update_record_medicine_record_menu.class);
        startActivity(intent);
    }

    public void sendUserToDoctorRecord()
    {
        Intent intent = new Intent(this, doctor_record_dashboard.class);
        startActivity(intent);
    }


    public void sendUserToPersonalRecord()
    {
        Intent intent = new Intent(this, personal_record_dashboard.class);
        startActivity(intent);
    }


    public void sendUserToMedicalReport()
    {
        Intent intent = new Intent(this, medical_report_dashboard.class);
        startActivity(intent);
    }


    public void sendUserToHospitalRecord()
    {
        Intent intent = new Intent(this, hospital_record_dashboard.class);
        startActivity(intent);
    }
}