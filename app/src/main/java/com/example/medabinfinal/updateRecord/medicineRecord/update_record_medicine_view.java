package com.example.medabinfinal.updateRecord.medicineRecord;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.medabinfinal.R;
import com.example.medabinfinal.mediNote.Adapter.adapter;
import com.example.medabinfinal.updateRecord.medicineRecord.Adapter.SearchAdapterMedicineRecord;
import com.example.medabinfinal.updateRecord.medicineRecord.Database.updateRecordMedicineDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class update_record_medicine_view extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapterMedicineRecord searchAdapterMedicineRecord;

    MaterialSearchBar searchBar;
    List<String> suggestList = new ArrayList<>();

    updateRecordMedicineDatabase medicineDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_record_medicine_record_view_all);


        //initialize view
        recyclerView = findViewById(R.id.recyclerSearchMedicineName);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        searchBar = findViewById(R.id.searchbarUpdateRecordMedicine);

        medicineDatabase = new updateRecordMedicineDatabase(this);

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
                    recyclerView.setAdapter(searchAdapterMedicineRecord);
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

        searchAdapterMedicineRecord = new SearchAdapterMedicineRecord(this,medicineDatabase.getAllRecords());
        recyclerView.setAdapter(searchAdapterMedicineRecord);
    }

    private void startSearch(String text) {
        searchAdapterMedicineRecord = new SearchAdapterMedicineRecord(this,medicineDatabase.searchByMedicineName(text));
        recyclerView.setAdapter(searchAdapterMedicineRecord);

    }

    private void loadSuggestedList() {

        suggestList = medicineDatabase.getMedicineName();
        searchBar.setLastSuggestions(suggestList);

    }
}