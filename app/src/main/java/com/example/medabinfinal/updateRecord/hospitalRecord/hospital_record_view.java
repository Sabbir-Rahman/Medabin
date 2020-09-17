package com.example.medabinfinal.updateRecord.hospitalRecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.medabinfinal.R;
import com.example.medabinfinal.hospitalinfo.Adapter.HospitalAdapter;
import com.example.medabinfinal.updateRecord.doctorRecord.Adapter.doctorRecordAdapter;
import com.example.medabinfinal.updateRecord.doctorRecord.Database.DoctorRecordDatabase;
import com.example.medabinfinal.updateRecord.hospitalRecord.Adapter.HospitalRecordAdapter;
import com.example.medabinfinal.updateRecord.hospitalRecord.Database.HospitalRecordDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class hospital_record_view extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    HospitalRecordAdapter adapter;

    MaterialSearchBar searchBar;

    List<String> suggestList = new ArrayList<>();

    HospitalRecordDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_record_view);

        //initialize view
        recyclerView = findViewById(R.id.recyclerSearchHospitalRecord);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = findViewById(R.id.searchbarHospitalRecordName);

        db = new HospitalRecordDatabase(this);

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

        adapter = new HospitalRecordAdapter(this,db.getAllHospitals());
        recyclerView.setAdapter(adapter);


    }

    private void startSearch(String text) {
        adapter = new HospitalRecordAdapter(this,db.searchByHospitalName(text));
        recyclerView.setAdapter(adapter);

    }

    private void loadSuggestedList() {

        suggestList = db.getHospitalName();
        searchBar.setLastSuggestions(suggestList);

    }
}