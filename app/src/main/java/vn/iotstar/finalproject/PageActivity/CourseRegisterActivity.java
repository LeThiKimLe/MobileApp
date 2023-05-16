package vn.iotstar.finalproject.PageActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.finalproject.Adapter.BillAdapter;
import vn.iotstar.finalproject.Adapter.CartAdapter;
import vn.iotstar.finalproject.Dao.iClickListener;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.CartItem;
import vn.iotstar.finalproject.databinding.ActivityMain2Binding;
import vn.iotstar.finalproject.databinding.RegisterCourseLayoutBinding;

public class CourseRegisterActivity extends AppCompatActivity {

    List<KhoaHoc> regisList;
    BillAdapter adapter;

    RegisterCourseLayoutBinding binding;

    private int sumCourse=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_course_layout);
        binding = RegisterCourseLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showInfor();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            regisList = (List<KhoaHoc>) extras.getSerializable("registerCourse");
            showBill();
        }
    }

    private void showBill()
    {

        adapter = new BillAdapter(this, regisList);
        binding.regisRc.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        binding.regisRc.setLayoutManager(layoutManager);
        binding.regisRc.setAdapter(adapter);

        binding.numCourse.setText(""+ regisList.size());
        binding.sumTotal.setText("đ"+sumCal());
    }

    private void showInfor()
    {
        binding.studentName.setText(MainActivity.hocVien.getTenHocVien());
        binding.studentEmail.setText(MainActivity.hocVien.getEmail());
        binding.studentPhone.setText(MainActivity.hocVien.getSdt());
    }

    private int sumCal()
    {
       int sum=0;
       for (int i=0; i<regisList.size();i++)
           sum+=regisList.get(i).getGiaTien();
       sumCourse=sum;
       return sum;
    }
}