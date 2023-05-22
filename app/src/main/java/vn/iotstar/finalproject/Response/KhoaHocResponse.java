package vn.iotstar.finalproject.Response;

import java.io.Serializable;

public class KhoaHocResponse implements Serializable {
    private String result;
    private String message;
    private String maKH;

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

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public KhoaHocResponse(String result, String message, String maKH) {
        this.result = result;
        this.message = message;
        this.maKH = maKH;
    }

}
