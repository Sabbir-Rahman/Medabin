package com.example.medabinfinal.updateRecord.medicalReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.doctorRecord.Adapter.doctorRecordAdapter;
import com.example.medabinfinal.updateRecord.medicalReport.Adapter.ReportAdapter;
import com.example.medabinfinal.updateRecord.medicalReport.Database.MedicalReportDatbase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class view_report extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ReportAdapter adapter;

    MaterialSearchBar searchBar;

    List<String> suggestList = new ArrayList<>();

    MedicalReportDatbase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        //initialize view
        recyclerView = findViewById(R.id.recyclerReport);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = findViewById(R.id.searchBarReport);

        db = new MedicalReportDatbase(this);

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

        adapter = new ReportAdapter(this,db.getAllReports());
        recyclerView.setAdapter(adapter);


    }

    private void startSearch(String text) {
        adapter = new ReportAdapter(this,db.searchByReportName(text));
        recyclerView.setAdapter(adapter);

    }

    private void loadSuggestedList() {

        suggestList = db.getReportNames();
        searchBar.setLastSuggestions(suggestList);

    }
}