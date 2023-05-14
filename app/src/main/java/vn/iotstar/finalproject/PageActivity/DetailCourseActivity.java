package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.TypicalCourseAdapter;
import vn.iotstar.finalproject.Database.CartDatabase;
import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.StatisticResponse;
import vn.iotstar.finalproject.Retrofit.GeneralAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.ActivityMain2Binding;
import vn.iotstar.finalproject.databinding.CourseInforLayoutBinding;

public class DetailCourseActivity extends AppCompatActivity {

    String courseId;
    GeneralAPI apiService;

    KhoaHoc khoaHoc;

    GiaoVien giaoVien;

    CourseInforLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CourseInforLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            courseId = extras.getString("courseId");
        }
        GetCourseInfor();
        GetTeacherInfor();
        addCartBtn();
    }

    private void GetCourseInfor() {
        apiService = RetrofitClient.getRetrofit().create(GeneralAPI.class);

        apiService.getCourseDetail(courseId).enqueue(new Callback<KhoaHoc>() {
            @Override
            public void onResponse(Call<KhoaHoc> call, Response<KhoaHoc> response) {
                if(response.isSuccessful()) {
                    khoaHoc = response.body();
                    binding.courseNamee.setText(khoaHoc.getTenKhoaHoc());
                    binding.courseDescript.setText(khoaHoc.getMoTa());
                    binding.courseCost.setText("Giá: "+khoaHoc.getGiaTien() +"đ");
                    binding.lessonNum.setText("Số bài học: "+ khoaHoc.getSoBaiHoc());
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<KhoaHoc> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetTeacherInfor() {
        apiService = RetrofitClient.getRetrofit().create(GeneralAPI.class);

        apiService.getTeacherInfor(courseId).enqueue(new Callback<GiaoVien>() {
            @Override
            public void onResponse(Call<GiaoVien> call, Response<GiaoVien> response) {
                if(response.isSuccessful()) {
                    giaoVien = response.body();
                    binding.teacherName.setText(giaoVien.getTenGiaoVien());
                    binding.teacherSdt.setText(giaoVien.getSdt());
                    binding.teacherEmail.setText(giaoVien.getEmail());
                }else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<GiaoVien> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void addCartBtn(){
        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartDatabase.getInstance(DetailCourseActivity.this).cartDao().insertAll(khoaHoc);
                Toast.makeText(DetailCourseActivity.this, "Đã thêm vào giỏ thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","1");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}