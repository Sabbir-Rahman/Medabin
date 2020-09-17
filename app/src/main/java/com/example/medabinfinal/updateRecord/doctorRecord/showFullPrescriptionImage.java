package com.example.medabinfinal.updateRecord.doctorRecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.DoctorRecordDatabase;

public class showFullPrescriptionImage extends AppCompatActivity {

    ImageView imageView;
    Bitmap bitmap;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;


    DoctorRecordDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_prescription_image);

        imageView = findViewById(R.id.imageViewPrescription);
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID",0);

        db = new DoctorRecordDatabase(this);

        bitmap = db.getPrescriptionImage(id);

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