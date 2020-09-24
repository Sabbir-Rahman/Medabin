package com.example.medabinfinal.medicineReminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class medicineReminderMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_reminder_menu);
    }


    public void sendUserToMedicineReminderAdd(View v)
    {
        Intent intent = new Intent(this,medicineReminderAdd.class);
        startActivity(intent);
    }

    public void sendUserToMedicineReminderView(View v)
    {
        Intent intent = new Intent(this,medicineReminderView.class);
        startActivity(intent);
    }
}