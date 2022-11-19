package com.example.duan1_spotify_clone.DTO;

public class HomeItem {
    private String nameHI;
    private int imgHI;

    public HomeItem(String nameHI, int imgHI) {
        this.nameHI = nameHI;
        this.imgHI = imgHI;
    }

    public String getNameHI() {
        return nameHI;
    }

    public void setNameHI(String nameHI) {
        this.nameHI = nameHI;
    }

    public int getImgHI() {
        return imgHI;
    }

    public void setImgHI(int imgHI) {
        this.imgHI = imgHI;
    }
}
