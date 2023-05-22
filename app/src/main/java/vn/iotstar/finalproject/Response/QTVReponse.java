package vn.iotstar.finalproject.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import vn.iotstar.finalproject.Model.HocVien;

public class QTVReponse implements Serializable {
    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;
    @SerializedName("qtv")
    private HocVien qtv;

    public QTVReponse(String result, String message, HocVien qtv) {
        this.result = result;
        this.message = message;
        this.qtv = qtv;
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

    public HocVien getQtv() {
        return qtv;
    }

    public void setQtv(HocVien qtv) {
        this.qtv = qtv;
    }
}
