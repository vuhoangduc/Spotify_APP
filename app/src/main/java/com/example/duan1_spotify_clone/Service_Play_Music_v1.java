package com.example.duan1_spotify_clone;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Service_Play_Music_v1 extends Service {

    MediaPlayer m;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("zzzzzzz service hoat dong", "onStartCommand: ");
        String text = intent.getStringExtra("link");
        startAudioStream(text);
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
        } catch (Exception e) {
            Log.d("mylog", "Error playing in SoundHandler: " + e.toString());
        }
    }
    @Override
    public void onDestroy() {
        m.pause();

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("zzzzzzzzzzzzzzzzzzz  service play music", "onBind: ");
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
