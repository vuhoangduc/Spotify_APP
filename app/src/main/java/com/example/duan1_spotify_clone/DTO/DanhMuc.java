package com.example.duan1_spotify_clone.DTO;

public class DanhMuc {
    private int id_DM;
    private String name_DM;
    private int img;

    public DanhMuc(int id_DM, String name_DM, int img) {
        this.id_DM = id_DM;
        this.name_DM = name_DM;
        this.img = img;
    }

    public DanhMuc() {
    }

    public int getId_DM() {
        return id_DM;
    }

    public void setId_DM(int id_DM) {
        this.id_DM = id_DM;
    }

    public String getName_DM() {
        return name_DM;
    }

    public void setName_DM(String name_DM) {
        this.name_DM = name_DM;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
