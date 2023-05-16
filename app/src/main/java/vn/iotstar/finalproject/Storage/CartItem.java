package vn.iotstar.finalproject.Storage;


import android.content.SharedPreferences;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import vn.iotstar.finalproject.Adapter.KhoaHocAdapter;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;

@Entity(tableName = "MyCourse")
public class CartItem extends KhoaHoc implements Serializable {
    public CartItem(String maKhoaHoc, String giaoVien, String phanMon, String tenKhoaHoc, String moTa, Integer soBaiHoc, Integer giaTien, String ngayCapNhat, String hinhAnhMoTa) {
        super(maKhoaHoc, giaoVien, phanMon, tenKhoaHoc, moTa, soBaiHoc, giaTien, ngayCapNhat, hinhAnhMoTa);
        this.maHocVien= MainActivity.userId;
    }

    public CartItem(KhoaHoc khoaHoc)
    {
        super(khoaHoc.getMaKhoaHoc(), khoaHoc.getGiaoVien(), khoaHoc.getPhanMon(), khoaHoc.getTenKhoaHoc(),
                khoaHoc.getMoTa(), khoaHoc.getSoBaiHoc(), khoaHoc.getGiaTien(), khoaHoc.getNgayCapNhat(), khoaHoc.getHinhAnhMoTa());
        this.maHocVien= MainActivity.userId;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String maHocVien;

    public String getMaHocVien() {
        return maHocVien;
    }

    public KhoaHoc getKhoaHoc()
    {
        return new KhoaHoc(this.getMaKhoaHoc(), this.getGiaoVien(), this.getPhanMon(), this.getTenKhoaHoc(), this.getMoTa(), this.getSoBaiHoc(), this.getGiaTien(), this.getNgayCapNhat(), this.getHinhAnhMoTa());
    }

    public void setMaHocVien(String maHocVien) {
        this.maHocVien = maHocVien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
