package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import vn.iotstar.finalproject.AsyncTask.ProgressTask;
import vn.iotstar.finalproject.R;

public class IntroActivity extends AppCompatActivity {

    Button btnStart;
    ProgressTask myAsyncTask;

    private static IntroActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_layout);
        instance = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                myAsyncTask= new ProgressTask(IntroActivity.this);
                myAsyncTask.execute();
            }
        }).start();
    }

    public static IntroActivity getInstance() {
        return instance;
    }

    public void ChuyenActivity()
    {
        IntroActivity.this.finish();
        Intent intent = new Intent(IntroActivity.this.getApplicationContext(), (Class) MainActivity.class);
        IntroActivity.this.startActivity(intent);
        return;
    }
}