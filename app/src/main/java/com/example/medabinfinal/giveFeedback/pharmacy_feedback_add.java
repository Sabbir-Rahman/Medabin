package com.example.medabinfinal.giveFeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.giveFeedback.Database.RatingMedicineModel;
import com.example.medabinfinal.giveFeedback.Database.RatingPharmacyModel;
import com.example.medabinfinal.giveFeedback.Database.giveFeedbackDatabase;

public class pharmacy_feedback_add extends AppCompatActivity {

    EditText pharmacyName;
    RatingBar service,pricing,wellOrganised,medicineAvailability;
    float serviceDatabase,pricingDatabase,wellOrganisedDatabase,medicineAvailabilityDatabase,total;
    Button submit;
    String nameDatabase;

    giveFeedbackDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_feedback_add);

        pharmacyName = findViewById(R.id.feedback_pharmacy_name);
        service  = findViewById(R.id.pharmacy_feedback_service);
        pricing = findViewById(R.id.feedback_pharmacy_pricing);
        wellOrganised = findViewById(R.id.pharmacy_feedback_organised);
        medicineAvailability = findViewById(R.id.pharmacy_feedback_medicine_availability);

        submit = findViewById(R.id.pharmacy_feedback_submit);

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

            nameDatabase = pharmacyName.getText().toString();

            serviceDatabase = service.getRating();
            pricingDatabase = pricing.getRating();
            wellOrganisedDatabase = wellOrganised.getRating();
            medicineAvailabilityDatabase = medicineAvailability.getRating();
            total = serviceDatabase+pricingDatabase+wellOrganisedDatabase+medicineAvailabilityDatabase;

            giveFeedbackDatabase db = new giveFeedbackDatabase(this);

            RatingPharmacyModel ratingPharmacyModel = new RatingPharmacyModel(nameDatabase,serviceDatabase,pricingDatabase,wellOrganisedDatabase,medicineAvailabilityDatabase,total);
            db.addDataPharmacy(ratingPharmacyModel);

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Something error happen please recheck all the value is properly given", Toast.LENGTH_SHORT).show();
        }

    }
}