package vn.iotstar.finalproject.Response;

import com.google.gson.annotations.SerializedName;

public class DocumentReponse {
    @SerializedName("result")
    private String result;

    @SerializedName("lyThuyet")
    private String lyThuyet;

    @SerializedName("document")
    private String document;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLyThuyet() {
        return lyThuyet;
    }

    public void setLyThuyet(String lyThuyet) {
        this.lyThuyet = lyThuyet;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentReponse(String result, String lyThuyet, String document) {
        this.result = result;
        this.lyThuyet = lyThuyet;
        this.document = document;
    }
}
