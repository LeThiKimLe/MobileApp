package vn.iotstar.finalproject.PageActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.TabHost.CommentActivity;
import vn.iotstar.finalproject.TabHost.DocumentActivity;
import vn.iotstar.finalproject.TabHost.LectureActivity;

public class TabHostActivity extends TabActivity {
    private  String maBaiHoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost_layout);

        Bundle extras = getIntent().getExtras();

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        TabHost.TabSpec spec;
        Intent intent;

        spec = tabHost.newTabSpec("Lecture");
        spec.setIndicator("LECTURE");
        intent = new Intent(this, LectureActivity.class);
        intent.putExtras(extras);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Document");
        spec.setIndicator("DOCUMENT");
        intent = new Intent(this, DocumentActivity.class);
        intent.putExtras(extras);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Comment");
        spec.setIndicator("COMMENT");
        intent = new Intent(this, CommentActivity.class);
        intent.putExtras(extras);
        spec.setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
