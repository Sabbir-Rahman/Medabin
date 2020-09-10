package com.example.medabinfinal.updateRecord.medicalReport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class medical_report_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medical_report_dashboard);
    }

    public void sendUserToAddReport(View v)
    {
        Intent intent = new Intent(this,add_report.class);
        startActivity(intent);
    }

    public void sendUserToViewreport(View v)
    {
        Intent intent = new Intent(this,view_report.class);
        startActivity(intent);
    }
}