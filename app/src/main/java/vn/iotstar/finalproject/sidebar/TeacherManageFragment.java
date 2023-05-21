package vn.iotstar.finalproject.sidebar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.SpinnerAdapter;
import vn.iotstar.finalproject.Adapter.TeacherAdapter;
import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.KhoiLop;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.QuanTriVienAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.FragmentTeacherManageBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TeacherManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeacherManageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Spinner spinner;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    QuanTriVienAPI apiService;

    KhoaHocAPI apiService3;
    TeacherAdapter adapter;
    List<GiaoVien> listGiaoVien;

    SpinnerAdapter spinnerAdapter;

    List<KhoiLop> listkhoilop;

    private FragmentTeacherManageBinding binding;

    public TeacherManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeacherManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TeacherManageFragment newInstance(String param1, String param2) {
        TeacherManageFragment fragment = new TeacherManageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeacherManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loadTeacher();
        HienSpinner();
        return root;
    }

    private void loadTeacher()
    {
        apiService = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
        apiService.getTeacher().enqueue(new Callback<List<GiaoVien>>() {
            @Override
            public void onResponse(Call<List<GiaoVien>> call, Response<List<GiaoVien>> response) {
                if(response.isSuccessful()) {
                    listGiaoVien = response.body();
                    adapter = new TeacherAdapter(MainActivity.getInstance(),listGiaoVien);
                    binding.recycle.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance().getApplicationContext());
                    binding.recycle.setLayoutManager(layoutManager);
                    binding.recycle.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<GiaoVien>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }

    private void HienSpinner(){
        spinner=binding.spinner2;
        apiService3 = RetrofitClient.getRetrofit().create(KhoaHocAPI.class);

        apiService3.getKhoiLop().enqueue(new Callback<List<KhoiLop>>() {
            @Override
            public void onResponse(Call<List<KhoiLop>> call, Response<List<KhoiLop>> response) {
                if(response.isSuccessful()) {
                    listkhoilop = response.body();

                    listkhoilop.add(0,new KhoiLop(null,"Tất cả"));

                    spinnerAdapter = new SpinnerAdapter(MainActivity.getInstance().getApplication(),listkhoilop);
                    spinner.setAdapter(spinnerAdapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                            String maKhoi=listkhoilop.get(i).getMaKhoi();
                            if(maKhoi!=null) {
                                String lop= maKhoi.substring(2);
                                TimTheoLop(lop);
//                                LayKhoaHocTheoPhanMon(maKhoi);
                            }
                            else{
                                loadTeacher();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<KhoiLop>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void TimTheoLop(String lop)
    {
//        List<GiaoVien> list_filter= new ArrayList<>();
        for(int i=0;i<listGiaoVien.size();i++)
        {
            if (!(listGiaoVien.get(i).getChuoiChuyenMon()).contains(lop)) {
                adapter.removeItem(listGiaoVien.get(i));
                i--;
            }
            else
            {
            }
        }
//        listGiaoVien=list_filter;
//        adapter.notifyDataSetChanged();
    }

}