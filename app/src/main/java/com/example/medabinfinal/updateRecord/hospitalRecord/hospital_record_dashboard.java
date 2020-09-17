package com.example.medabinfinal.updateRecord.hospitalRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;

public class hospital_record_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_record_dashboard);
    }


    public void sendUserToHospitalRecordAdd(View v)
    {
        Intent intent = new Intent(this,hospital_record_add.class);
        startActivity(intent);
        Toast.makeText(this, "Going To Hospital Record Add", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void sendUserToHospitalRecordView(View v)
    {
        Intent intent = new Intent(this,hospital_record_view.class);
        startActivity(intent);
        Toast.makeText(this, "Going to hospital Record View", Toast.LENGTH_SHORT).show();
    }
}