package com.example.medabinfinal.updateRecord.personalRecord.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import android.widget.Toast;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.List;

public class PersonalRecordDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Personal_Record.db";
    private static final String DATABASE_TABLE_HEIGHT = "height_table";
    private static final String DATABASE_TABLE_WEIGHT = "weight_table";
    private static final String DATABASE_TABLE_BP = "bp_table";
    private static final String DATABASE_TABLE_GLUCOSE = "glucose_table";
    private static final String DATABASE_TABLE_FOOD = "food_table";
    private static final String DATABASE_TABLE_DISEASE = "disease_table";
    private static final String DATABASE_TABLE_DAILY_TIME = "daily_time_table";

    private static  String COL_ID = "Id";
    private static final String COL_DATE = "Date_dd_mm_yyyy";
    private static final String COL_TIME = "Time";
    private static final String COl_FEET = "Feet";
    private static final String COl_INCH = "Inch";


    private static final String COL_BP_HIGH = "Bp_High";
    private static final String COL_BP_LOW = "Bp_Low";


    private static final String COL_CARBOHYDRATE = "Carbohydrate_percentage";
    private static final String COL_PROTEIN = "Protein_percentage";
    private static final String COL_FAT = "Fat_percentage";
    private static final String COL_VITAMIN = "Vitamin_percentage";
    private static final String COL_WATER = "Water_percentage";
    private static final String COL_MINERAL = "Mineral_percentage";
    private static final String COL_UNKNOWN_FOOD_TYPE = "Unknown_food_type_percentage";

    private static final String  COL_WEIGHT = "Weight_kg";

    private static final String COL_GLUCOSE_BEFORE_FAST = "Glucose_before_fast";
    private static final String COL_GLUCOSE_AFTER_FAST = "Glucose_after_fast";

    private static final String COL_FEVER = "Feel_fever";
    private static final String COL_STOMACH_PAIN = "Stomach_pain";
    private static final String COL_BODY_PAIN = "Body_pain";
    private static final String COL_HEAD_PAIN = "Head_pain";

    private static final String COL_SLEEP_TIME = "Sleep_time";
    private static final String COL_READ_TIME = "Read_time";
    private static final String COL_WORK_TIME = "Working_time";
    private static final String COL_EXERCISE_TIME = "Exercise_time";
    private static final String COL_OTHERS_TIME = "Others_time";
    private static final String COL_UNKNOWN_TIME = "Unknown_time";


    private Context context;

    public PersonalRecordDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table height
        String createTableHeight = "CREATE TABLE "+ DATABASE_TABLE_HEIGHT + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COl_FEET + " INTEGER, " + COl_INCH + " INTEGER)";

        //create table weight
        String createTableWeight = "CREATE TABLE "+ DATABASE_TABLE_WEIGHT + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_WEIGHT + " INTEGER)";

        //create table bp
        String createTableBP = "CREATE TABLE "+ DATABASE_TABLE_BP + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_TIME + " TEXT, " + COL_BP_HIGH + " INTEGER, " + COL_BP_LOW + " INTEGER)";

        //create table glucose
        String createTableGlucose = "CREATE TABLE "+ DATABASE_TABLE_GLUCOSE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_GLUCOSE_BEFORE_FAST + " FLOAT, " + COL_GLUCOSE_AFTER_FAST + " FLOAT)";

        //create table food
        String createTableFood = "CREATE TABLE "+ DATABASE_TABLE_FOOD + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_CARBOHYDRATE + " INTEGER, " + COL_PROTEIN + " INTEGER, " + COL_FAT + " INTEGER, " + COL_VITAMIN + " INTEGER, " + COL_MINERAL + " INTEGER, " + COL_WATER + " INTEGER, " + COL_UNKNOWN_FOOD_TYPE + " INTEEGR)";

        //create table disease
        String createTableDisease = "CREATE TABLE "+ DATABASE_TABLE_DISEASE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_TIME + " TEXT, " + COL_FEVER + " TEXT, " + COL_STOMACH_PAIN + " TEXT, " + COL_BODY_PAIN + " TEXT, " + COL_HEAD_PAIN + " TEXT)";

        //create table daily time
        String createTableDailyTime = "CREATE TABLE "+ DATABASE_TABLE_DAILY_TIME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DATE + " TEXT, " + COL_SLEEP_TIME + " INTEGER, " + COL_READ_TIME + " INTEGER, " + COL_WORK_TIME + " INTEGER, " + COL_EXERCISE_TIME + " INTEGER, " + COL_OTHERS_TIME + " INTEGER, " + COL_UNKNOWN_TIME + " INTEGER)";


        db.execSQL(createTableHeight);
        db.execSQL(createTableWeight);
        db.execSQL(createTableBP);
        db.execSQL(createTableGlucose);
        db.execSQL(createTableFood);
        db.execSQL(createTableDisease);
        db.execSQL(createTableDailyTime);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_HEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_WEIGHT);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_BP);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_GLUCOSE);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_DISEASE);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_DAILY_TIME);

        onCreate(db);

    }

    public long addDataHeight(HeightModel heightModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DATE,heightModel.getDate());
        contentValues.put(COl_FEET,heightModel.getFeet());
        contentValues.put(COl_INCH,heightModel.getInch());

        long ID = db.insert(DATABASE_TABLE_HEIGHT,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }


    public List<HeightModel> getAllHeights(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<HeightModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_HEIGHT+" ORDER BY "+COL_DATE+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                HeightModel heightModel = new HeightModel();

                heightModel.setId(cursor.getLong(0));
                heightModel.setDate(cursor.getString(1));
                heightModel.setFeet(cursor.getInt(2));
                heightModel.setInch(cursor.getInt(3));

                allRecords.add(heightModel);
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in database", Toast.LENGTH_SHORT).show();
        }

        db.close();
        cursor.close();
        return allRecords;



    }

    public List<HeightModel> searchHeightByDate(String date)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_DATE,COl_FEET,COl_INCH};
        String tableName = DATABASE_TABLE_HEIGHT;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Date_dd_mm_yyyy LIKE ?",new String[]{"%"+date+"%"},null,null,null);

        List<HeightModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                HeightModel heightModel = new HeightModel();
                heightModel.setId(cursor.getLong(0));
                heightModel.setDate(cursor.getString(1));
                heightModel.setFeet(cursor.getInt(2));
                heightModel.setInch(cursor.getInt(3));

                allRecords.add(heightModel);

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


    public List<String> getHeightDates()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_DATE};
        String tablename = DATABASE_TABLE_HEIGHT;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_DATE)));

            }while (cursor.moveToNext());
        }
        return result;




    }



    public long addDataWeight(WeightModel weightModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DATE,weightModel.getDate());
        contentValues.put(COL_WEIGHT,weightModel.getWeightKg());


        long ID = db.insert(DATABASE_TABLE_WEIGHT,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }


    public List<WeightModel> getAllWeights(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<WeightModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_WEIGHT+" ORDER BY "+COL_DATE+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                WeightModel weightModel = new WeightModel();

                weightModel.setId(cursor.getLong(0));
                weightModel.setDate(cursor.getString(1));
                weightModel.setWeightKg(cursor.getInt(2));


                allRecords.add(weightModel);
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in database", Toast.LENGTH_SHORT).show();
        }

        db.close();
        cursor.close();
        return allRecords;



    }

    public List<WeightModel> searchByWeight(String weight)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_DATE,COL_WEIGHT};
        String tableName = DATABASE_TABLE_WEIGHT;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Weight_kg LIKE ?",new String[]{"%"+weight+"%"},null,null,null);

        List<WeightModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                WeightModel weightModel = new WeightModel();

                weightModel.setId(cursor.getLong(0));
                weightModel.setDate(cursor.getString(1));
                weightModel.setWeightKg(cursor.getInt(2));


                allRecords.add(weightModel);

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

    public List<String> getWeights()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_WEIGHT};
        String tablename = DATABASE_TABLE_WEIGHT;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_WEIGHT)));

            }while (cursor.moveToNext());
        }
        return result;




    }


    public long addDataBp(BpModel bpModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DATE,bpModel.getDate());
        contentValues.put(COL_TIME,bpModel.getTime());
        contentValues.put(COL_BP_HIGH,bpModel.getHigh());
        contentValues.put(COL_BP_LOW,bpModel.getLow());

        long ID = db.insert(DATABASE_TABLE_BP,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }

    public List<BpModel> getAllBP(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<BpModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_BP+" ORDER BY "+COL_DATE+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                BpModel bpModel = new BpModel();

                bpModel.setId(cursor.getLong(0));
                bpModel.setDate(cursor.getString(1));
                bpModel.setTime(cursor.getString(2));
                bpModel.setHigh(cursor.getInt(3));
                bpModel.setLow(cursor.getInt(4));


                allRecords.add(bpModel);
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in database", Toast.LENGTH_SHORT).show();
        }

        db.close();
        cursor.close();
        return allRecords;



    }

    public List<BpModel> searchByTimeBp(String time)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_DATE,COL_TIME,COL_BP_HIGH,COL_BP_LOW};
        String tableName = DATABASE_TABLE_BP;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Time LIKE ?",new String[]{"%"+time+"%"},null,null,null);

        List<BpModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                BpModel bpModel = new BpModel();

                bpModel.setId(cursor.getLong(0));
                bpModel.setDate(cursor.getString(1));
                bpModel.setTime(cursor.getString(2));
                bpModel.setHigh(cursor.getInt(3));
                bpModel.setLow(cursor.getInt(4));


                allRecords.add(bpModel);
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

    public List<String> getTimesBP()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME};
        String tablename = DATABASE_TABLE_BP;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_TIME)));

            }while (cursor.moveToNext());
        }
        return result;




    }




    public long addDataGlucose(GlucoseModel glucoseModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DATE,glucoseModel.getDate());
        contentValues.put(COL_GLUCOSE_BEFORE_FAST,glucoseModel.getBeforeFast());
        contentValues.put(COL_GLUCOSE_AFTER_FAST,glucoseModel.getAfterFast());

        long ID = db.insert(DATABASE_TABLE_GLUCOSE,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }



    public List<GlucoseModel> getAllGlucose(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<GlucoseModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_GLUCOSE+" ORDER BY "+COL_DATE+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                GlucoseModel glucoseModel = new GlucoseModel();

                glucoseModel.setId(cursor.getLong(0));
                glucoseModel.setDate(cursor.getString(1));
                glucoseModel.setBeforeFast(cursor.getFloat(2));
                glucoseModel.setAfterFast(cursor.getFloat(3));



                allRecords.add(glucoseModel);
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in database", Toast.LENGTH_SHORT).show();
        }

        db.close();
        cursor.close();
        return allRecords;



    }

    public List<GlucoseModel> searchByDateGlucose(String date)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_DATE,COL_GLUCOSE_BEFORE_FAST,COL_GLUCOSE_AFTER_FAST};
        String tableName = DATABASE_TABLE_GLUCOSE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Date_dd_mm_yyyy LIKE ?",new String[]{"%"+date+"%"},null,null,null);

        List<GlucoseModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                GlucoseModel glucoseModel = new GlucoseModel();

                glucoseModel.setId(cursor.getLong(0));
                glucoseModel.setDate(cursor.getString(1));
                glucoseModel.setBeforeFast(cursor.getFloat(2));
                glucoseModel.setAfterFast(cursor.getFloat(3));



                allRecords.add(glucoseModel);

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

    public List<String> getGlucoseAllDates()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_DATE};
        String tablename = DATABASE_TABLE_GLUCOSE;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_DATE)));

            }while (cursor.moveToNext());
        }
        return result;




    }



    public long addDataFood(FoodModel foodModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DATE,foodModel.getDate());
        contentValues.put(COL_CARBOHYDRATE,foodModel.getCarbohydratesPercentage());
        contentValues.put(COL_PROTEIN,foodModel.getProteinPercentage());
        contentValues.put(COL_FAT,foodModel.getFatPercetage());
        contentValues.put(COL_VITAMIN,foodModel.getVitaminPercentage());
        contentValues.put(COL_MINERAL,foodModel.getMineralPercentage());
        contentValues.put(COL_WATER,foodModel.getWaterPercentage());
        contentValues.put(COL_UNKNOWN_FOOD_TYPE,foodModel.getUnknownPercentage());

        long ID = db.insert(DATABASE_TABLE_FOOD,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }

    public List<FoodModel> getAllFoods(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<FoodModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_FOOD+" ORDER BY "+COL_DATE+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                FoodModel foodModel = new FoodModel();

                foodModel.setId(cursor.getLong(0));
                foodModel.setDate(cursor.getString(1));
                foodModel.setCarbohydratesPercentage(cursor.getInt(2));
                foodModel.setProteinPercentage(cursor.getInt(3));
                foodModel.setFatPercetage(cursor.getInt(4));
                foodModel.setVitaminPercentage(cursor.getInt(5));
                foodModel.setMineralPercentage(cursor.getInt(6));
                foodModel.setWaterPercentage(cursor.getInt(7));
                foodModel.setUnknownPercentage(cursor.getInt(8));



                allRecords.add(foodModel);
            }while (cursor.moveToNext());
        }
        else
        {
            Toast.makeText(context, "No value in database", Toast.LENGTH_SHORT).show();
        }

        db.close();
        cursor.close();
        return allRecords;



    }

    public List<FoodModel> searchByDateFood(String food)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_DATE,COL_CARBOHYDRATE,COL_PROTEIN,COL_FAT,COL_VITAMIN,COL_MINERAL,COL_WATER,COL_UNKNOWN_FOOD_TYPE};
        String tableName = DATABASE_TABLE_FOOD;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Date_dd_mm_yyyy LIKE ?",new String[]{"%"+food+"%"},null,null,null);

        List<FoodModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                FoodModel foodModel = new FoodModel();

                foodModel.setId(cursor.getLong(0));
                foodModel.setDate(cursor.getString(1));
                foodModel.setCarbohydratesPercentage(cursor.getInt(2));
                foodModel.setProteinPercentage(cursor.getInt(3));
                foodModel.setFatPercetage(cursor.getInt(4));
                foodModel.setVitaminPercentage(cursor.getInt(5));
                foodModel.setMineralPercentage(cursor.getInt(6));
                foodModel.setWaterPercentage(cursor.getInt(7));
                foodModel.setUnknownPercentage(cursor.getInt(8));



                allRecords.add(foodModel);

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

    public List<String> getAllFoodDates()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_DATE};
        String tablename = DATABASE_TABLE_FOOD;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_DATE)));

            }while (cursor.moveToNext());
        }
        return result;




    }



    public long addDataDisease(DiceaseModel diceaseModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DATE,diceaseModel.getDate());
        contentValues.put(COL_TIME,diceaseModel.getTime());
        contentValues.put(COL_FEVER,diceaseModel.getFeelFever());
        contentValues.put(COL_STOMACH_PAIN,diceaseModel.getFeelStomachPain());
        contentValues.put(COL_BODY_PAIN,diceaseModel.getFeelBodyPain());
        contentValues.put(COL_HEAD_PAIN,diceaseModel.getFeelHeadPain());

        long ID = db.insert(DATABASE_TABLE_DISEASE,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }

    public long addDataDailyTime(DailyTimeModel dailyTimeModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_DATE,dailyTimeModel.getDate());
        contentValues.put(COL_SLEEP_TIME,dailyTimeModel.getSleepTime());
        contentValues.put(COL_READ_TIME,dailyTimeModel.getReadTime());
        contentValues.put(COL_WORK_TIME,dailyTimeModel.getWorkingTime());
        contentValues.put(COL_EXERCISE_TIME,dailyTimeModel.getExerciseTime());
        contentValues.put(COL_OTHERS_TIME,dailyTimeModel.getOthers());
        contentValues.put(COL_UNKNOWN_TIME,dailyTimeModel.getUnknown());

        long ID = db.insert(DATABASE_TABLE_DAILY_TIME,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }
}
