package com.example.medabinfinal.updateRecord.hospitalRecord.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.medabinfinal.updateRecord.medicineRecord.Database.updateRecordMedicineModel;

import java.util.ArrayList;
import java.util.List;

public class HospitalRecordDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Hospital_record.db";
    private static final String DATABASE_TABLE = "hospital_record_table";

    private static  String COL_ID = "Id";
    private static final String COL_NAME = "Hospital_Name";
    private static final String COl_REASON = "Admit_Reason";
    private static final String COL_ADMIT_UNDER = "Admit_under";
    private static final String COL_ABOUT_CABIN_WARD = "About_cabin_ward";
    private static final String COL_COST = "Total_cost";
    private static final String COL_ADMIT_DATE = "Admit_date";
    private static final String COL_RELEASE_DATE = "Release_date";
    private static final String COL_COMMENTS = "Comments";


    private Context context;

    public HospitalRecordDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        String createTable = "CREATE TABLE "+ DATABASE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COl_REASON + " TEXT, " + COL_ADMIT_UNDER + " TEXT, "
                + COL_ABOUT_CABIN_WARD + " TEXT, " + COL_COST + " TEXT, " + COL_ADMIT_DATE + " TEXT, " + COL_RELEASE_DATE + " TEXT, " + COL_COMMENTS + " TEXT)";

        db.execSQL(createTable);

        Toast.makeText(context, "Table is created", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addData(HospitalRecordModel hospitalRecordModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //putt data in the database
        contentValues.put(COL_NAME,hospitalRecordModel.getHospitalName());
        contentValues.put(COl_REASON,hospitalRecordModel.getAdmitReason());
        contentValues.put(COL_ADMIT_UNDER,hospitalRecordModel.getAdmitUnder());
        contentValues.put(COL_ABOUT_CABIN_WARD,hospitalRecordModel.getAboutCabinWard());
        contentValues.put(COL_COST,hospitalRecordModel.getTotalCost());
        contentValues.put(COL_ADMIT_DATE,hospitalRecordModel.getAdmitDate());
        contentValues.put(COL_RELEASE_DATE,hospitalRecordModel.getReleaseDate());
        contentValues.put(COL_COMMENTS,hospitalRecordModel.getComments());


        long ID = db.insert(DATABASE_TABLE,null,contentValues);

        if(ID == -1)
        {
            Toast.makeText(context, "Error!! Data not Inserted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
        }

        //if any error return -1
        Log.d("Inserted","Id -> "+ID);
        db.close();
        return ID;
    }

    public List<HospitalRecordModel> getAllHospitals(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<HospitalRecordModel> allRecords = new ArrayList<>();

        //select *from databaseName

//        String query = "SELECT * FROM "+DATABASE_TABLE;

        //edit query to show thw new record first
        String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+COL_RELEASE_DATE+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                HospitalRecordModel hospitalRecordModel = new HospitalRecordModel();

                hospitalRecordModel.setId(cursor.getLong(0));
                hospitalRecordModel.setHospitalName(cursor.getString(1));
                hospitalRecordModel.setAdmitReason(cursor.getString(2));
                hospitalRecordModel.setAdmitUnder(cursor.getString(3));
                hospitalRecordModel.setAboutCabinWard(cursor.getString(4));
                hospitalRecordModel.setTotalCost(cursor.getInt(5));
                hospitalRecordModel.setAdmitDate(cursor.getString(6));
                hospitalRecordModel.setReleaseDate(cursor.getString(7));
                hospitalRecordModel.setComments(cursor.getString(8));

                //all notes list added
                allRecords.add(hospitalRecordModel);

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return allRecords;
    }

    public List<HospitalRecordModel> searchByHospitalName(String name){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COl_REASON,COL_ADMIT_UNDER,COL_ABOUT_CABIN_WARD,COL_COST,
                              COL_ADMIT_DATE,COL_RELEASE_DATE,COL_COMMENTS};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Hospital_Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<HospitalRecordModel> result = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                HospitalRecordModel hospitalRecordModel = new HospitalRecordModel();

                hospitalRecordModel.setId(cursor.getLong(0));
                hospitalRecordModel.setHospitalName(cursor.getString(1));
                hospitalRecordModel.setAdmitReason(cursor.getString(2));
                hospitalRecordModel.setAdmitUnder(cursor.getString(3));
                hospitalRecordModel.setAboutCabinWard(cursor.getString(4));
                hospitalRecordModel.setTotalCost(cursor.getInt(5));
                hospitalRecordModel.setAdmitDate(cursor.getString(6));
                hospitalRecordModel.setReleaseDate(cursor.getString(7));
                hospitalRecordModel.setComments(cursor.getString(8));

                result.add(hospitalRecordModel);

            }while (cursor.moveToNext());
        }

        return result;


    }

    public List<String> getHospitalName(){


        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_NAME};
        String tablename = DATABASE_TABLE;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_NAME)));

            }while (cursor.moveToNext());
        }
        return result;


    }




}
