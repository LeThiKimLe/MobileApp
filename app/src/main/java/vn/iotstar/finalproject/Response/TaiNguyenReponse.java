package vn.iotstar.finalproject.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaiNguyenReponse implements Serializable {
    @SerializedName("result")
    private String result;

    @SerializedName("description")
    private String description;

    @SerializedName("tenBH")
    private  String tenBH;

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    @SerializedName("video")
    private String video;


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public TaiNguyenReponse(String result, String description, String tenBH, String video) {
        this.result = result;
        this.description = description;
        this.tenBH = tenBH;
        this.video = video;
    }
}
