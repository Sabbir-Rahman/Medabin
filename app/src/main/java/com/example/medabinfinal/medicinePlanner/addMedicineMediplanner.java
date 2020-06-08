package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.dashboard.UserDashboard;
import com.example.medabinfinal.loginRegister.SignUp;
import com.example.medabinfinal.medicinePlanner.Database.MediplannerDatabaseHelper;
import com.example.medabinfinal.medicinePlanner.Database.mediplannerMedicineModel;
import com.google.android.material.textfield.TextInputLayout;

public class addMedicineMediplanner extends AppCompatActivity {
    TextInputLayout medicineName,medicineCompnay,medicineSchedule,medicineConsume,medicineRate;
    Button addMedicine;
    Switch isCurrent;


    MediplannerDatabaseHelper databaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine_mediplanner);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        medicineName = findViewById(R.id.medicine_name);
        medicineCompnay = findViewById(R.id.company_name);
        medicineSchedule = findViewById(R.id.schedule_medicine);
        medicineConsume = findViewById(R.id.consume_medicine);
        medicineRate = findViewById(R.id.medicine_rate);
        addMedicine = findViewById(R.id.buttonAddMedicine);
        isCurrent = findViewById(R.id.switch1);

        databaseHelper = new MediplannerDatabaseHelper(addMedicineMediplanner.this);

        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=medicineName.getEditText().getText().toString().trim();
                String company=medicineCompnay.getEditText().getText().toString().trim();
                Integer schedule=Integer.parseInt(medicineSchedule.getEditText().getText().toString().trim());
                Float consume=Float.parseFloat(medicineConsume.getEditText().getText().toString().trim());
                Float rate=Float.parseFloat(medicineRate.getEditText().getText().toString().trim());

                    long value=databaseHelper.addOne(name,company,schedule,consume,rate,isCurrent.isChecked());
                    if(value>0){
                        Toast.makeText(addMedicineMediplanner.this,"Medicine have registered",Toast.LENGTH_SHORT).show();
                        }

            }
        });

    }
}