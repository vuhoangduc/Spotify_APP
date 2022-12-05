package com.example.duan1_spotify_clone.intefaces;

import com.example.duan1_spotify_clone.DTO.Playlist;

public interface PlaylistItemAction {
    void onItemClickListener(int position, Playlist playlist);
}
