package com.example.medabinfinal.updateRecord.doctorRecord;

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
import com.example.medabinfinal.updateRecord.medicineRecord.medicineRecordAddMedicine;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class doctor_record_add extends AppCompatActivity {


    EditText doctorname,doctorSpeciality,chamberLocation,patientSymtomps,consultationFee,commentsDoctor;
    EditText doctorVisitDate;
    Button submit;
    int doctorVisitYear,doctorVisitMonth,doctorVisitDay;
    String doctorVisitDateDatabase;

    private ImageView imagePrescription;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    DoctorRecordDatabase doctorDatabase;

    private static final int PICK_IMAGE_REQUEST =100;

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
        imagePrescription = findViewById(R.id.chooseImagePrescription);
        submit = findViewById(R.id.doctor_record_submit_button);

        doctorDatabase = new DoctorRecordDatabase(this);

        //testdate picking
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

                storeData();
            }
        });
    }

    public void chooseImagePrescription(View v)
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

                imagePrescription.setImageBitmap(imageToStore);
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
                    updateRecordDoctorModel doctorModel = new updateRecordDoctorModel(name,speciality,chamber,symptoms,fees,comments,date,imageToStore);
                    DoctorRecordDatabase db = new DoctorRecordDatabase(doctor_record_add.this);

                    db.addData(doctorModel);
        }

    }
}