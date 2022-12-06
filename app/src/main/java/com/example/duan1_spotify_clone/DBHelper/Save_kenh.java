package com.example.duan1_spotify_clone.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duan1_spotify_clone.DTO.Kenh;

import org.w3c.dom.Text;

public class Save_kenh extends SQLiteOpenHelper {
    public static final String KENH = "KENH";
    public static final String ID_KENH = "ID_" + KENH;
    public static final String TEN_KENH = "TEN_" + KENH;
    public static final String IMG_KENH = "IMG_" + KENH;
    public static final String IMG_GIOI_THIEU = "IMG_GIOI_THIEU";
    public static final String THONG_TIN_GT = "THONG_TIN_GT";
    Context context;
    SQLiteDatabase database;

    public Save_kenh(@Nullable Context context) {
        super(context, "kenh.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + KENH + "(" + ID_KENH + " TEXT," + TEN_KENH + " TEXT," + IMG_KENH + " TEXT," + IMG_GIOI_THIEU + " TEXT," + THONG_TIN_GT + " TEXT)";

            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void ADD_NEW(Kenh kenh){
        database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_KENH,kenh.getId_kenh());
        contentValues.put(TEN_KENH,kenh.getTen_kenh());
        contentValues.put(IMG_KENH,kenh.getImg_kenh());
        contentValues.put(IMG_GIOI_THIEU,kenh.getImg_gioiThieu());
        contentValues.put(THONG_TIN_GT,kenh.getThongtin_gioiThieu());
        database.insert(KENH,null,contentValues);
    }
    public void DELETE_ALL(){
        database = this.getWritableDatabase();
        database.delete(KENH,null,null);
    }
    public Kenh getData(){
        database = this.getReadableDatabase();
        Kenh kenh = new Kenh() ;
        String text = "";
        Cursor cursor = database.query(KENH,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            kenh = new Kenh(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            cursor.moveToNext();
        }
        return kenh;
    }
}
