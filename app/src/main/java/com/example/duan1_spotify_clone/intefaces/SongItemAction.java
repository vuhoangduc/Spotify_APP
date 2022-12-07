package com.example.duan1_spotify_clone.intefaces;


import android.widget.ImageView;

import com.example.duan1_spotify_clone.DTO.Music1;

import java.util.List;

public interface SongItemAction {
    void showMoreAction(int position, List<Music1> songs, ImageView img);
    void setOnItemClickListener(int position, List<Music1> songs);
}
