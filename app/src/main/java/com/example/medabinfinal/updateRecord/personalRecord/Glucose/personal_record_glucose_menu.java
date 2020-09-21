package com.example.medabinfinal.updateRecord.personalRecord.Glucose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class personal_record_glucose_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona_record_glucose_menu);
    }

    public void sendUserToGlucoseAdd(View v)
    {
        Intent intent = new Intent(this,personal_record_glucose_add.class);
        startActivity(intent);
    }

    public void sendUserToGlucoseView(View v)
    {
        Intent intent = new Intent(this,personal_record_glucose_view.class);
        startActivity(intent);
    }
}