package com.example.duan1_spotify_clone.DanhSachNhac;

import java.util.List;

public class Tong {
    List<String> nameTong;
    List<ItemNhac> listTong;
    int Count_img;
    public Tong(List<String> nameTong, List<ItemNhac> listTong) {
        this.nameTong = nameTong;
        this.listTong = listTong;
    }

    public Tong(List<String> nameTong, List<ItemNhac> listTong, int count_img) {
        this.nameTong = nameTong;
        this.listTong = listTong;
        Count_img = count_img;
    }

    public int getCount_img() {
        return Count_img;
    }

    public void setCount_img(int count_img) {
        Count_img = count_img;
    }

    public Tong() {
    }

    public List<String> getNameTong() {
        return nameTong;
    }

    public void setNameTong(List<String> nameTong) {
        this.nameTong = nameTong;
    }

    public List<ItemNhac> getListTong() {
        return listTong;
    }

    public void setListTong(List<ItemNhac> listTong) {
        this.listTong = listTong;
    }
}
