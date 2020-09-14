package com.example.medabinfinal.updateRecord.doctorRecord.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;




import java.util.ArrayList;
import java.util.List;

public class DoctorRecordDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "doctor_record.db";
    private static final String DATABASE_TABLE = "doctor_record_table";

    private static  String COL_ID = "Id";
    private static final String COL_NAME = "Doctor_Name";
    private static final String COl_SPECIALITY = "Doctor_Speciality";
    private static final String COL_CHAMBER = "Chamber_Location";
    private static final String COL_SYMPTOMS = "Patient_Symptoms";
    private static final String COL_FEES = "Consultation_Fees";
    private static final String COL_COMMENST = "Comments";
    private static final String COL_VISIT = "Visit_Date";

    private Context context;

    public DoctorRecordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTable = "CREATE TABLE "+ DATABASE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COl_SPECIALITY + " TEXT, " + COL_CHAMBER + " TEXT, "
                + COL_SYMPTOMS + " TEXT, " + COL_FEES + " INTEGER, " + COL_COMMENST + " TEXT, " + COL_VISIT + " TEXT)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public long addData(updateRecordDoctorModel doctorModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COL_NAME,doctorModel.getDoctorName());
        contentValues.put(COl_SPECIALITY,doctorModel.getSpeciality());
        contentValues.put(COL_CHAMBER,doctorModel.getChamberLocation());
        contentValues.put(COL_SYMPTOMS,doctorModel.getSymtomps());
        contentValues.put(COL_FEES,doctorModel.getConsultantFee());
        contentValues.put(COL_COMMENST,doctorModel.getComments());
        contentValues.put(COL_VISIT,doctorModel.getVisitDate());



        long ID = db.insert(DATABASE_TABLE,null,contentValues);

        if(ID!=-1)
        {
            Toast.makeText(context, "Data Inserted", Toast.LENGTH_SHORT).show();
        }
        //if any error return -1
        Log.d("Inserted","Id -> "+ID);
        db.close();
        return ID;
    }

    public List<updateRecordDoctorModel> getAllDoctors(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<updateRecordDoctorModel> allRecords = new ArrayList<>();

        //select *from databaseName

//        String query = "SELECT * FROM "+DATABASE_TABLE;

        //edit query to show thw new record first
        String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+COL_NAME+" ASC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                updateRecordDoctorModel doctorModel = new updateRecordDoctorModel();

                doctorModel.setId(cursor.getLong(0));
                doctorModel.setDoctorName(cursor.getString(1));
                doctorModel.setSpeciality(cursor.getString(2));
                doctorModel.setChamberLocation(cursor.getString(3));
                doctorModel.setSymtomps(cursor.getString(4));
                doctorModel.setConsultantFee(cursor.getInt(5));
                doctorModel.setComments(cursor.getString(6));
                doctorModel.setVisitDate(cursor.getString(7));



                //all notes list added
                allRecords.add(doctorModel);

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return allRecords;
    }


}
