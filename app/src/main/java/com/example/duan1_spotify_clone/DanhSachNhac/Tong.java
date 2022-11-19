package com.example.duan1_spotify_clone.DanhSachNhac;

import java.util.List;

public class Tong {
    String nameTong;
    List<ItemNhac> listTong;

    public String getNameTong() {
        return nameTong;
    }

    public void setNameTong(String nameTong) {
        this.nameTong = nameTong;
    }

    public List<ItemNhac> getListTong() {
        return listTong;
    }

    public void setListTong(List<ItemNhac> listTong) {
        this.listTong = listTong;
    }

    public Tong() {
    }

    public Tong(String nameTong, List<ItemNhac> listTong) {
        this.nameTong = nameTong;
        this.listTong = listTong;
    }
}
