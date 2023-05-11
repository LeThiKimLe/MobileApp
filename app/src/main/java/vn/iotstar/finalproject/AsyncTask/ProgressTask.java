package vn.iotstar.finalproject.AsyncTask;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import vn.iotstar.finalproject.PageActivity.IntroActivity;
import vn.iotstar.finalproject.R;

public class ProgressTask extends AsyncTask <Void, Integer, Void>{
    Activity contextParent;
    public ProgressTask(Activity contextParent)
    {
        this.contextParent=contextParent;
    }
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
//        Toast.makeText(contextParent, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... params)
    {
        for (int i=0; i<=100; i++) {
            SystemClock.sleep(5);
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
        ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.progress1);
        int number = values[0];
        progressBar.setProgress(number);
        TextView textView = (TextView) contextParent.findViewById(R.id.loadStatus);
        textView.setText("Loading "+number+" %");
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        IntroActivity.getInstance().ChuyenActivity();
    }
}
