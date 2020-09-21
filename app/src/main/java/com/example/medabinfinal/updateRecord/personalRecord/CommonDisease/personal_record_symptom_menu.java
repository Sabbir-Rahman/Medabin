package com.example.medabinfinal.updateRecord.personalRecord.CommonDisease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class personal_record_symptom_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_symptom_menu);
    }

    public void sendUserToCommonDiseaseAdd(View v)
    {
        Intent intent = new Intent(this,personal_record_common_disease_add.class);
        startActivity(intent);
    }

    public void sendUserToCommonDiseaseView(View v)
    {
        Intent intent = new Intent(this,personal_record_common_disease_view.class);
        startActivity(intent);
    }
}