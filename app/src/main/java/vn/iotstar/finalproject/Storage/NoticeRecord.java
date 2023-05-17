package vn.iotstar.finalproject.Storage;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "NoticeTable")
public class NoticeRecord implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private String titleMsg;
    private String content;
    private String maHoaDon;

    private String maHocVien;

    private String ngayTao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleMsg() {
        return titleMsg;
    }

    public void setTitleMsg(String titleMsg) {
        this.titleMsg = titleMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(String maHocVien) {
        this.maHocVien = maHocVien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = formatter.format(ngayTao);
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public NoticeRecord(String titleMsg, String content, String maHoaDon, String maHocVien) {
        this.titleMsg = titleMsg;
        this.content = content;
        this.maHoaDon = maHoaDon;
        this.maHocVien = maHocVien;
        this.ngayTao= formatter.format(new Date());
    }
}
