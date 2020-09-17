package com.example.medabinfinal.updateRecord.medicalReport;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.DoctorRecordDatabase;

public class add_report extends AppCompatActivity {

    private EditText reportName,prescribedPeople,diagnosticName,reportDetails,reportComments,reportDate,reportImage;
    private EditText testDate; 
    private Button submit;
    private int testYear,testMonth,testDoctor;
    String testReportDatabase;

    private ImageView imageReport;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_report);

    }
}