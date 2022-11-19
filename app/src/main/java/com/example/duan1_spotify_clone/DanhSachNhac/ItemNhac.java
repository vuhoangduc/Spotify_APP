package com.example.duan1_spotify_clone.DanhSachNhac;

public class ItemNhac {
    private int img;
    private String tv1,tv2;

    public ItemNhac() {
    }

    public ItemNhac(int img, String tv1, String tv2) {
        this.img = img;
        this.tv1 = tv1;
        this.tv2 = tv2;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTv1() {
        return tv1;
    }

    public void setTv1(String tv1) {
        this.tv1 = tv1;
    }

    public String getTv2() {
        return tv2;
    }

    public void setTv2(String tv2) {
        this.tv2 = tv2;
    }
}
