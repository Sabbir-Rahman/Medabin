package com.example.medabinfinal.medicinePlanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MediplannerDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mediplanner.db";
    public static final String TABLE_NAME = "MEDICINE_TABLE";
    public static final String ID = "ID";
    public static final String COL_1 = "Medicine_Name";
    public static final String COL_2 = "Company";
    public static final String COL_5 = "Rate";
    public static final String COL_3 = "Schedule";
    public static final String COL_4 = "Consume";
    public static final String COL_6 = "Active";





    public MediplannerDatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_1 + " TEXT, " + COL_2 + " TEXT, " + COL_3 + " INTEGER, " + COL_4 + " REAL, " + COL_5 + " REAL, " + COL_6 + " BOOL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addOne(mediplannerMedicineModel medicineModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_1,medicineModel.getName());
        cv.put(COL_2,medicineModel.getCompany());
        cv.put(COL_3,medicineModel.getSchedule());
        cv.put(COL_4,medicineModel.getConsume());
        cv.put(COL_5,medicineModel.getRate());
        cv.put(COL_6,medicineModel.isActive());

        long insert = db.insert(TABLE_NAME,null,cv);

        if ((insert == -1))
        {
            return false;
        }
        else {
            return true;
        }

    }


}
