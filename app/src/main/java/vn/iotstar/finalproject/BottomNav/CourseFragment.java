package vn.iotstar.finalproject.BottomNav;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.KhoaHocAdapter;
import vn.iotstar.finalproject.Adapter.SpinnerAdapter;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.Model.KhoiLop;
import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.PageActivity.LoginActivity;
import vn.iotstar.finalproject.PageActivity.MainActivity;

import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.CourseListLayoutBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    CircleImageView imageViewprofile;
    RecyclerView rcCate,rcCate2,rcCate3;
    Spinner spinner;
    List<KhoiLop> listkhoilop;
    List<KhoaHoc> KhoaHocList,KhoaHocList2;
    SpinnerAdapter spinnerAdapter;


    KhoaHocAdapter KhoaHocAdapter;

    KhoaHocAPI apiService,apiService2,apiService3;
    private CourseListLayoutBinding binding;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
        binding = CourseListLayoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        HienSpinner();
        ClickTimKiem();



        return root;


    }
    private void HienSpinner(){
        spinner=binding.spinner;
        apiService3 = RetrofitClient.getRetrofit().create(KhoaHocAPI.class);

        apiService3.getKhoiLop().enqueue(new Callback<List<KhoiLop>>() {

            @Override
            public void onResponse(Call<List<KhoiLop>> call, Response<List<KhoiLop>> response) {
                if(response.isSuccessful()) {
                    listkhoilop = response.body();

                    listkhoilop.add(new KhoiLop(null,"Tất cả"));

                    spinnerAdapter = new SpinnerAdapter(MainActivity.getInstance().getApplication(),listkhoilop);
                    spinner.setAdapter(spinnerAdapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            String maKhoi=listkhoilop.get(i).getMaKhoi();
                            if(maKhoi!=null) {
                                LayKhoaHocTheoPhanMon(maKhoi);
                            }
                            else{
                                LayKhoaHoc();
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
    private void ClickTimKiem(){
        binding.searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=binding.search.getText().toString().trim();
                TimKiemKhoaHoc(key);
            }
        });
    }

    private void LayKhoaHoc() {
        apiService = RetrofitClient.getRetrofit().create(KhoaHocAPI.class);
        rcCate=binding.rcCategory2;
        apiService.getKHAll().enqueue(new Callback<List<KhoaHoc>>() {

            @Override
            public void onResponse(Call<List<KhoaHoc>> call, Response<List<KhoaHoc>> response) {
                if(response.isSuccessful()) {
                    KhoaHocList = response.body();
                    KhoaHocAdapter = new KhoaHocAdapter(MainActivity.getInstance(),KhoaHocList);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.getInstance().getApplicationContext(), 1);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(KhoaHocAdapter);
                    KhoaHocAdapter.notifyDataSetChanged();
                }else {
                    int statusCode = response.code();
                }
    }
            @Override
            public void onFailure(Call<List<KhoaHoc>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void LayKhoaHocTheoPhanMon(String maKhoi) {
        apiService2 = RetrofitClient.getRetrofit().create(KhoaHocAPI.class);
        rcCate2=binding.rcCategory2;

        apiService2.getKHKhoi(maKhoi).enqueue(new Callback<List<KhoaHoc>>() {

            @Override
            public void onResponse(Call<List<KhoaHoc>> call, Response<List<KhoaHoc>> response) {
                if(response.isSuccessful()) {
                    KhoaHocList = response.body();
                    KhoaHocAdapter = new KhoaHocAdapter(MainActivity.getInstance(),KhoaHocList);
                    rcCate2.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.getInstance().getApplicationContext(), 1);
                    rcCate2.setLayoutManager(layoutManager);
                    rcCate2.setAdapter(KhoaHocAdapter);
                    KhoaHocAdapter.notifyDataSetChanged();
                }else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<KhoaHoc>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
    private void TimKiemKhoaHoc(String key) {
        apiService3 = RetrofitClient.getRetrofit().create(KhoaHocAPI.class);
        rcCate2=binding.rcCategory2;

        apiService3.getTimKiem(key).enqueue(new Callback<List<KhoaHoc>>() {

            @Override
            public void onResponse(Call<List<KhoaHoc>> call, Response<List<KhoaHoc>> response) {
                if(response.isSuccessful()) {
                    KhoaHocList2 = response.body();
                    KhoaHocAdapter = new KhoaHocAdapter(MainActivity.getInstance(),KhoaHocList2);
                    rcCate2.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.getInstance().getApplicationContext(), 1);
                    rcCate2.setLayoutManager(layoutManager);
                    rcCate2.setAdapter(KhoaHocAdapter);
                    KhoaHocAdapter.notifyDataSetChanged();
                }else {
                    int statusCode = response.code();
                }
            }
            @Override
            public void onFailure(Call<List<KhoaHoc>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });


    }



}