package vn.iotstar.finalproject.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import vn.iotstar.finalproject.Model.HocVien;

public class GVReponse implements Serializable {
    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;
    @SerializedName("giaoVien")
    private HocVien giaoVien;

    public GVReponse(String result, String message, HocVien giaoVien) {
        this.result = result;
        this.message = message;
        this.giaoVien = giaoVien;
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

    public HocVien getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(HocVien giaoVien) {
        this.giaoVien = giaoVien;
    }
}
