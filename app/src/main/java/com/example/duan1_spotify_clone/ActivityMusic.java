package com.example.duan1_spotify_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.DTO.Music1;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ActivityMusic extends AppCompatActivity {
    TextView tenNgheSi,ten_music,tg_batdau,tg_ketthuc;
    ImageView img_Music,control_btn,back,next,prev;
    SeekBar thoigianchay;
    MediaPlayer mediaPlayer;
    int position;
    int timeline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        init();
        Bundle bundle = getIntent().getExtras();
        List<Music1> list = new ArrayList<>();
        music1s = new ArrayList<>();
        music1s = (List<Music1>) bundle.getSerializable("list");

        int index = bundle.getInt("index");
        timeline = bundle.getInt("timeline");
        position = index;
        ten_music.setText(music1s.get(index).getTen_music());
        Glide.with(this).load(music1s.get(index).getImg_music()).into(img_Music);


        try {
            khoitao();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    List<Music1> music1s;
    public void init(){
        ten_music = findViewById(R.id.ten_music);
        tenNgheSi = findViewById(R.id.ten_ngheSi);
        img_Music = findViewById(R.id.img_music);
        thoigianchay = findViewById(R.id.thoi_gian_chay_v1);
        tg_batdau = findViewById(R.id.tg_batdau);
        tg_ketthuc = findViewById(R.id.tg_ketthuc);
        control_btn = findViewById(R.id.control_musicplayer);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next_f);
        prev = findViewById(R.id.prev);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                finish();
            }
        });
        control_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    control_btn.setImageResource(R.drawable.play);
//                    cd.clearAnimation();


                }else {
                    mediaPlayer.start();
                    control_btn.setImageResource(R.drawable.pause);
//                    cd.startAnimation(animation);
                }
                SetTimeToal();
                capNhapthoigian();

            }
        });
        thoigianchay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("zzzzzzzzzzzzzzzzzzzzzzzzzzzzz "+thoigianchay.getProgress(), "onStopTrackingTouch: ");
                mediaPlayer.seekTo(thoigianchay.getProgress());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position > music1s.size() - 1){
                    position = 0;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                try {
                    khoitao();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                SetTimeToal();
                capNhapthoigian();
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position < 0){
                    position = music1s.size() - 1;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                try {
                    khoitao();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                SetTimeToal();
                capNhapthoigian();
            }
        });
        
    }

    public void khoitao() throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(music1s.get(position).getFile_music());
        mediaPlayer.prepare();
        ten_music.setText(music1s.get(position).getTen_music());
        Glide.with(this).load(music1s.get(position).getImg_music()).into(img_Music);

    }
    public void SetTimeToal(){
        SimpleDateFormat dinhgio = new SimpleDateFormat("mm:ss");
        tg_ketthuc.setText(dinhgio.format(mediaPlayer.getDuration()));
        thoigianchay.setMax(mediaPlayer.getDuration());
    }
    public void  capNhapthoigian(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhgio = new SimpleDateFormat("mm:ss");
                tg_batdau.setText(dinhgio.format(mediaPlayer.getCurrentPosition()));

                thoigianchay.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position > music1s.size() - 1){
                            position = 0;
                        }

                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        try {
                            khoitao();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        control_btn.setImageResource(R.drawable.pause);
                        SetTimeToal();
                        capNhapthoigian();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }
}
