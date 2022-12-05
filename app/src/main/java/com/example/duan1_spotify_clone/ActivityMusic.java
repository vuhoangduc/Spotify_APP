package com.example.duan1_spotify_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ActivityMusic extends AppCompatActivity {
    TextView tenNgheSi,ten_music,tg_batdau,tg_ketthuc;
    ImageView img_Music,control_btn;
    SeekBar thoigianchay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        init();
    }


    public void init(){
        ten_music = findViewById(R.id.ten_music);
        tenNgheSi = findViewById(R.id.ten_ngheSi);
        img_Music = findViewById(R.id.img_music);
        thoigianchay = findViewById(R.id.thoi_gian_chay_v1);
        tg_batdau = findViewById(R.id.tg_batdau);
        tg_ketthuc = findViewById(R.id.tg_ketthuc);
        control_btn = findViewById(R.id.control_musicplayer);


    }
}