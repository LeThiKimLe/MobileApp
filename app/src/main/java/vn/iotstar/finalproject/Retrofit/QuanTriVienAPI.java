package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.DonHang;
import vn.iotstar.finalproject.Model.Feedback;
import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Response.KhoaHocResponse;
import vn.iotstar.finalproject.Response.OrderConfirmResponse;
import vn.iotstar.finalproject.Response.OrderInforResponse;

public interface QuanTriVienAPI {

    @GET("api/manager/getOrderUnconfirmed")
    Call<List<DonHang>> getOrder();

    @FormUrlEncoded
    @POST("api/manager/updateOrderUnconfirmed")
    Call<OrderConfirmResponse> confirmOrder(@Field("maDonHang") String maDonHang, @Field("xacNhan") String xacNhan);

    @GET("api/manager/getTeacher")
    Call<List<GiaoVien>> getTeacher();

    @FormUrlEncoded
    @POST("api/manager/getOrderDetail")
    Call<OrderInforResponse>getChiTietDon(@Field("maDonHang") String maDonHang);

    @FormUrlEncoded
    @POST("api/manager/getTeacherByPhanMon")
    Call<List<GiaoVien>>getTeacherByPhanMon(@Field("maPhanMon") String maPhanMon);

    @FormUrlEncoded
    @POST("api/manager/setCourse")
    Call<KhoaHocResponse>updateCourse(@Field("action") String action, @Field("maKhoaHoc") String maKhoaHoc, @Field("tenKhoaHoc") String tenKhoaHoc, @Field("phanMon") String phanMon, @Field("moTa") String moTa, @Field("giaTien") String giaTien, @Field("giaoVien") String giaoVien, @Field("ngayCapNhat") String ngayCapNhat);

}
