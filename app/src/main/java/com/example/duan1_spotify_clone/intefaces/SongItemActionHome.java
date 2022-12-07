package com.example.duan1_spotify_clone.intefaces;


import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.Music1;

import java.util.List;

public interface SongItemActionHome {
    void showMoreActionv1(int position, List<GoiY> songs);
    void setOnItemClickListenerv1(int position, List<GoiY> songs);
}
