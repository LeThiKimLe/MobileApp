package vn.iotstar.finalproject.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GiaoVien implements Serializable {
    private String maGiaoVien;
    private String tenGiaoVien;
    private String sdt;
    private String cccd;
    private String diaChi;
    private long ngayKyKet;
    private String chuyenmon;

    private List<PhanMon> chuyen;

    public List<PhanMon> getChuyen() {
        return chuyen;
    }

    public void setChuyen(List<PhanMon> chuyen) {
        this.chuyen = chuyen;
    }

    private String email;

    private String listKhoaHocs;

    public String getListKhoaHocs() {
        return listKhoaHocs;
    }

    public void setListKhoaHocs(String listKhoaHocs) {
        this.listKhoaHocs = listKhoaHocs;
    }

    public String getMaGiaoVien() {
        return maGiaoVien;
    }

    public void setMaGiaoVien(String maGiaoVien) {
        this.maGiaoVien = maGiaoVien;
    }

    public String getTenGiaoVien() {
        return tenGiaoVien;
    }

    public void setTenGiaoVien(String tenGiaoVien) {
        this.tenGiaoVien = tenGiaoVien;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public long getNgayKyKet() {
        return ngayKyKet;
    }

    public void setNgayKyKet(long ngayKyKet) {
        this.ngayKyKet = ngayKyKet;
    }

    public String getChuyenmon() {
        return chuyenmon;
    }

    public void setChuyenmon(String chuyenmon) {
        this.chuyenmon = chuyenmon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GiaoVien(String maGiaoVien, String tenGiaoVien, String sdt, String email) {
        this.maGiaoVien = maGiaoVien;
        this.tenGiaoVien = tenGiaoVien;
        this.sdt = sdt;
        this.email = email;
    }

    public GiaoVien(String maGiaoVien, String tenGiaoVien, String sdt, String cccd, String diaChi, String email, long ngayKyKet, String chuyenmon) {
        this.maGiaoVien = maGiaoVien;
        this.tenGiaoVien = tenGiaoVien;
        this.sdt = sdt;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.ngayKyKet = ngayKyKet;
        this.chuyenmon = chuyenmon;
        this.email = email;
    }

    public GiaoVien(String maGiaoVien, String tenGiaoVien, String sdt, String cccd, String diaChi, long ngayKyKet, String chuyenmon, String email, String listKhoaHocs) {
        this.maGiaoVien = maGiaoVien;
        this.tenGiaoVien = tenGiaoVien;
        this.sdt = sdt;
        this.cccd = cccd;
        this.diaChi = diaChi;
        this.ngayKyKet=ngayKyKet;
        this.chuyenmon = chuyenmon;
        this.email = email;
        this.listKhoaHocs = listKhoaHocs;
    }
}
