package com.example.medabinfinal.hospitalinfo.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;



import com.example.medabinfinal.hospitalinfo.Model.hospitalModel;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class hospitalInfoDatabase extends SQLiteAssetHelper {

    private static final String DB_NAME = "hospital.db";
    private static final int DB_VER = 2;
    private static final String TABLE_NAME ="hospital";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_LOCATION = "LOCATION";
    private static final String COL_PHONE = "PHONE";
    private static final String COL_EMERGENCY_UNIT = "EMERGENCY_UNIT";
    private static final String COL_HOTLINE_AMBULANCE = "HOTLINE_AMBULANCE";
    private static final String COL_SPECIALITY = "SPECIALITY";
    private static final String COL_CEBIN = "CEBIN_NO";
    private static final String COL_WARD = "WARD_NO";
    private static final String COL_OTHRS = "OTHERS";

    public hospitalInfoDatabase(Context context) {

        super(context, DB_NAME, null,DB_VER);
    }


    //get all hospital info
    public List<hospitalModel> getAllHospitalInfo()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        List<hospitalModel> result = new ArrayList<>();

        String query = "SELECT * FROM "+TABLE_NAME+" ORDER BY "+COL_NAME+" ASC";
        Cursor cursor = db.rawQuery(query,null);

//        String[] sqlSelect = {COL_ID,COL_NAME,COL_LOCATION,COL_PHONE,COL_EMERGENCY_UNIT,COL_HOTLINE_AMBULANCE,COL_SPECIALITY,COL_CEBIN,COL_WARD,COL_OTHRS};
//        String tableName = TABLE_NAME;
//
//        queryBuilder.setTables(tableName);
//        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        //List<hospitalModel> result = new ArrayList<>();


        if(cursor.moveToFirst())
        {
            do{
                hospitalModel hospital = new hospitalModel();
                hospital.setId(cursor.getInt(0));
                hospital.setName(cursor.getString(1));
                hospital.setLocation(cursor.getString(2));
                hospital.setPhone(cursor.getString(3));
                hospital.setEmergencyUnit(cursor.getString(4));
                hospital.setHotlineAmbulance(cursor.getString(5));
                hospital.setSpeciality(cursor.getString(6));
                hospital.setCebinNo(cursor.getInt(7));
                hospital.setWardNo(cursor.getInt(8));
                hospital.setOthers(cursor.getString(9));

                result.add(hospital);

            }while (cursor.moveToNext());
        }

        return result;

    }

    //get all hospital name
    public List<String> getAllHospitalNames()
    {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_NAME};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_NAME)));

            }while (cursor.moveToNext());
        }

        return result;
    }

    //method to get hospital by searching names

    public List<hospitalModel> getHospitalByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COL_LOCATION,COL_PHONE,COL_EMERGENCY_UNIT,COL_HOTLINE_AMBULANCE,COL_SPECIALITY,COL_CEBIN,COL_WARD,COL_OTHRS};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //if you want to get exact name , just change
        //Cursor cursor = queryBuilder.query(db,sqlSelect,"Name = ?",new String[]{name},null,null,null);

        //This will like query : Select * from Friends where Name LIKE %pattern%
        Cursor cursor = queryBuilder.query(db,sqlSelect,"NAME LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<hospitalModel> result = new ArrayList<>();

        if(cursor.moveToFirst())
        {
            do{
                hospitalModel hospital = new hospitalModel();
                hospital.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                hospital.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                hospital.setLocation(cursor.getString(cursor.getColumnIndex(COL_LOCATION)));
                hospital.setPhone(cursor.getString(cursor.getColumnIndex(COL_PHONE)));
                hospital.setEmergencyUnit(cursor.getString(cursor.getColumnIndex(COL_EMERGENCY_UNIT)));
                hospital.setHotlineAmbulance(cursor.getString(cursor.getColumnIndex(COL_HOTLINE_AMBULANCE)));
                hospital.setSpeciality(cursor.getString(cursor.getColumnIndex(COL_SPECIALITY)));
                hospital.setCebinNo(cursor.getInt(cursor.getColumnIndex(COL_CEBIN)));
                hospital.setWardNo(cursor.getInt(cursor.getColumnIndex(COL_WARD)));
                hospital.setOthers(cursor.getString(cursor.getColumnIndex(COL_OTHRS)));

                result.add(hospital);

            }while (cursor.moveToNext());
        }
        return result;


    }


}
