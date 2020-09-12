package com.example.medabinfinal.giveFeedback;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;

public class giveFeedbackMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback_menu);
    }

    public void sendUserToDoctorFeedback(View v)
    {
        Toast.makeText(this, "Going to Doctor Feedback", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,doctor_feedback_add.class);
        startActivity(intent);
        finish();
    }
}