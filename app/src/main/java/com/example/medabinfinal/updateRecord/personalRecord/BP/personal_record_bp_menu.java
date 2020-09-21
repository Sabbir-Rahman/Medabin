package com.example.medabinfinal.updateRecord.personalRecord.BP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.medabinfinal.R;

public class personal_record_bp_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_record_bp_menu);
    }

    public void sendUserToBpAdd(View v)
    {
        Intent intent = new Intent(this,personal_record_bp_add.class);
        startActivity(intent);
    }

    public void sendUserToBpView(View v)
    {
        Intent intent = new Intent(this,personal_record_bp_view.class);
        startActivity(intent);
    }
}