package vn.iotstar.finalproject.sidebar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.OrderAdapter;
import vn.iotstar.finalproject.Adapter.TypicalCourseAdapter;
import vn.iotstar.finalproject.BottomNav.HomePageFragment;
import vn.iotstar.finalproject.Model.DonHang;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.StatisticResponse;
import vn.iotstar.finalproject.Retrofit.GeneralAPI;
import vn.iotstar.finalproject.Retrofit.QuanTriVienAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.FragmentBillManageBinding;
import vn.iotstar.finalproject.databinding.MainLayoutBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BillManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BillManageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    QuanTriVienAPI apiService;
    OrderAdapter adapter;
    List<DonHang> listDon;

    private FragmentBillManageBinding binding;

    public BillManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BillManageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BillManageFragment newInstance(String param1, String param2) {
        BillManageFragment fragment = new BillManageFragment();
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
        binding = FragmentBillManageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loadOrder();
        return root;
    }

    private void loadOrder()
    {
        apiService = RetrofitClient.getRetrofit().create(QuanTriVienAPI.class);
        apiService.getOrder().enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                if(response.isSuccessful()) {
                    listDon = response.body();
                    adapter = new OrderAdapter(MainActivity.getInstance(),listDon);
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
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });

    }
}