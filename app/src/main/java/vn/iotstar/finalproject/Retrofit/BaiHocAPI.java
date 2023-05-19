package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Response.BaiHocReponse;

public interface BaiHocAPI {
    @FormUrlEncoded
    @POST("api/student/myCourse/info")
    Call<BaiHocReponse> getLession(@Field("maHocVien") String maHocVien, @Field("maKhoaHoc") String maKhoaHoc);

    @FormUrlEncoded
    @POST("api/general/lesson")
    Call<List<BaiHoc>>getBaiHoc(@Field("maKhoaHoc") String maKhoaHoc);


}
