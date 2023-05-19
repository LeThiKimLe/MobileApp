package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class PhanHoi implements Serializable {
    private int id;
    private int rate;
    private String feedbackString;
    private String maHocVienString;
    private String maKhoaHocString;
    private String tenHocVienString;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getFeedbackString() {
        return feedbackString;
    }

    public void setFeedbackString(String feedbackString) {
        this.feedbackString = feedbackString;
    }

    public String getMaHocVienString() {
        return maHocVienString;
    }

    public void setMaHocVienString(String maHocVienString) {
        this.maHocVienString = maHocVienString;
    }

    public String getMaKhoaHocString() {
        return maKhoaHocString;
    }

    public void setMaKhoaHocString(String maKhoaHocString) {
        this.maKhoaHocString = maKhoaHocString;
    }

    public String getTenHocVienString() {
        return tenHocVienString;
    }

    public void setTenHocVienString(String tenHocVienString) {
        this.tenHocVienString = tenHocVienString;
    }
}
