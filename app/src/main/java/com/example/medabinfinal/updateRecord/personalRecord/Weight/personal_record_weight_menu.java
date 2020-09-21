package com.example.medabinfinal.updateRecord.personalRecord.Weight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class personal_record_weight_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_weight_menu);
    }

    public void sendUserToWeightAdd(View v)
    {
        Intent intent = new Intent(this,personal_record_weight_add.class);
        startActivity(intent);
    }

    public void sendUserToWeightView(View v)
    {
        Intent intent = new Intent(this,personal_record_weight_view.class);
        startActivity(intent);
    }
}