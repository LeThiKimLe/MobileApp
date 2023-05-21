package vn.iotstar.finalproject.Response;

import java.io.Serializable;
import java.util.List;

import vn.iotstar.finalproject.Model.HocVien;

public class OrderInforResponse implements Serializable {
    private HocVien hocVien;
    private List<BillItem> listItem;

    public HocVien getHocVien() {
        return hocVien;
    }

    public void setHocVien(HocVien hocVien) {
        this.hocVien = hocVien;
    }

    public List<BillItem> getListItem() {
        return listItem;
    }

    public void setListItem(List<BillItem> listItem) {
        this.listItem = listItem;
    }
}
