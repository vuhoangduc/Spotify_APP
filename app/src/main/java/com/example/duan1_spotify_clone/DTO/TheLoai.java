package com.example.duan1_spotify_clone.DTO;

public class TheLoai {
    private String id;
    private String title;
    private String img;

    public TheLoai(String id, String title, String img) {
        this.id = id;
        this.title = title;
        this.img = img;
    }

    public TheLoai() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
