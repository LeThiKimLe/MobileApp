package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.SubjectAdapter;
import vn.iotstar.finalproject.Model.DonHang;
import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.KhoaHocResponse;
import vn.iotstar.finalproject.Retrofit.GeneralAPI;
import vn.iotstar.finalproject.Retrofit.GiaoVienAPI;
import vn.iotstar.finalproject.Retrofit.QuanTriVienAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.ActivityEditCourseBinding;
import vn.iotstar.finalproject.databinding.CourseInforLayoutBinding;

public class EditCourseActivity extends AppCompatActivity {

    private ActivityEditCourseBinding binding;

    ArrayAdapter<PhanMon> adapterPhanMon;

    ArrayAdapter<GiaoVien> adapterGiaoVien;

    GeneralAPI apiService;

    QuanTriVienAPI apiService2, apiService3;

    List<PhanMon> listPM;

    List<GiaoVien> giaoViens;

    String currentMaPhanMon;

    String currentMaGiaoVien;

    KhoaHoc khoaHoc;

    KhoaHocResponse resp;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    final Intent data = new Intent();

    public static final String KQ_EDIT = "KQ_EDIT";

    boolean edited;

    boolean added;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            khoaHoc = (KhoaHoc) extras.getSerializable("khoaHoc");
            loadData();
            addSaveBtn();
        }
        else
        {
            loadAddLayout();
            loadAddBtn();
        }

    }

    private void loadData()
    {
        binding.maKH.setText(khoaHoc.getMaKhoaHoc());
        binding.nameKH.setText(khoaHoc.getTenKhoaHoc());
        binding.descKH.setText(khoaHoc.getMoTa());
        binding.priceKH.setText(khoaHoc.getGiaTien()+"");
        GetSubject();
    }

    private void GetSubject() {
        apiService = RetrofitClient.getRetrofit().create(GeneralAPI.class);
        apiService.getSubject().enqueue(new Callback<List<PhanMon>>() {
            @Override
            public void onResponse(Call<List<PhanMon>> call, Response<List<PhanMon>> response) {
                if(response.isSuccessful()) {
                    listPM = response.body();
                    loadPhanMon(listPM);
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<PhanMon>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void loadPhanMon(List<PhanMon> listPhanMon)
    {
        if (khoaHoc!=null)
            listPhanMon= arrangeList(listPhanMon);
        adapterPhanMon = new ArrayAdapter<PhanMon>(this, android.R.layout.simple_spinner_item, listPhanMon);
        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapterPhanMon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPhanMon.setAdapter(adapterPhanMon);
        // When user select a List-Item.
        binding.spinnerPhanMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GetTeacherByPhanMon(adapterPhanMon.getItem(position).getMaPhanMon());
                currentMaPhanMon=adapterPhanMon.getItem(position).getMaPhanMon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void GetTeacherByPhanMon(String maPhanMon) {
        apiService2 = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
        apiService2.getTeacherByPhanMon(maPhanMon).enqueue(new Callback<List<GiaoVien>>() {
            @Override
            public void onResponse(Call<List<GiaoVien>> call, Response<List<GiaoVien>> response) {
                if(response.isSuccessful()) {
                    giaoViens = response.body();
                    loadGiaoVien(giaoViens);
                }else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<GiaoVien>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void loadGiaoVien(List<GiaoVien> listGiaoVien)
    {
        if (khoaHoc!=null)
            listGiaoVien= arrangeListGV(listGiaoVien);
        adapterGiaoVien = new ArrayAdapter<GiaoVien>(this, android.R.layout.simple_spinner_item, listGiaoVien);
        // Layout for All ROWs of Spinner.  (Optional for ArrayAdapter).
        adapterGiaoVien.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerGiaoVien.setAdapter(adapterGiaoVien);
        // When user select a List-Item.
        binding.spinnerGiaoVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    currentMaGiaoVien=adapterGiaoVien.getItem(position).getMaGiaoVien();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

//    private int getPMPosition(List<PhanMon> listPhanMon ,String TenPhanMon)
//    {
//        int x=0;
//        for (int i=0;i<listPhanMon.size();i++)
//            if (listPhanMon.get(i).getTenPhanMon().equals(TenPhanMon))
//                return i;
//        return x;
//    }
//
//    private int getGVPosition(List<GiaoVien> listGV, String TenGiaoVien)
//    {
//        int x=0;
//        for (int i=0;i<listGV.size();i++)
//            if (listGV.get(i).getTenGiaoVien().equals(TenGiaoVien)) {
//                Toast.makeText(this,listGV.get(i).getTenGiaoVien(), Toast.LENGTH_SHORT).show();
//                return i;
//            }
//        return x;
//    }
    private List<PhanMon> arrangeList(List<PhanMon> list_in)
    {
        PhanMon temp=null;
        for (int i=0;i<list_in.size();i++) {
            if (list_in.get(i).getTenPhanMon().equals(khoaHoc.getPhanMon())) {
                temp = list_in.get(i);
                list_in.remove(i);
                break;
            }
        }
        if (temp!=null)
            list_in.add(0, temp);
        return list_in;
    }

    private List<GiaoVien> arrangeListGV(List<GiaoVien> list_in)
    {
        GiaoVien temp=null;
        for (int i=0;i<list_in.size();i++)
            if (list_in.get(i).getTenGiaoVien().equals(khoaHoc.getGiaoVien())) {
                temp= list_in.get(i);
                list_in.remove(i);
                Toast.makeText(this, "Find", Toast.LENGTH_SHORT).show();
                break;
            }
        if (temp!=null)
            list_in.add(0, temp);
        return list_in;
    }

    private void addSaveBtn()
    {
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= String.valueOf(binding.nameKH.getText());
                String moTa = String.valueOf(binding.descKH.getText());
                String giaTien = String.valueOf(binding.priceKH.getText());
                String today = formatter.format(new Date());

                apiService3 = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
                apiService3.updateCourse("update", khoaHoc.getMaKhoaHoc(), name, currentMaPhanMon, moTa, giaTien, currentMaGiaoVien, today).enqueue(new Callback<KhoaHocResponse>() {
                    @Override
                    public void onResponse(Call<KhoaHocResponse> call, Response<KhoaHocResponse> response) {
                        if(response.isSuccessful()) {

                            resp = response.body();
                            if (resp.getResult().equals("success")) {
                                Toast.makeText(EditCourseActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                                edited=true;
                                data.putExtra(KQ_EDIT, "true");
                            }
                            else
                                Toast.makeText(EditCourseActivity.this, "Update thất bại" , Toast.LENGTH_SHORT).show();
                        }else {
                            int statusCode = response.code();
                            Toast.makeText(EditCourseActivity.this, statusCode+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<KhoaHocResponse> call, Throwable t) {
                        Log.d("logg",t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (edited || added)
            setResult(Activity.RESULT_OK, data);
        else
            setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    private void loadAddLayout()
    {
        binding.maKhTitle.setVisibility(View.INVISIBLE);
        binding.maKH.setVisibility(View.INVISIBLE);
        GetSubject();
    }

    private void loadAddBtn()
    {
        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= String.valueOf(binding.nameKH.getText());
                String moTa = String.valueOf(binding.descKH.getText());
                String giaTien = String.valueOf(binding.priceKH.getText());
                String today = formatter.format(new Date());

                apiService3 = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
                apiService3.updateCourse("add", "" , name, currentMaPhanMon, moTa, giaTien, currentMaGiaoVien, today).enqueue(new Callback<KhoaHocResponse>() {
                    @Override
                    public void onResponse(Call<KhoaHocResponse> call, Response<KhoaHocResponse> response) {
                        if(response.isSuccessful()) {
                            resp = response.body();
                            if (resp.getResult().equals("success")) {
                                Toast.makeText(EditCourseActivity.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                                added=true;
                                data.putExtra(KQ_EDIT, "true");
                            }
                            else
                                Toast.makeText(EditCourseActivity.this, "Update thất bại" , Toast.LENGTH_SHORT).show();
                        }else {
                            int statusCode = response.code();
                            Toast.makeText(EditCourseActivity.this, statusCode+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<KhoaHocResponse> call, Throwable t) {
                        Log.d("logg",t.getMessage());
                    }
                });
            }
        });
    }

}