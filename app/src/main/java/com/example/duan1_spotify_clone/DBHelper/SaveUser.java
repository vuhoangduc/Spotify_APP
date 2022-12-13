package com.example.duan1_spotify_clone.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duan1_spotify_clone.DTO.User;

import java.util.ArrayList;

public class SaveUser extends SQLiteOpenHelper {
    public static final String TB_SAVE_USER = "TB_SAVE_USER";
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String AGE = "AGE";
    public static final String GENDER = "GENDER";
    public static final String ID_KENH = "ID_KENH";
    public static final String ID_MUSIC = "ID_MUSIC";
    public static final String ID_PLAYLIST = "ID_playlist";

    public SaveUser(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }
    SQLiteDatabase database;
    public void open(){
        database = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + TB_SAVE_USER + "( " + EMAIL + " TEXT NOT NULL," + NAME + " TEXT NOT NULL," + PASSWORD + " TEXT NOT NULL," + AGE + " TEXT NOT NULL," + GENDER + " TEXT NOT NULL," + ID_KENH + " TEXT ," + ID_MUSIC + " TEXT," + ID_PLAYLIST + " TEXT )";
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    public long Save_NEW_USER(User user){
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL,user.getEmail_user());
        contentValues.put(NAME,user.getName_user());
        contentValues.put(PASSWORD,user.getPass_user());
        contentValues.put(AGE,user.getAge_user());
        contentValues.put(GENDER,user.getGender_user());
        return database.insert(TB_SAVE_USER,null,contentValues);
    }



    public long DELETE(){
        database = this.getReadableDatabase();
        return database.delete(TB_SAVE_USER,null,null);
    }


    public ArrayList<User> getData(){
        database = this.getReadableDatabase();
        ArrayList<User> users = new ArrayList<>();

        Cursor cursor = database.query(TB_SAVE_USER,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User();
            user.setEmail_user(cursor.getString(0));
            user.setName_user(cursor.getString(1));
            user.setPass_user(cursor.getString(2));
            user.setAge_user(Integer.parseInt(cursor.getString(3)));
            user.setGender_user(cursor.getString(4));
            users.add(user);
            cursor.moveToNext();
        }
        return users;
    }
}
