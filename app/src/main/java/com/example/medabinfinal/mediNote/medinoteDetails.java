package com.example.medabinfinal.mediNote;

import android.content.Intent;
import android.os.Bundle;

import com.example.medabinfinal.mediNote.Database.NoteDatabase;
import com.example.medabinfinal.mediNote.Database.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medabinfinal.R;

public class medinoteDetails extends AppCompatActivity {

    TextView noteDetails;
    NoteDatabase db;
    NoteModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medinote_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Feelings");

        noteDetails = findViewById(R.id.detailsNote);


        //receieve the passing data (Id) from adapter
        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID",0);

         db = new NoteDatabase(this);
         noteModel = db.getNote(id);

        //make the toolbar text to title
        getSupportActionBar().setTitle(noteModel.getTitle());

        //writing the details
        noteDetails.setText(noteModel.getContent());
        noteDetails.setMovementMethod(new ScrollingMovementMethod());

        Toast.makeText(this, "Title ->"+noteModel.getTitle(), Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNotes(noteModel.getId());
                //intent to go to main activity
                startActivity(new Intent(getApplicationContext(),mainActivityMediNote.class));
                Toast.makeText(medinoteDetails.this, ""+noteModel.getTitle()+" note is deleted", Toast.LENGTH_SHORT).show();

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.medinote_edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.editNote){
            //send user to edit activity
            Toast.makeText(this, "Going to edit Note", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,editNote.class);
            intent.putExtra("ID",noteModel.getId());
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,mainActivityMediNote.class);
        startActivity(intent);
        finish();
    }


}