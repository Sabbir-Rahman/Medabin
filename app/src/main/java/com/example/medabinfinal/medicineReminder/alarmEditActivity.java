package com.example.medabinfinal.medicineReminder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.dashboard.UserDashboard;
import com.example.medabinfinal.medicineReminder.Database.AlarmDatabase;
import com.example.medabinfinal.medicineReminder.Database.AlarmModel;

import java.util.Calendar;

public class alarmEditActivity extends AppCompatActivity {

    private EditText name,count,time1,time2,time3;
    private int time1Hour,time1minute,time2Hour,time2minute,time3Hour,time3minute;
    private String time1Databse,time2Databse,time3Databse,amPm1,amPm2,amPm3;
    private int time1HourDatabse,time1MinuteDatabse,time2HourDatabse,time2MinuteDatabse,time3HourDatabse,time3MinuteDatabse;
    private Button update;
    private Switch isTaken;
    int id;
    Long idLong;
    int remain;
    AlarmService alarmService;

    AlarmDatabase db;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_edit);

        idLong = getIntent().getLongExtra("IDEDIT",0);;


        id = Integer.parseInt(idLong.toString());

        System.out.println("id checked"+id);
//        Toast.makeText(this, Math.toIntExact(idLong), Toast.LENGTH_SHORT).show();

        name = findViewById(R.id.reminder_edit_medicine_name);
        count = findViewById(R.id.reminder_edit_MedicineCount);
        time1 = findViewById(R.id.remindTime1Edit);
        time2 = findViewById(R.id.remindTime2Edit);
        time3 = findViewById(R.id.remindTime3Edit);
        isTaken = findViewById(R.id.switchMedicineReminderEdit);
        update = findViewById(R.id.submitMedicineReminderRecordEdit);
        db = new AlarmDatabase(this);

        name.setText(db.getOneMedicneName(id));
        count.setText(String.valueOf(db.getOneMedicineCount(id)));
        time1.setText(db.getOneMedicneTime1(id));
        time2.setText(db.getOneMedicneTime2(id));
        time3.setText(db.getOneMedicneTime3(id));

        time1Databse = db.getOneMedicneTime1(id);
        time2Databse = db.getOneMedicneTime2(id);
        time3Databse = db.getOneMedicneTime3(id);
        time1HourDatabse = db.getOneMedicineTime1Hour(id);
        time1MinuteDatabse = db.getOneMedicineTime1Minute(id);
        time2HourDatabse = db.getOneMedicineTime2Hour(id);
        time2MinuteDatabse = db.getOneMedicineTime2Minute(id);
        time3HourDatabse = db.getOneMedicineTime3Hour(id);
        time3MinuteDatabse = db.getOneMedicineTime3Minute(id);


        if(db.getOneAlarmIsOne(id).equals("Yes"))
        {
            isTaken.setChecked(true);
        }
        else {
            isTaken.setChecked(false);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });




        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                time1Hour = calendar.get(calendar.HOUR_OF_DAY);
                time1minute = calendar.get(calendar.MINUTE);




                TimePickerDialog timePickerDialog = new TimePickerDialog(alarmEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time1Hour = hourOfDay;
                        time1minute = minute;


                        calendar.set(calendar.HOUR_OF_DAY,time1Hour);
                        calendar.set(calendar.MINUTE,time1minute);

                        if(time1Hour>=12)
                        {
                            amPm1 = "PM";
                        }
                        else
                        {
                            amPm1 = "AM";
                        }

                        String time ;

                        if(time1Hour>12)
                        {
                            time = String.format("%02d:%02d",time1Hour-12,time1minute) + " "+ amPm1;
                        }
                        else {

                            time = String.format("%02d:%02d",time1Hour,time1minute) + " "+ amPm1;
                        }


                        time1.setText(time);
                        Toast.makeText(alarmEditActivity.this, "time :"+time1Hour+":"+time1minute, Toast.LENGTH_SHORT).show();

                        time1Databse=time;
                        time1HourDatabse = time1Hour;
                        time1MinuteDatabse = time1minute;
                    }
                },time1Hour,time1minute,false);
                timePickerDialog.show();


            }
        });

        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                time2Hour = calendar.get(calendar.HOUR_OF_DAY);
                time2minute = calendar.get(calendar.MINUTE);




                TimePickerDialog timePickerDialog = new TimePickerDialog(alarmEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time2Hour = hourOfDay;
                        time2minute = minute;


                        calendar.set(calendar.HOUR_OF_DAY,time2Hour);
                        calendar.set(calendar.MINUTE,time2minute);

                        if(time2Hour>=12)
                        {
                            amPm2 = "PM";
                        }
                        else
                        {
                            amPm2 = "AM";
                        }

                        String time ;

                        if(time2Hour>12)
                        {
                            time = String.format("%02d:%02d",time2Hour-12,time2minute) + " "+ amPm2;
                        }
                        else {

                            time = String.format("%02d:%02d",time2Hour,time2minute) + " "+ amPm2;
                        }


                        time2.setText(time);
                        Toast.makeText(alarmEditActivity.this, "time :"+time2Hour+":"+time2minute, Toast.LENGTH_SHORT).show();


                        time2Databse=time;
                        time2HourDatabse = time2Hour;
                        time2MinuteDatabse = time2minute;
                    }
                },time2Hour,time2minute,false);
                timePickerDialog.show();


            }
        });

        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                time3Hour = calendar.get(calendar.HOUR_OF_DAY);
                time3minute = calendar.get(calendar.MINUTE);




                TimePickerDialog timePickerDialog = new TimePickerDialog(alarmEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time3Hour = hourOfDay;
                        time3minute = minute;


                        calendar.set(calendar.HOUR_OF_DAY,time3Hour);
                        calendar.set(calendar.MINUTE,time3minute);

                        if(time3Hour>=12)
                        {
                            amPm3 = "PM";
                        }
                        else
                        {
                            amPm3 = "AM";
                        }

                        String time ;

                        if(time3Hour>12)
                        {
                            time = String.format("%02d:%02d",time3Hour-12,time3minute) + " "+ amPm3;
                        }
                        else {

                            time = String.format("%02d:%02d",time3Hour,time3minute) + " "+ amPm3;
                        }


                        time3.setText(time);
                        Toast.makeText(alarmEditActivity.this, "time :"+time3Hour+":"+time3minute, Toast.LENGTH_SHORT).show();


                        time3Databse=time;
                        time3HourDatabse = time3Hour;
                        time3MinuteDatabse = time3minute;
                    }
                },time3Hour,time3minute,false);
                timePickerDialog.show();


            }
        });




    }


    public void updateData()
    {
        try
        {
            String nameMedicine = name.getText().toString();
            String isAlarm;
            if(isTaken.isChecked())
            {
                isAlarm = "Yes";
            }
            else
            {
                isAlarm = "No";
            }
            Integer countMedicineDatabase = Integer.parseInt(count.getText().toString());
            Integer remain = db.getOneMedicineRemain(id);


            if(time1.getText().toString().matches(""))
            {
                time1HourDatabse =99;
            }
            if(time2.getText().toString().matches(""))
            {
                time2HourDatabse =99;
            }
            if(time3.getText().toString().matches(""))
            {
                time3HourDatabse =99;
            }


            AlarmModel alarmModel = new AlarmModel(nameMedicine,countMedicineDatabase,remain,time1Databse,time2Databse,time3Databse,isAlarm,time1HourDatabse,time1MinuteDatabse,time2HourDatabse,time2MinuteDatabse,time3HourDatabse,time3MinuteDatabse);
            AlarmDatabase db = new AlarmDatabase(this);


            db.editAlarm(alarmModel,id);






        }
        catch (Exception e)
        {
            Toast.makeText(this, "Please provide all value", Toast.LENGTH_SHORT).show();
        }



    }

    private  void sendUserToDashboard()
    {
        Intent intent = new Intent(this, UserDashboard.class);
        startActivity(intent);
        finish();
    }



}