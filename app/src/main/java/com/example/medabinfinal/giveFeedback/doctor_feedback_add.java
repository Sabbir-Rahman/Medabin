package com.example.medabinfinal.giveFeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.giveFeedback.Database.giveFeedbackDatabase;
import com.example.medabinfinal.giveFeedback.Database.RatingDoctorModel;

public class doctor_feedback_add extends AppCompatActivity {

    EditText doctorName;
    RatingBar behaviour,fee,prescription,diagnosis;
    float behaviourDatabase,feeDatabase,prescriptionDatabase,diagnosisDatabase,total;
    Button submit;
    String nameDatabase;

    giveFeedbackDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_feedback_add);

        db = new giveFeedbackDatabase(this);


        doctorName = findViewById(R.id.feedback_name_doctor);
        behaviour = findViewById(R.id.doctor_feedback_behaviour_rating);
        fee = findViewById(R.id.doctor_feedback_fee_rating);
        prescription = findViewById(R.id.doctor_feedback_prescription_rating);
        diagnosis = findViewById(R.id.doctor_feedback_diagnosis_rating);
        submit = findViewById(R.id.doctor_feedback_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

    }

    private void addData()
    {



        try {

            nameDatabase = doctorName.getText().toString();

            behaviourDatabase = behaviour.getRating();
            feeDatabase = fee.getRating();
            prescriptionDatabase = prescription.getRating();
            diagnosisDatabase = diagnosis.getRating();
            total = behaviourDatabase + feeDatabase + prescriptionDatabase + diagnosisDatabase;

            giveFeedbackDatabase db = new giveFeedbackDatabase(this);

            RatingDoctorModel ratingDoctorModel;

            ratingDoctorModel = new RatingDoctorModel(nameDatabase, behaviourDatabase, feeDatabase,
                    prescriptionDatabase, diagnosisDatabase, total);


            db.addDataDoctor(ratingDoctorModel);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Something error happen please recheck all the value is properly given", Toast.LENGTH_SHORT).show();
        }


    }
}