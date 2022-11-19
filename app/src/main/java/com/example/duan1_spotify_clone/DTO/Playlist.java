package com.example.duan1_spotify_clone.DTO;

public class Playlist {
    private int idList;
    private String tenList;
    private byte[] imgList;

    public Playlist(int idList, String tenList,byte[] imgList) {
        this.idList = idList;
        this.tenList = tenList;
        this.imgList=imgList;
    }

    public byte[] getImgList() {
        return imgList;
    }

    public void setImgList(byte[] imgList) {
        this.imgList = imgList;
    }

    public Playlist() {
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int id) {
        this.idList = id;
    }

    public String getTenList() {
        return tenList;
    }

    public void setTenList(String tenList) {
        this.tenList = tenList;
    }
}
