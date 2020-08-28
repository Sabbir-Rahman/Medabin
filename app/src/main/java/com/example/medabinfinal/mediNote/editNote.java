package com.example.medabinfinal.mediNote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.mediNote.Database.NoteDatabase;
import com.example.medabinfinal.mediNote.Database.NoteModel;

import java.util.Calendar;

public class editNote extends AppCompatActivity {

    Toolbar toolbar;
    EditText mediNoteTitle,mediNoteFeelings;
    Calendar calendar;
    String oldDate;
    String oldTime;
    NoteModel noteModel;
    NoteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        //receive the data (Id) from mediNoteDetails
        Intent intent = getIntent();
        Long id = intent.getLongExtra("ID",0);
        db = new NoteDatabase(this);
        noteModel = db.getNote(id);

        toolbar = findViewById(R.id.toolbarMediNote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(noteModel.getTitle());
        //make back button to home



        mediNoteTitle = findViewById(R.id.add_medinote_title);
        mediNoteFeelings = findViewById(R.id.add_feelings_medinote);

        mediNoteTitle.setText(noteModel.getTitle());
        mediNoteFeelings.setText(noteModel.getContent());


        //appear the title in the status bar
        mediNoteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() !=0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //get current date and time date/month/year(date format) hh:mm(time format)
        oldDate = noteModel.getDate();
        oldTime = noteModel.getTime();


        System.out.println("Date:"+oldDate+"  Time: "+oldTime);
        Log.d("calender","Date and Time:  " +oldDate+" and "+ oldTime);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //showing the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.delete){
            Toast.makeText(this, "Delete button is clicked", Toast.LENGTH_SHORT).show();
            onBackPressed();

        }
        if(item.getItemId() == R.id.update){
            //add note to the database when note is added no id is needed in constructor

            noteModel.setTitle(mediNoteTitle.getText().toString());
            noteModel.setContent(mediNoteFeelings.getText().toString());
            noteModel.setDate(oldDate);
            noteModel.setTime(oldTime);

            //as edit note return integer
            int id = db.editNote(noteModel);

            //check note is updated or not
            if(id==noteModel.getId()){
                Toast.makeText(this, "Note is updated", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Sorry some error occured updating node try again", Toast.LENGTH_SHORT).show();
            }


            Intent intent = new Intent(getApplicationContext(),medinoteDetails.class);
            //medinoteDetails activity needs a id
            intent.putExtra("ID",noteModel.getId());
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void goToMedinoteMain() {
        Intent intent = new Intent(this,mainActivityMediNote.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}