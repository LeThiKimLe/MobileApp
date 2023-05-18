package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.BillAdapter;
import vn.iotstar.finalproject.Adapter.SpinnerAdapter;
import vn.iotstar.finalproject.Adapter.TypicalCourseAdapter;
import vn.iotstar.finalproject.Model.DonHang;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.KhoiLop;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.BillItem;
import vn.iotstar.finalproject.Response.DonHangInfor;
import vn.iotstar.finalproject.Response.RegisResponse;
import vn.iotstar.finalproject.Response.StatisticResponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.ActivityMain2Binding;
import vn.iotstar.finalproject.databinding.BillDetailLayoutBinding;

public class BillActivity extends AppCompatActivity {

    private BillDetailLayoutBinding binding;
    private HocVienApi apiService;
    private BillAdapter adapter;
    private DonHangInfor donHang;

    private List<KhoaHoc> khoaHocs;
    private RegisResponse resp;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    private String maHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BillDetailLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            maHoaDon = extras.getString("billId");
        }
        getBillInfor();
        addPayBtn();
        addReloadBtn();
    }
    private void getBillInfor()
    {
        apiService = RetrofitClient.getRetrofit().create(HocVienApi.class);
        apiService.getBill(MainActivity.userId, maHoaDon).enqueue(new Callback<DonHangInfor>() {
            @Override
            public void onResponse(Call<DonHangInfor> call, Response<DonHangInfor> response) {
                if(response.isSuccessful()) {
                    donHang = response.body();
                    showInfor(donHang);
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<DonHangInfor> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void showInfor(DonHangInfor donHang)
    {
        binding.payBtn.setEnabled(false);
        boolean check1=false;

        binding.createDay.setText(donHang.getDonHang().getNgayTao());

        if (donHang.getDonHang().getTinhTrangXacNhan()!=null) {
            if (donHang.getDonHang().getTinhTrangXacNhan()==true) {
                binding.stateBill.setText("Đã được duyệt");
                check1=true;
            }
            else {
                binding.stateBill.setText("Đang chờ duyệt");

            }
        }
        else
        {
            binding.stateBill.setText("Bị từ chối");
        }

        if (donHang.getDonHang().getNgayThanhToan()!=null) {
            binding.payDay.setText(donHang.getDonHang().getNgayThanhToan());
            binding.payBtn.setEnabled(false);
        }
        else {
            binding.payDay.setText("---Chưa có--");
            if (check1==true)
                binding.payBtn.setEnabled(true);

        }
        binding.totalBill.setText(donHang.getDonHang().getTongSoTien()+"đ");

        khoaHocs=new ArrayList<>();
        BillItem temp=null;
        for (int i=0; i<donHang.getHangDat().size(); i++) {
            temp = donHang.getHangDat().get(i);
            khoaHocs.add(new KhoaHoc(temp.getMaKhoaHoc(), temp.getTenKhoaHoc(), temp.getGiaTien()));
        }

        adapter = new BillAdapter(BillActivity.this.getApplicationContext(), khoaHocs);
        binding.regisRc.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.regisRc.setLayoutManager(layoutManager);
        binding.regisRc.setAdapter(adapter);
    }
    private void addPayBtn()
    {
        resp= new RegisResponse();
        binding.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiService = RetrofitClient.getRetrofit().create(HocVienApi.class);
                apiService.payBill(MainActivity.userId, maHoaDon).enqueue(new Callback<RegisResponse>() {
                    @Override
                    public void onResponse(Call<RegisResponse> call, Response<RegisResponse> response) {
                        if(response.isSuccessful()) {
                            resp = response.body();
                            if (resp.getResult().equals("success"))
                                showNotice("Đăng ký thành công","Bạn đã thanh toán thành công đơn hàng");
                            else
                                showNotice("Đăng ký thất bại",resp.getMessage());
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

    private void addReloadBtn()
    {
        binding.reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBillInfor();
            }
        });
    }

    private void showNotice(String title, String message)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(BillActivity.this);
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