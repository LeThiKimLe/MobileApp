package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.SharedPrefManager;
import vn.iotstar.finalproject.databinding.ActivityMain2Binding;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private SupportRequestManagerFragment supportRequestManagerFragment;
    private NavController navController;
    private ActivityMain2Binding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private static MainActivity instance;

    private static final int MY_REQUEST_CODE=10;
    public static final String TAG = MainActivity.class.getName();
    public static String userId;

    View headerView;

    TextView userName, userEmail, logout;
    ImageView imageViewprofile;

    public static HocVien hocVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            hocVien = SharedPrefManager.getInstance(this).getHocVien();
        }
        else{

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            hocVien = (HocVien) extras.getSerializable("currentUser");
        }
        }
        //setContentView(R.layout.activity_main2);
        //AnhXa();
        addSideBar();
        setPersonalInfor();
        //addViewPager();
    }

    public void setPersonalInfor()
    {
        headerView = binding.navView.getHeaderView(0);
        userName=(TextView)headerView.findViewById(R.id.personName);
        userEmail = (TextView) headerView.findViewById(R.id.personalEmail);
        imageViewprofile = (ImageView) headerView.findViewById(R.id.personalImg);
        userName.setText(hocVien.getTenHocVien());
        userEmail.setText(hocVien.getEmail());
        userId=hocVien.getMaHocVien();
        Glide.with(getApplicationContext()).load(hocVien.getImage()).into(imageViewprofile);
        addLogOut();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    private void addSideBar()
    {
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_mypage,R.id.nav_cart)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void addLogOut()
    {
        logout= (TextView) headerView.findViewById(R.id.logoutBtn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.getInstance());
        alert.setTitle("Xác nhận đăng xuất");
        alert.setMessage("Bạn có chắc muốn đăng xuất?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPrefManager.getInstance(MainActivity.getInstance().getApplicationContext()).logout();
            }
        });

        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void goToCourseDetail(String courseId)
    {
        Intent intent = new Intent(MainActivity.this, DetailCourseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("courseId", courseId);
        intent.putExtras(bundle);
        startActivity(intent);
//        startActivityForResult(intent, 1);
//        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            }
        }
    }

    @Override
    public void onBackPressed() {
        int backstack = getSupportFragmentManager().getBackStackEntryCount();
        if (backstack > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
            System.exit(0);
        }
    }


}