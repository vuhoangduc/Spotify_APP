package com.example.duan1_spotify_clone.intefaces;


import com.example.duan1_spotify_clone.DTO.Music1;

import java.util.List;

public interface SongItemAction {
    void showMoreAction(int position, List<Music1> songs);
    void setOnItemClickListener(int position, List<Music1> songs);
}
