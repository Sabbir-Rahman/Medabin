package com.example.medabinfinal.mediNote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;

public class mediNoteSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medi_note_search);
    }


    public void searchByTitle(View view){
        Toast.makeText(this, "Search By Title", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,searchByTitle.class);
        startActivity(intent);
    }

    public void searchByDate(View view){
        Toast.makeText(this, "Search By Date", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,searchByDate.class);
        startActivity(intent);
    }

    public void searchByTime(View view){
        Toast.makeText(this, "Search By Time", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,searchByTime.class);
        startActivity(intent);
    }

    public void searchByDetails(View view){
        Toast.makeText(this, "Search By Details", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,searchByContent.class);
        startActivity(intent);

    }
}