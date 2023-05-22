package vn.iotstar.finalproject.TabHost;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.TaiNguyen;
import vn.iotstar.finalproject.Model.Video1Model;
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
        GetVideo(maBaiHoc);
        GetLeture(maBaiHoc);
    }

    private void GetVideo(String maBaiHoc){
        final String[] myUrl = {null};
        final ProgressDialog pd = new ProgressDialog(LectureActivity.this);
        pd.setMessage("Please Wait!");

        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("video");
        pd.show();
        mDatabaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot itemDataSnapshot: snapshot.getChildren()){
                    Video1Model video = itemDataSnapshot.getValue(Video1Model.class);
                    if(video.getMaBaiHoc().equals(maBaiHoc)){
                        videoBH.setVisibility(View.VISIBLE);
                        playVideo(video.getUrl());
                        pd.dismiss();
                        return;
                    }
                }
                pd.dismiss();
                Toast.makeText(LectureActivity.this, "Bài học chưa được phát hành, vui lòng đợi thêm !!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.dismiss();
                videoBH.setVisibility(View.GONE);
            }
        });

    }

    //Video bai giang
    private void GetLeture(String maBaiHoc) {
        taiNguyenAPI = RetrofitClient.getRetrofit().create(TaiNguyenAPI.class);
        taiNguyenAPI.getVideo(maBaiHoc).enqueue(new Callback<TaiNguyenReponse>() {
            @Override
            public void onResponse(Call<TaiNguyenReponse> call, Response<TaiNguyenReponse> response) {
                if (response.isSuccessful()) {
                    taiNguyenReponse = response.body();
                    tenBH.setText(taiNguyenReponse.getTenBH());
                    noidung.setText(taiNguyenReponse.getDescription());
                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<TaiNguyenReponse> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void AnhXa() {
        tenBH = (TextView) findViewById(R.id.tv_tenBG);
        noidung = (TextView) findViewById(R.id.tv_MoTaND);
        videoBH = (VideoView) findViewById(R.id.video);
    }

    private void playVideo(String myUrl) {
        videoBH.setVideoURI(Uri.parse(myUrl));
        MediaController mediaController = new MediaController(this);
        videoBH.setMediaController(mediaController);
        mediaController.setAnchorView(videoBH);
    }


}
