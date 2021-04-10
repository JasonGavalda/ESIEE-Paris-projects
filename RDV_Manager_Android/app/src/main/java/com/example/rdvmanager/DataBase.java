package com.example.rdvmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    //Database Name
    public static final String DATABASE_NAME = "RDV";

    //Table Name
    public static final String TABLE_NAME = "RDVs" ;

    //Attributes Name
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String DATE = "date";
    public static final String TIME = "time";
    public static final String ADDRESS = "address";
    public static final String CONTACT = "contact";
    public static final String PHONEN = "phone_number";
    public static final String STATE = "state";

    // Database Information
    static final String DB_NAME = "RDVs.DB";
    // database version
    static final int DB_VERSION = 1;

    //Table creation
    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE +  " TEXT NOT NULL, " + DATE + " TEXT, " + TIME + " TEXT, " + ADDRESS + " TEXT, " + CONTACT + " TEXT, " + PHONEN + " INTEGER, " + STATE + " BOOLEAN);";

    public DataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TABLE_NAME");
        onCreate(db);
    }

    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }
    public void addContact(RDV rdv){
        ContentValues insertValues = new ContentValues();

        insertValues.put(TITLE, rdv.getTitle());
        insertValues.put(DATE, rdv.getDate());
        insertValues.put(TIME, rdv.getTime());
        insertValues.put(ADDRESS, rdv.getAddress());
        insertValues.put(CONTACT, rdv.getContact());
        insertValues.put(PHONEN, rdv.getPhoneNumber());
        insertValues.put(STATE, rdv.getState());

        this.database.insert(TABLE_NAME, null, insertValues);
    }

    public void close() {
        database.close();
    }

    public int update(RDV rdv) {
        Long _id= rdv.getId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, rdv.getTitle());
        contentValues.put(DATE,rdv.getDate());
        contentValues.put(TIME,rdv.getTime());
        contentValues.put(ADDRESS, rdv.getAddress());
        contentValues.put(CONTACT, rdv.getContact());
        contentValues.put(PHONEN, rdv.getPhoneNumber());
        int count = database.update(TABLE_NAME, contentValues, this._ID + " = " + _id, null);
        return count;
    }

    public void delete(long _id)
    {
        database.delete(TABLE_NAME, _ID + "=" + _id, null);
    }

    public void deleteDataBase(){
        SQLiteDatabase base = this.getWritableDatabase();
        onUpgrade(base, 1, 1);
        base.close();
    }

    public Cursor getAllRDVs ()
    {
        String[] projection = {_ID,TITLE,DATE,TIME,ADDRESS,CONTACT,PHONEN,STATE};
        Cursor cursor = database.query(TABLE_NAME,projection,null,null,null,null,null,null);
        return cursor;
    }

}
