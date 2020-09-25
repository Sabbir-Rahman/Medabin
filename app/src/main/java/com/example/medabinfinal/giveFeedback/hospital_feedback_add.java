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
import com.example.medabinfinal.giveFeedback.Database.RatingHospitalModel;

public class hospital_feedback_add extends AppCompatActivity {

    EditText hospitalName;
    RatingBar service,expense,infastructure,testingQuality;
    float servicedatabase,expenseDatabase,infastructureDatabase,testingQualityDatbase,total;
    Button submit;
    String nameDatabase;

    giveFeedbackDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_feedback_add);

        db = new giveFeedbackDatabase(this);

        hospitalName = findViewById(R.id.feedback_hospital_name);
        service = findViewById(R.id.hospital_feedback_service);
        expense = findViewById(R.id.feedback_hospital_expense);
        infastructure = findViewById(R.id.feedback_hospital_infrastructure);
        testingQuality = findViewById(R.id.feedback_hospital_testing_quality);
        submit = findViewById(R.id.hospital_feedback_submit);

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

            nameDatabase = hospitalName.getText().toString();

            servicedatabase = service.getRating();
            expenseDatabase = expense.getRating();
            infastructureDatabase = infastructure.getRating();
            testingQualityDatbase = testingQuality.getRating();
            total = servicedatabase + expenseDatabase + infastructureDatabase + testingQualityDatbase;

            giveFeedbackDatabase db = new giveFeedbackDatabase(this);

            RatingHospitalModel ratingHospitalModel = new RatingHospitalModel(nameDatabase,servicedatabase,expenseDatabase,infastructureDatabase,testingQualityDatbase,total);

            db.addDataHospital(ratingHospitalModel);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Something error happen please recheck all the value is properly given", Toast.LENGTH_SHORT).show();
        }


    }



}