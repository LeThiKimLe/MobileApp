package vn.iotstar.finalproject.PageActivity;

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
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Storage.SharedPrefManager;
import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class RegisterActivity extends AppCompatActivity {

    //
    private final String TAG = RegisterActivity.class.getName();

    private SignupLayoutBinding signup_layout;

    HocVienApi hvApi;

    HocVienReponse hvReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signup_layout=SignupLayoutBinding.inflate(getLayoutInflater());
        setContentView(signup_layout.getRoot());
        initComponents();
        DatePicker();
        handleRegisterUser();
        handleGotoLoginButton();
    }

    private void handleRegisterUser() {
        // Listen event
        signup_layout.signupBt.setOnClickListener(view -> {
            // Get data
            String username =  signup_layout.textUsername.getText().toString();
            String email = signup_layout.textEmail.getText().toString();
            String sdt =  signup_layout.textSdt.getText().toString();

            String ngaysinh =  signup_layout.textNgaysinh.getText().toString();
            String name =  signup_layout.textName.getText().toString();
            String password = signup_layout.textPassword.getText().toString();


            // Create user instance
            // Call API
            hvApi.registerHocVien(username,name,sdt,email,ngaysinh,password).enqueue(new Callback<HocVienReponse>() {
                @Override
                public void onResponse(Call<HocVienReponse> call, Response<HocVienReponse> response)
                {

                    if (response.isSuccessful())
                    {


                        hvReponse = response.body();

                        if (hvReponse.getResult().compareTo("success")==0) {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            SharedPrefManager.getInstance(getApplicationContext()).hocvienLogin(hvReponse.getHocVien());
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                       else
                        {
                            Toast.makeText(RegisterActivity.this,hvReponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Không kết nối", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<HocVienReponse> call, Throwable t)
                {
                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, t.getMessage());
                }
            });
        });
    }

    private void handleGotoLoginButton() {
        signup_layout.textDangnhap.setOnClickListener(view -> {
            goToLoginForm();
        });
    }
    private  void DatePicker()
    {
        signup_layout.ngaysinhBt.setOnClickListener(new View.OnClickListener() {
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
                        RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                signup_layout.textNgaysinh.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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

    private  void goToLoginForm() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void initComponents() {

        hvApi = vn.iotstar.finalproject.Retrofit.RetrofitClient.getRetrofit().create(HocVienApi.class);
    }
}