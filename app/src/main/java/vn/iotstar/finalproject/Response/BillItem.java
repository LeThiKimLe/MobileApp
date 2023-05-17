package vn.iotstar.finalproject.Response;

import java.io.Serializable;

public class BillItem implements Serializable {
    private String maKhoaHoc;
    private String tenKhoaHoc;
    private int giaTien;
    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }
    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }
    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }
    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }
    public int getGiaTien() {
        return giaTien;
    }
    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
