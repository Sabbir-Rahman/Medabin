package com.example.medabinfinal.updateRecord.medicineRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.medicineRecord.Database.updateRecordMedicineDatabase;

public class update_record_medicine_record_menu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record_medicine_record_menu);




    }

    public void enterAddNewMedicine(View v){
        Toast.makeText(this, "Going to add new medicine", Toast.LENGTH_SHORT).show();
        sendUserToAddNewMedicine();

    }

    public void enterViewMedicine(View v){
        Toast.makeText(this, "Going to view medicine", Toast.LENGTH_SHORT).show();
        sendUserToViewMedicine();


    }


    public void sendUserToAddNewMedicine()
    {
        Intent intent = new Intent(this,medicineRecordAddMedicine.class);
        startActivity(intent);
    }

    public void sendUserToViewMedicine()
    {
        Intent intent = new Intent(this,update_record_medicine_view.class);
        startActivity(intent);
    }

}