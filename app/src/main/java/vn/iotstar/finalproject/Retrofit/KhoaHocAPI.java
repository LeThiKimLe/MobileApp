package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.KhoiLop;

public interface KhoaHocAPI {
    @GET("api/general/listCourse")
    Call<List<KhoaHoc>> getKHAll();
    @GET("api/general/khoiLop")
    Call<List<KhoiLop>> getKhoiLop();
    @FormUrlEncoded
    @POST("api/general/listSubject")
    Call<List<KhoaHoc>> getKHKhoi(@Field("maKhoi") String maKhoi);

    @FormUrlEncoded
    @POST("api/general/search")
    Call<List<KhoaHoc>>getTimKiem(@Field("key") String key);

    @FormUrlEncoded
    @POST("api/student/myCourse")
    Call<List<KhoaHoc>>getMyCourse(@Field("maHocVien") String maHocVien);


}
