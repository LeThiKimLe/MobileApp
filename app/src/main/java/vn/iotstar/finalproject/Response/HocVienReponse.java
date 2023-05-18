package vn.iotstar.finalproject.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Model.QuanTriVien;

public class HocVienReponse implements Serializable {
    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;
    @SerializedName("hocVien")
    private HocVien hocVien;

    private GiaoVien giaoVien;
    private QuanTriVien qtv;

    public GiaoVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
    }

    public QuanTriVien getQtv() {
        return qtv;
    }

    public void setQtv(QuanTriVien qtv) {
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

    public HocVien getHocVien() {
        return hocVien;
    }

    public void setHocVien(HocVien hocVien) {
        this.hocVien = hocVien;
    }

    public HocVienReponse(String result, String message, HocVien hocVien) {
        this.result = result;
        this.message = message;
        this.hocVien = hocVien;
    }
}
