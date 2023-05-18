package vn.iotstar.finalproject.TabHost;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Model.TaiNguyen;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.DocumentReponse;
import vn.iotstar.finalproject.Response.TaiNguyenReponse;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.Retrofit.TaiNguyenAPI;

public class DocumentActivity extends Activity {

    TaiNguyenAPI taiNguyenAPI;
    DocumentReponse documentReponse;

    TextView lythuyet, baitap;

    String maBaiHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document_fragment1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            maBaiHoc = extras.getString("maBaiHoc");
        }
        AnhXa();
        GetDocument(maBaiHoc);
    }
    private void GetDocument(String maBaiHoc)
    {
        taiNguyenAPI = RetrofitClient.getRetrofit().create(TaiNguyenAPI.class);
        taiNguyenAPI.getDocument(maBaiHoc).enqueue(new Callback<DocumentReponse>() {
            @Override
            public void onResponse(Call<DocumentReponse> call, Response<DocumentReponse> response) {
                if(response.isSuccessful()) {
                    documentReponse = response.body();
                    lythuyet.setText(documentReponse.getLyThuyet());
                    lythuyet.setMovementMethod(new ScrollingMovementMethod());
                    baitap.setText(documentReponse.getDocument());
                }else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<DocumentReponse> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private  void AnhXa()
    {
        lythuyet = (TextView) findViewById(R.id.tv_lythuyet);
        baitap = (TextView) findViewById(R.id.tv_baitap);
    }
}
