package vn.iotstar.finalproject.Response;

import java.io.Serializable;
import java.util.List;

import vn.iotstar.finalproject.Model.DonHang;

public class DonHangInfor implements Serializable {

    private String result;
    private String message;
    private DonHang donHang;
    private List<BillItem> hangDat;

    public DonHang getDonHang() {
        return donHang;
    }
    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public List<BillItem> getHangDat() {
        return hangDat;
    }
    public void setHangDat(List<BillItem> hangDat) {
        this.hangDat = hangDat;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
