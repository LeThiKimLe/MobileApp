package vn.iotstar.finalproject.sidebar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.KHAdapter;
import vn.iotstar.finalproject.Adapter.TransactionAdapter;
import vn.iotstar.finalproject.Model.GiaoDich;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.PageActivity.MyCourseActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.ViThanhToanResponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Retrofit.KhoaHocAPI;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.databinding.FragmentWalletBinding;
import vn.iotstar.finalproject.databinding.ProfileFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentWalletBinding binding;

    private HocVienApi apiService;


    private TransactionAdapter adapter;

    private List<GiaoDich> listGD;

    private ViThanhToanResponse resp;

    private String maHocVien;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public WalletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
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
        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        maHocVien= MainActivity.userId;
        getTransaction();
        return root;
    }

    private void getTransaction()
    {
        apiService = RetrofitClient.getRetrofit().create(HocVienApi.class);

        apiService.getWallet(maHocVien).enqueue(new Callback<ViThanhToanResponse>() {

            @Override
            public void onResponse(Call<ViThanhToanResponse> call, Response<ViThanhToanResponse> response) {

                if (response.isSuccessful()) {
                    resp = response.body();
                   if (resp.getResult().equals("success")) {
                       adapter = new TransactionAdapter(MainActivity.getInstance(), resp.getListGD());
                       binding.rcTransaction.setHasFixedSize(true);
                       LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance().getApplicationContext());
                       binding.rcTransaction.setLayoutManager(layoutManager);
                       binding.rcTransaction.setAdapter(adapter);
                       adapter.notifyDataSetChanged();

                       binding.maVi.setText(resp.getWallet().getMaVi());
                       binding.ngayCapNhatVi.setText(formatter.format(resp.getWallet().getNgayCapNhat()));
                       binding.balance.setText(resp.getWallet().getSoDu()+"");
                   }
                } else {
//                    Toast.makeText(MyCourseActivity.this, "Không gọi được", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ViThanhToanResponse> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }


}