package com.example.duan1_spotify_clone.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.ActivityMusic;
import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DTO.WordCup;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.SongItemAction;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class NowPlayingFragmentBottom extends Fragment {
    ImageView control_btn,img_music_tabar;
    Bundle bundle;
    TextView tenBaiHat;
    public SeekBar thoiGianChay;
    MediaPlayer mediaPlayer;
    public int position1 ;
ImageView imgImageView;
public int countP,countS;

    public void setImgImageView(ImageView imgImageView) {
        this.imgImageView = imgImageView;
    }

    List<Music1> music1s= new ArrayList<>();
    List<GoiY> music2s= new ArrayList<>();
    boolean check = false;
    public void showMoreActionv1( int index,List<GoiY> songs) {
        music2s.addAll(songs);
        position1 = index;
        if (music2s.size()!=0){
            try {
                khoitao();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            SetTimeToal();
            capNhapthoigian();
        }


        thoiGianChay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                bundle.putInt("timeline", mediaPlayer.getDuration());
                countS=progress/1000;
                if(countS==60){
                    countS=0;
                }
                countP=progress;
                Log.d("Timea",countP+":"+countS);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(thoiGianChay.getProgress());
            }
        });



    }


    public void showMoreAction( int index,List<Music1> songs) {
        music1s.addAll(songs);
        position1 = index;
        if (music1s.size()!=0){
            try {
                khoitao();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            SetTimeToal();
            capNhapthoigian();
        }


        thoiGianChay.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                bundle.putInt("timeline", mediaPlayer.getDuration());
                    countS=progress/1000;
                if(countS==60){
                    countS=0;
                }
                countP=progress;
                Log.d("Timea",countP+":"+countS);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(thoiGianChay.getProgress());
            }
        });



    }
    public void showMoreAction_1( int index,List<Music1> songs) {
        music1s.clear();
        music1s.addAll(songs);
        position1 = index;
        if (mediaPlayer!= null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }
        Log.d("zzzzzzzzzzzzzzzzzzzzz "+position1, "showMoreAction_1: ");
        if (music1s.size()!=0){
            try {
                khoitao();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            SetTimeToal();
            capNhapthoigian();
        }
    }
    public void showMoreAction_2( WordCup songs) {
        music1s.clear();
        Music1 music1_wc = new Music1();
            music1_wc=new Music1(songs.getId_wc(),songs.getTen_wc(),songs.getImg_wc(),songs.getFile_wc(),songs.getNam_wc(),null);
            music1s.add(music1_wc);
        if (mediaPlayer!= null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }
        if (music1s.size()!=0){
            try {
                khoitao();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            SetTimeToal();
            capNhapthoigian();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    public static NowPlayingFragmentBottom getInstance(){
        return new NowPlayingFragmentBottom();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing_bottom, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.item_Music);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (music1s.size()!=0) {
                    Intent intent = new Intent(getActivity(), ActivityMusic.class);
                    bundle = new Bundle();
                    bundle.putInt("Giay",countS);
                    bundle.putInt("Phut",countP);

                    bundle.putInt("index", position1);
                    bundle.putSerializable("list", (Serializable) music1s);
                    intent.putExtras(bundle);
                    mediaPlayer.stop();
                    startActivity(intent);
                }

            }
        });
        tenBaiHat = view.findViewById(R.id.ten_music_tabar);
        img_music_tabar = view.findViewById(R.id.img_music_tabar);
        control_btn = view.findViewById(R.id.control_tabar_music);
        thoiGianChay = view.findViewById(R.id.thoi_gian_chay);
        control_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgImageView.setImageResource(R.drawable.play1);
                    control_btn.setImageResource(R.drawable.play_button_arrowhead);
//                    cd.clearAnimation();
                }else {
                    mediaPlayer.start();
                    imgImageView.setImageResource(R.drawable.pause);
                    control_btn.setImageResource(R.drawable.pause_button_arrowhead);
//                    cd.startAnimation(animation);
                }
                SetTimeToal();
                capNhapthoigian();

            }
        });

        return view;
    }

    public void  capNhapthoigian(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhgio = new SimpleDateFormat("mm:ss");
//                txt_tgbatdau.setText(dinhgio.format(mediaPlayer.getCurrentPosition()));

                thoiGianChay.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position1++;
                        if(position1 > music1s.size() - 1){
                            position1 = 0;
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
                handler.postDelayed(this, 500);
            }
        }, 100);
    }
    public void khoitao() throws IOException {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(music1s.get(position1).getFile_music());
        mediaPlayer.prepare();
        tenBaiHat.setText(music1s.get(position1).getTen_music());
        Glide.with(this).load(music1s.get(position1).getImg_music()).into(img_music_tabar);
    }
    public void SetTimeToal(){
        SimpleDateFormat dinhgio = new SimpleDateFormat("mm:ss");
//        txt_tgketthuc.setText(dinhgio.format(mediaPlayer.getDuration()));
        Log.d("zzzzzzzzzzz "+mediaPlayer.getDuration(), "SetTimeToal: ");
        thoiGianChay.setMax(mediaPlayer.getDuration());
    }
public void pauseMusic(){
        mediaPlayer.pause();
        control_btn.setImageResource(R.drawable.play_button_arrowhead);
}
    public void playMusic(){
        mediaPlayer.start();
        control_btn.setImageResource(R.drawable.pause_button_arrowhead);
    }

}