package com.example.duan1_spotify_clone.DTO;

import java.io.Serializable;

public class Kenh implements Serializable {
    private String id_kenh;
    private String ten_kenh;
    private String img_kenh;
    private String img_gioiThieu;
    private String thongtin_gioiThieu;

    public Kenh(String id_kenh, String ten_kenh, String img_kenh, String img_gioiThieu, String thongtin_gioiThieu) {
        this.id_kenh = id_kenh;
        this.ten_kenh = ten_kenh;
        this.img_kenh = img_kenh;
        this.img_gioiThieu = img_gioiThieu;
        this.thongtin_gioiThieu = thongtin_gioiThieu;
    }

    public Kenh() {
    }

    public String getId_kenh() {
        return id_kenh;
    }

    public void setId_kenh(String id_kenh) {
        this.id_kenh = id_kenh;
    }

    public String getTen_kenh() {
        return ten_kenh;
    }

    public void setTen_kenh(String ten_kenh) {
        this.ten_kenh = ten_kenh;
    }

    public String getImg_kenh() {
        return img_kenh;
    }

    public void setImg_kenh(String img_kenh) {
        this.img_kenh = img_kenh;
    }

    public String getImg_gioiThieu() {
        return img_gioiThieu;
    }

    public void setImg_gioiThieu(String img_gioiThieu) {
        this.img_gioiThieu = img_gioiThieu;
    }

    public String getThongtin_gioiThieu() {
        return thongtin_gioiThieu;
    }

    public void setThongtin_gioiThieu(String thongtin_gioiThieu) {
        this.thongtin_gioiThieu = thongtin_gioiThieu;
    }
}
