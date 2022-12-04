package com.example.duan1_spotify_clone.Service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.R;

import java.util.List;

public class Service_Play_Music extends Service {

    MediaPlayer m;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("z zz z zzz service hoat dong", "onStartCommand: ");
        Bundle bundle = intent.getBundleExtra("FileMusic");
        String text = bundle.getString("link");
        startAudioStream(text);
        m.start();
        return START_NOT_STICKY;
    }
    public void startAudioStream(String url) {
        if (m == null)
            m = new MediaPlayer();
        try {
            m.setAudioStreamType(AudioManager.STREAM_MUSIC);
            m.setDataSource(url);
            m.prepare();
            m.setVolume(1f, 1f);
            m.setLooping(false);
            m.start();
        } catch (Exception e) {
            Log.d("mylog", "Error playing in SoundHandler: " + e.toString());
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        m.stop();

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("zzzzzzzzzzzzzzzzzzz  service play music", "onBind: ");
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
