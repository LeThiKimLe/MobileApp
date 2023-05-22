package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class PhanMon implements Serializable {
    private String maPhanMon;

    private String tenPhanMon;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

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

    @Override
    public String toString()  {
        return this.getTenPhanMon();
    }
}
