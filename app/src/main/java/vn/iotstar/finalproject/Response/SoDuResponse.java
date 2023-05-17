package vn.iotstar.finalproject.Response;

import java.io.Serializable;
import java.util.Date;


public class SoDuResponse implements Serializable {

    private String result;
    private String message;
    private int soDu;
    //private long ngayCapNhat;
    public int getSoDu() {
        return soDu;
    }
    public void setSoDu(int soDu) {
        this.soDu = soDu;
    }
//    public Date getNgayCapNhat() {
//        return ngayCapNhat;
//    }
//    public void setNgayCapNhat(Date ngayCapNhat) {
//        this.ngayCapNhat = ngayCapNhat;
//    }

    public SoDuResponse(int soDuVi, Date ngayUpdate)
    {
        this.soDu= soDuVi;
//        this.ngayCapNhat = ngayUpdate;
    }

    public SoDuResponse () {}
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

}

