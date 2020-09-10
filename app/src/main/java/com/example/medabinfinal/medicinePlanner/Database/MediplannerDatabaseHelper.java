package com.example.medabinfinal.medicinePlanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

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


    public long addOne(String name,String company,Integer schedule,Float consume,Float rate,boolean b){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_1,name);
        cv.put(COL_2,company);
        cv.put(COL_3,schedule);
        cv.put(COL_4,consume);
        cv.put(COL_5,rate);
        cv.put(COL_6,b);

        long insert = db.insert(TABLE_NAME,null,cv);
        db.close();
        return insert;

    }

    public List<mediplannerMedicineModel> getEveryone(){
        List<mediplannerMedicineModel> retuenList = new ArrayList<>();

        String queryString = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cs = db.rawQuery(queryString,null);

        if(cs.moveToFirst()){
            //loop through the cursor

            do{
                int medicineId = cs.getInt(0);
                String medicineName = cs.getString(1);
                String companyName = cs.getString(2);
                Integer schedule = cs.getInt(3);
                Float consume  = cs.getFloat(4);
                Float rate  = cs.getFloat(5);
                boolean medicineActive = cs.getInt(6) == 1 ? true:false;


                //new object

                mediplannerMedicineModel newMedicine = new mediplannerMedicineModel(medicineId,medicineName,companyName,schedule,consume,rate,medicineActive);
                retuenList.add(newMedicine);

            }
            while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return retuenList;

    }


    public List<plannerModel> planMedicine(Integer day){

        List<plannerModel> retuenList = new ArrayList<>();


        String queryString = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cs = db.rawQuery(queryString,null);

        if(cs.moveToFirst()){
            //loop through the cursor


                do {

                    if (cs.getInt(6) == 1) {
                        int medicineId = cs.getInt(0);
                        String medicineName = cs.getString(1);
                        String companyName = cs.getString(2);
                        Integer schedule = cs.getInt(3);
                        Float consume = cs.getFloat(4);
                        Float rate = cs.getFloat(5);
                        Float quantity = day * (consume / schedule);
                        double roundQuantity = Math.ceil(quantity);
                        double ttl_rate = rate * roundQuantity;
                        // Float ttl_rate = rate * quantity;


                        //new object

                        plannerModel Medicine = new plannerModel(medicineName, roundQuantity, rate, ttl_rate);
                        retuenList.add(Medicine);

                    }
                }
                while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return retuenList;

    }


    public double totalMoney(Integer day){
        //List<plannerModel> retuenList = new ArrayList<>();
        float total = 0;

        String queryString = "SELECT * FROM " +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cs = db.rawQuery(queryString,null);

        if(cs.moveToFirst()){
            //loop through the cursor


                do {
                    if (cs.getInt(6) == 1) {
                        int medicineId = cs.getInt(0);
                        String medicineName = cs.getString(1);
                        String companyName = cs.getString(2);
                        Integer schedule = cs.getInt(3);
                        Float consume = cs.getFloat(4);
                        Float rate = cs.getFloat(5);
                        Float quantity = day * (consume / schedule);
                        double roundQuantity = Math.ceil(quantity);
                        double ttl_rate = rate * roundQuantity;
                        total += ttl_rate;


                        //new object

                    }
                }
                while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return total;

    }


    public String getSingleMedicineName(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " +id;
        Cursor cs = db.rawQuery(queryString,null);

        String name = null;
        if(cs.moveToFirst()){
            //loop through the cursor


            do {

                    String medicineName = cs.getString(1);
                    name = medicineName;


            }
            while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return name;

    }

    public String getSingleMedicineCompany(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " +id;
        Cursor cs = db.rawQuery(queryString,null);

        String company = null;
        if(cs.moveToFirst()){
            //loop through the cursor


            do {

                String medicineCompany = cs.getString(2);
                company = medicineCompany;


            }
            while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return company;

    }

    public Integer getSingleMedicineSchedule(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " +id;
        Cursor cs = db.rawQuery(queryString,null);

        Integer schedule = 0;
        if(cs.moveToFirst()){
            //loop through the cursor


            do {

                Integer medicineSchedule = cs.getInt(3);
                schedule = medicineSchedule;


            }
            while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return schedule;

    }


    public boolean getSingleMedicineIsActive(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " +id;
        Cursor cs = db.rawQuery(queryString,null);

        Boolean isActive = false;
        if(cs.moveToFirst()){
            //loop through the cursor


            do {


                    Integer active = cs.getInt(6);
                    if(active ==1)
                        isActive = true;



            }
            while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return isActive;

    }


    public Float getSingleMedicineConsume(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " +id;
        Cursor cs = db.rawQuery(queryString,null);

        Float consume = null;
        if(cs.moveToFirst()){
            //loop through the cursor


            do {

                Float medicineConsume = cs.getFloat(4);
                consume = medicineConsume;


            }
            while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return consume;

    }

    public Float getSingleMedicineRate(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " +id;
        Cursor cs = db.rawQuery(queryString,null);

        Float rate = null;
        if(cs.moveToFirst()){
            //loop through the cursor


            do {

                Float medicineRate = cs.getFloat(5);
                rate = medicineRate;


            }
            while (cs.moveToNext());


        }

        else {
            //failure do not do anything
        }
        cs.close();
        db.close();

        return rate;

    }






    public boolean deleteOne(mediplannerMedicineModel medicineModel){

        //find the medicine in the database if exist  return true

        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = " + medicineModel.getId();

        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            return true;
        }
        else
        {
            return false;
        }



    }

    public boolean updateData(String id,String name,String company,Integer schedule,Float consume,Float rate,boolean isCurrent){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,id);
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,company);
        contentValues.put(COL_3,schedule);
        contentValues.put(COL_4,consume);
        contentValues.put(COL_5,rate);
        contentValues.put(COL_6,isCurrent);

        db.update(TABLE_NAME,contentValues,"ID = ?",new String[] {id});
        return true;

    }


}
