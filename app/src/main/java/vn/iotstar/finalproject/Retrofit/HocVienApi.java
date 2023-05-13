package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Response.StatisticResponse;

public interface HocVienApi {

    @FormUrlEncoded
    @POST("api/general/signup")
    Call<HocVienReponse> registerHocVien(@Field("username") String username, @Field("name") String name, @Field("sdt") String sdt, @Field("email") String email, @Field("ngaysinh") String ngaysinh, @Field("password") String password);
    @FormUrlEncoded
    @POST("api/general/login")
    Call<HocVienReponse> loginHocVien(@Field("username") String username, @Field("password") String password);
    @GET("api/general/listCourse")
    Call<List<KhoaHoc>> getKHAll();



}
