package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.medabinfinal.R;

public class medicinePlannerMenu extends AppCompatActivity {

    ImageButton medicine,planning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_planner_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        medicine = findViewById(R.id.imageButtonMedicine);
        planning = findViewById(R.id.imageButtonPlanning);

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToMedicineMenu();
            }
        });

        planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToPlanningMenu();
            }
        });



    }

    public void SendUserToMedicineMenu(){
        Intent intent = new Intent(medicinePlannerMenu.this,MedicineMenu.class);
        startActivity(intent);
    }

    public void SendUserToPlanningMenu(){
        Intent intent = new Intent(medicinePlannerMenu.this,planningMenu.class);
        startActivity(intent);
    }
}