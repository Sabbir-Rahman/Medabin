package com.example.medabinfinal.updateRecord.personalRecord.DailyRoutine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.DailyTimeModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.PersonalRecordDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class personal_record_daily_routine_add extends AppCompatActivity {
    EditText sleep,read,working,exercise,others;
    Button submit;
    PersonalRecordDatabase db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_daily_routine_add);

        sleep = findViewById(R.id.personal_record_daily_routine_sleep_time);
        read = findViewById(R.id.personal_record_daily_routine_reading_time);
        working = findViewById(R.id.personal_record_daily_routine_working_time);
        exercise = findViewById(R.id.personal_record_daily_routine_exercise_time);
        others = findViewById(R.id.personal_record_daily_routine_others_time);

        submit = findViewById(R.id.personal_record_daily_routine_submit_button);

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
            int sleepDatabase,readDatabase,workingDatabase,exercisedatabse,otherDatabase,unknownDatabase,sum;

            sleepDatabase = Integer.parseInt(sleep.getText().toString());
            readDatabase = Integer.parseInt(read.getText().toString());
            workingDatabase = Integer.parseInt(working.getText().toString());
            exercisedatabse = Integer.parseInt(exercise.getText().toString());
            otherDatabase = Integer.parseInt(others.getText().toString());

            sum = sleepDatabase+readDatabase+workingDatabase+exercisedatabse+otherDatabase;
            if(sum>24)
            {
                Toast.makeText(this, "Sum of all value is greater than 24, Please check again", Toast.LENGTH_SHORT).show();
            }
            else
            {
                unknownDatabase = 24-sum;
                PersonalRecordDatabase db = new PersonalRecordDatabase(this);
                DailyTimeModel dailyTimeModel = new DailyTimeModel(currrentDate,sleepDatabase,readDatabase,workingDatabase,exercisedatabse,otherDatabase,unknownDatabase);
                db.addDataDailyTime(dailyTimeModel);
            }

        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please insert all values", Toast.LENGTH_SHORT).show();
            System.out.println("Error: "+e);
        }

    }


}