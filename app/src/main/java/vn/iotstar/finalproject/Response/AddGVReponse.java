package vn.iotstar.finalproject.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import vn.iotstar.finalproject.Model.BaiHoc;

public class AddGVReponse implements Serializable {
    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;
    @SerializedName("maGV")
    private String maGV;

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

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public AddGVReponse(String result, String message, String maGV) {
        this.result = result;
        this.message = message;
        this.maGV = maGV;
    }
}
