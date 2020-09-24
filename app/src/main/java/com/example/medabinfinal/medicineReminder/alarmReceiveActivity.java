package com.example.medabinfinal.medicineReminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.medicineReminder.Database.AlarmDatabase;
import com.example.medabinfinal.medicineReminder.Database.AlarmModel;

public class alarmReceiveActivity extends AppCompatActivity {

    String medicineName;
    String nameDatabase,time1Database,time2Database,time3Database,isActive;
    int countDatbase,remainDatbase,time1HourDatabase,time2HourDatabase,time3HourDatabase,time1MinuteDatabase,time2MinuteDatabase,time3MinuteDtabase;
    String medicineIdText;

    int id;
    TextView medicineNameTaken,noticeText;
    Button yes,later,no;
    Ringtone ringtone;
    AlarmDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_stop_activty);
        db = new AlarmDatabase(this);




        medicineName = getIntent().getStringExtra("medicineName");
        id = getIntent().getIntExtra("Id",0);
        //medicineIdText = getIntent().getStringExtra("Id");
        //medicineId = db.getAlarmId(medicineName);
        System.out.println("id medicine"+db.getAlarmId(medicineName));
        nameDatabase = db.getOneMedicneName(id);
        countDatbase = db.getOneMedicineCount(id);
        remainDatbase = db.getOneMedicineRemain(id);
        time1Database = db.getOneMedicneTime1(id);
        time2Database = db.getOneMedicneTime2(id);
        time3Database = db.getOneMedicneTime3(id);

        isActive = db.getOneAlarmIsOne(id);

        time1HourDatabase = db.getOneMedicineTime1Hour(id);
        time1MinuteDatabase = db.getOneMedicineTime1Minute(id);
        time2HourDatabase = db.getOneMedicineTime2Hour(id);
        time2MinuteDatabase = db.getOneMedicineTime2Minute(id);
        time3HourDatabase = db.getOneMedicineTime3Hour(id);
        time3MinuteDtabase = db.getOneMedicineTime3Minute(id);

        System.out.println("id is : -"+id+" name "+nameDatabase);




        medicineNameTaken = findViewById(R.id.medicineNametaken);
        yes = findViewById(R.id.buttonYes);
        later = findViewById(R.id.buttonLater);
        no = findViewById(R.id.buttonNo);

        ringtone = AlarmService.ringtone;

        noticeText = findViewById(R.id.noticeText);

        medicineNameTaken.setText(medicineName);


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone.stop();
                noticeText.setText("Thanks for taking medicine "+medicineName);
                noticeText.setTextColor(Color.GREEN);
                editData();
            }
        });

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone.stop();
                noticeText.setText("Please take "+medicineName+" on your own");
                noticeText.setTextColor(Color.BLUE);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeText.setText("Warning "+medicineName+" is not taken");
                noticeText.setTextColor(Color.RED);
            }
        });


        Toast.makeText(this, "Medicine name: "+medicineName+"Medicine id : "+id, Toast.LENGTH_SHORT).show();
    }



    private void editData()
    {
        int countUpdate = countDatbase ;
        int remainUpdate = countUpdate-1;

        AlarmModel alarmModel = new AlarmModel(nameDatabase,countUpdate,remainUpdate,time1Database,time2Database,time3Database,isActive,time1HourDatabase,time1MinuteDatabase,time2HourDatabase,time2MinuteDatabase,time3HourDatabase,time3MinuteDtabase);
        AlarmDatabase db = new AlarmDatabase(this);

        db.editAlarm(alarmModel,id);

    }
}