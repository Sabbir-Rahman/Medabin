package com.example.medabinfinal.updateRecord.personalRecord.Food;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.mediNote.Database.NoteDatabase;
import com.example.medabinfinal.updateRecord.personalRecord.Database.FoodModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.GlucoseModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_record_food_add extends AppCompatActivity {

    EditText carbohydret,protein,fat,vitamin,mineral,water;
    Button submit;
    PersonalRecordDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_food_add);

        db = new PersonalRecordDatabase(this);
        carbohydret = findViewById(R.id.personal_record_food_carbohydret_percentage);
        protein = findViewById(R.id.personal_record_food_protein_percentage);
        fat = findViewById(R.id.personal_record_food_fat_percentage);
        vitamin = findViewById(R.id.personal_record_food_vitamin_percentage);
        mineral = findViewById(R.id.personal_record_food_mineral_percentage);
        water = findViewById(R.id.personal_record_food_water_percentage);


        submit = findViewById(R.id.personal_record_food_submit_button);

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

           int carbohydrateDatabase = Integer.parseInt(carbohydret.getText().toString());
           int proteinDatbase = Integer.parseInt(protein.getText().toString());
           int fatDatabase = Integer.parseInt(fat.getText().toString());
           int waterDatabase = Integer.parseInt(water.getText().toString());
           int vitaminDatabse = Integer.parseInt(vitamin.getText().toString());
           int mineralDatabase = Integer.parseInt(mineral.getText().toString());

           int sum = carbohydrateDatabase+proteinDatbase+fatDatabase+waterDatabase+vitaminDatabse+mineralDatabase;
           int unknownDatabse = 100-sum;//rest of the food is record as unknown

           if(sum>100)
           {
               Toast.makeText(this, "Sum of all food cross 100", Toast.LENGTH_SHORT).show();
           }
           else {

               PersonalRecordDatabase db = new PersonalRecordDatabase(this);
               FoodModel foodModel = new FoodModel(currrentDate,carbohydrateDatabase,proteinDatbase,fatDatabase,vitaminDatabse,mineralDatabase,waterDatabase,unknownDatabse);
               db.addDataFood(foodModel);


           }


        }
        catch (Exception e)
        {
            Toast.makeText(this, "You don't put all the value", Toast.LENGTH_SHORT).show();
        }

    }
}