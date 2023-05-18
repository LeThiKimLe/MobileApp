package vn.iotstar.finalproject.Response;

import java.io.Serializable;
import java.util.List;

import vn.iotstar.finalproject.Model.GiaoDich;
import vn.iotstar.finalproject.Model.ViThanhToan;

public class ViThanhToanResponse implements Serializable {
    private String result;
    private String message;
    private ViThanhToan wallet;
    private List<GiaoDich> listGD;

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

    public ViThanhToan getWallet() {
        return wallet;
    }

    public void setWallet(ViThanhToan wallet) {
        this.wallet = wallet;
    }

    public List<GiaoDich> getListGD() {
        return listGD;
    }

    public void setListGD(List<GiaoDich> listGD) {
        this.listGD = listGD;
    }
}
