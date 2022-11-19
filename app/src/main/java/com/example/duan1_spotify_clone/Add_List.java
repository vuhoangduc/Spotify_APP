package com.example.duan1_spotify_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.FragmentLibrary;
import com.example.duan1_spotify_clone.Fragment.FragmentLibraryAll;
import com.example.duan1_spotify_clone.Fragment.FragmentSearch;

import java.io.ByteArrayOutputStream;

public class Add_List extends AppCompatActivity {
    TextView tvHuy,tvAdd;
    EditText ed;
    MainActivity2 mainActivity2;
    public ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_playlist);
        tvHuy = findViewById(R.id.btnHuyList);
        tvAdd = findViewById(R.id.btnAddList);
        ed = findViewById(R.id.edTenPlaylist);
        tvHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playlist playlist = new Playlist();
                playlist.setTenList(ed.getText().toString());
                DBPlayList db = new DBPlayList(Add_List.this);
               if( db.add(playlist)>0){
                   Toast.makeText(Add_List.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                 finish();
               }else{
                   Toast.makeText(Add_List.this, "That bai", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}