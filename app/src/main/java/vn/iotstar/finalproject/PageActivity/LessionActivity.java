package vn.iotstar.finalproject.PageActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.KhoaHocAdapter;
import vn.iotstar.finalproject.Adapter.LessionAdapter;
import vn.iotstar.finalproject.Adapter.MyCourseInforAdapter;
import vn.iotstar.finalproject.Adapter.SpinnerAdapter;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoiLop;
import vn.iotstar.finalproject.Response.BaiHocReponse;
import vn.iotstar.finalproject.Retrofit.BaiHocAPI;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.DetailCourseLayoutBinding;
import vn.iotstar.finalproject.databinding.LessionLayoutBinding;

public class LessionActivity extends AppCompatActivity {
    BaiHocAPI apiService;
    List<BaiHoc> BaiHocList;
    BaiHocReponse bh;
    private LessionLayoutBinding binding;
    RecyclerView recyclerView;
    String maKhoaHoc;

    LessionAdapter lessionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LessionLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            maKhoaHoc = bundle.getString("maKhoaHoc");
            GetCourseLession(maKhoaHoc);

        }


    }

    private void GetCourseLession(String maKhoaHoc) {
        recyclerView=binding.recycleLession;

        apiService = RetrofitClient.getRetrofit().create(BaiHocAPI.class);

        apiService.getBaiHoc(maKhoaHoc).enqueue(new Callback<List<BaiHoc>>() {

            @Override
            public void onResponse(Call<List<BaiHoc>> call, Response<List<BaiHoc>> response) {
                if (response.isSuccessful()) {
                    BaiHocList = response.body();
                    lessionAdapter = new LessionAdapter(LessionActivity.this,BaiHocList);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(LessionActivity.this.getApplicationContext(), 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(lessionAdapter);
                    lessionAdapter.notifyDataSetChanged();


                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHoc>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });


    }
}
