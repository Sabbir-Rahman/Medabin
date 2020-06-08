package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.example.medabinfinal.R;
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
                mediplannerMedicineModel medicineModel;


                try {
                    medicineModel = new mediplannerMedicineModel(-1,medicineName.getEditText().toString(),medicineCompnay.getEditText().toString(),Integer.parseInt(medicineSchedule.getEditText().toString()),Float.parseFloat(medicineConsume.getEditText().toString()),Float.parseFloat(medicineRate.getEditText().toString()),isCurrent.isChecked());

                    Toast.makeText(addMedicineMediplanner.this, medicineModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(addMedicineMediplanner.this, "Oh no! Something happen input again", Toast.LENGTH_SHORT).show();

                    medicineModel = new mediplannerMedicineModel(-1,"Error","Error",0,0,0,false);

                }

                MediplannerDatabaseHelper databaseHelper = new MediplannerDatabaseHelper(addMedicineMediplanner.this);

                boolean success = databaseHelper.addOne(medicineModel);

                Toast.makeText(addMedicineMediplanner.this, "Success="+success, Toast.LENGTH_SHORT).show();
            }
        });

    }
}