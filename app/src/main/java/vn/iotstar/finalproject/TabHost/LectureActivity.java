package vn.iotstar.finalproject.TabHost;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.TaiNguyen;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.TaiNguyenReponse;
import vn.iotstar.finalproject.Retrofit.GeneralAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.Retrofit.TaiNguyenAPI;

public class LectureActivity extends Activity {
    TaiNguyenAPI taiNguyenAPI;
    TaiNguyenReponse taiNguyenReponse;
    TextView tenBH, noidung;
    VideoView videoBH;
    String maBaiHoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecture_fragment1);
        AnhXa();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            maBaiHoc = extras.getString("maBaiHoc");
        }
        GetLeture(maBaiHoc);
        playVideo();

    }

    //Video bai giang
    private void GetLeture(String maBaiHoc)
    {
        taiNguyenAPI = RetrofitClient.getRetrofit().create(TaiNguyenAPI.class);
        taiNguyenAPI.getVideo(maBaiHoc).enqueue(new Callback<TaiNguyenReponse>() {
            @Override
            public void onResponse(Call<TaiNguyenReponse> call, Response<TaiNguyenReponse> response) {
                if(response.isSuccessful()) {
                    taiNguyenReponse = response.body();
                    tenBH.setText(taiNguyenReponse.getTenBH());
                    noidung.setText(taiNguyenReponse.getDescription());
                }else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<TaiNguyenReponse> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private  void AnhXa()
    {
        tenBH = (TextView) findViewById(R.id.tv_tenBG);
        noidung =(TextView) findViewById(R.id.tv_MoTaND);
        videoBH =(VideoView) findViewById(R.id.video);
    }

    private void playVideo()
    {
        videoBH.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.baigiang));
        MediaController mediaController = new MediaController(this);
        videoBH.setMediaController(mediaController);
        mediaController.setAnchorView(videoBH);
    }
}
