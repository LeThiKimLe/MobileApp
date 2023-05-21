package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class BaiHoc implements Serializable {
    private String maBaiHoc;
    private String maKhoaHoc;
    private String tenBaiHoc;
    private String moTaNoiDung;

    public BaiHoc(String tenBaiHoc, String moTaNoiDung) {
        this.tenBaiHoc = tenBaiHoc;
        this.moTaNoiDung = moTaNoiDung;
    }

    public BaiHoc(String maBaiHoc,String tenBaiHoc, String moTaNoiDung) {
        this.maBaiHoc= maBaiHoc;
        this.tenBaiHoc = tenBaiHoc;
        this.moTaNoiDung = moTaNoiDung;
    }



    public String getMaBaiHoc() {
        return maBaiHoc;
    }

    public void setMaBaiHoc(String maBaiHoc) {
        this.maBaiHoc = maBaiHoc;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenBaiHoc() {
        return tenBaiHoc;
    }

    public void setTenBaiHoc(String tenBaiHoc) {
        this.tenBaiHoc = tenBaiHoc;
    }

    public String getMoTaNoiDung() {
        return moTaNoiDung;
    }

    public void setMoTaNoiDung(String moTaNoiDung) {
        this.moTaNoiDung = moTaNoiDung;
    }

    public BaiHoc(String maBaiHoc, String maKhoaHoc, String tenBaiHoc, String moTaNoiDung) {
        this.maBaiHoc = maBaiHoc;
        this.maKhoaHoc = maKhoaHoc;
        this.tenBaiHoc = tenBaiHoc;
        this.moTaNoiDung = moTaNoiDung;
    }
}