package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class BaiHoc implements Serializable {
    private String maBaiHoc;
    private String maKhoaHoc;
    private String tenBaiHoc;
    private String MoTaNoiDung;

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
        return MoTaNoiDung;
    }

    public void setMoTaNoiDung(String moTaNoiDung) {
        MoTaNoiDung = moTaNoiDung;
    }

    public BaiHoc(String maBaiHoc, String maKhoaHoc, String tenBaiHoc, String moTaNoiDung) {
        this.maBaiHoc = maBaiHoc;
        this.maKhoaHoc = maKhoaHoc;
        this.tenBaiHoc = tenBaiHoc;
        MoTaNoiDung = moTaNoiDung;
    }
}