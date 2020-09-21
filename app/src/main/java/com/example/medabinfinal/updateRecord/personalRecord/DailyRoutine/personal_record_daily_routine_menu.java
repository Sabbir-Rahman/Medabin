package com.example.medabinfinal.updateRecord.personalRecord.DailyRoutine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class personal_record_daily_routine_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_daily_routine_menu);
    }

    public void sendUserToDailyRoutineAdd(View v)
    {
        Intent intent = new Intent(this,personal_record_daily_routine_add.class);
        startActivity(intent);
    }

    public void sendUserToDailyRoutineView(View v)
    {
        Intent intent = new Intent(this,personal_record_daily_routine_view.class);
        startActivity(intent);

    }
}