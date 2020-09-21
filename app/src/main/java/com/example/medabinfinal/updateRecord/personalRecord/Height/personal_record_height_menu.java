package com.example.medabinfinal.updateRecord.personalRecord.Height;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class personal_record_height_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_height_menu);
    }


    public void sendUserToHeightAdd(View v) {
        Intent intent = new Intent(this, personal_record_height_add.class);
        startActivity(intent);
    }

    public void sendUserToHeightView(View v) {
        Intent intent = new Intent(this, personal_record_height_view.class);
        startActivity(intent);
    }

}