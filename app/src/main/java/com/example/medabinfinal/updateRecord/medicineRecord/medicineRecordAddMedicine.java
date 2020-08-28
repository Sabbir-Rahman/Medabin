package com.example.medabinfinal.updateRecord.medicineRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.medicineRecord.Database.updateRecordMedicineDatabase;
import com.example.medabinfinal.updateRecord.medicineRecord.Database.updateRecordMedicineModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class medicineRecordAddMedicine extends AppCompatActivity {

    updateRecordMedicineDatabase db;

    EditText startDate,endDate,medicineName,medicineConsume,medicineSchedule,reasonToTake;
    Button submit;

    int startyear,endYear,startMonth,endMonth,startDay,endDay;
    RadioGroup radiogroupType,radioGroupPrescribe;
    RadioButton radioButtonType,radioButtonPrescribe;
    CheckBox morning,noon,night,others;
    String dateStartDatabase,dateEndDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_record_add_medicine_record);


        db = new updateRecordMedicineDatabase(this);

        startDate = findViewById(R.id.add_medicine_record_start_date);
        endDate = findViewById(R.id.add_medicine_record_end_date);
        submit = findViewById(R.id.medicine_record_submit_button);

        radiogroupType = findViewById(R.id.medicine_record_medicine_type_choice);
        radioGroupPrescribe = findViewById(R.id.medicine_record_medicine_type_prescribed_by);

        morning = findViewById(R.id.time_break_down_morining);
        noon = findViewById(R.id.time_break_down_noon);
        night = findViewById(R.id.time_break_down_night);
        others = findViewById(R.id.time_break_down_others);

        medicineName = findViewById(R.id.add_medicine_record_medicine_name);
        medicineConsume = findViewById(R.id.edit_text_current_dose_times);
        medicineSchedule = findViewById(R.id.edit_text_current_dose_days);
        reasonToTake = findViewById(R.id.add_medicine_record_medicine_reason);

        



        //startdate picking
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                startyear = calendar.get(calendar.YEAR);
                startMonth = calendar.get(calendar.MONTH);
                startDay = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(medicineRecordAddMedicine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        startyear = year;
                        startMonth = month;
                        startDay = dayOfMonth;


                        calendar.set(calendar.YEAR, startyear);
                        calendar.set(calendar.MONTH, startMonth);
                        calendar.set(calendar.DAY_OF_MONTH, startDay);


                        startDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        dateStartDatabase = SimpleDateFormat.getDateInstance().format(calendar.getTime());

                    }
                }, startyear, startMonth, startDay);
                datePickerDialog.show();

            }
        });



//        //end date time picking
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                endYear = calendar.get(calendar.YEAR);
                endMonth = calendar.get(calendar.MONTH);
                endDay = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(medicineRecordAddMedicine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        endYear = year;
                        endMonth = month;
                        endDay = dayOfMonth;


                        calendar.set(calendar.YEAR, endYear);
                        calendar.set(calendar.MONTH, endMonth);
                        calendar.set(calendar.DAY_OF_MONTH, endDay);


                        endDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        dateEndDatabase = SimpleDateFormat.getDateInstance().format(calendar.getTime());

                    }
                }, endYear, endMonth, endDay);
                datePickerDialog.show();



            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String morningCheck = checkMorning();
                String nightCheck = checkNight();
                String noonCheck = checkNoon();
                String othersCheck  = checkOthers();



                    String radioType = checkRadioButtonType();
                    String radioPrescribe = checkRadioButtonPrescribe();

                    if(radioType == null || radioPrescribe==null)
                        Toast.makeText(medicineRecordAddMedicine.this, "You put nothing in type or prescribe", Toast.LENGTH_SHORT).show();

                //Toast.makeText(medicineRecordAddMedicine.this, "Morning "+morningCheck+" Night "+nightCheck+" Noon "+noonCheck+" Others "+othersCheck, Toast.LENGTH_SHORT).show();
                //Toast.makeText(medicineRecordAddMedicine.this, "Start Date "+dateStartDatabase+" End Date "+dateEndDatabase, Toast.LENGTH_SHORT).show();
                Toast.makeText(medicineRecordAddMedicine.this, "Type "+radioType+"Prescribe "+radioPrescribe, Toast.LENGTH_SHORT).show();


                updateRecordMedicineModel medicineModel = new updateRecordMedicineModel(medicineName.getText().toString(),radioType,
                        Float.parseFloat(medicineConsume.getText().toString()),Integer.parseInt(medicineSchedule.getText().toString()),
                        morningCheck,noonCheck,nightCheck,othersCheck,reasonToTake.getText().toString(),radioPrescribe,dateStartDatabase,dateEndDatabase);

                updateRecordMedicineDatabase db = new updateRecordMedicineDatabase(medicineRecordAddMedicine.this);
                db.addData(medicineModel);


            }
        });




    }



    private String checkRadioButtonType(){
        int radioIdType = radiogroupType.getCheckedRadioButtonId();

        radioButtonType = findViewById(radioIdType);
        return (String) radioButtonType.getText();
    }

    private String checkRadioButtonPrescribe(){
        int radioIdPrescribe = radioGroupPrescribe.getCheckedRadioButtonId();

        radioButtonPrescribe = findViewById(radioIdPrescribe);
        return (String) radioButtonPrescribe.getText();


    }


    private String checkMorning()
    {
        String yes = "Yes";
        String no = "No";

        if(morning.isChecked())
            return yes;
        else
            return no;
    }

    private String checkNoon()
    {
        String yes = "Yes";
        String no = "No";

        if(noon.isChecked())
            return yes ;
        else
            return no ;
    }

    private String checkNight()
    {
        String yes = "Yes";
        String no = "No";
        if(night.isChecked())
            return yes;
        else
            return no;
    }

    private String checkOthers()
    {
        String yes = "Yes";
        String no = "No";
        if(others.isChecked())
            return yes;
        else
            return no;
    }








}