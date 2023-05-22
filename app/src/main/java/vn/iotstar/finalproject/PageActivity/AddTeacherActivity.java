package vn.iotstar.finalproject.PageActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import vn.iotstar.finalproject.databinding.AddgiaovienLayoutBinding;
import vn.iotstar.finalproject.databinding.ListChuyenmonBinding;

public class AddTeacherActivity extends AppCompatActivity {
    private AddgiaovienLayoutBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddgiaovienLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        LayDSPM();


    }
    public void LayDSPM()
    {
        binding.btnchuyenmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddTeacherActivity.this, AddMaijorActivity.class);
                startActivity(intent);
            }
        });
    }

}
