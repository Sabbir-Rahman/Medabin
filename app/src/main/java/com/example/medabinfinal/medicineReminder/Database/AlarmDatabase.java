package com.example.medabinfinal.medicineReminder.Database;

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

public class AlarmDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "Alarm.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "alarm";
    private static final String COL_ID = "ID";
    private static final String COL_MEDICINE_NAME = "Medicine_name";
    private static final String COL_COUNT = "Medicine_count";
    private static final String COL_REMAIN = "Medicine_remain";
    private static final String COL_TIME_1 = "time_1";
    private static final String COL_TIME_2 = "time_2";
    private static final String COL_TIME_3 = "time_3";
    private static final String IS_TAKEN = "Currently_take_this_medicine";
    private static final String COL_TIME_1_HOUR = "time_1_hour";
    private static final String COL_TIME_1_MINUTE = "time_1_minute";
    private static final String COL_TIME_2_HOUR = "time_2_hour";
    private static final String COL_TIME_2_MINUTE = "time_2_minute";
    private static final String COL_TIME_3_HOUR = "time_3_hour";
    private static final String COL_TIME_3_MINUTE = "time_3_minute";


    private Context context;





    public AlarmDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table
        String createTable = "CREATE TABLE "+ TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_MEDICINE_NAME + " TEXT, " + COL_COUNT + " INTEGER, " + COL_REMAIN + " INTEGER, " + COL_TIME_1 + " TEXT, " + IS_TAKEN + " TEXT, "
                + COL_TIME_2 + " TEXT, " + COL_TIME_3 + " TEXT, " + COL_TIME_1_HOUR + " INTEGER, " + COL_TIME_1_MINUTE + " INTEGER, " + COL_TIME_2_HOUR + " INTEGER, " + COL_TIME_2_MINUTE + " INTEGER, "
                + COL_TIME_3_HOUR + " INTEGER, " + COL_TIME_3_MINUTE + " INTEGER)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }

    public long addData(AlarmModel alarmModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //getting the data
        contentValues.put(COL_MEDICINE_NAME,alarmModel.getMedicineName());
        contentValues.put(COL_COUNT,alarmModel.getCount());
        contentValues.put(COL_REMAIN,alarmModel.getRemain());
        contentValues.put(COL_TIME_1,alarmModel.getTime1());
        contentValues.put(COL_TIME_2,alarmModel.getTime2());
        contentValues.put(COL_TIME_3,alarmModel.getTime3());
        contentValues.put(IS_TAKEN,alarmModel.getIsAlarm());
        contentValues.put(COL_TIME_1_HOUR,alarmModel.getTime1_hour());
        contentValues.put(COL_TIME_1_MINUTE,alarmModel.getTime1_minute());
        contentValues.put(COL_TIME_2_HOUR,alarmModel.getTime2_hour());
        contentValues.put(COL_TIME_2_MINUTE,alarmModel.getTime2_minute());
        contentValues.put(COL_TIME_3_HOUR,alarmModel.getTime3_hour());
        contentValues.put(COL_TIME_3_MINUTE,alarmModel.getTime3_minute());

        long ID = db.insert(TABLE_NAME,null,contentValues);
        //if any error return -1

        if(ID == -1)
        {
            Toast.makeText(context, "Someyhing error happen", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Data is inserted", Toast.LENGTH_SHORT).show();
        }

        Log.d("Inserted","Id -> "+ID);
        db.close();
        return ID;
    }

    public List<Integer> getTime1Hour()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_1_HOUR};
        String tablename = TABLE_NAME;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<Integer> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getInt(cursor.getColumnIndex(COL_TIME_1_HOUR)));

            }while (cursor.moveToNext());
        }
        return result;




    }

    public List<Integer> getTime1Minutes()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_1_MINUTE};
        String tablename = TABLE_NAME;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<Integer> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getInt(cursor.getColumnIndex(COL_TIME_1_MINUTE)));

            }while (cursor.moveToNext());
        }
        return result;




    }

    public List<Integer> getTime2Hour()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_2_HOUR};
        String tablename = TABLE_NAME;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<Integer> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getInt(cursor.getColumnIndex(COL_TIME_2_HOUR)));

            }while (cursor.moveToNext());
        }
        return result;




    }

    public List<Integer> getTime2Minute()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_2_MINUTE};
        String tablename = TABLE_NAME;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<Integer> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getInt(cursor.getColumnIndex(COL_TIME_2_MINUTE)));

            }while (cursor.moveToNext());
        }
        return result;




    }

    public List<Integer> getTime3Hour()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_3_HOUR};
        String tablename = TABLE_NAME;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<Integer> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getInt(cursor.getColumnIndex(COL_TIME_3_HOUR)));

            }while (cursor.moveToNext());
        }
        return result;




    }

    public List<Integer> getTime3Minute()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_3_MINUTE};
        String tablename = TABLE_NAME;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<Integer> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getInt(cursor.getColumnIndex(COL_TIME_3_MINUTE)));

            }while (cursor.moveToNext());
        }
        return result;




    }

    public String getAlarmTitle(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_MEDICINE_NAME};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        String result = null;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getString(cursor.getColumnIndex(COL_MEDICINE_NAME));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public String getOneMedicneName(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_MEDICINE_NAME};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        String result = null;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getString(cursor.getColumnIndex(COL_MEDICINE_NAME));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public Integer getOneMedicineCount(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_COUNT};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =  cursor.getInt(cursor.getColumnIndex(COL_COUNT));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public Integer getOneMedicineRemain(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_REMAIN};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_REMAIN));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }


    public String getOneMedicneTime1(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_1};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        String result = null;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getString(cursor.getColumnIndex(COL_TIME_1));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public String getOneMedicneTime2(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_2};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        String result = null;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getString(cursor.getColumnIndex(COL_TIME_2));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }


    public String getOneMedicneTime3(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_3};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        String result = null;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getString(cursor.getColumnIndex(COL_TIME_3));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }


    public Integer getOneMedicineTime1Hour(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_1_HOUR};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_TIME_1_HOUR));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }


    public Integer getOneMedicineTime1Minute(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_1_MINUTE};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_TIME_1_MINUTE));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public Integer getOneMedicineTime2Hour(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_2_HOUR};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_TIME_2_HOUR));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public Integer getOneMedicineTime2Minute(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_2_MINUTE};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_TIME_2_MINUTE));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public Integer getOneMedicineTime3Hour(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_3_HOUR};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_TIME_3_HOUR));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public Integer getOneMedicineTime3Minute(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME_3_MINUTE};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        int result = 0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_TIME_3_MINUTE));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public int editAlarm(AlarmModel alarmModel,int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       // Log.d("Edited","Edited title: ->"+noteModel.getTitle()+"\n ID ->"+noteModel.getId());
        contentValues.put(COL_MEDICINE_NAME,alarmModel.getMedicineName());
        contentValues.put(COL_COUNT,alarmModel.getCount());
        contentValues.put(COL_REMAIN,alarmModel.getRemain());
        contentValues.put(COL_TIME_1,alarmModel.getTime1());
        contentValues.put(COL_TIME_2,alarmModel.getTime2());
        contentValues.put(COL_TIME_3,alarmModel.getTime3());
        contentValues.put(IS_TAKEN,alarmModel.getIsAlarm());
        contentValues.put(COL_TIME_1_HOUR,alarmModel.getTime1_hour());
        contentValues.put(COL_TIME_1_MINUTE,alarmModel.getTime1_minute());
        contentValues.put(COL_TIME_2_HOUR,alarmModel.getTime2_hour());
        contentValues.put(COL_TIME_2_MINUTE,alarmModel.getTime2_minute());
        contentValues.put(COL_TIME_3_HOUR,alarmModel.getTime3_hour());
        contentValues.put(COL_TIME_3_MINUTE,alarmModel.getTime3_minute());


        return db.update(TABLE_NAME,contentValues,COL_ID+"=?",new String[]{String.valueOf(id)});

    }

    public List<String> getAlarmIsOn()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {IS_TAKEN};
        String tablename = TABLE_NAME;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(IS_TAKEN)));

            }while (cursor.moveToNext());
        }
        return result;




    }

    public String getOneAlarmIsOne(Integer id)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {IS_TAKEN};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"ID LIKE ?",new String[]{String.valueOf(id)},null,null,null);

        String result = null;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getString(cursor.getColumnIndex(IS_TAKEN));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }

    public int getAlarmId(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID};
        String tableName = TABLE_NAME;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Medicine_name LIKE ?",new String[]{String.valueOf(name)},null,null,null);

        int result =0;

        if (cursor.moveToFirst())
        {
            do{
                result =   cursor.getInt(cursor.getColumnIndex(COL_ID));
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in Database", Toast.LENGTH_SHORT).show();
        }
        db.close();
        cursor.close();
        return result;




    }




}
