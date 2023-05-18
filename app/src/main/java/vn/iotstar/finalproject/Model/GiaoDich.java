package vn.iotstar.finalproject.Model;

import java.io.Serializable;
import java.math.BigDecimal;

public class GiaoDich implements Serializable {

    private String viThanhToan;
    private String maGiaoDich;
    private BigDecimal soTienGiaoDich;
    private String noiDungGiaoDich;
    private long ngayGiaoDich;
    private BigDecimal soDuCapNhat;

    public String getViThanhToan() {
        return viThanhToan;
    }

    public void setViThanhToan(String viThanhToan) {
        this.viThanhToan = viThanhToan;
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public BigDecimal getSoTienGiaoDich() {
        return soTienGiaoDich;
    }

    public void setSoTienGiaoDich(BigDecimal soTienGiaoDich) {
        this.soTienGiaoDich = soTienGiaoDich;
    }

    public String getNoiDungGiaoDich() {
        return noiDungGiaoDich;
    }

    public void setNoiDungGiaoDich(String noiDungGiaoDich) {
        this.noiDungGiaoDich = noiDungGiaoDich;
    }

    public long getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(long ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public BigDecimal getSoDuCapNhat() {
        return soDuCapNhat;
    }

    public void setSoDuCapNhat(BigDecimal soDuCapNhat) {
        this.soDuCapNhat = soDuCapNhat;
    }


}
