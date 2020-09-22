package com.example.medabinfinal.updateRecord.personalRecord.CommonDisease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.DiceaseModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_record_common_disease_add extends AppCompatActivity {

    CheckBox fever,stomachPain,bodyPain,headPain;
    PersonalRecordDatabase db;

    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_common_disease_add);

        db = new PersonalRecordDatabase(this);

        fever = findViewById(R.id.Symptoms_break_down_fever);
        stomachPain = findViewById(R.id.Symptoms_break_down_stomach_pain);
        bodyPain = findViewById(R.id.Symptoms_break_down_body_pain);
        headPain = findViewById(R.id.Symptoms_break_down_head_pain);

        submit = findViewById(R.id.personal_record_symptom_submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

    }

    private void addData()
    {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/mm/yyyy");
        String currrentDate = sdfDate.format(new Date());
        SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss");
        String currrentTime = sdfTime.format(new Date());

        try {

            String yes = "Yes";
            String no ="No";
            String feverDatabase = no,stomachPainDatabse=no,bodyPainDatabse=no,headPainDatabse=no;
            if(fever.isChecked())
                feverDatabase = yes;
            if(stomachPain.isChecked())
                stomachPainDatabse = yes;
            if(bodyPain.isChecked())
                bodyPainDatabse = yes;
            if(headPain.isChecked())
                headPainDatabse = yes;

            PersonalRecordDatabase db = new PersonalRecordDatabase(this);
            DiceaseModel diceaseModel = new DiceaseModel(currrentDate,currrentTime,feverDatabase,stomachPainDatabse,bodyPainDatabse,headPainDatabse);
            db.addDataDisease(diceaseModel);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please put all information", Toast.LENGTH_SHORT).show();
            System.out.println("Error :"+e);
        }


    }
}