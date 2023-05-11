package vn.iotstar.finalproject.BottomNav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.finalproject.Adapter.ImagesViewPageAdapter;
import vn.iotstar.finalproject.Model.ViewPageImage;
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
}