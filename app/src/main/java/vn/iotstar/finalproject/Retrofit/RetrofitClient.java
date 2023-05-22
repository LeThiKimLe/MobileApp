package vn.iotstar.finalproject.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit1, retrofit2;
    private static final String BASE_URL1 = "http://192.168.1.3:8080/WebCuoiKy/";
    public static Retrofit getRetrofit() {
        if(retrofit1 == null) {
            retrofit1 = new Retrofit.Builder()
                    .baseUrl(BASE_URL1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit1;
    }
}
