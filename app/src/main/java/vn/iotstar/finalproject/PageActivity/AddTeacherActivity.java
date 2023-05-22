package vn.iotstar.finalproject.PageActivity;

import static vn.iotstar.finalproject.PageActivity.MainActivity.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Response.AddGVReponse;
import vn.iotstar.finalproject.Response.GVReponse;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Retrofit.QuanTriVienAPI;
import vn.iotstar.finalproject.databinding.AddgiaovienLayoutBinding;
import vn.iotstar.finalproject.databinding.ListChuyenmonBinding;

public class AddTeacherActivity extends AppCompatActivity {
    private AddgiaovienLayoutBinding binding;
    QuanTriVienAPI qtvApi;
    AddGVReponse addGVReponse;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddgiaovienLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        qtvApi = vn.iotstar.finalproject.Retrofit.RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
        binding.ngaykkBox.setEnabled(false);
        LayDSPM();
        DatePicker();
        handleAddGV();


    }
    private  void DatePicker()
    {
        binding.ngaykkBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        AddTeacherActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                binding.ngaykkBox.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
    }
    public void LayDSPM()
    {
        binding.btnchuyenmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTeacherActivity.this, AddMaijorActivity.class);
                startActivity(intent);
            }
        });
    }
    private void handleAddGV() {
        // Listen event
        binding.addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String action = "add";
                // Get data

                String name = binding.usernameBox.getText().toString();
                String email = binding.emailBox.getText().toString();
                String sdt = binding.sdtBox.getText().toString();
                String cccd = binding.cccd.getText().toString();
                String ngaykk = binding.ngaykkBox.getText().toString();

                String diachi = binding.diachiBox.getText().toString();
                String chuyenmon="";
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    chuyenmon = bundle.getString("chuyen");



                }



                qtvApi.AddGiaoVien(action, name, sdt, email, cccd, diachi, ngaykk, chuyenmon).enqueue(new Callback<AddGVReponse>() {
                    @Override
                    public void onResponse(Call<AddGVReponse> call, Response<AddGVReponse> response) {
                        if (response.isSuccessful())
                        {
                            addGVReponse = response.body();
                            Log.d(TAG, "onResponse: "+addGVReponse.getResult());
                            if (addGVReponse.getResult().compareTo("success")==0) {
                                Toast.makeText(AddTeacherActivity.this,"Thêm giáo viên thành công", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AddTeacherActivity.this,"Thêm giáo viên thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(AddTeacherActivity.this,"Không kết nối", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<AddGVReponse> call, Throwable t) {
                        Log.d("ErrorAddGV : ", t.getMessage());

                    }
                });

            }
        });


    }
}