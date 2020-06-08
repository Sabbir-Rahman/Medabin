package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.medicinePlanner.Database.MediplannerDatabaseHelper;
import com.example.medabinfinal.medicinePlanner.Database.mediplannerMedicineModel;

public class deleteMedicineMediplanner extends AppCompatActivity {

    ListView lv_medicine;
    ArrayAdapter medicineArrayAdapter;
    MediplannerDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_medicine_mediplanner);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lv_medicine = findViewById(R.id.listViewDelete);

        databaseHelper = new MediplannerDatabaseHelper(deleteMedicineMediplanner.this);
        ShowMedicineOnlistView(databaseHelper);

        lv_medicine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediplannerMedicineModel clickmedicineModel = (mediplannerMedicineModel) parent.getItemAtPosition(position);
                databaseHelper.deleteOne(clickmedicineModel);
                ShowMedicineOnlistView(databaseHelper);
                Toast.makeText(deleteMedicineMediplanner.this, "Deleted "+clickmedicineModel.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowMedicineOnlistView(MediplannerDatabaseHelper dataBasehelper2) {
        medicineArrayAdapter = new ArrayAdapter<mediplannerMedicineModel>(deleteMedicineMediplanner.this, android.R.layout.simple_list_item_1, dataBasehelper2.getEveryone());
        lv_medicine.setAdapter(medicineArrayAdapter);
    }



}