package com.example.medabinfinal.hospitalinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.medabinfinal.R;
import com.example.medabinfinal.hospitalinfo.Adapter.HospitalAdapter;
import com.example.medabinfinal.hospitalinfo.Database.hospitalInfoDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class hospitalInfoVIew extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    HospitalAdapter adapter;

    MaterialSearchBar searchBar;

    List<String> suggestList = new ArrayList<>();

    hospitalInfoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info_view);

        //init view
        recyclerView = (RecyclerView)findViewById(R.id.recyclerSearchHospitalView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = (MaterialSearchBar) findViewById(R.id.search_bar_hospital_view);

        //init db
        db = new hospitalInfoDatabase(this);

        //setup search bar
        searchBar.setHint("Search");
        searchBar.setCardViewElevation(10);
        loadSuggestList();
        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                List <String> suggest = new ArrayList<>();
                for(String search:suggestList)
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
                if(!enabled)
                {
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


        //Init after default set all result
        adapter = new HospitalAdapter(this,db.getAllHospitalInfo());
        recyclerView.setAdapter(adapter);


    }

    private void startSearch(String text) {

        adapter = new HospitalAdapter(this,db.getHospitalByName(text));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = db.getAllHospitalNames();
        searchBar.setLastSuggestions(suggestList);
    }
}