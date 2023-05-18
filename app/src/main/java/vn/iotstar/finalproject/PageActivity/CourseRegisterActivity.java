package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.BillAdapter;
import vn.iotstar.finalproject.Adapter.CartAdapter;
import vn.iotstar.finalproject.Dao.iClickListener;
import vn.iotstar.finalproject.Database.CartDatabase;
import vn.iotstar.finalproject.Database.NoticeDatabase;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.RegisResponse;
import vn.iotstar.finalproject.Response.SoDuResponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.Storage.CartItem;
import vn.iotstar.finalproject.Storage.NoticeRecord;
import vn.iotstar.finalproject.databinding.ActivityMain2Binding;
import vn.iotstar.finalproject.databinding.RegisterCourseLayoutBinding;

public class CourseRegisterActivity extends AppCompatActivity {

    List<KhoaHoc> regisList;
    BillAdapter adapter;

    RegisterCourseLayoutBinding binding;

    HocVienApi apiService;

    private int sumCourse=0;

    RegisResponse resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_course_layout);
        binding = RegisterCourseLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showInfor();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            regisList = (List<KhoaHoc>) extras.getSerializable("registerCourse");
            showBill();
        }
        addRegisBtn();
    }

    private void showBill()
    {
        adapter = new BillAdapter(this, regisList);
        binding.regisRc.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        binding.regisRc.setLayoutManager(layoutManager);
        binding.regisRc.setAdapter(adapter);
        binding.numCourse.setText(""+ regisList.size());
        binding.sumTotal.setText("đ"+sumCal());
    }

    private void showInfor()
    {
        binding.studentName.setText(MainActivity.hocVien.getTenHocVien());
        binding.studentEmail.setText(MainActivity.hocVien.getEmail());
        binding.studentPhone.setText(MainActivity.hocVien.getSdt());
    }

    private int sumCal()
    {
       int sum=0;
       for (int i=0; i<regisList.size();i++)
           sum+=regisList.get(i).getGiaTien();
       sumCourse=sum;
       return sum;
    }

    private void addRegisBtn()
    {
        binding.RegisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cacKhoaHoc="";
                for(int i=0; i<regisList.size();i++)
                    cacKhoaHoc+=regisList.get(i).getMaKhoaHoc()+",";

                apiService = RetrofitClient.getRetrofit().create(HocVienApi.class);
                resp = new RegisResponse();
                apiService.regisCourse(MainActivity.hocVien.getMaHocVien(), cacKhoaHoc).enqueue(new Callback<RegisResponse>() {
                    @Override
                    public void onResponse(Call<RegisResponse> call, Response<RegisResponse> response) {
                        if(response.isSuccessful()) {
                            resp = response.body();
                            if (resp.getResult().equals("fail"))
                            {
                                showNotice("Đăng ký không thành công" , resp.getMessage());
                            }
                            else {
                                showNotice("Đăng ký thành công", "Bạn đã đăng ký khóa học thành công. Hãy đợi quan trị viên xác nhận đon nha");
                                NoticeRecord new_notice= new NoticeRecord("Đơn hàng: "+ resp.getMaHoaDon() +" đã được gửi yêu cầu thành công", "Click vào đây để xem chi tiết đơn hàng và tình trạng", resp.getMaHoaDon(), MainActivity.userId);
                                NoticeDatabase.getInstance(CourseRegisterActivity.this).noticeDao().insertAll(new_notice);
                            }

                        }else {
                            int statusCode = response.code();
                        }
                    }
                    @Override
                    public void onFailure(Call<RegisResponse> call, Throwable t) {
                        Log.d("logg",t.getMessage());
                    }
                });
            }
        });
    }

    private void showNotice(String title, String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(CourseRegisterActivity.this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void onBackPressed() {
        finish();
    }


}