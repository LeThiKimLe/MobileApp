package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Response.StatisticResponse;

public interface GeneralAPI {
    @GET("api/manager/getStatistics")
    Call<StatisticResponse> getTypical();

    @GET("api/general/subject")
    Call<List<PhanMon>> getSubject();

    @FormUrlEncoded
    @POST("api/general/courseDetail")
    Call<KhoaHoc> getCourseDetail(@Field("courseId") String courseId);

    @FormUrlEncoded
    @POST("api/general/getTeacher")
    Call<GiaoVien> getTeacherInfor(@Field("courseId") String courseId);


}
