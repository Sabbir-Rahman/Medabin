package com.example.medabinfinal.medicineReminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.medabinfinal.R;
import com.example.medabinfinal.medicineReminder.Database.AlarmDatabase;
import com.example.medabinfinal.medicineReminder.Database.alarmAdapter;
import com.example.medabinfinal.updateRecord.medicalReport.Adapter.ReportAdapter;
import com.example.medabinfinal.updateRecord.medicalReport.Database.MedicalReportDatbase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class medicineReminderView extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    alarmAdapter adapter;

    MaterialSearchBar searchBar;

    List<String> suggestList = new ArrayList<>();

    AlarmDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_reminder_view);

        //initialize view
        recyclerView = findViewById(R.id.recyclerReminder);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = findViewById(R.id.searchBarReminder);

        db = new AlarmDatabase(this);

        //set up search bar
        searchBar.setHint("Search");
        searchBar.setCardViewElevation(10);
        loadSuggestedList();

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
                    recyclerView.setAdapter(adapter);
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

        adapter = new alarmAdapter(this,db.getAllReminders());
        recyclerView.setAdapter(adapter);


    }

    private void startSearch(String text) {
        adapter = new alarmAdapter(this,db.searchAlarmByMedicineName(text));
        recyclerView.setAdapter(adapter);

    }

    private void loadSuggestedList() {

        suggestList = db.getMedicineNames();
        searchBar.setLastSuggestions(suggestList);

    }
}