package vn.iotstar.finalproject.BottomNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.iotstar.finalproject.Adapter.ImagesViewPageAdapter;
import vn.iotstar.finalproject.Adapter.SubjectAdapter;
import vn.iotstar.finalproject.Adapter.TypicalCourseAdapter;
import vn.iotstar.finalproject.Model.PhanMon;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.Response.StatisticResponse;
import vn.iotstar.finalproject.Retrofit.GeneralAPI;
import vn.iotstar.finalproject.Retrofit.*;
import vn.iotstar.finalproject.ViewModel.ViewPageImage;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.databinding.HomeFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<ViewPageImage> imageList;

    private BottomNavigationView bottomNavigationView;

    private Handler handler = new Handler();

    private HomeFragmentBinding binding;

    GeneralAPI apiService;
    StatisticResponse statistic;
    TypicalCourseAdapter typicalAdapter;

    SubjectAdapter subjectAdapter;

    List<PhanMon> listSubject;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
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
        binding = HomeFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        addViewPager();
        GetTypicalCourse();
        GetSubject();
        return root;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(binding.viewpage.getCurrentItem() == imageList.size()-1)
            {
                binding.viewpage.setCurrentItem(0);

            }else
            {
                binding.viewpage.setCurrentItem(binding.viewpage.getCurrentItem()+1);
            }
        }
    };

    @Override
    public void onPause()
    {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    private void addViewPager()
    {
        imageList= getImageList();
        ImagesViewPageAdapter adpater = new ImagesViewPageAdapter(imageList);
        binding.viewpage.setAdapter(adpater);
        binding.circleIndicator.setViewPager(binding.viewpage);
        handler.postDelayed(runnable, 3000);
        binding.viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    private List<ViewPageImage> getImageList(){
        List<ViewPageImage> list = new ArrayList<>();
        list.add(new ViewPageImage(R.drawable.banner1));
        list.add(new ViewPageImage(R.drawable.banner2));
        list.add(new ViewPageImage(R.drawable.banner3));
        return list;
    }

    private void GetTypicalCourse() {
        apiService = RetrofitClient.getRetrofit().create(GeneralAPI.class);
        apiService.getTypical().enqueue(new Callback<StatisticResponse>() {
            @Override
            public void onResponse(Call<StatisticResponse> call, Response<StatisticResponse> response) {
                if(response.isSuccessful()) {
                    statistic = response.body();

                    typicalAdapter = new TypicalCourseAdapter(MainActivity.getInstance(),statistic.getDangKyNhieu());
                    binding.rcAttractivecourse.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance().getApplicationContext());
                    binding.rcAttractivecourse.setLayoutManager(layoutManager);
                    binding.rcAttractivecourse.setAdapter(typicalAdapter);
                    typicalAdapter.notifyDataSetChanged();
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<StatisticResponse> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void GetSubject() {
        apiService = RetrofitClient.getRetrofit().create(GeneralAPI.class);
        apiService.getSubject().enqueue(new Callback<List<PhanMon>>() {
            @Override
            public void onResponse(Call<List<PhanMon>> call, Response<List<PhanMon>> response) {
                if(response.isSuccessful()) {
                    listSubject = response.body();
                    subjectAdapter = new SubjectAdapter(MainActivity.getInstance(), listSubject);
                    binding.rcCoursecategory.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance().getApplicationContext()
                            ,LinearLayoutManager.HORIZONTAL, false);
                    binding.rcCoursecategory.setLayoutManager(layoutManager);
                    binding.rcCoursecategory.setAdapter(subjectAdapter);
                    subjectAdapter.notifyDataSetChanged();
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<PhanMon>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }



}