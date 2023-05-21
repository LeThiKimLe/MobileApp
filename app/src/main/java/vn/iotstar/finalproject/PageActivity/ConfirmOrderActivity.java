package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.BillAdapter;
import vn.iotstar.finalproject.Adapter.OrderAdapter;
import vn.iotstar.finalproject.Model.DonHang;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.BillItem;
import vn.iotstar.finalproject.Response.DonHangInfor;
import vn.iotstar.finalproject.Response.OrderConfirmResponse;
import vn.iotstar.finalproject.Response.OrderInforResponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Retrofit.QuanTriVienAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.ActivityConfirmOrderBinding;

public class ConfirmOrderActivity extends AppCompatActivity {

    private ActivityConfirmOrderBinding binding;

    private QuanTriVienAPI apiService;
    private OrderInforResponse resp;
    private BillAdapter adapter;
    private DonHang donHang;

    private OrderConfirmResponse confirm_response;

    public static final String KQ_DUYET = "KQ_DUYET";

    boolean choose=false;

    final Intent data = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityConfirmOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            donHang = (DonHang) extras.getSerializable("donHang");
        }
        loadDetailOrder();
        addAcceptBtn();
        addDenyBtn();
    }

    private void loadDetailOrder()
    {
        apiService = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
        apiService.getChiTietDon(donHang.getMaDonHang()).enqueue(new Callback<OrderInforResponse>() {
            @Override
            public void onResponse(Call<OrderInforResponse> call, Response<OrderInforResponse> response) {
                if(response.isSuccessful()) {
                    resp = response.body();
                    showInfor(resp);
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<OrderInforResponse> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void showInfor(OrderInforResponse response)
    {
       showInfor(response.getHocVien());
       showBill(response.getListItem());
    }


    private void showBill(List<BillItem> list_item)
    {
        List<KhoaHoc> list_kh = new ArrayList<>();
        KhoaHoc kh= null;
        for(int i=0;i<list_item.size();i++)
        {
            kh=new KhoaHoc(list_item.get(i).getMaKhoaHoc(), list_item.get(i).getTenKhoaHoc(), list_item.get(i).getGiaTien());
            list_kh.add(kh);
        }
        adapter = new BillAdapter(this, list_kh);
        binding.regisRc.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        binding.regisRc.setLayoutManager(layoutManager);
        binding.regisRc.setAdapter(adapter);
    }

    private void showInfor(HocVien hocVien)
    {
        binding.studentName.setText(hocVien.getTenHocVien());
        binding.studentEmail.setText(hocVien.getEmail());
        binding.studentPhone.setText(hocVien.getSdt());
    }

    private void sendConfirm(String confirm)
    {

        apiService = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
        apiService.confirmOrder(donHang.getMaDonHang(), confirm).enqueue(new Callback<OrderConfirmResponse>() {
            @Override
            public void onResponse(Call<OrderConfirmResponse> call, Response<OrderConfirmResponse> response) {
                if (response.isSuccessful()) {
                    confirm_response = response.body();
                    if (confirm_response.getResult().equals("success"))
                        Toast.makeText(ConfirmOrderActivity.this, confirm_response.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ConfirmOrderActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<OrderConfirmResponse> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void addAcceptBtn()
    {
        binding.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendConfirm("1");
                binding.btnAccept.setEnabled(false);
                data.putExtra(KQ_DUYET, "true");
                choose=true;

            }

        });
    }

    private void addDenyBtn()
    {
        binding.btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendConfirm("0");
                binding.btnDeny.setEnabled(false);
                data.putExtra(KQ_DUYET, "false");
                choose=true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (choose)
            setResult(Activity.RESULT_OK, data);
        else
            setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

}