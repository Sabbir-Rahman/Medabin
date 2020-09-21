package com.example.medabinfinal.updateRecord.personalRecord.Height;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_record_height_add extends AppCompatActivity {

    EditText feet,inch;
    Button submit;
    PersonalRecordDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_height_add);

        db = new PersonalRecordDatabase(this);

        feet = findViewById(R.id.personal_record_height_feet);
        inch = findViewById(R.id.personal_record_height_inch);
        submit = findViewById(R.id.personal_record_height_submit_button);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

    }

    public void addData()
    {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        String currentDate = sdf.format(new Date());

        try
        {
            //put data
            int feetDatabase = Integer.parseInt(feet.getText().toString());
            int inchDatabase = Integer.parseInt(inch.getText().toString());

            HeightModel heightModel = new HeightModel(currentDate,feetDatabase,inchDatabase);
            PersonalRecordDatabase db = new PersonalRecordDatabase(this);

            db.addDataHeight(heightModel);



        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please provide all value", Toast.LENGTH_SHORT).show();
        }



    }
}