package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.medicinePlanner.Database.MediplannerDatabaseHelper;
import com.example.medabinfinal.medicinePlanner.Database.mediplannerMedicineModel;
import com.example.medabinfinal.medicinePlanner.Database.plannerModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class plannerActivity extends AppCompatActivity {

    EditText days;
    TextView money,consume_quantity,types;
    Button plan;
    ListView lv;
    MediplannerDatabaseHelper databaseHelper;
    ArrayAdapter planMedicineAdapter;
    public static double total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        days = findViewById(R.id.editTextDays);
        money = findViewById(R.id.textViewMoney);
        consume_quantity = findViewById(R.id.textViewquantity);
        types = findViewById(R.id.textViewtypes);
        plan = findViewById(R.id.buttonPlan);
        lv = findViewById(R.id.listViewPlan);
        databaseHelper = new MediplannerDatabaseHelper(plannerActivity.this);

        plan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Integer day = Integer.parseInt(days.getText().toString());
                ShowDetails(databaseHelper);
                total = databaseHelper.totalMoney(day);
                money.setText(String.format("%.2f",total));
                //Toast.makeText(plannerActivity.this, "Total money needed"+String.valueOf(total), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void ShowDetails(MediplannerDatabaseHelper dataBasehelper2){
        Integer day = Integer.parseInt(days.getText().toString());
        List<plannerModel> List = new ArrayList<>();
        List = dataBasehelper2.planMedicine(day);
        double quantity= (double) 0.0;
        int count=0;
        for(plannerModel medi: List){
            quantity = quantity + medi.getQuantity();
            count++;
        }
        Toast.makeText(plannerActivity.this,  "Plan of"+day+"is here", Toast.LENGTH_SHORT).show();
        //money.setText(String.valueOf(totalCost));
        types.setText(String.valueOf(count));
        consume_quantity.setText(String.format("%.0f",quantity));


        planMedicineAdapter = new ArrayAdapter<plannerModel>(plannerActivity.this, android.R.layout.simple_list_item_1, dataBasehelper2.planMedicine(day));
        lv.setAdapter(planMedicineAdapter);


    }

    public void onBackPressed(){
        Intent intent = new Intent(plannerActivity.this,medicinePlannerMenu.class);
        startActivity(intent);
        finish();
    }
}