package vn.iotstar.finalproject.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class  KhoaHoc implements Serializable {

    private String maKhoaHoc;
    private String giaoVien;
    private String phanMon;
    private String tenKhoaHoc;
    private String moTa;
    private Integer soBaiHoc;
    private Integer giaTien;
    private String ngayCapNhat;
    private String hinhAnhMoTa;

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }

    public String getPhanMon() {
        return phanMon;
    }

    public void setPhanMon(String phanMon) {
        this.phanMon = phanMon;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoBaiHoc() {
        return soBaiHoc;
    }

    public void setSoBaiHoc(Integer soBaiHoc) {
        this.soBaiHoc = soBaiHoc;
    }

    public Integer getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Integer giaTien) {
        this.giaTien = giaTien;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getHinhAnhMoTa() {
        return hinhAnhMoTa;
    }

    public void setHinhAnhMoTa(String hinhAnhMoTa) {
        this.hinhAnhMoTa = hinhAnhMoTa;
    }

    public KhoaHoc(String maKhoaHoc, String giaoVien, String phanMon, String tenKhoaHoc, String moTa, Integer soBaiHoc, Integer giaTien, String ngayCapNhat, String hinhAnhMoTa) {
        this.maKhoaHoc = maKhoaHoc;
        this.giaoVien = giaoVien;
        this.phanMon = phanMon;
        this.tenKhoaHoc = tenKhoaHoc;
        this.moTa = moTa;
        this.soBaiHoc = soBaiHoc;
        this.giaTien = giaTien;
        this.ngayCapNhat = ngayCapNhat;
        this.hinhAnhMoTa = hinhAnhMoTa;
    }
}
