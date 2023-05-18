package vn.iotstar.finalproject.Model;

import java.io.Serializable;

public class Feedback implements Serializable {
    String tenNguoiDungFeedback;
    String starRate;
    String comment;

    public String getTenNguoiDungFeedback() {
        return tenNguoiDungFeedback;
    }

    public void setTenNguoiDungFeedback(String tenNguoiDungFeedback) {
        this.tenNguoiDungFeedback = tenNguoiDungFeedback;
    }

    public String getStarRate() {
        return starRate;
    }

    public void setStarRate(String starRate) {
        this.starRate = starRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
