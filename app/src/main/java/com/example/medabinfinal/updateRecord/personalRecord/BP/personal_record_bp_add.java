package com.example.medabinfinal.updateRecord.personalRecord.BP;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.BpModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;
import com.example.medabinfinal.updateRecord.personalRecord.Database.WeightModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_record_bp_add extends AppCompatActivity {

    EditText bpHigh,bpLow;
    Button submit;
    PersonalRecordDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_bp_add);

        bpHigh = findViewById(R.id.personal_record_bp_high);
        bpLow = findViewById(R.id.personal_record_bp_low);

        submit = findViewById(R.id.personal_record_bp_submit_button);

        db = new PersonalRecordDatabase(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private void addData()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/YYYY");
        String currentDate = sdfDate.format(new Date());

        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
        String currentTime = sdfTime.format(new Date());


        try
        {
            //put data
            int bpHighDatabase = Integer.parseInt(bpHigh.getText().toString());
            int bpLowDatabase = Integer.parseInt(bpLow.getText().toString());

            BpModel bpModel = new BpModel(currentDate,currentTime,bpHighDatabase,bpLowDatabase);
            PersonalRecordDatabase db = new PersonalRecordDatabase(this);

            db.addDataBp(bpModel);



        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please provide all value", Toast.LENGTH_SHORT).show();
            System.out.println("Error"+e);
        }

    }
}