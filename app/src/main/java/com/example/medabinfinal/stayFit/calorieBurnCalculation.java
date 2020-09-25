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

    EditText meat,rice,fish,vegetables,oil,lentil,bread,fruits,egg,juice,cheese,cake,milk,noodles;
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
        cake = findViewById(R.id.edit_text_cake_consume_gram);
        milk = findViewById(R.id.edit_text_milk_consume_gram);
        cheese = findViewById(R.id.edit_text_cheese_consume_gram);
        noodles = findViewById(R.id.edit_text_noodles_consume_gram);
        egg = findViewById(R.id.edit_text_egg_consume_gram);
        juice = findViewById(R.id.edit_text_juice_consume_gram);

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
            int cakeConsume = Integer.parseInt(cake.getText().toString().trim());
            int milkConsume = Integer.parseInt(milk.getText().toString().trim());
            int cheeseConsume = Integer.parseInt(cheese.getText().toString().trim());
            int noodlesConsume = Integer.parseInt(noodles.getText().toString().trim());
            int eggConsume = Integer.parseInt(egg.getText().toString().trim());
            int juiceConsume = Integer.parseInt(juice.getText().toString().trim());


            if (meat.getText().toString().trim().matches("")) {
                meatConsume = 0;
            }
            if (rice.getText().toString().trim().matches("")) {
                riceConsume = 0;
            }
            if (fish.getText().toString().trim().matches("")) {
                fishConsume = 0;
            }
            if (vegetables.getText().toString().trim().matches("")) {
                vegConsume = 0;
            }
            if (oil.getText().toString().trim().matches("")) {
                oilConsume = 0;
            }
            if (lentil.getText().toString().trim().matches("")) {
                lentilConsume = 0;
            }
            if (bread.getText().toString().trim().matches("")) {
                breadConsume = 0;
            }
            if (fruits.getText().toString().trim().matches("")) {
                fruitConsume = 0;
            }
            if (cake.getText().toString().trim().matches("")) {
                cakeConsume = 0;
            }
            if (milk.getText().toString().trim().matches("")) {
                milkConsume = 0;
            }
            if (cheese.getText().toString().trim().matches("")) {
                cheeseConsume = 0;
            }
            if (noodles.getText().toString().trim().matches("")) {
                noodlesConsume = 0;
            }
            if (egg.getText().toString().trim().matches("")) {
                eggConsume = 0;
            }
            if (juice.getText().toString().trim().matches("")) {
                juiceConsume = 0;
            }


            float meatCalorie = 1.43f;
            float fishCalorie = 2.058f;
            float riceCalorie = 1.30f;
            float vegCalorie = .652f;
            float oilCalorie = 8.841f;
            float lentilCalorie = 1.164f;
            float breadCalorie = 2.646f;
            float fruitsCalorie = .284f;
            float cakeCalorie = 2.57f;
            float milkCalorie = .42f;
            float cheeseCalorie = 3.71f;
            float noodlesCalorie = 1.38f;
            float eggCalorie = 1.55f;
            float juiceCalorie = .54f;


            float totalMeat, totalRice, totalFish, totalLentil, totalOil, totalVegetables, totalBread, totalFruits, totalCake,totalMilk,totalCheese,totalNoodles,totalEgg,totalJuice;

            totalMeat = meatConsume * meatCalorie;
            totalRice = riceConsume * riceCalorie;
            totalFish = fishConsume * fishCalorie;
            totalVegetables = vegConsume * vegCalorie;
            totalOil = oilConsume * oilCalorie;
            totalLentil = lentilConsume * lentilCalorie;
            totalBread = breadConsume * breadCalorie;
            totalFruits = fruitConsume * fruitsCalorie;
            totalMilk = milkConsume * milkCalorie;
            totalCake= cakeConsume * cakeCalorie;
            totalCheese= cheeseConsume * cheeseCalorie;
            totalNoodles= noodlesConsume * noodlesCalorie;
            totalEgg= eggConsume * eggCalorie;
            totalJuice= juiceConsume * juiceCalorie;



            finalTotal = totalRice + totalMeat + totalFish + totalVegetables + totalOil + totalLentil + totalBread + totalFruits + totalCake + totalMilk + totalCheese + totalNoodles + totalEgg + totalJuice;

            totalCalorie.setText(String.valueOf(finalTotal));
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please put atleast 0 if empty"+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}