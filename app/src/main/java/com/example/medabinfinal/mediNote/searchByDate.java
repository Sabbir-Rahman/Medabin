package com.example.medabinfinal.mediNote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.medabinfinal.R;
import com.example.medabinfinal.mediNote.Adapter.adapter;
import com.example.medabinfinal.mediNote.Database.NoteDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class searchByDate extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    adapter searchAdapter;

    MaterialSearchBar searchBar;
    List<String> suggestList = new ArrayList<>();

    NoteDatabase noteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medinote_search_by_date);

        //initialize view
        recyclerView = findViewById(R.id.recyclerSearchDate);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = findViewById(R.id.search_bar_medinote_date);

        noteDatabase = new NoteDatabase(this);

        //set up search bar
        searchBar.setHint("Search");
        searchBar.setCardViewElevation(10);
        loadSuggestedList();

        //it will happen when text is changed
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                List<String> suggest = new ArrayList<>();
                for (String search:suggestList)
                {
                    if(search.toLowerCase().contains(searchBar.getText().toLowerCase()))
                    {
                        suggest.add(search);
                    }
                    searchBar.setLastSuggestions(suggest);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    recyclerView.setAdapter(searchAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }



            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        searchAdapter = new adapter(this,noteDatabase.getAllNotes());
        recyclerView.setAdapter(searchAdapter);


    }

    private void startSearch(String date) {
        searchAdapter = new adapter(this,noteDatabase.searchByDate(date));
        recyclerView.setAdapter(searchAdapter);

    }

    private void loadSuggestedList() {

        suggestList = noteDatabase.getDates();
        searchBar.setLastSuggestions(suggestList);

    }
}