package com.example.medabinfinal.giveFeedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.medabinfinal.R;
import com.example.medabinfinal.giveFeedback.Adapter.HospitalRankAdapter;
import com.example.medabinfinal.giveFeedback.Adapter.MedicineRankAdapter;
import com.example.medabinfinal.giveFeedback.Database.giveFeedbackDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class medicine_feedback_view extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MedicineRankAdapter adapter;

    MaterialSearchBar searchBar;

    List<String> suggestList = new ArrayList<>();

    giveFeedbackDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_feedback_view);

        //initialize view
        recyclerView = findViewById(R.id.recycler_view_medicine_rating);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = findViewById(R.id.search_bar_medicine_rating_name);

        db = new giveFeedbackDatabase(this);

        //set up search bar
        searchBar.setHint("Search here by name");
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

        adapter = new MedicineRankAdapter(this,db.getAllMedicineRating());
        recyclerView.setAdapter(adapter);


    }

    private void startSearch(String text) {
        adapter = new MedicineRankAdapter(this,db.searchMedicineRatingByName(text));
        recyclerView.setAdapter(adapter);

    }

    private void loadSuggestedList() {

        suggestList = db.getMedicineRatingsNames();
        searchBar.setLastSuggestions(suggestList);

    }
}