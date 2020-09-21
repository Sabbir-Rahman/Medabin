package com.example.medabinfinal.updateRecord.personalRecord.Glucose;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.GlucoseModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_record_glucose_add extends AppCompatActivity {

    EditText beforeFast,afterfast;
    Button submit;
    PersonalRecordDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_glucose_add);
        beforeFast = findViewById(R.id.personal_record_glucose_before_fast);
        afterfast = findViewById(R.id.personal_record_glucose_after_fast);

        db = new PersonalRecordDatabase(this);
        submit = findViewById(R.id.personal_record_glucose_submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private void addData()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        String currrentDate = sdfDate.format(new Date());


        try {


            PersonalRecordDatabase db = new PersonalRecordDatabase(this);


            //taking till 2 digit after point
            float beforeFastDatabse = (Math.round(Float.parseFloat(String.format(beforeFast.getText().toString().trim(), "%.02f"))*100f))/100f;
            float afterFastDatabase = (Math.round(Float.parseFloat(String.format(afterfast.getText().toString().trim(), "%.02f"))*100f))/100f;

            GlucoseModel glucoseModel = new GlucoseModel(currrentDate,beforeFastDatabse,afterFastDatabase);
            db.addDataGlucose(glucoseModel);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please insert all value", Toast.LENGTH_SHORT).show();
        }



    }
}