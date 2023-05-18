package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class QuanTriVien implements Serializable {
    private String maQtv;
    private String hoTen;
    private String sdt;
    private String email;
    private String diaChi;
    private String cccd;

    public String getMaQtv() {
        return maQtv;
    }

    public void setMaQtv(String maQtv) {
        this.maQtv = maQtv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
}
