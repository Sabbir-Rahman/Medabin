package com.example.medabinfinal.updateRecord.personalRecord.Food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class personal_record_food_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_food_menu);
    }

    public void sendUserToFoodAdd(View v)
    {
        Intent intent = new Intent(this,personal_record_food_add.class);
        startActivity(intent);
    }

    public void sendUserToFoodView(View v)
    {
        Intent intent = new Intent(this,personal_record_food_view.class);
        startActivity(intent);
    }
}