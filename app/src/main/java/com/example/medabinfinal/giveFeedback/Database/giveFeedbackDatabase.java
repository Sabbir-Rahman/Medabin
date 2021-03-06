package com.example.medabinfinal.giveFeedback.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;

import java.util.ArrayList;
import java.util.List;

public class giveFeedbackDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Feedback_database.db";
    public static final int DATABASE_VERSION = 1 ;
    public static final String DATABASE_TABLE_DOCTOR = "doctor_feedback";
    public static final String DATABASE_TABLE_HOSPITAL = "hospital_feedback";
    public static final String DATABASE_TABLE_MEDICINE = "medicine_feedback";
    public static final String DATABASE_TABLE_PHARMACY = "pharmacy_feedback";
    public static final String COL_ID = "Id";


    public static final String COL_NAME = "Name";
    public static final String COL_BEHAVIOUR = "Behaviour";
    public static final String COL_PRESCRIPTION = "Prescription";
    public static final String COL_DIAGNOSIS = "Diagnosis_ability";
    public static final String COL_TOTAL = "Total";


    public static final String COL_EXPENSE = "Hospital_expense";
    public static final String COL_INFRASTRUCTURE = "Hospital_infrastructure";
    public static final String COL_TESTING_QUALITY = "Hospital_testing";

    public static final String COL_FEES = "Fees_rating";
    public static final String COL_HOSPITAL_SERVICE = "Hospital_service";



    public static final String COL_PRICE_MEDICINE = "Medicine_pricing";
    public static final String COL_PACKAGING = "Medicine_packaging";
    public static final String COL_EFFECTIVENESS = "Hospital_effectiveness";
    public static final String COL_SIDE_EFFECTS = "medicine_side_effects";

    public static final String COL_SERVICE_PHARMACY = "Pharmacy_service";
    public static final String COL_SERVICE_HOSPITAL = "Hospital_service";
    public static final String COL_PRICE_PHARMACY = "Pharmacy_pricing";
    public static final String COL_WELL_ORGANISED = "Well_organised";
    public static final String COL_MEDICINE_AVAILABILITY = "Medicine_availability";





    private Context context;

    public giveFeedbackDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //create table doctor
        String createTableDoctor = "CREATE TABLE "+ DATABASE_TABLE_DOCTOR + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_BEHAVIOUR + " FLOAT, " + COL_FEES + " FLOAT, " + COL_PRESCRIPTION + " FLOAT, " + COL_DIAGNOSIS + " FLOAT, " + COL_TOTAL + " FLOAT)";

        //create table hospital
        String createTableHospital = "CREATE TABLE "+ DATABASE_TABLE_HOSPITAL + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_HOSPITAL_SERVICE + " FLOAT, " + COL_EXPENSE + " FLOAT, " + COL_INFRASTRUCTURE + " FLOAT, " + COL_TESTING_QUALITY + " FLOAT, " + COL_TOTAL + " FLOAT)";

        //create table medicine
        String createTableMedicine = "CREATE TABLE "+ DATABASE_TABLE_MEDICINE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_PRICE_MEDICINE + " FLOAT, " + COL_PACKAGING + " FLOAT, " + COL_EFFECTIVENESS + " FLOAT, " + COL_SIDE_EFFECTS + " FLOAT, " + COL_TOTAL + " FLOAT)";

        //create table pharmacy
        String createTablePharmacy = "CREATE TABLE "+ DATABASE_TABLE_PHARMACY + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_SERVICE_PHARMACY + " FLOAT, " + COL_PRICE_PHARMACY + " FLOAT, " + COL_WELL_ORGANISED + " FLOAT, " + COL_MEDICINE_AVAILABILITY + " FLOAT, " + COL_TOTAL + " FLOAT)";


        db.execSQL(createTableDoctor);
        db.execSQL(createTableHospital);
        db.execSQL(createTableMedicine);
        db.execSQL(createTablePharmacy);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion >= newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_DOCTOR);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_HOSPITAL);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_MEDICINE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PHARMACY);

            onCreate(db);
        }

    }

    public long addDataDoctor(RatingDoctorModel ratingDoctorModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_NAME,ratingDoctorModel.getName());
        contentValues.put(COL_BEHAVIOUR,ratingDoctorModel.getBehaviour());
        contentValues.put(COL_FEES,ratingDoctorModel.getFee());
        contentValues.put(COL_PRESCRIPTION,ratingDoctorModel.getPrescription());
        contentValues.put(COL_DIAGNOSIS,ratingDoctorModel.getDiagnosis());
        contentValues.put(COL_TOTAL,ratingDoctorModel.getTotal());

        long ID = db.insert(DATABASE_TABLE_DOCTOR,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }

    public long addDataHospital(RatingHospitalModel ratingHospitalModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_NAME, ratingHospitalModel.getName());
        contentValues.put(COL_SERVICE_HOSPITAL, ratingHospitalModel.getService());
        contentValues.put(COL_EXPENSE, ratingHospitalModel.getExpense());
        contentValues.put(COL_INFRASTRUCTURE, ratingHospitalModel.getInfrastructure());
        contentValues.put(COL_TESTING_QUALITY, ratingHospitalModel.getTestingQuality());
        contentValues.put(COL_TOTAL, ratingHospitalModel.getTotal());

        long ID = db.insert(DATABASE_TABLE_HOSPITAL,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }


    public List<RatingDoctorModel> getAllDoctorsRating(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<RatingDoctorModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_DOCTOR+" ORDER BY "+COL_TOTAL+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                RatingDoctorModel ratingDoctorModel = new RatingDoctorModel();

                ratingDoctorModel.setId(cursor.getInt(0));
                ratingDoctorModel.setName(cursor.getString(1));
                ratingDoctorModel.setBehaviour(cursor.getFloat(2));
                ratingDoctorModel.setFee(cursor.getFloat(3));
                ratingDoctorModel.setPrescription(cursor.getFloat(4));
                ratingDoctorModel.setDiagnosis(cursor.getFloat(5));
                ratingDoctorModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingDoctorModel);
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

    public List<RatingDoctorModel> searchDoctorRatingByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COL_BEHAVIOUR,COL_FEES,COL_PRESCRIPTION,COL_DIAGNOSIS,COL_TOTAL};
        String tableName = DATABASE_TABLE_DOCTOR;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<RatingDoctorModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                RatingDoctorModel ratingDoctorModel = new RatingDoctorModel();

                ratingDoctorModel.setId(cursor.getInt(0));
                ratingDoctorModel.setName(cursor.getString(1));
                ratingDoctorModel.setBehaviour(cursor.getFloat(2));
                ratingDoctorModel.setFee(cursor.getFloat(3));
                ratingDoctorModel.setPrescription(cursor.getFloat(4));
                ratingDoctorModel.setDiagnosis(cursor.getFloat(5));
                ratingDoctorModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingDoctorModel);

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

    public List<String> getDoctorRatingsNames()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_NAME};
        String tablename = DATABASE_TABLE_DOCTOR;

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



    public List<RatingHospitalModel> getAllHospitalsRating(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<RatingHospitalModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_HOSPITAL+" ORDER BY "+COL_TOTAL+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                RatingHospitalModel ratingHospitalModel = new RatingHospitalModel();

                ratingHospitalModel.setId(cursor.getInt(0));
                ratingHospitalModel.setName(cursor.getString(1));
                ratingHospitalModel.setService(cursor.getFloat(2));
                ratingHospitalModel.setExpense(cursor.getFloat(3));
                ratingHospitalModel.setInfrastructure(cursor.getFloat(4));
                ratingHospitalModel.setTestingQuality(cursor.getFloat(5));
                ratingHospitalModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingHospitalModel);
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

    public List<RatingHospitalModel> searchHospitalRatingByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COL_SERVICE_HOSPITAL,COL_EXPENSE,COL_INFRASTRUCTURE,COL_TESTING_QUALITY,COL_TOTAL};
        String tableName = DATABASE_TABLE_HOSPITAL;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<RatingHospitalModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                RatingHospitalModel ratingHospitalModel = new RatingHospitalModel();

                ratingHospitalModel.setId(cursor.getInt(0));
                ratingHospitalModel.setName(cursor.getString(1));
                ratingHospitalModel.setService(cursor.getFloat(2));
                ratingHospitalModel.setExpense(cursor.getFloat(3));
                ratingHospitalModel.setInfrastructure(cursor.getFloat(4));
                ratingHospitalModel.setTestingQuality(cursor.getFloat(5));
                ratingHospitalModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingHospitalModel);

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

    public List<String> getHospitalRatingsNames()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_NAME};
        String tablename = DATABASE_TABLE_HOSPITAL;

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


    public List<RatingMedicineModel> getAllMedicineRating(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<RatingMedicineModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_MEDICINE+" ORDER BY "+COL_TOTAL+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                RatingMedicineModel ratingMedicineModel = new RatingMedicineModel();

                ratingMedicineModel.setId(cursor.getInt(0));
                ratingMedicineModel.setName(cursor.getString(1));
                ratingMedicineModel.setPrice(cursor.getFloat(2));
                ratingMedicineModel.setPackaging(cursor.getFloat(3));
                ratingMedicineModel.setEffectiveness(cursor.getFloat(4));
                ratingMedicineModel.setTotal(cursor.getFloat(5));
                ratingMedicineModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingMedicineModel);
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

    public List<RatingMedicineModel> searchMedicineRatingByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COL_PRICE_MEDICINE,COL_PACKAGING,COL_EFFECTIVENESS,COL_SIDE_EFFECTS,COL_TOTAL};
        String tableName = DATABASE_TABLE_MEDICINE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<RatingMedicineModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                RatingMedicineModel ratingMedicineModel = new RatingMedicineModel();

                ratingMedicineModel.setId(cursor.getInt(0));
                ratingMedicineModel.setName(cursor.getString(1));
                ratingMedicineModel.setPrice(cursor.getFloat(2));
                ratingMedicineModel.setPackaging(cursor.getFloat(3));
                ratingMedicineModel.setEffectiveness(cursor.getFloat(4));
                ratingMedicineModel.setTotal(cursor.getFloat(5));
                ratingMedicineModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingMedicineModel);


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

    public List<String> getMedicineRatingsNames()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_NAME};
        String tablename = DATABASE_TABLE_MEDICINE;

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



    public List<RatingPharmacyModel> getAllpharmacyRating(){

        SQLiteDatabase db = this.getReadableDatabase();
        List<RatingPharmacyModel> allRecords = new ArrayList<>();

        //select from database

        String query = "SELECT * FROM "+DATABASE_TABLE_PHARMACY+" ORDER BY "+COL_TOTAL+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                RatingPharmacyModel ratingPharmacyModel = new RatingPharmacyModel();

                ratingPharmacyModel.setId(cursor.getInt(0));
                ratingPharmacyModel.setName(cursor.getString(1));
                ratingPharmacyModel.setService(cursor.getFloat(2));
                ratingPharmacyModel.setPricing(cursor.getFloat(3));
                ratingPharmacyModel.setWellOrganised(cursor.getFloat(4));
                ratingPharmacyModel.setMedicineAvailability(cursor.getFloat(5));
                ratingPharmacyModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingPharmacyModel);
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

    public List<RatingPharmacyModel> searchPharmacyRatingByName(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_NAME,COL_SERVICE_PHARMACY,COL_PRICE_PHARMACY,COL_WELL_ORGANISED,COL_MEDICINE_AVAILABILITY,COL_TOTAL};
        String tableName = DATABASE_TABLE_PHARMACY;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"Name LIKE ?",new String[]{"%"+name+"%"},null,null,null);

        List<RatingPharmacyModel> allRecords = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                RatingPharmacyModel ratingPharmacyModel = new RatingPharmacyModel();

                ratingPharmacyModel.setId(cursor.getInt(0));
                ratingPharmacyModel.setName(cursor.getString(1));
                ratingPharmacyModel.setService(cursor.getFloat(2));
                ratingPharmacyModel.setPricing(cursor.getFloat(3));
                ratingPharmacyModel.setWellOrganised(cursor.getFloat(4));
                ratingPharmacyModel.setMedicineAvailability(cursor.getFloat(5));
                ratingPharmacyModel.setTotal(cursor.getFloat(6));

                allRecords.add(ratingPharmacyModel);

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

    public List<String> getPharmacyRatingNames()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_NAME};
        String tablename = DATABASE_TABLE_PHARMACY;

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






    public long addDataMedicine(RatingMedicineModel ratingMedicineModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_NAME,ratingMedicineModel.getName());
        contentValues.put(COL_PRICE_MEDICINE,ratingMedicineModel.getPrice());
        contentValues.put(COL_PACKAGING,ratingMedicineModel.getPackaging());
        contentValues.put(COL_EFFECTIVENESS,ratingMedicineModel.getEffectiveness());
        contentValues.put(COL_SIDE_EFFECTS,ratingMedicineModel.getSide());
        contentValues.put(COL_TOTAL,ratingMedicineModel.getTotal());

        long ID = db.insert(DATABASE_TABLE_MEDICINE,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }

    public long addDataPharmacy(RatingPharmacyModel pharmacyModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_NAME,pharmacyModel.getName());
        contentValues.put(COL_SERVICE_PHARMACY,pharmacyModel.getService());
        contentValues.put(COL_PRICE_PHARMACY,pharmacyModel.getPricing());
        contentValues.put(COL_WELL_ORGANISED,pharmacyModel.getWellOrganised());
        contentValues.put(COL_MEDICINE_AVAILABILITY,pharmacyModel.getMedicineAvailability());
        contentValues.put(COL_TOTAL,pharmacyModel.getTotal());

        long ID = db.insert(DATABASE_TABLE_PHARMACY,null,contentValues);

        if(ID==-1)
        {
            Toast.makeText(context, "Something error happen", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Data inserted", Toast.LENGTH_SHORT).show();

        return ID;
    }

}
