package com.example.medabinfinal.stayFit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medabinfinal.R;

public class calorieBurnCalculation extends AppCompatActivity {

    EditText meat,rice,fish,vegetables,oil,lentil,bread,fruits;
    TextView totalCalorie;
    Float finalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_burn_calculation);

        meat = findViewById(R.id.edit_text_meat_consume_gram);
        rice = findViewById(R.id.edit_text_rice_consume_gram);
        fish = findViewById(R.id.edit_text_fish_consume_gram);
        vegetables = findViewById(R.id.edit_text_vegetable_consume_gram);
        oil = findViewById(R.id.edit_text_oil_consume_gram);
        lentil = findViewById(R.id.edit_text_lentil_consume_gram);
        bread = findViewById(R.id.edit_text_bread_consume_gram);
        fruits = findViewById(R.id.edit_text_fruit_consume_gram);

        totalCalorie = findViewById(R.id.calorieResult);



    }

    public void calculateTotalCalorie(View v)
    {
        try{

            int meatConsume = Integer.parseInt(meat.getText().toString());
            int riceConsume = Integer.parseInt(rice.getText().toString().trim());
            int fishConsume = Integer.parseInt(fish.getText().toString().trim());
            int vegConsume = Integer.parseInt(vegetables.getText().toString().trim());
            int oilConsume = Integer.parseInt(oil.getText().toString().trim());
            int lentilConsume = Integer.parseInt(lentil.getText().toString().trim());
            int breadConsume = Integer.parseInt(bread.getText().toString().trim());
            int fruitConsume = Integer.parseInt(fruits.getText().toString().trim());


            if (meat.getText().toString().trim() == null) {
                meatConsume = 0;
            }
            if (rice.getText().toString().trim() == null) {
                riceConsume = 0;
            }
            if (fish.getText().toString().trim() == null) {
                fishConsume = 0;
            }
            if (vegetables.getText().toString().trim() == null) {
                vegConsume = 0;
            }
            if (oil.getText().toString().trim() == null) {
                oilConsume = 0;
            }
            if (lentil.getText().toString().trim() == null) {
                lentilConsume = 0;
            }
            if (bread.getText().toString().trim() == null) {
                breadConsume = 0;
            }
            if (fruits.getText().toString().trim() == null) {
                fruitConsume = 0;
            }


            float meatCalorie = 1.43f;
            float fishCalorie = 2.058f;
            float riceCalorie = 1.30f;
            float vegCalorie = .652f;
            float oilCalorie = 8.841f;
            float lentilCalorie = 1.164f;
            float breadCalorie = 2.646f;
            float fruitsCalorie = .284f;


            float totalMeat, totalRice, totalFish, totalLentil, totalOil, totalVegetables, totalBread, totalFruits;

            totalMeat = meatConsume * meatCalorie;
            totalRice = riceConsume * riceCalorie;
            totalFish = fishConsume * fishCalorie;
            totalVegetables = vegConsume * vegCalorie;
            totalOil = oilConsume * oilCalorie;
            totalLentil = lentilConsume * lentilCalorie;
            totalBread = breadConsume + breadCalorie;
            totalFruits = fruitConsume * fruitsCalorie;

            finalTotal = totalRice + totalMeat + totalFish + totalVegetables + totalOil + totalLentil + totalBread + totalFruits;

            totalCalorie.setText(String.valueOf(finalTotal));
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}