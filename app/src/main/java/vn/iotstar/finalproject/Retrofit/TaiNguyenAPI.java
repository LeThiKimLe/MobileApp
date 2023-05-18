package vn.iotstar.finalproject.Retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Response.BaiHocReponse;
import vn.iotstar.finalproject.Response.DocumentReponse;
import vn.iotstar.finalproject.Response.TaiNguyenReponse;

public interface TaiNguyenAPI {
    @FormUrlEncoded
    @POST("api/student/getLecture")
    Call<TaiNguyenReponse> getVideo(@Field("maBaiHoc") String maBaiHoc);

    @FormUrlEncoded
    @POST("api/student/getDocument")
    Call<DocumentReponse> getDocument(@Field("maBaiHoc") String maBaiHoc);
}
