package com.example.duan1_spotify_clone.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dont_Open_V1 extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase database;
    public static final String TEXT = "TEXT";
    public static final String TEN_THE_LOAI = "TEN_THE_LOAI";
    public void open(){
        database = this.getWritableDatabase();
    }
    public Dont_Open_V1(@Nullable Context context) {
        super(context, "tentheloai1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TEXT + "(" + TEN_THE_LOAI + " TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void ADD_NEW(String text){
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TEN_THE_LOAI,text);
        database.insert(TEXT,null,contentValues);
    }
    public void DELETE_ALL(){
        database = this.getWritableDatabase();
        database.delete(TEXT,null,null);
    }
    public String getData(){
        database = this.getReadableDatabase();
        String text = "";
        Cursor cursor = database.query(TEXT,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
             text = cursor.getString(0);
            cursor.moveToNext();
        }
        return text;
    }

}
