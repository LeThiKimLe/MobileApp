package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class KhoiLop implements Serializable {
    private String maKhoi;
    private String tenKhoi;

    public KhoiLop(String maKhoi, String tenKhoi) {
        this.maKhoi = maKhoi;
        this.tenKhoi = tenKhoi;
    }

    public String getMaKhoi() {
        return maKhoi;
    }

    public void setMaKhoi(String maKhoi) {
        this.maKhoi = maKhoi;
    }

    public String getTenKhoi() {
        return tenKhoi;
    }

    public void setTenKhoi(String tenKhoi) {
        this.tenKhoi = tenKhoi;
    }
}
