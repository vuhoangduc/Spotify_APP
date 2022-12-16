package com.example.duan1_spotify_clone.Fragment.POST_API_JSON;

import java.lang.reflect.Array;

public class Post_music_fv {
    String email;
    String id_music;

    public Post_music_fv(String email, String id_music) {
        this.email = email;
        this.id_music = id_music;
    }

    public Post_music_fv() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_music() {
        return id_music;
    }

    public void setId_music(String id_music) {
        this.id_music = id_music;
    }
}
