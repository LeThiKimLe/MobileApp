package vn.iotstar.finalproject.PageActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.KhoaHocAdapter;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.Storage.SharedPrefManager;
import vn.iotstar.finalproject.databinding.LoginLayoutBinding;
import vn.iotstar.finalproject.databinding.MycourseLayoutBinding;

public class MyCourseActivity extends AppCompatActivity {
    private MycourseLayoutBinding mycourseLayoutBinding;
    KhoaHocAPI apiService;
    List<KhoaHoc> KhoaHocList;
    RecyclerView recyclerView;
    KhoaHocAdapter KhoaHocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mycourseLayoutBinding = MycourseLayoutBinding.inflate(getLayoutInflater());
        setContentView(mycourseLayoutBinding.getRoot());
       GetMyCourse("HV0001");
    }

    private void GetMyCourse(String maHocVien) {
        apiService = RetrofitClient.getRetrofit().create(KhoaHocAPI.class);
        recyclerView = mycourseLayoutBinding.recycle;

        apiService.getMyCourse(maHocVien).enqueue(new Callback<List<KhoaHoc>>() {

            @Override
            public void onResponse(Call<List<KhoaHoc>> call, Response<List<KhoaHoc>> response) {
                if (response.isSuccessful()) {
                    KhoaHocList = response.body();
                    Log.d(TAG,"log "+ KhoaHocList.get(0).getMaKhoaHoc());
                    KhoaHocAdapter = new KhoaHocAdapter(MainActivity.getInstance(), KhoaHocList);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.getInstance().getApplicationContext(), 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(KhoaHocAdapter);
                    KhoaHocAdapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<KhoaHoc>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });


    }
}
