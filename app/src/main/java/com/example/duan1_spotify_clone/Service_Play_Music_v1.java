package com.example.duan1_spotify_clone;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.duan1_spotify_clone.DTO.Music1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service_Play_Music_v1 extends Service implements MediaPlayer.OnErrorListener {
    public static final String TAG = "Service_Play_Music_v1";
    public static final String PACKAGE_NAME_MUSIC = "com.baontq.mob201.player";

    public static final String ACTION_TOGGLE_PAUSE = PACKAGE_NAME_MUSIC + ".toggle_pause";
    public static final String ACTION_PLAY = PACKAGE_NAME_MUSIC + ".play";
    public static final String ACTION_RESUME = PACKAGE_NAME_MUSIC + ".resume";
    public static final String ACTION_PAUSE = PACKAGE_NAME_MUSIC + ".pause";
    public static final String ACTION_STOP = PACKAGE_NAME_MUSIC + ".stop";
    public static final String ACTION_NEXT = PACKAGE_NAME_MUSIC + ".next";
    public static final String ACTION_PREV = PACKAGE_NAME_MUSIC + ".prev";
    public static final String ACTION_KILL = PACKAGE_NAME_MUSIC + ".kill_service";

    public static final String PARAM_SONG = PACKAGE_NAME_MUSIC + ".param_song";
    public static final String PARAM_SONG_INDEX = PACKAGE_NAME_MUSIC + ".param_song_index";
    private IBinder mBinder = new ServiceBinder();

    private Music1 mSong;
    private List<Music1> listSongs;
    private MediaPlayer mediaPlayer;
    private int playIndex = 0;
    private int length = 0;
    @Override
    public boolean onError(MediaPlayer mp, int i, int i1) {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            } finally {
                mediaPlayer = null;
            }
        }
        return false;
    }

    public class ServiceBinder extends Binder {
        public Service_Play_Music_v1 getInstance() {
            return Service_Play_Music_v1.this;
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction()) {
            case ACTION_PLAY:
                try {
                    mSong = intent.getParcelableExtra(PARAM_SONG);
                    playIndex = intent.getIntExtra(PARAM_SONG_INDEX, 0);
                    if (mediaPlayer == null) {
                        mediaPlayer = MediaPlayer.create(this ,Uri.parse(mSong.getFile_music()));
                        mediaPlayer.setOnCompletionListener(mp -> {
                            next();
//                            sendNotification(listSongs.get(playIndex));
                            mSong = listSongs.get(playIndex);
                        });
                        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                            @Override
                            public void onSeekComplete(MediaPlayer mp) {
                                Log.d(TAG, "onSeekComplete: " + mp.getCurrentPosition());
                            }
                        });
                        mediaPlayer.setOnErrorListener(this);
                        mediaPlayer.setLooping(false);
                    } else {
                        mediaPlayer.reset();
                        mediaPlayer.setDataSource(mSong.getFile_music());
                        mediaPlayer.prepare();
                    }
                    mediaPlayer.start();
//                    sendNotification(mSong);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case ACTION_PAUSE:
                pause();
                break;
            case ACTION_STOP:
                stop();
                break;
            case ACTION_RESUME:
                resume();
                break;
            case ACTION_NEXT:
                next();
//                sendNotification(mSong);
                break;
            case ACTION_PREV:
                prev();
//                sendNotification(mSong);
                break;
            default:
                break;
        }
        return START_NOT_STICKY;
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            length = mediaPlayer.getCurrentPosition();
//            sendNotification(mSong);
        }
    }

    public void seek(int progress) {

        if (mediaPlayer != null) {
            mediaPlayer.pause();
            length = progress;
            mediaPlayer.seekTo(progress);
//            sendNotification(mSong);
        }
    }

    public void next() {
        if (listSongs != null && mediaPlayer != null) {
            try {
                if (playIndex == listSongs.size() - 1) {
                    playIndex = 0;
                } else {
                    playIndex++;
                }
                if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.setDataSource(listSongs.get(playIndex).getFile_music());
                mediaPlayer.prepare();
                mediaPlayer.start();
                mSong = listSongs.get(playIndex);
                sendBroadcast(new Intent(ACTION_NEXT));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void prev() {
        if (listSongs != null && mediaPlayer != null) {
            try {
                if (playIndex == 0) {
                    playIndex = listSongs.size() - 1;
                } else {
                    playIndex--;
                }
                if (mediaPlayer.isPlaying()) mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.setDataSource(listSongs.get(playIndex).getFile_music());
                mediaPlayer.prepare();
                mediaPlayer.start();
                mSong = listSongs.get(playIndex);
                sendBroadcast(new Intent(ACTION_PREV));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resume() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
//            sendNotification(mSong);
            sendBroadcast(new Intent(ACTION_RESUME));
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public boolean isPlaying() {
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void setListSongs(ArrayList<Music1> listSongs) {
        this.listSongs = listSongs;
    }

    public Music1 getSongPlaying() {
        return mSong;
    }

    public int getPlayIndex() {
        return playIndex;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
}
