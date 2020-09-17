package com.example.medabinfinal.updateRecord.hospitalRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.DoctorRecordDatabase;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.updateRecordDoctorModel;
import com.example.medabinfinal.updateRecord.doctorRecord.doctor_record_add;
import com.example.medabinfinal.updateRecord.hospitalRecord.Database.HospitalRecordDatabase;
import com.example.medabinfinal.updateRecord.hospitalRecord.Database.HospitalRecordModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class hospital_record_add extends AppCompatActivity {

    EditText hospitalName,admitReason,admitUnder,aboutCabinWard,totalCost,admitDate,releaseDate,comment;
    Button submit;
    int admitYear,admitMonth,admitDay,releaseYear,releaseMonth,releaseDay;
    String admitDateDatabase,releaseDateDatabase;

    HospitalRecordDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_record_add);

        hospitalName = findViewById(R.id.hospital_record_hospital_name);
        admitReason = findViewById(R.id.hospital_record_admit_reason);
        admitUnder = findViewById(R.id.hospital_record_admit_under);
        aboutCabinWard = findViewById(R.id.hospital_record_about_cabin_ward);
        totalCost = findViewById(R.id.hospital_record_total_cost);
        admitDate = findViewById(R.id.hospital_record_admit_date);
        releaseDate = findViewById(R.id.hospital_record_release_date);
        comment = findViewById(R.id.hospital_record_comments);

        submit = findViewById(R.id.hospital_record_submit_button);

        db = new HospitalRecordDatabase(this);

        //admit date picking
        admitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                admitYear = calendar.get(calendar.YEAR);
                admitMonth = calendar.get(calendar.MONTH);
                admitDay = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(hospital_record_add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        admitYear = year;
                        admitMonth = month;
                        admitDay = dayOfMonth;


                        calendar.set(calendar.YEAR, admitYear);
                        calendar.set(calendar.MONTH, admitMonth);
                        calendar.set(calendar.DAY_OF_MONTH, admitDay);


                        admitDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        admitDateDatabase = SimpleDateFormat.getDateInstance().format(calendar.getTime());

                    }
                }, admitYear, admitMonth, admitDay);
                datePickerDialog.show();

            }
        });

        //release date picking
        releaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                releaseYear = calendar.get(calendar.YEAR);
                releaseMonth = calendar.get(calendar.MONTH);
                releaseDay = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(hospital_record_add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        releaseYear = year;
                        releaseMonth = month;
                        releaseDay = dayOfMonth;


                        calendar.set(calendar.YEAR, releaseYear);
                        calendar.set(calendar.MONTH, releaseMonth);
                        calendar.set(calendar.DAY_OF_MONTH, releaseDay);


                        releaseDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        releaseDateDatabase = SimpleDateFormat.getDateInstance().format(calendar.getTime());

                    }
                }, releaseYear, releaseMonth, releaseDay);
                datePickerDialog.show();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeData();
            }
        });




    }

    private void storeData(){
        String nameHospital = hospitalName.getText().toString();
        String reason = admitReason.getText().toString();
        String underAdmit = admitUnder.getText().toString();
        String cabinWard = aboutCabinWard.getText().toString();

        String comments = comment.getText().toString();
        String dateAdmit = admitDateDatabase;
        String dateRelease = releaseDateDatabase;

        if(nameHospital==null||reason==null||underAdmit==null ||cabinWard==null ||totalCost.getText().toString()==null||dateAdmit==null||dateRelease == null){
            Toast.makeText(hospital_record_add.this, "Please give all the information", Toast.LENGTH_SHORT).show();
        }
        else {
            //it is initiaize here to check empty value
            Integer cost = Integer.parseInt(totalCost.getText().toString());

            //adding data by model
            HospitalRecordModel hospitalRecordModel = new HospitalRecordModel(nameHospital,reason,underAdmit,cabinWard,cost,dateAdmit,dateRelease,comments);
            HospitalRecordDatabase db = new HospitalRecordDatabase(hospital_record_add.this);

            db.addData(hospitalRecordModel);
        }

    }
}