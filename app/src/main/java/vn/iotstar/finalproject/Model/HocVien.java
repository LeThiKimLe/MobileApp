package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class HocVien implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maHocVien;

    public String getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(String maHocVien) {
        this.maHocVien = maHocVien;
    }

    public String getTenHocVien() {
        return tenHocVien;
    }

    public void setTenHocVien(String tenHocVien) {
        this.tenHocVien = tenHocVien;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

//    public HocVien(String maHocVien, String tenHocVien, String ngaySinh, String sdt, String email, String image, String username, String password) {
//        this.maHocVien = maHocVien;
//        this.tenHocVien = tenHocVien;
//        this.ngaySinh = ngaySinh;
//        this.sdt = sdt;
//        this.email = email;
//        this.image = image;
//        this.username = username;
//        this.password = password;
//    }
//    public HocVien(String maHocVien, String tenHocVien, String ngaySinh, String sdt, String email, String image, String username) {
//        this.maHocVien = maHocVien;
//        this.tenHocVien = tenHocVien;
//        this.ngaySinh = ngaySinh;
//        this.sdt = sdt;
//        this.email = email;
//        this.image = image;
//        this.username = username;
//
//    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    private String tenHocVien;

    private String ngaySinh;
    private String sdt;
    private String email;
    private String image;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;

    public HocVien(String maHocVien, String tenHocVien, String ngaySinh, String sdt, String email, String image) {
        this.maHocVien = maHocVien;
        this.tenHocVien = tenHocVien;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.email = email;
        this.image = image;
    }
}
