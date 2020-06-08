package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.medabinfinal.R;

public class MedicineMenu extends AppCompatActivity {

    ImageButton addMedicine,editMedicine,viewMedicine,deleteMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addMedicine = findViewById(R.id.addMedicine);
        editMedicine = findViewById(R.id.editMedicine);
        deleteMedicine = findViewById(R.id.deleteMedicine);
        viewMedicine = findViewById(R.id.viewMedicine);

        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToAddMedicine();
            }
        });

        editMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToEditMedicine();
            }
        });

        deleteMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToDeleteMedicine();
            }
        });

        viewMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToViewMedicine();
            }
        });
    }

    public void SendUserToAddMedicine(){
        Intent intent = new Intent(MedicineMenu.this,addMedicineMediplanner.class);
        startActivity(intent);
    }

    public void SendUserToViewMedicine(){
        Intent intent = new Intent(MedicineMenu.this,viewMedicineMediplanner.class);
        startActivity(intent);
    }

    public void SendUserToEditMedicine(){
        Intent intent = new Intent(MedicineMenu.this,editMedicineMediplanner.class);
        startActivity(intent);
    }

    public void SendUserToDeleteMedicine(){
        Intent intent = new Intent(MedicineMenu.this,deleteMedicineMediplanner.class);
        startActivity(intent);
    }
}