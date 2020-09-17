package com.example.medabinfinal.updateRecord.medicalReport.Database;

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

import com.example.medabinfinal.updateRecord.doctorRecord.Database.updateRecordDoctorModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MedicalReportDatbase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Report_record.db";
    private static final String DATABASE_TABLE = "report_record_table";

    private static  String COL_ID = "Id";
    private static final String COL_NAME = "Report_Name";
    private static final String COl_PRESCRIBED = "Prescribed_people";
    private static final String COL_DIAGNOSTIC = "Diagnostic_name";
    private static final String COL_DETAILS = "Report_details";
    private static final String COL_COST = "Test_cost";
    private static final String COL_COMMENTS = "Report_comments";
    private static final String COL_DATE = "Test_Date";
    private static final String COL_IMAGE = "Report_Image";

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    private Context context;



    public MedicalReportDatbase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTable = "CREATE TABLE "+ DATABASE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COl_PRESCRIBED + " TEXT, " + COL_DIAGNOSTIC + " TEXT, "
                + COL_DETAILS + " TEXT, " + COL_COST + " INTEGER, " + COL_COMMENTS + " TEXT, " + COL_DATE + " TEXT, " + COL_IMAGE + " BLOB)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);

    }

    public long addData(MedicalReportModel reportModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COL_NAME,reportModel.getReportName());
        contentValues.put(COl_PRESCRIBED,reportModel.getPrescribedPeople());
        contentValues.put(COL_DIAGNOSTIC,reportModel.getDiagnosticName());
        contentValues.put(COL_DETAILS,reportModel.getReprotDetails());
        contentValues.put(COL_COST,reportModel.getTestCost());
        contentValues.put(COL_COMMENTS,reportModel.getRecordComment());
        contentValues.put(COL_DATE,reportModel.getDate());

        //image storing
        Bitmap imageToStoreBitmap = reportModel.getImageReport();
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

    public List<MedicalReportModel> getAllReports(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<MedicalReportModel> allRecords = new ArrayList<>();

        //select *from databaseName

//        String query = "SELECT * FROM "+DATABASE_TABLE;

        //edit query to show thw new record first
        String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+COL_DATE+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                MedicalReportModel reportModel = new MedicalReportModel();

                reportModel.setId(cursor.getLong(0));
                reportModel.setReportName(cursor.getString(1));
                reportModel.setPrescribedPeople(cursor.getString(2));
                reportModel.setDiagnosticName(cursor.getString(3));
                reportModel.setReprotDetails(cursor.getString(4));
                reportModel.setTestCost(cursor.getInt(5));
                reportModel.setRecordComment(cursor.getString(6));
                reportModel.setDate(cursor.getString(7));

                byte[] imageBytes = cursor.getBlob(8);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                reportModel.setImageReport(bitmap);



                //all notes list added
                allRecords.add(reportModel);

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

    public List<MedicalReportModel> searchByReportName(String name){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COl_PRESCRIBED,COL_DIAGNOSTIC,COL_DETAILS,COL_COST,COL_COMMENTS
                ,COL_DATE,COL_IMAGE};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Report_Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<MedicalReportModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                MedicalReportModel reportModel = new MedicalReportModel();

                reportModel.setId(cursor.getLong(0));
                reportModel.setReportName(cursor.getString(1));
                reportModel.setPrescribedPeople(cursor.getString(2));
                reportModel.setDiagnosticName(cursor.getString(3));
                reportModel.setReprotDetails(cursor.getString(4));
                reportModel.setTestCost(cursor.getInt(5));
                reportModel.setRecordComment(cursor.getString(6));
                reportModel.setDate(cursor.getString(7));

                byte[] imageBytes = cursor.getBlob(8);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                reportModel.setImageReport(bitmap);



                //all notes list added
                allRecords.add(reportModel);


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

    public List<String> getReportNames(){


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



    public Bitmap getReportImage(long id){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COl_PRESCRIBED,COL_DIAGNOSTIC,COL_DETAILS,COL_COST,COL_COMMENTS
                ,COL_DATE,COL_IMAGE};
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
