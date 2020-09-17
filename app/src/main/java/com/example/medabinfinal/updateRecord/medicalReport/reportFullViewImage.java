package com.example.medabinfinal.updateRecord.medicalReport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.DoctorRecordDatabase;
import com.example.medabinfinal.updateRecord.doctorRecord.showFullPrescriptionImage;
import com.example.medabinfinal.updateRecord.medicalReport.Database.MedicalReportDatbase;

public class reportFullViewImage extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;

    MedicalReportDatbase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_full_view_image);

        imageView = findViewById(R.id.imageFullReport);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID",0);

        db = new MedicalReportDatbase(this);

        bitmap = db.getReportImage(id);

        imageView.setImageBitmap(bitmap);
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}