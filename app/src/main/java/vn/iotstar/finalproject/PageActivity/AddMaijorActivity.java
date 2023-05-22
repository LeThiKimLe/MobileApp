package vn.iotstar.finalproject.PageActivity;

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
import vn.iotstar.finalproject.Adapter.chuyenmonAdapter;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.Retrofit.GiaoVienAPI;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.QuanTriVienAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.ListChuyenmonBinding;
import vn.iotstar.finalproject.databinding.MycourseLayoutBinding;

public class AddMaijorActivity extends AppCompatActivity {
    private ListChuyenmonBinding binding;
    QuanTriVienAPI apiService;

    List<PhanMon> PMList;
    RecyclerView recyclerView;
    chuyenmonAdapter cmAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ListChuyenmonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        GetListPhanMon();

    }

    private void GetListPhanMon() {
        apiService = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
        recyclerView = binding.recyclePm;

        apiService.getPhanMon().enqueue(new Callback<List<PhanMon>>() {

            @Override
            public void onResponse(Call<List<PhanMon>> call, Response<List<PhanMon>> response) {

                if (response.isSuccessful()) {
                    PMList = response.body();

                    cmAdapter = new chuyenmonAdapter(AddMaijorActivity.this, PMList);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(AddMaijorActivity.this, 2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(cmAdapter);
                    cmAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AddMaijorActivity.this, "Không gọi được", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<PhanMon>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }



}
