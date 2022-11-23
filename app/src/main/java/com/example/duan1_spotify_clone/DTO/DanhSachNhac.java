package com.example.duan1_spotify_clone.DTO;

public class DanhSachNhac {
    private String id_DS,name_DS,img_DS,tieuDe_DS,id_DM;

    public DanhSachNhac(String id_DS, String name_DS, String img_DS, String tieuDe_DS, String id_DM) {
        this.id_DS = id_DS;
        this.name_DS = name_DS;
        this.img_DS = img_DS;
        this.tieuDe_DS = tieuDe_DS;
        this.id_DM = id_DM;
    }
    public DanhSachNhac() {
    }

    public String getId_DS() {
        return id_DS;
    }

    public void setId_DS(String id_DS) {
        this.id_DS = id_DS;
    }

    public String getName_DS() {
        return name_DS;
    }

    public void setName_DS(String name_DS) {
        this.name_DS = name_DS;
    }

    public String getImg_DS() {
        return img_DS;
    }

    public void setImg_DS(String img_DS) {
        this.img_DS = img_DS;
    }

    public String getTieuDe_DS() {
        return tieuDe_DS;
    }

    public void setTieuDe_DS(String tieuDe_DS) {
        this.tieuDe_DS = tieuDe_DS;
    }

    public String getId_DM() {
        return id_DM;
    }

    public void setId_DM(String id_DM) {
        this.id_DM = id_DM;
    }
}
