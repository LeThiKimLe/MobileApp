package vn.iotstar.finalproject.PageActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.KhoaHocAdapter;
import vn.iotstar.finalproject.Adapter.MyCourseInforAdapter;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Response.BaiHocReponse;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Retrofit.BaiHocAPI;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.DetailCourseLayoutBinding;

public class MyLessionActivity extends AppCompatActivity {
    BaiHocAPI apiService;
    List<BaiHoc> BaiHocList;
    BaiHocReponse bh;
    private DetailCourseLayoutBinding binding;
    RecyclerView recyclerView;
    String maKhoaHoc;
    String maHocVien;
    MyCourseInforAdapter myCourseInforAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailCourseLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            maHocVien=MainActivity.hocVien.getMaHocVien();
            maKhoaHoc=bundle.getString("maKhoaHoc");
            GetMyCourseLession(maHocVien,maKhoaHoc);

        }
    }

    private void GetMyCourseLession(String maHocVien, String maKhoaHoc) {
        apiService = RetrofitClient.getRetrofit().create(BaiHocAPI.class);
        recyclerView = binding.recycleLession;

        apiService.getLession(maHocVien, maKhoaHoc).enqueue(new Callback<BaiHocReponse>() {

            @Override
            public void onResponse(Call<BaiHocReponse> call, Response<BaiHocReponse> response) {

                if (response.isSuccessful())
                {
                    bh = response.body();

                    if (bh.getResult().compareTo("success")==0) {
                        myCourseInforAdapter  = new MyCourseInforAdapter(MyLessionActivity.this, bh.getBaiHoc());
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MyLessionActivity.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(myCourseInforAdapter);
                    myCourseInforAdapter.notifyDataSetChanged();


                    }
                    else
                    {
                        Toast.makeText(MyLessionActivity.this,bh.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MyLessionActivity.this,"Không kết nối", Toast.LENGTH_SHORT).show();

                }
//                if (response.isSuccessful()) {
//                    bh = response.body();
//                   // Toast.makeText(MyLessionActivity.this, "Teexxt "+ BaiHocList.size(), Toast.LENGTH_SHORT).show();
//
//                    myCourseInforAdapter  = new MyCourseInforAdapter(MyLessionActivity.this, hv);
//                    recyclerView.setHasFixedSize(true);
//                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MyLessionActivity.this, 1);
//                    recyclerView.setLayoutManager(layoutManager);
//                    recyclerView.setAdapter(myCourseInforAdapter);
//                    myCourseInforAdapter.notifyDataSetChanged();
//                } else {
//                    Toast.makeText(MyLessionActivity.this, "Không gọi được", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<BaiHocReponse> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });


    }
}
