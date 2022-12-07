package com.example.duan1_spotify_clone.intefaces;


import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.Music1;
import com.example.duan1_spotify_clone.DTO.WordCup;

import java.util.List;

public interface SongItemActionHome {
    void showMoreActionv1(int position, List<WordCup> songs);
    void setOnItemClickListenerv1( List<WordCup> songs);
}
