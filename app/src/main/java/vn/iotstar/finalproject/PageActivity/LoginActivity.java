package vn.iotstar.finalproject.PageActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Storage.SharedPrefManager;
import vn.iotstar.finalproject.databinding.LoginLayoutBinding;


public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private LoginLayoutBinding login_layout;
    HocVienApi hvApi;
    HocVienReponse hvReponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        login_layout= LoginLayoutBinding.inflate(getLayoutInflater());
        setContentView(login_layout.getRoot());
        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            goToHomeActivity();
        }
        initComponents();
        handleLoginButton();
        goToRegisterForm();

    }
    private void handleLoginButton() {

        login_layout.loginBt.setOnClickListener(v -> {
            String username = login_layout.textUsername.getText().toString().trim();
            String password =login_layout.textPassword.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                login_layout.textUsername.setError("Please enter your username");
                login_layout.textUsername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                login_layout.textPassword.setError("Please enter your password");
                login_layout.textPassword.requestFocus();
                return;
            }
            if(!username.isEmpty() & !password.isEmpty()) {
                hvApi.loginHocVien(username, password).enqueue(new Callback<HocVienReponse>()
                {
                    @SuppressLint("SuspiciousIndentation")
                    @Override
                    public void onResponse(Call<HocVienReponse> call, Response<HocVienReponse> response)
                    {
//                        System.out.printf("hi "+response.body().toString());
                        if (response.isSuccessful() && response.body().getHocVien()!=null)
                        {
                            hvReponse= response.body();
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            if (login_layout.checkBoxRemember.isChecked())
                                SharedPrefManager.getInstance(getApplicationContext()).hocvienLogin(hvReponse.getHocVien());
                            goToHomeActivity();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Tên đăng nhập hoặc mật khẩu sai", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<HocVienReponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Sai" + t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d("Loi", t.getMessage());
                    }})
                ;
//
            } else {
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private  void goToRegisterForm() {
        login_layout.textDangky.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });
    }

    public void initComponents() {

        hvApi = vn.iotstar.finalproject.Retrofit.RetrofitClient.getRetrofit().create(HocVienApi.class);
    }
}
