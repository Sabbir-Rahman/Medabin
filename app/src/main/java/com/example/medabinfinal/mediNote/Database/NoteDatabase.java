package com.example.medabinfinal.mediNote.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "medinote.db";
    private static final String DATABASE_TABLE = "notestable";

    private static  String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COl_CONTENT = "content";
    private static final String COL_DATE = "date";
    private static final String COL_TIME = "time";



    public NoteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        String createTable = "CREATE TABLE "+ DATABASE_TABLE + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TITLE + " TEXT, " + COl_CONTENT + " TEXT, " + COL_DATE + " TEXT, "  + COL_TIME + " TEXT)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);

    }

    public long addData(NoteModel noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //getting the data by help of hashmap
        contentValues.put(COL_TITLE,noteModel.getTitle());
        contentValues.put(COl_CONTENT,noteModel.getContent());
        contentValues.put(COL_DATE,noteModel.getDate());
        contentValues.put(COL_TIME,noteModel.getTime());


        long ID = db.insert(DATABASE_TABLE,null,contentValues);
        //if any error return -1
        Log.d("Inserted","Id -> "+ID);
        db.close();
        return ID;
    }

    //single note
    public NoteModel getNote(long id){
        //select $ from database where id =l
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{COL_ID,COL_TITLE,COl_CONTENT,COL_DATE,COL_TIME},COL_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();

        return new NoteModel(cursor.getLong(0),cursor.getString(1),
                cursor.getString(2),cursor.getString(3),cursor.getString(4));


    }

    public List<NoteModel> getAllNotes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<NoteModel> allNotes = new ArrayList<>();
        //select *from databaseName

//        String query = "SELECT * FROM "+DATABASE_TABLE;

        //edit query to show thw new note first
        String query = "SELECT * FROM "+DATABASE_TABLE+" ORDER BY "+COL_ID+" DESC";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getLong(0));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setContent(cursor.getString(2));
                noteModel.setDate(cursor.getString(3));
                noteModel.setTime(cursor.getString(4));

                //all notes list added
                allNotes.add(noteModel);

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return allNotes;
    }

    public void deleteNotes(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE,COL_ID+="=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public int editNote(NoteModel noteModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("Edited","Edited title: ->"+noteModel.getTitle()+"\n ID ->"+noteModel.getId());
        contentValues.put(COL_TITLE,noteModel.getTitle());
        contentValues.put(COl_CONTENT,noteModel.getContent());
        contentValues.put(COL_DATE,noteModel.getDate());
        contentValues.put(COL_TIME,noteModel.getTime());

        return db.update(DATABASE_TABLE,contentValues,COL_ID+"=?",new String[]{String.valueOf(noteModel.getId())});

    }

    public List<NoteModel> searchByTitle(String title){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_TITLE,COl_CONTENT,COL_DATE,COL_TIME};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"title LIKE ?",new String[]{"%"+title+"%"},null,null,null);

        List<NoteModel> result = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getLong(0));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setContent(cursor.getString(2));
                noteModel.setDate(cursor.getString(3));
                noteModel.setTime(cursor.getString(4));
                result.add(noteModel);
            }while (cursor.moveToNext());
        }

        return result;


    }

    public List<String> getTitles(){


        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TITLE};
        String tablename = DATABASE_TABLE;

        queryBuilder.setTables(tablename);

        Cursor cursor = queryBuilder.query(db,sqlSelect,null,null,null,null,null);

        List<String> result = new ArrayList<>();

        if (cursor.moveToFirst()){
            do{

                result.add(cursor.getString(cursor.getColumnIndex(COL_TITLE)));

            }while (cursor.moveToNext());
        }
        return result;


    }



    public List<NoteModel> searchByDate(String date){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_TITLE,COl_CONTENT,COL_DATE,COL_TIME};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"date LIKE ?",new String[]{"%"+date+"%"},null,null,null);

        List<NoteModel> result = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getLong(0));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setContent(cursor.getString(2));
                noteModel.setDate(cursor.getString(3));
                noteModel.setTime(cursor.getString(4));
                result.add(noteModel);
            }while (cursor.moveToNext());
        }

        return result;


    }

    public List<String> getDates(){


        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_DATE};
        String tablename = DATABASE_TABLE;

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


    public List<NoteModel> searchByTime(String time){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_TITLE,COl_CONTENT,COL_DATE,COL_TIME};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"time LIKE ?",new String[]{"%"+time+"%"},null,null,null);

        List<NoteModel> result = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getLong(0));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setContent(cursor.getString(2));
                noteModel.setDate(cursor.getString(3));
                noteModel.setTime(cursor.getString(4));
                result.add(noteModel);
            }while (cursor.moveToNext());
        }

        return result;


    }

    public List<String> getTimes(){


        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_TIME};
        String tablename = DATABASE_TABLE;

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

    public List<NoteModel> searchByContent(String content){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {COL_ID,COL_TITLE,COl_CONTENT,COL_DATE,COL_TIME};
        String tableName = DATABASE_TABLE;

        queryBuilder.setTables(tableName);

        //select from title with like query

        Cursor cursor = queryBuilder.query(db,sqlSelect,"content LIKE ?",new String[]{"%"+content+"%"},null,null,null);

        List<NoteModel> result = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do{
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getLong(0));
                noteModel.setTitle(cursor.getString(1));
                noteModel.setContent(cursor.getString(2));
                noteModel.setDate(cursor.getString(3));
                noteModel.setTime(cursor.getString(4));
                result.add(noteModel);
            }while (cursor.moveToNext());
        }

        return result;


    }
}
