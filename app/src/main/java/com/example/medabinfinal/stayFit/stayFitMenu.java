package com.example.medabinfinal.stayFit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;

public class stayFitMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_chart_menu);


    }

    public void sendUserToCaloryBurn(View v){
        Toast.makeText(this, "User Sent to calory burn", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,calorieBurnCalculation.class);
        startActivity(intent);
        finish();
    }

    public void sendUserToDietChartView(View v){
        Toast.makeText(this, "User Sent to diet chart view", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,CalorieChartView.class);
        startActivity(intent);
        finish();
    }

    public void sendUserToCaloryNeed(View v){
        Toast.makeText(this, "User Sent to diet chart view", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,neededCalorieCalculator.class);
        startActivity(intent);
        finish();
    }
}