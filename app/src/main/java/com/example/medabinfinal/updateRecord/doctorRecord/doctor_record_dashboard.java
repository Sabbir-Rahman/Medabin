package com.example.medabinfinal.updateRecord.doctorRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class doctor_record_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_record_dashboard);
    }


    public void enterDoctorRecordAdd(View v){
        Intent intent = new Intent(this,doctor_record_add.class);
        startActivity(intent);
        finish();

    }

    public void enterDoctorRecordView(View v){
        Intent intent = new Intent(this,doctor_record_view.class);
        startActivity(intent);
        finish();

    }
}