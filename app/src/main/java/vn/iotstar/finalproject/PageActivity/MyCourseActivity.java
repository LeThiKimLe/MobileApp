package vn.iotstar.finalproject.PageActivity;

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
import vn.iotstar.finalproject.Adapter.KHAdapter;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Retrofit.GiaoVienAPI;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.MycourseLayoutBinding;

public class MyCourseActivity extends AppCompatActivity {
    private MycourseLayoutBinding mycourseLayoutBinding;
    KhoaHocAPI apiService;
    GiaoVienAPI apiService2;
    List<KhoaHoc> KhoaHocList;
    RecyclerView recyclerView;
    KHAdapter khAdapter;
    private static MyCourseActivity instance;

    String maHocVien, maGiaoVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mycourseLayoutBinding = MycourseLayoutBinding.inflate(getLayoutInflater());
        setContentView(mycourseLayoutBinding.getRoot());
        if (MainActivity.role.equals("HV")) {
            maHocVien = MainActivity.hocVien.getMaHocVien();
            GetMyCourse(maHocVien);
        }
        else
        {
            maGiaoVien=MainActivity.userId;
            GetTeacherCourse();
        }
    }
    public static MyCourseActivity getInstance() {
        return instance;
    }
    private void GetMyCourse(String maHocVien) {
        apiService = RetrofitClient.getRetrofit().create(KhoaHocAPI.class);
        recyclerView = mycourseLayoutBinding.recycle;

        apiService.getMyCourse(maHocVien).enqueue(new Callback<List<KhoaHoc>>() {

            @Override
            public void onResponse(Call<List<KhoaHoc>> call, Response<List<KhoaHoc>> response) {

                if (response.isSuccessful()) {
                    KhoaHocList = response.body();
                    if(KhoaHocList.size()==0)
                    {
                        mycourseLayoutBinding.textView8.setText("Bạn chưa có khóa học nào");
                        mycourseLayoutBinding.textView8.setVisibility(TextView.VISIBLE);
                        mycourseLayoutBinding.recycle.setVisibility(RecyclerView.INVISIBLE);
                    }
                    khAdapter = new KHAdapter(MyCourseActivity.getInstance(), KhoaHocList, true);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MyCourseActivity.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(khAdapter);
                    khAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MyCourseActivity.this, "Không gọi được", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<KhoaHoc>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void GetTeacherCourse()
    {
        apiService2 = RetrofitClient.getRetrofit().create(GiaoVienAPI.class);
        recyclerView = mycourseLayoutBinding.recycle;
        apiService2.getTeacherCourse(maGiaoVien).enqueue(new Callback<List<KhoaHoc>>() {

            @Override
            public void onResponse(Call<List<KhoaHoc>> call, Response<List<KhoaHoc>> response) {

                if (response.isSuccessful()) {
                    KhoaHocList = response.body();
                    if(KhoaHocList.size()==0)
                    {
                        mycourseLayoutBinding.textView8.setText("Bạn chưa có khóa học nào");
                        mycourseLayoutBinding.textView8.setVisibility(TextView.VISIBLE);
                        mycourseLayoutBinding.recycle.setVisibility(RecyclerView.INVISIBLE);
                    }
                    khAdapter = new KHAdapter(MyCourseActivity.getInstance(), KhoaHocList, false);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MyCourseActivity.this, 1);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(khAdapter);
                    khAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MyCourseActivity.this, "Không gọi được", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<KhoaHoc>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });

    }
    public void goToCourseLession(String maKhoaHoc, String tenKhoaHoc)
    {
        Intent intent = new Intent(MyCourseActivity.this, MyLessionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("maKhoaHoc", maKhoaHoc);
        bundle.putString("tenKhoaHoc", tenKhoaHoc);
        intent.putExtras(bundle);
        startActivity(intent);
//        startActivityForResult(intent, 1);
//        finish();
    }

}
