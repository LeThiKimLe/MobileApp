package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Response.GVReponse;
import vn.iotstar.finalproject.Response.HocVienReponse;

public interface GiaoVienAPI {

    @FormUrlEncoded
    @POST("api/teacher/myCourse")
    Call<List<KhoaHoc>> getTeacherCourse(@Field("maGiaoVien") String maGiaoVien);

    @FormUrlEncoded
    @POST("api/teacher/setLesson")
    Call<BaiHoc> setLesson(@Field("maGiaoVien") String maGiaoVien, @Field("maKhoaHoc") String maKhoaHoc, @Field("action") String action, @Field("maBaiHoc") String maBaiHoc, @Field("tenBaiHoc") String tenBaiHoc , @Field("moTaNoiDung") String moTaNoiDung);


    @FormUrlEncoded
    @POST("api/general/updateGV")
    Call<GVReponse> updateGV(@Field("MaGV") String maGV, @Field("name") String name, @Field("sdt") String sdt, @Field("email") String email, @Field("diachi") String diachi, @Field("cccd") String cccd);



}
