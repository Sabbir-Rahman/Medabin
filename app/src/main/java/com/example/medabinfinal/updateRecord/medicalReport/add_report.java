package com.example.medabinfinal.updateRecord.medicalReport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.DoctorRecordDatabase;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.updateRecordDoctorModel;
import com.example.medabinfinal.updateRecord.doctorRecord.doctor_record_add;
import com.example.medabinfinal.updateRecord.medicalReport.Database.MedicalReportDatbase;
import com.example.medabinfinal.updateRecord.medicalReport.Database.MedicalReportModel;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_report extends AppCompatActivity {

    private EditText reportName,prescribedPeople,diagnosticName,reportDetails,reportComments,reportDate,reportCost;
    private EditText testDate; 
    private Button submit;
    private int testYear,testMonth,testDay;
    String testReportDatabase;

    private ImageView imageReport;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    MedicalReportDatbase db;

    private static final int PICK_IMAGE_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_report);

        reportName = findViewById(R.id.medical_report_report_name);
        prescribedPeople = findViewById(R.id.medical_report_prescribed_people_name);
        diagnosticName = findViewById(R.id.medical_report_diagnostic_name);
        reportDetails = findViewById(R.id.medical_report_details);
        reportCost = findViewById(R.id.medical_report_cost);
        reportComments = findViewById(R.id.medical_report_comments);
        reportDate = findViewById(R.id.medical_report_test_date);
        imageReport = findViewById(R.id.choose_medical_report_image);
        submit = findViewById(R.id.medical_report_submit_button);

        MedicalReportDatbase db = new MedicalReportDatbase(this);

        //startdate picking
        reportDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();
                testYear = calendar.get(calendar.YEAR);
                testMonth = calendar.get(calendar.MONTH);
                testDay = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(add_report.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        testYear = year;
                        testMonth = month;
                        testDay = dayOfMonth;


                        calendar.set(calendar.YEAR, testYear);
                        calendar.set(calendar.MONTH, testMonth);
                        calendar.set(calendar.DAY_OF_MONTH, testDay);


                        reportDate.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                        testReportDatabase = SimpleDateFormat.getDateInstance().format(calendar.getTime());

                    }
                }, testYear, testMonth, testDay);
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

    public void chooseImageReport(View v)
    {
        try {
            Intent intent = new Intent();
            intent.setType("image/*");

            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE_REQUEST);
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
            {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                imageReport.setImageBitmap(imageToStore);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageToStore.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);

                byte[] imageInBytes = byteArrayOutputStream.toByteArray();
                long sizeOfImage = imageInBytes.length; //Image size

                //devide by 1024 to convert into byte
                //if bigger than 1900kb
                if(sizeOfImage/1024>1900)
                {
                    Toast.makeText(this, "Image size is big it will be compressed", Toast.LENGTH_SHORT).show();
                    imageToStore.compress(Bitmap.CompressFormat.JPEG,30,byteArrayOutputStream);
                }


            }

        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private void storeData(){
        String name = reportName.getText().toString();
        String prescribed = prescribedPeople.getText().toString();
        String diagnostic = diagnosticName.getText().toString();
        String details = reportDetails.getText().toString();

        String comments = reportComments.getText().toString();
        String date = testReportDatabase;

        if(name==null||prescribed==null||diagnostic==null ||details==null ||reportCost.getText().toString()==null||date==null){
            Toast.makeText(add_report.this, "Please give all the information", Toast.LENGTH_SHORT).show();
        }
        else {
            //it is initiaize here to check empty value
            Integer cost = Integer.parseInt(reportCost.getText().toString());

            //adding data by model
            MedicalReportModel reportModel = new MedicalReportModel(name,prescribed,diagnostic,details,cost,comments,date,imageToStore);
            MedicalReportDatbase db = new MedicalReportDatbase(add_report.this);

            db.addData(reportModel);
        }

    }


}