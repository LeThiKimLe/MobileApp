package vn.iotstar.finalproject.sidebar;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import vn.iotstar.finalproject.BottomNav.AboutFragment;
import vn.iotstar.finalproject.BottomNav.CourseFragment;
import vn.iotstar.finalproject.BottomNav.HomePageFragment;
import vn.iotstar.finalproject.BottomNav.NoticeFragment;
import vn.iotstar.finalproject.ViewModel.ViewPageImage;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.databinding.MainLayoutBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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

    private MainLayoutBinding binding;

    public HomeFragment() {
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
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        binding = MainLayoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loadFragment(new HomePageFragment());
        addMenu();
        return root;
    }

    public void addMenu()
    {
        bottomNavigationView= binding.bottomNavigationView;
        bottomNavigationView.setBackground(null);
        bottomNavigationView.bringToFront();
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomePageFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.course:
//                        toolbar.setTitle("My Gifts");
                        fragment = new CourseFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.about:
//                        toolbar.setTitle("Cart");
                        fragment = new AboutFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.notice:
//                        toolbar.setTitle("Profile");
                        fragment = new NoticeFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }});
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.setReorderingAllowed(true);
        transaction.commit();
    }
}