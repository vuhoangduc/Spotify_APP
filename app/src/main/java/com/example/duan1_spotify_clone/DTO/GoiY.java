package com.example.duan1_spotify_clone.DTO;

public class GoiY {
    private String id_music,ten_music,img_music,file_music,id_kenh;

    public GoiY(String id_music, String ten_music, String img_music, String file_music, String id_kenh) {
        this.id_music = id_music;
        this.ten_music = ten_music;
        this.img_music = img_music;
        this.file_music = file_music;
        this.id_kenh = id_kenh;
    }

    public GoiY() {
    }

    public String getId_music() {
        return id_music;
    }

    public void setId_music(String id_music) {
        this.id_music = id_music;
    }

    public String getTen_music() {
        return ten_music;
    }

    public void setTen_music(String ten_music) {
        this.ten_music = ten_music;
    }

    public String getImg_music() {
        return img_music;
    }

    public void setImg_music(String img_music) {
        this.img_music = img_music;
    }

    public String getFile_music() {
        return file_music;
    }

    public void setFile_music(String file_music) {
        this.file_music = file_music;
    }

    public String getId_kenh() {
        return id_kenh;
    }

    public void setId_kenh(String id_kenh) {
        this.id_kenh = id_kenh;
    }
}
