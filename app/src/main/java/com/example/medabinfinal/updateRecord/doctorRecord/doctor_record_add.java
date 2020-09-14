package com.example.medabinfinal.updateRecord.doctorRecord;

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
import com.example.medabinfinal.updateRecord.medicineRecord.medicineRecordAddMedicine;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class doctor_record_add extends AppCompatActivity {


    EditText doctorname,doctorSpeciality,chamberLocation,patientSymtomps,consultationFee,commentsDoctor;
    EditText doctorVisitDate;
    Button submit;
    int doctorVisitYear,doctorVisitMonth,doctorVisitDay;
    String doctorVisitDateDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_record_add);

        doctorname = findViewById(R.id.doctor_record_doctor_name);
        doctorSpeciality = findViewById(R.id.doctor_record_doctor_speciality);
        chamberLocation = findViewById(R.id.doctor_record_doctor_chamber_location);
        patientSymtomps = findViewById(R.id.doctor_record_patientnt_symtomps);
        consultationFee = findViewById(R.id.doctor_record_doctor_consultation_fee);
        commentsDoctor = findViewById(R.id.doctor_record_comments);
        doctorVisitDate = findViewById(R.id.doctor_record_visit_date);
        submit = findViewById(R.id.doctor_record_submit_button);

        //startdate picking
        doctorVisitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                doctorVisitYear = calendar.get(calendar.YEAR);
                doctorVisitMonth = calendar.get(calendar.MONTH);
                doctorVisitDay = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(doctor_record_add.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        doctorVisitYear = year;
                        doctorVisitMonth = month;
                        doctorVisitDay = dayOfMonth;


                        calendar.set(calendar.YEAR, doctorVisitYear);
                        calendar.set(calendar.MONTH, doctorVisitMonth);
                        calendar.set(calendar.DAY_OF_MONTH, doctorVisitDay);


                        doctorVisitDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        doctorVisitDateDatabase = SimpleDateFormat.getDateInstance().format(calendar.getTime());

                    }
                }, doctorVisitYear, doctorVisitMonth, doctorVisitDay);
                datePickerDialog.show();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = doctorname.getText().toString();
                String speciality = doctorSpeciality.getText().toString();
                String chamber = chamberLocation.getText().toString();
                String symptoms = patientSymtomps.getText().toString();

                String comments = commentsDoctor.getText().toString();
                String date = doctorVisitDateDatabase;

                if(name==null||speciality==null||chamber==null ||symptoms==null ||consultationFee.getText().toString()==null||date==null){
                    Toast.makeText(doctor_record_add.this, "Please give all the information", Toast.LENGTH_SHORT).show();
                }
                else {
                    //it is initiaize here to check empty value
                    Integer fees = Integer.parseInt(consultationFee.getText().toString());

                    //adding data by model
                    updateRecordDoctorModel doctorModel = new updateRecordDoctorModel(name,speciality,chamber,symptoms,fees,comments,date);
                    DoctorRecordDatabase db = new DoctorRecordDatabase(doctor_record_add.this);

                    db.addData(doctorModel);
                }

            }
        });
    }
}