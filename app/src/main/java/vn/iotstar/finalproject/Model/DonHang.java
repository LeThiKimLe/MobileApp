package vn.iotstar.finalproject.Model;

import java.io.Serializable;
import java.sql.Date;

public class DonHang implements Serializable {
    private String maDonHang;
    private String hocVien;
    private int tongSoTien;
    private long ngayThanhToan;
    private Boolean tinhTrangXacNhan;
    private long ngayTao;

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getHocVien() {
        return hocVien;
    }

    public void setHocVien(String hocVien) {
        this.hocVien = hocVien;
    }

    public int getTongSoTien() {
        return tongSoTien;
    }

    public void setTongSoTien(int tongSoTien) {
        this.tongSoTien = tongSoTien;
    }


    public boolean isTinhTrangXacNhan() {
        return tinhTrangXacNhan;
    }

    public void setTinhTrangXacNhan(boolean tinhTrangXacNhan) {
        this.tinhTrangXacNhan = tinhTrangXacNhan;
    }

    public long getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(long ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public long getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(long ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Boolean getTinhTrangXacNhan() {
        return tinhTrangXacNhan;
    }

    public void setTinhTrangXacNhan(Boolean tinhTrangXacNhan) {
        this.tinhTrangXacNhan = tinhTrangXacNhan;
    }

}
