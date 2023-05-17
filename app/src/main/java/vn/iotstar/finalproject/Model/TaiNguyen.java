package vn.iotstar.finalproject.Model;

public class TaiNguyen {
    private String maBaiHoc;
    private String BaiGiang;
    private  String LyThuyet;
    private String BaiTap;

    public TaiNguyen(String maBaiHoc, String baiGiang, String lyThuyet, String baiTap) {
        this.maBaiHoc = maBaiHoc;
        BaiGiang = baiGiang;
        LyThuyet = lyThuyet;
        BaiTap = baiTap;
    }

    public String getMaBaiHoc() {
        return maBaiHoc;
    }

    public void setMaBaiHoc(String maBaiHoc) {
        this.maBaiHoc = maBaiHoc;
    }

    public String getBaiGiang() {
        return BaiGiang;
    }

    public void setBaiGiang(String baiGiang) {
        BaiGiang = baiGiang;
    }

    public String getLyThuyet() {
        return LyThuyet;
    }

    public void setLyThuyet(String lyThuyet) {
        LyThuyet = lyThuyet;
    }

    public String getBaiTap() {
        return BaiTap;
    }

    public void setBaiTap(String baiTap) {
        BaiTap = baiTap;
    }
}
