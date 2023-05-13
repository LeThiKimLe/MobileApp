package vn.iotstar.finalproject.Response;

import java.io.Serializable;
import java.util.List;

public class StatisticResponse implements Serializable {
    private int tongSoHocVien;
    private List<SoLuongHocVienDangKyKhoaHoc> dangKyNhieu;
    /**
     * @return the tongSoHocVien
     */
    public int getTongSoHocVien() {
        return tongSoHocVien;
    }
    /**
     * @param tongSoHocVien the tongSoHocVien to set
     */
    public void setTongSoHocVien(int tongSoHocVien) {
        this.tongSoHocVien = tongSoHocVien;
    }
    /**
     * @return the dangKyNhieu
     */
    public List<SoLuongHocVienDangKyKhoaHoc> getDangKyNhieu() {
        return dangKyNhieu;
    }
    /**
     * @param dangKyNhieu the dangKyNhieu to set
     */
    public void setDangKyNhieu(List<SoLuongHocVienDangKyKhoaHoc> dangKyNhieu) {
        this.dangKyNhieu = dangKyNhieu;
    }

    public class SoLuongHocVienDangKyKhoaHoc implements Serializable
    {
        private String tenKhoaHoc;
        private String giaoVien;
        private int soLuongDangKy;
        private String maKhoaHoc;

        private String hinhAnhMoTa;
        /**
         * @return the tenKhoaHoc
         */
        public String getTenKhoaHoc() {
            return tenKhoaHoc;
        }
        /**
         * @param tenKhoaHoc the tenKhoaHoc to set
         */
        public void setTenKhoaHoc(String tenKhoaHoc) {
            this.tenKhoaHoc = tenKhoaHoc;
        }
        /**
         * @return the giaoVien
         */
        public String getGiaoVien() {
            return giaoVien;
        }
        /**
         * @param giaoVien the giaoVien to set
         */
        public void setGiaoVien(String giaoVien) {
            this.giaoVien = giaoVien;
        }
        /**
         * @return the soLuongDangKy
         */
        public int getSoLuongDangKy() {
            return soLuongDangKy;
        }
        /**
         * @param soLuongDangKy the soLuongDangKy to set
         */
        public void setSoLuongDangKy(int soLuongDangKy) {
            this.soLuongDangKy = soLuongDangKy;
        }
        public String getMaKhoaHoc() {
            return maKhoaHoc;
        }
        public void setMaKhoaHoc(String maKhoaHoc) {
            this.maKhoaHoc = maKhoaHoc;
        }
        public String getHinhAnhMoTa() {
            return hinhAnhMoTa;
        }
        public void setHinhAnhMoTa(String hinhAnhMoTa) {
            this.hinhAnhMoTa = hinhAnhMoTa;
        }

    }

}
