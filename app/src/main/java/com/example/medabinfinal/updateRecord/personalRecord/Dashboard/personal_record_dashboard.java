package com.example.medabinfinal.updateRecord.personalRecord.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.BP.personal_record_bp_menu;
import com.example.medabinfinal.updateRecord.personalRecord.DailyRoutine.personal_record_daily_routine_menu;
import com.example.medabinfinal.updateRecord.personalRecord.Food.personal_record_food_menu;
import com.example.medabinfinal.updateRecord.personalRecord.Glucose.personal_record_glucose_menu;
import com.example.medabinfinal.updateRecord.personalRecord.Height.personal_record_height_menu;
import com.example.medabinfinal.updateRecord.personalRecord.ShowRecords.personal_record_showRecords_menu;
import com.example.medabinfinal.updateRecord.personalRecord.CommonDisease.personal_record_symptom_menu;
import com.example.medabinfinal.updateRecord.personalRecord.Weight.personal_record_weight_menu;

public class personal_record_dashboard extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_record_dashboard);
    }

    public void sendUserToHeightMenu(View v){
        Intent intent = new Intent(this, personal_record_height_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to height", Toast.LENGTH_SHORT).show();


    }

    public void sendUserToWeightMenu(View v){
        Intent intent = new Intent(this, personal_record_weight_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to weight", Toast.LENGTH_SHORT).show();


    }

    public void sendUserToBPMenu(View v){

        Intent intent = new Intent(this, personal_record_bp_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to BP", Toast.LENGTH_SHORT).show();

    }

    public void sendUserToGlucoseMenu(View v){
        Intent intent = new Intent(this, personal_record_glucose_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to glucose", Toast.LENGTH_SHORT).show();


    }

    public void sendUserToFoodMenu(View v){

        Intent intent = new Intent(this, personal_record_food_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to food", Toast.LENGTH_SHORT).show();


    }

    public void sendUserToSymptomMenu(View v)
    {
        Intent intent = new Intent(this, personal_record_symptom_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to symptom", Toast.LENGTH_SHORT).show();


    }

    public void sendUserToDailyRoutine(View v)
    {
        Intent intent = new Intent(this, personal_record_daily_routine_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to Daily Routine", Toast.LENGTH_SHORT).show();


    }

    public void sendUserToAllReports(View v){

        Intent intent = new Intent(this, personal_record_showRecords_menu.class);
        startActivity(intent);
        Toast.makeText(this, "Going to all records", Toast.LENGTH_SHORT).show();


    }

}