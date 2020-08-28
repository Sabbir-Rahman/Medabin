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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.mediNote.Database.NoteDatabase;
import com.example.medabinfinal.mediNote.Database.NoteModel;

import java.util.Calendar;

public class addNote extends AppCompatActivity {
    Toolbar toolbar;
    EditText mediNoteTitle,mediNoteFeelings;
    Calendar calendar;
    String todaysDate;
    String currentTime;
    String finalTime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar = findViewById(R.id.toolbarMediNote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("What you feel now");
        //make back button to home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mediNoteTitle = findViewById(R.id.add_medinote_title);
        mediNoteFeelings = findViewById(R.id.add_feelings_medinote);
        NoteDatabase db = new NoteDatabase(this);

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
        calendar = Calendar.getInstance();
        todaysDate = calendar.get(Calendar.DAY_OF_MONTH) + "/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
        currentTime = pad(calendar.get(Calendar.HOUR))+ ":"+pad(+calendar.get(Calendar.MINUTE));
        if(calendar.get(Calendar.AM_PM) == 1){
            finalTime = currentTime +" PM";
        }
        if(calendar.get(Calendar.AM_PM) == 0){
            finalTime = currentTime +" AM";
        }

        System.out.println("Date:"+todaysDate+"  Time: "+finalTime);
        Log.d("calender","Date and Time:  " +todaysDate+" and "+ finalTime);


    }

    //method to add 0 in the left if hour/minute/second is less then 10
    private String pad(int i) {
        if(i<10)
           return  "0"+i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //showing the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu,menu);
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

            NoteModel noteModel = new NoteModel(mediNoteTitle.getText().toString(),mediNoteFeelings.getText().toString(),todaysDate,finalTime);
            NoteDatabase db = new NoteDatabase(this);
            db.addData(noteModel);


            Toast.makeText(this, "Save button is clicked", Toast.LENGTH_SHORT).show();
            goToMedinoteMain();

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