package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.KhoaHoc;

public interface GiaoVienAPI {

    @FormUrlEncoded
    @POST("api/teacher/myCourse")
    Call<List<KhoaHoc>> getTeacherCourse(@Field("maGiaoVien") String maGiaoVien);
}
