package com.example.medabinfinal.updateRecord.medicineRecord.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.medabinfinal.mediNote.Database.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class updateRecordMedicineDatabase extends SQLiteOpenHelper {

   private static final int DATABASE_VERSION = 3;
   private static final String DATABASE_NAME = "medicine_record.db";
   private static final String DATABASE_TABLE = "medicine_record_table";

   private static  String COL_ID = "Id";
   private static final String COL_NAME = "Medicine_Name";
   private static final String COl_TYPE = "Medicine_Type";
   private static final String COL_CONSUME = "Consume";
   private static final String COL_SCHEDULE = "Schedule";
   private static final String COL_MORNING = "Morning";
   private static final String COL_NOON = "Noon";
   private static final String COL_NIGHT = "Night";
   private static final String COL_OTHERS = "Others";
   private static final String COL_REASON_TAKE_MEDICINE = "Reason_to_take_medicine";
   private static final String COL_PRESCRIBED_BY = "Prescribed_by";
   private static final String COL_STARTDATE = "Start_Date";
   private static final String COL_ENDDATE = "End_Date";

   private Context context;


   public updateRecordMedicineDatabase(@Nullable Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
      this.context = context;
   }


   @Override
   public void onCreate(SQLiteDatabase db) {

      //create table
      String createTable = "CREATE TABLE "+ DATABASE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COl_TYPE + " TEXT, " + COL_CONSUME + " INTEGER, "
              + COL_SCHEDULE + " INTEGER, " + COL_MORNING + " TEXT, " + COL_NOON + " TEXT, " + COL_NIGHT + " TEXT, " + COL_OTHERS + " TEXT, " + COL_REASON_TAKE_MEDICINE + " TEXT, " + COL_PRESCRIBED_BY + " TEXT, "
              + COL_STARTDATE + " TEXT, " + COL_ENDDATE + " TEXT)";

      db.execSQL(createTable);

      Toast.makeText(context, "", Toast.LENGTH_SHORT).show();



   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      if(oldVersion >= newVersion)
         return;
      db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
      onCreate(db);

   }

   public long addData(updateRecordMedicineModel medicineModel){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      //getting the data by help of hashmap
      contentValues.put(COL_NAME,medicineModel.getMedicineName());
      contentValues.put(COl_TYPE,medicineModel.getMedicineType());
      contentValues.put(COL_CONSUME,medicineModel.getTimes());
      contentValues.put(COL_SCHEDULE,medicineModel.getDays());
      contentValues.put(COL_MORNING,medicineModel.getMorning());
      contentValues.put(COL_NOON,medicineModel.getNoon());
      contentValues.put(COL_NIGHT,medicineModel.getNight());
      contentValues.put(COL_OTHERS,medicineModel.getOthers());
      contentValues.put(COL_REASON_TAKE_MEDICINE,medicineModel.getReasonToTakeMedicine());
      contentValues.put(COL_PRESCRIBED_BY,medicineModel.getPrescribedBy());
      contentValues.put(COL_STARTDATE,medicineModel.getStartDate());
      contentValues.put(COL_ENDDATE,medicineModel.getEndDate());


      long ID = db.insert(DATABASE_TABLE,null,contentValues);
      //if any error return -1
      Log.d("Inserted","Id -> "+ID);
      db.close();
      return ID;
   }


   //single medicine record
   public updateRecordMedicineModel getMedicineRecord(long id){
      //select $ from database where id =l
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(DATABASE_TABLE,new String[]{COL_ID,COL_NAME,COl_TYPE,COL_CONSUME,COL_SCHEDULE,COL_MORNING,COL_NOON
                      ,COL_NIGHT,COL_OTHERS,COL_REASON_TAKE_MEDICINE,COL_PRESCRIBED_BY,COL_STARTDATE,COL_ENDDATE},COL_ID+"=?",
              new String[]{String.valueOf(id)},null,null,null);

      if(cursor != null)
         cursor.moveToFirst();

      db.close();

      return new updateRecordMedicineModel(cursor.getLong(0),cursor.getString(1),
              cursor.getString(2),cursor.getFloat(3),cursor.getInt(4),
              cursor.getString(5),cursor.getString(6),cursor.getString(7),
              cursor.getString(8),cursor.getString(9),cursor.getString(10),
              cursor.getString(11));


   }

   public List<updateRecordMedicineModel> getAllRecords(){
      SQLiteDatabase db = this.getReadableDatabase();
      List<updateRecordMedicineModel> allRecords = new ArrayList<>();

      //select *from databaseName

//        String query = "SELECT * FROM "+DATABASE_TABLE;

      //edit query to show thw new record first
      String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+COL_NAME+" ASC";

      Cursor cursor = db.rawQuery(query,null);

      if(cursor.moveToFirst()){
         do{
            updateRecordMedicineModel medicineModel = new updateRecordMedicineModel();

            medicineModel.setId(cursor.getLong(0));
            medicineModel.setMedicineName(cursor.getString(1));
            medicineModel.setMedicineType(cursor.getString(2));
            medicineModel.setTimes(cursor.getFloat(3));
            medicineModel.setDays(cursor.getInt(4));
            medicineModel.setMorning(cursor.getString(5));
            medicineModel.setNoon(cursor.getString(6));
            medicineModel.setNight(cursor.getString(7));
            medicineModel.setOthers(cursor.getString(8));
            medicineModel.setReasonToTakeMedicine(cursor.getString(9));
            medicineModel.setPrescribedBy(cursor.getString(10));
            medicineModel.setStartDate(cursor.getString(11));
            medicineModel.setEndDate(cursor.getString(12));



            //all notes list added
            allRecords.add(medicineModel);

         }while (cursor.moveToNext());
      }
      db.close();
      cursor.close();
      return allRecords;
   }

   public List<updateRecordMedicineModel> searchByMedicineName(String name){

      SQLiteDatabase db = getReadableDatabase();
      SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

      String[] sqlSelect = {COL_ID,COL_NAME,COl_TYPE,COL_CONSUME,COL_SCHEDULE,COL_MORNING,COL_NOON
              ,COL_NIGHT,COL_OTHERS,COL_REASON_TAKE_MEDICINE,COL_PRESCRIBED_BY,COL_STARTDATE,COL_ENDDATE};
      String tableName = DATABASE_TABLE;

      queryBuilder.setTables(tableName);

      //select from title with like query

      Cursor cursor = queryBuilder.query(db,sqlSelect,"Medicine_Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

      List<updateRecordMedicineModel> result = new ArrayList<>();

      if (cursor.moveToFirst())
      {
         do{
            updateRecordMedicineModel medicineModel = new updateRecordMedicineModel();

            medicineModel.setId(cursor.getLong(0));
            medicineModel.setMedicineName(cursor.getString(1));
            medicineModel.setMedicineType(cursor.getString(2));
            medicineModel.setTimes(cursor.getFloat(3));
            medicineModel.setDays(cursor.getInt(4));
            medicineModel.setMorning(cursor.getString(5));
            medicineModel.setNoon(cursor.getString(6));
            medicineModel.setNight(cursor.getString(7));
            medicineModel.setOthers(cursor.getString(8));
            medicineModel.setReasonToTakeMedicine(cursor.getString(9));
            medicineModel.setPrescribedBy(cursor.getString(10));
            medicineModel.setStartDate(cursor.getString(11));
            medicineModel.setEndDate(cursor.getString(12));

            result.add(medicineModel);

         }while (cursor.moveToNext());
      }

      return result;


   }


   public List<String> getMedicineName(){


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
