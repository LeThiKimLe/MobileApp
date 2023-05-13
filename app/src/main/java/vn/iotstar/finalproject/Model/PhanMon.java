package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class PhanMon implements Serializable {
    private String maPhanMon;

    private String tenPhanMon;

    public String getMaPhanMon() {
        return maPhanMon;
    }

    public void setMaPhanMon(String maPhanMon) {
        this.maPhanMon = maPhanMon;
    }

    public String getTenPhanMon() {
        return tenPhanMon;
    }

    public void setTenPhanMon(String tenPhanMon) {
        this.tenPhanMon = tenPhanMon;
    }

    public PhanMon(String maPhanMon, String tenPhanMon) {
        this.maPhanMon = maPhanMon;
        this.tenPhanMon = tenPhanMon;
    }
}
