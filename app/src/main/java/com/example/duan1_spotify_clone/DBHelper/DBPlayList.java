package com.example.duan1_spotify_clone.DBHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duan1_spotify_clone.DTO.Playlist;

import java.util.ArrayList;

public class DBPlayList extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public DBPlayList(Context context) {
        super(context, "test", null, 2);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTablePlayList= "create table Playlist (" +
                "idList INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenList TEXT NOT NULL, " +
                "imgList BLOB )";
        db.execSQL(createTablePlayList);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Playlist");
    }
    @SuppressLint("Range")
    public ArrayList<Playlist> getAll(){
        ArrayList<Playlist> list = new ArrayList<>();
        String sql = "Playlist";
        Cursor cursor =db.query(sql,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Playlist playlist =new Playlist();
            playlist.setIdList(cursor.getInt(0));
            playlist.setTenList(cursor.getString(1));
            playlist.setImgList(cursor.getBlob(2));
            list.add(playlist);
            cursor.moveToNext();
        }

        return list;
    }
    public long add(Playlist playlist ){
        ContentValues values = new ContentValues();
//        values.put("idList",playlist.getIdList());
        values.put("tenList",playlist.getTenList());
//        values.put("imgList",playlist.getImgList());
        return db.insert("Playlist",null,values);
    }
    public long delete(Playlist playlist ){
        return db.delete("Playlist","idList=?",new String[]{playlist.getIdList()+""});
    }
}
