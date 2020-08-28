package com.example.medabinfinal.mediNote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.mediNote.Adapter.adapter;
import com.example.medabinfinal.mediNote.Database.NoteDatabase;
import com.example.medabinfinal.mediNote.Database.NoteModel;

import java.util.List;

public class mainActivityMediNote extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    adapter adapterMain;
    List<NoteModel> notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_medi_note);

        toolbar = findViewById(R.id.toolbarMediNote);
        setSupportActionBar(toolbar);
        //going back with back button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NoteDatabase db = new NoteDatabase(this);
        notes  = db.getAllNotes();

        recyclerView = findViewById(R.id.listOfNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterMain = new adapter(this,notes);
        recyclerView.setAdapter(adapterMain);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //showing the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu_medinote,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.add){
            Toast.makeText(this, "Add button is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,addNote.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.mediNoteSearch){
            Toast.makeText(this, "Search button is clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,mediNoteSearch.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}