package com.example.duan1_spotify_clone.DTO;

import java.util.List;

public class Home {
    private String nameHome;
    private List<HomeItem> list;

    public String getNameHome() {
        return nameHome;
    }


    public void setNameHome(String nameHome) {
        this.nameHome = nameHome;
    }

    public List<HomeItem> getList() {
        return list;
    }

    public void setList(List<HomeItem> list) {
        this.list = list;
    }

    public Home(String nameHome, List<HomeItem> list) {
        this.nameHome = nameHome;
        this.list = list;
    }
}
