package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.PhanHoi;
import vn.iotstar.finalproject.Response.DonHangInfor;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Response.RegisResponse;
import vn.iotstar.finalproject.Response.SoDuResponse;
import vn.iotstar.finalproject.Response.ViThanhToanResponse;

public interface HocVienApi {

    @FormUrlEncoded
    @POST("api/general/signup")
    Call<HocVienReponse> registerHocVien(@Field("username") String username, @Field("name") String name, @Field("sdt") String sdt, @Field("email") String email, @Field("ngaysinh") String ngaysinh, @Field("password") String password);
    @FormUrlEncoded
    @POST("api/general/login")
    Call<HocVienReponse> loginHocVien(@Field("username") String username, @Field("password") String password);
    @GET("api/general/listCourse")
    Call<List<KhoaHoc>> getKHAll();
    @FormUrlEncoded
    @POST("api/general/updateProfile")
    Call<HocVienReponse> updateProfile(@Field("maUser") String maUser,@Field("name") String name, @Field("ngaysinh") String ngaysinh, @Field("sdt") String sdt, @Field("email") String email, @Field("image") String image);

    @FormUrlEncoded
    @POST("api/student/getBalance")
    Call<SoDuResponse> getBalance(@Field("maHocVien") String maUser);

    @FormUrlEncoded
    @POST("api/student/regisRequest")
    Call<RegisResponse> regisCourse(@Field("maHocVien") String maUser, @Field("cacKhoaHoc") String course);

    @FormUrlEncoded
    @POST("api/student/getBill")
    Call<DonHangInfor> getBill(@Field("maHocVien") String maUser, @Field("maHoaDon") String maDon);

    @FormUrlEncoded
    @POST("api/student/payBill")
    Call<RegisResponse> payBill(@Field("maHocVien") String maUser, @Field("maHoaDon") String maDon);

    @FormUrlEncoded
    @POST("api/student/getWallet")
    Call<ViThanhToanResponse> getWallet(@Field("maHocVien") String maUser);

    @FormUrlEncoded
    @POST("api/general/sendFeedback")
    Call<PhanHoi> sendFeedback(@Field("maKhoaHoc") String maKhoaHoc, @Field("maHocVien") String maHocVien, @Field("rate") int rate, @Field("content") String content);


}
