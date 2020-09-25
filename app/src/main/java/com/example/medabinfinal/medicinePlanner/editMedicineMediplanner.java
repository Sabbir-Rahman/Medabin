package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    Button btn_update,retrive_info;
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
        retrive_info = findViewById(R.id.retrive_info);

        dataBasehelper = new MediplannerDatabaseHelper(editMedicineMediplanner.this);
        ShowMedicineOnlistView(dataBasehelper);
        updateData();


    }

    public void retreiveData(View v)
    {

        try {
            //calling the single function from database to retrive info
            editMedicineName.setText(dataBasehelper.getSingleMedicineName(edit_id.getText().toString()));
            editMedicineCompany.setText(dataBasehelper.getSingleMedicineCompany(edit_id.getText().toString()));
            editMedicineSchedule.setText(Integer.toString(dataBasehelper.getSingleMedicineSchedule(edit_id.getText().toString())));
            editMedicineConsume.setText(Float.toString(dataBasehelper.getSingleMedicineConsume(edit_id.getText().toString())));
            editMedicineRate.setText(Float.toString(dataBasehelper.getSingleMedicineRate(edit_id.getText().toString())));
            sw_active.setChecked(dataBasehelper.getSingleMedicineIsActive(edit_id.getText().toString()));

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please enter id first", Toast.LENGTH_SHORT).show();
        }

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

    public void onBackPressed(){
        Intent intent = new Intent(editMedicineMediplanner.this,MedicineMenu.class);
        startActivity(intent);
        finish();
    }
}