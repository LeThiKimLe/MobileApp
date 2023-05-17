package vn.iotstar.finalproject.PageActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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
    KHAdapter khAdapter;
    private static MyCourseActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        mycourseLayoutBinding = MycourseLayoutBinding.inflate(getLayoutInflater());
        setContentView(mycourseLayoutBinding.getRoot());
        String maHocVien=MainActivity.hocVien.getMaHocVien();
       GetMyCourse(maHocVien);
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
                    khAdapter = new KHAdapter(MyCourseActivity.getInstance(), KhoaHocList);
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
    public void goToCourseLession(String maKhoaHoc)
    {
        Intent intent = new Intent(MyCourseActivity.this, MyLessionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("maKhoaHoc", maKhoaHoc);
        intent.putExtras(bundle);
        startActivity(intent);
//        startActivityForResult(intent, 1);
//        finish();
    }
}
