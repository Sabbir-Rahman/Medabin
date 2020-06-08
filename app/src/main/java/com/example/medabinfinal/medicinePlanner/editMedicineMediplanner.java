package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.medicinePlanner.Database.MediplannerDatabaseHelper;
import com.example.medabinfinal.medicinePlanner.Database.mediplannerMedicineModel;

public class editMedicineMediplanner extends AppCompatActivity {

    EditText edit_id,editMedicineName,editMedicineCompany,editMedicineSchedule,editMedicineConsume,editMedicineRate;
    Button btn_update;
    Switch sw_active;
    ListView lv_medicine;
    ArrayAdapter medicineArrayAdapter;
    MediplannerDatabaseHelper dataBasehelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicine_mediplanner);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        edit_id = findViewById(R.id.editTextId);
        editMedicineName = findViewById(R.id.editTextName);
        editMedicineCompany = findViewById(R.id.editTextCompanyName);
        editMedicineSchedule = findViewById(R.id.editTextSchedule);
        editMedicineConsume = findViewById(R.id.editTextConsume);
        editMedicineRate = findViewById(R.id.editTextRate);
        btn_update = findViewById(R.id.button_update);
        sw_active = findViewById(R.id.switchEdit);
        lv_medicine = findViewById(R.id.list_view_edit);

        dataBasehelper = new MediplannerDatabaseHelper(editMedicineMediplanner.this);
        ShowMedicineOnlistView(dataBasehelper);
        updateData();


    }

    private void ShowMedicineOnlistView(MediplannerDatabaseHelper dataBasehelper2) {
        medicineArrayAdapter = new ArrayAdapter<mediplannerMedicineModel>(editMedicineMediplanner.this, android.R.layout.simple_list_item_1, dataBasehelper2.getEveryone());
        lv_medicine.setAdapter(medicineArrayAdapter);
    }

    public void updateData(){
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = dataBasehelper.updateData(edit_id.getText().toString(),editMedicineName.getText().toString(),editMedicineCompany.getText().toString(),Integer.parseInt(editMedicineSchedule.getText().toString()),Float.parseFloat(editMedicineConsume.getText().toString()),Float.parseFloat(editMedicineRate.getText().toString()),sw_active.isChecked());

                if(isUpdate == true){
                    Toast.makeText(editMedicineMediplanner.this, "Data Updated", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(editMedicineMediplanner.this, "Data not updated", Toast.LENGTH_SHORT).show();
                }

                ShowMedicineOnlistView(dataBasehelper);

            }
        });
    }
}