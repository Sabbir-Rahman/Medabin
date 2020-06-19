package com.example.medabinfinal.loginRegister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String ID = "ID";
    public static final String USER_FULL_NAME = "Fullname";
    public static final String USER_NAME = "Username";
    public static final String NATIONALITY = "Nationality";
    public static final String AGE = "Age";
    public static final String BLOOD_GROUP = "Blood_group";
    public static final String OCCUPATION= "Occupation";
    public static final String GENDER = "Gender";
    public static final String PHONE = "Phone";
    public static final String EMAIL = "Email";
    public static final String ADDRESS = "Address";
    public static final String EMERGENCY_CONTACT = "Emergency_contact";
    public static final String PASS_WORD = "Password";
    public static final String EXTRA1 = "Extra1";

    public LoginDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dbRegister) {


        dbRegister.execSQL("CREATE TABLE " + TABLE_NAME
                + "("
                + ID + " INTEGER PRIMARY KEY, "
                + USER_FULL_NAME + " TEXT, "
                + USER_NAME+ " TEXT UNIQUE, "
                + NATIONALITY + " TEXT, "
                + AGE + " TEXT, "
                + BLOOD_GROUP + " TEXT, "
                + OCCUPATION + " TEXT, "
                + GENDER + " TEXT, "
                + PHONE + " TEXT, "
                + EMAIL + " TEXT, "
                + ADDRESS + " TEXT, "
                + EMERGENCY_CONTACT + " TEXT, "
                + PASS_WORD + " TEXT, "
                + EXTRA1 + " TEXT"
                + ");"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbRegister, int oldVersion, int newVersion) {
        dbRegister.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(dbRegister);
    }

    public long addUser(String fullname, String user,String nationality,String age, String bloodgroup,String occupation,String gender, String phone,String email,String address, String emergencyContatc,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Fullname", fullname);
        contentValues.put("Username", user);
        contentValues.put("Nationality", nationality);
        contentValues.put("Age", age);
        contentValues.put("Blood_group", bloodgroup);
        contentValues.put("Occupation", occupation);
        contentValues.put("Gender", gender);
        contentValues.put("Phone", phone);
        contentValues.put("Email", email);
        contentValues.put("Address", address);
        contentValues.put("Emergency_contact", emergencyContatc);
        contentValues.put("Password", password);
        long res=db.insert("registeruser",null,contentValues);
        db.close();
        return res;
    }

    //query for search

    public boolean duplicateUser(String username, SQLiteDatabase db){

        String query = "SELECT username FROM " + TABLE_NAME + " WHERE " +USER_NAME+ "=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(username)});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            if(username.matches(cursor.getString(0))) {
                return true;
            }
            cursor.moveToNext();
        }
        cursor.close();
        return false;



    }


    public boolean checkUser(String username,String password){

        String[] columns={ID};
        SQLiteDatabase db=getReadableDatabase();
        String selection = USER_NAME + "=?" + " and " + PASS_WORD + "=?";
        String[] selectionArgs={username,password};
        Cursor cursor=db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count>0)
            return true;
        else
            return false;

    }

}