package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.medicinePlanner.Database.MediplannerDatabaseHelper;
import com.example.medabinfinal.medicinePlanner.Database.mediplannerMedicineModel;

public class viewMedicineMediplanner extends AppCompatActivity {

    ListView lv_medicine;
    ArrayAdapter medicineArrayAdapter;
    MediplannerDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medicine_mediplanner);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lv_medicine = findViewById(R.id.listView);

        databaseHelper = new MediplannerDatabaseHelper(viewMedicineMediplanner.this);
        ShowMedicineOnlistView(databaseHelper);
    }

    private void ShowMedicineOnlistView(MediplannerDatabaseHelper dataBasehelper2) {
        medicineArrayAdapter = new ArrayAdapter<mediplannerMedicineModel>(viewMedicineMediplanner.this, android.R.layout.simple_list_item_1, dataBasehelper2.getEveryone());
        lv_medicine.setAdapter(medicineArrayAdapter);
    }
}