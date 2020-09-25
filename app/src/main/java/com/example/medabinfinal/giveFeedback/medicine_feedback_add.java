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
import com.example.medabinfinal.giveFeedback.Database.RatingMedicineModel;

public class medicine_feedback_add extends AppCompatActivity {

    EditText medicineName;
    RatingBar price,packaging,effectiveness,sideEffects;
    float priceDatabase,packagingDatabase,effectivenessDatabase,sideEffectsDatabase,total;
    Button submit;
    String nameDatabase;

    giveFeedbackDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicine_feedback_add);

        medicineName = findViewById(R.id.feedback_medicine_name);
        price = findViewById(R.id.medicine_feedback_pricing);
        packaging = findViewById(R.id.feedback_medicine_packaging);
        effectiveness = findViewById(R.id.medicine_feedback_effectiveness);
        sideEffects = findViewById(R.id.medicine_feedback_sideEffects);
        submit = findViewById(R.id.medicine_feedback_submit);

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

            nameDatabase = medicineName.getText().toString();

            priceDatabase = price.getRating();
            packagingDatabase = packaging.getRating();
            effectivenessDatabase = effectiveness.getRating();
            sideEffectsDatabase = sideEffects.getRating();
            total = priceDatabase + packagingDatabase + effectivenessDatabase + sideEffectsDatabase;

            giveFeedbackDatabase db = new giveFeedbackDatabase(this);

            RatingMedicineModel medicineModel = new RatingMedicineModel(nameDatabase,priceDatabase,packagingDatabase,effectivenessDatabase,sideEffectsDatabase,total);

            db.addDataMedicine(medicineModel);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Something error happen please recheck all the value is properly given", Toast.LENGTH_SHORT).show();
        }



    }
}