package vn.iotstar.finalproject.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.Response.StatisticResponse;

public interface GeneralAPI {
    @GET("api/manager/getStatistics")
    Call<StatisticResponse> getTypical();

    @GET("api/general/subject")
    Call<List<PhanMon>> getSubject();

}
