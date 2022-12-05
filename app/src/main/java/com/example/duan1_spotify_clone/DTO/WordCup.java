package com.example.duan1_spotify_clone.DTO;

public class WordCup {
    private String id_wc,ten_wc,img_wc,nam_wc,file_wc;

    public WordCup(String id_wc, String ten_wc, String img_wc, String nam_wc, String file_wc) {
        this.id_wc = id_wc;
        this.ten_wc = ten_wc;
        this.img_wc = img_wc;
        this.nam_wc = nam_wc;
        this.file_wc = file_wc;
    }

    public WordCup() {
    }

    public String getId_wc() {
        return id_wc;
    }

    public void setId_wc(String id_wc) {
        this.id_wc = id_wc;
    }

    public String getTen_wc() {
        return ten_wc;
    }

    public void setTen_wc(String ten_wc) {
        this.ten_wc = ten_wc;
    }

    public String getImg_wc() {
        return img_wc;
    }

    public void setImg_wc(String img_wc) {
        this.img_wc = img_wc;
    }

    public String getNam_wc() {
        return nam_wc;
    }

    public void setNam_wc(String nam_wc) {
        this.nam_wc = nam_wc;
    }

    public String getFile_wc() {
        return file_wc;
    }

    public void setFile_wc(String file_wc) {
        this.file_wc = file_wc;
    }
}
