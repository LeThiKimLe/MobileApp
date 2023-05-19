package vn.iotstar.finalproject.PageActivity;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
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
import vn.iotstar.finalproject.Adapter.LessionAdapter;
import vn.iotstar.finalproject.Adapter.MyCourseInforAdapter;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.PhanHoi;
import vn.iotstar.finalproject.Response.BaiHocReponse;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Retrofit.BaiHocAPI;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.DetailCourseLayoutBinding;

public class MyLessionActivity extends AppCompatActivity {
    BaiHocAPI apiService;

    HocVienApi apiService2;
    List<BaiHoc> BaiHocList;
    BaiHocReponse bh;
    private DetailCourseLayoutBinding binding;
    RecyclerView recyclerView;
    String maKhoaHoc;
    String maHocVien;
    String maGiaoVien;

    LinearLayout layout;

    String content;

    PhanHoi phanHoi;

    int rate;
    MyCourseInforAdapter myCourseInforAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailCourseLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            maKhoaHoc=bundle.getString("maKhoaHoc");
            if (MainActivity.role.equals("HV")) {
                maHocVien = MainActivity.userId;
                GetMyCourseLession(maHocVien,maKhoaHoc);
                addFeedbackFunction();
            }
            else {
                maGiaoVien = MainActivity.userId;
                getTeacherCourseLesson();
            }
        }
        MofidyLayout();
    }

    private void MofidyLayout()
    {

        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params1.weight = 0f;

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params2.weight=7f;

        if (MainActivity.role.equals("GV"))
        {
            binding.feedBackBox.setVisibility(View.INVISIBLE);
            binding.feedBackBox.setLayoutParams(params1);
            binding.lessonBox.setLayoutParams(params2);
        }
    }

    private void GetMyCourseLession(String maHocVien, String maKhoaHoc) {
        apiService = RetrofitClient.getRetrofit().create(BaiHocAPI.class);
        recyclerView = binding.recycleLession;
        apiService.getLession(maHocVien, maKhoaHoc).enqueue(new Callback<BaiHocReponse>() {
            @Override
            public void onResponse(Call<BaiHocReponse> call, Response<BaiHocReponse> response) {

                if (response.isSuccessful()) {
                    bh = response.body();

                    if (bh.getResult().compareTo("success") == 0) {
                        myCourseInforAdapter = new MyCourseInforAdapter(MyLessionActivity.this, bh.getBaiHoc());
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MyLessionActivity.this, 1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(myCourseInforAdapter);
                        myCourseInforAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MyLessionActivity.this, bh.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MyLessionActivity.this, "Không kết nối", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaiHocReponse> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
        private void getTeacherCourseLesson()
        {
            apiService = RetrofitClient.getRetrofit().create(BaiHocAPI.class);
            recyclerView = binding.recycleLession;
            apiService.getBaiHoc(maKhoaHoc).enqueue(new Callback<List<BaiHoc>>() {
                @Override
                public void onResponse(Call<List<BaiHoc>> call, Response<List<BaiHoc>> response) {
                    if (response.isSuccessful()) {
                        BaiHocList = response.body();
                        myCourseInforAdapter = new MyCourseInforAdapter(getApplicationContext(), BaiHocList);
                        recyclerView.setHasFixedSize(true);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(myCourseInforAdapter);
                        myCourseInforAdapter.notifyDataSetChanged();
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

    private void addFeedbackFunction() {

//        binding.feedBackRate.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                float rating = binding.feedBackRate.getRating();
//                rate = Math.round(rating);
//
//                return false;
//            }
//        });

        binding.feedBackRate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float rating = binding.feedBackRate.getRating();
                rate = Math.round(rating);
            }
        });

        binding.btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                content= String.valueOf(binding.feedbackContent.getText());
                apiService2 = RetrofitClient.getRetrofit().create(HocVienApi.class);
                apiService2.sendFeedback( maKhoaHoc, maHocVien,rate, content).enqueue(new Callback<PhanHoi>() {

                    @Override
                    public void onResponse(Call<PhanHoi> call, Response<PhanHoi> response) {

                        if (response.isSuccessful()) {
                            phanHoi = response.body();
                            Toast.makeText(MyLessionActivity.this, "Cảm ơn bạn đã feedback", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MyLessionActivity.this, "Không kết nối", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PhanHoi> call, Throwable t) {
                        Log.d("logg", t.getMessage());
                    }
                });

            }
        });
    }

}
