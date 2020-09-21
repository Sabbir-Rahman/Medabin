package com.example.medabinfinal.updateRecord.personalRecord.Weight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;
import com.example.medabinfinal.updateRecord.personalRecord.Database.WeightModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_record_weight_add extends AppCompatActivity {

    EditText weight;
    PersonalRecordDatabase db;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_weight_add);

        db = new PersonalRecordDatabase(this);

        weight = findViewById(R.id.personal_record_weight_add);
        submit = findViewById(R.id.personal_record_weight_submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

    }

    private void addData()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        String currentDate = sdf.format(new Date());


        try
        {
            //put data
            int weightDatabase = Integer.parseInt(weight.getText().toString());

            WeightModel weightModel = new WeightModel(currentDate,weightDatabase);
            PersonalRecordDatabase db = new PersonalRecordDatabase(this);

            db.addDataWeight(weightModel);



        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please provide all value", Toast.LENGTH_SHORT).show();
        }

    }
}