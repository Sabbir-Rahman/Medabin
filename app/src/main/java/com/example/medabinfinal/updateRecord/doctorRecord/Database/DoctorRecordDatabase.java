package com.example.medabinfinal.updateRecord.doctorRecord.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.example.medabinfinal.updateRecord.medicineRecord.Database.updateRecordMedicineModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DoctorRecordDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Doctor_record.db";
    private static final String DATABASE_TABLE = "doctor_record_table";

    private static  String COL_ID = "Id";
    private static final String COL_NAME = "Doctor_Name";
    private static final String COl_SPECIALITY = "Doctor_Speciality";
    private static final String COL_CHAMBER = "Chamber_Location";
    private static final String COL_SYMPTOMS = "Patient_Symptoms";
    private static final String COL_FEES = "Consultation_Fees";
    private static final String COL_COMMENTS = "Comments";
    private static final String COL_VISIT = "Visit_Date";
    private static final String COL_IMAGE = "Prescription_Image";

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    private Context context;

    public DoctorRecordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTable = "CREATE TABLE "+ DATABASE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COl_SPECIALITY + " TEXT, " + COL_CHAMBER + " TEXT, "
                + COL_SYMPTOMS + " TEXT, " + COL_FEES + " INTEGER, " + COL_COMMENTS + " TEXT, " + COL_VISIT + " TEXT, " + COL_IMAGE + " BLOB)";

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
        contentValues.put(COL_COMMENTS,doctorModel.getComments());
        contentValues.put(COL_VISIT,doctorModel.getVisitDate());

        //image storing
        Bitmap imageToStoreBitmap = doctorModel.getImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,30,byteArrayOutputStream);

        imageInBytes = byteArrayOutputStream.toByteArray();
        long sizeOfImage = imageInBytes.length; //Image size

        Toast.makeText(context, "Image Size"+sizeOfImage, Toast.LENGTH_SHORT).show();
        contentValues.put(COL_IMAGE,imageInBytes);




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
        String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+COL_VISIT+" DESC";

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

                byte[] imageBytes = cursor.getBlob(8);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                doctorModel.setImage(bitmap);



                //all notes list added
                allRecords.add(doctorModel);

            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return allRecords;
    }

    public List<updateRecordDoctorModel> searchByDoctorName(String name){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COl_SPECIALITY,COL_CHAMBER,COL_SYMPTOMS,COL_FEES,COL_COMMENTS
                ,COL_VISIT,COL_IMAGE};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Doctor_Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<updateRecordDoctorModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
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

                byte[] imageBytes = cursor.getBlob(8);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                doctorModel.setImage(bitmap);



                //all notes list added
                allRecords.add(doctorModel);

            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return allRecords;

    }

    public List<String> getDoctorNames(){


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

    public Bitmap getPrescriptionImage(long id){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COl_SPECIALITY,COL_CHAMBER,COL_SYMPTOMS,COL_FEES,COL_COMMENTS
                ,COL_VISIT,COL_IMAGE};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Id LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        Bitmap bitmap = null;

        if (cursor.moveToFirst())
        {
            do{
                updateRecordDoctorModel doctorModel = new updateRecordDoctorModel();

                byte[] imageBytes = cursor.getBlob(8);

                bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                doctorModel.setImage(bitmap);



            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();

        return bitmap;

    }




}
