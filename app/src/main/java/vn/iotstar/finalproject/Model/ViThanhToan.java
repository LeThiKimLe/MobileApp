package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class ViThanhToan implements Serializable {
    private String maVi;
    private String hocVien;
    private int soDu;
    private long ngayCapNhat;

    public String getMaVi() {
        return maVi;
    }

    public void setMaVi(String maVi) {
        this.maVi = maVi;
    }

    public String getHocVien() {
        return hocVien;
    }

    public void setHocVien(String hocVien) {
        this.hocVien = hocVien;
    }

    public int getSoDu() {
        return soDu;
    }

    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }

    public long getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(long ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
}
