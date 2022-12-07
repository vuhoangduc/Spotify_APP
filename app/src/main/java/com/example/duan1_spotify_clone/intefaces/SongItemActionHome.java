package com.example.duan1_spotify_clone.intefaces;


import com.example.duan1_spotify_clone.DTO.GoiY;
import com.example.duan1_spotify_clone.DTO.Music1;

import java.util.List;

public interface SongItemActionHome {
    void showMoreAction(int position, List<GoiY> songs);
    void setOnItemClickListener(int position, List<GoiY> songs);
}
