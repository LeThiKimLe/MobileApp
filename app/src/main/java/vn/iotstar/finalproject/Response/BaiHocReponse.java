package vn.iotstar.finalproject.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.HocVien;

public class BaiHocReponse  implements Serializable {
    @SerializedName("result")
    private String result;
    @SerializedName("message")
    private String message;
    @SerializedName("lessons")
    private List<BaiHoc> baiHoc;

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

    public List<BaiHoc> getBaiHoc() {
        return baiHoc;
    }

    public void setBaiHoc(List<BaiHoc> baiHoc) {
        this.baiHoc = baiHoc;
    }

    public BaiHocReponse(String result, String message, List<BaiHoc> baiHoc) {
        this.result = result;
        this.message = message;
        this.baiHoc = baiHoc;
    }
}
