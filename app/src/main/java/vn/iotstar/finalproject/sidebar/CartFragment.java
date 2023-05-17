package vn.iotstar.finalproject.sidebar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.Adapter.CartAdapter;
import vn.iotstar.finalproject.Adapter.TypicalCourseAdapter;
import vn.iotstar.finalproject.BottomNav.HomePageFragment;
import vn.iotstar.finalproject.Dao.iClickListener;
import vn.iotstar.finalproject.Database.CartDatabase;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.CourseRegisterActivity;
import vn.iotstar.finalproject.PageActivity.LoginActivity;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.PageActivity.RegisterActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.SoDuResponse;
import vn.iotstar.finalproject.Response.StatisticResponse;
import vn.iotstar.finalproject.Retrofit.GeneralAPI;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Retrofit.RetrofitClient;
import vn.iotstar.finalproject.Storage.CartItem;
import vn.iotstar.finalproject.databinding.CartLayoutBinding;
import vn.iotstar.finalproject.databinding.MainLayoutBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<CartItem> listCartItem;

    private CartAdapter adapter;

    private CartLayoutBinding binding;

    private int current_pay;
    private int my_pay;

    private int current_count;

    HocVienApi apiService;

    private SoDuResponse soDu;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        binding = CartLayoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        getCart();
        addRegisterBtn();
        GetBalance();
        return root;
    }

    public void getCart(){
        Toast.makeText(MainActivity.getInstance(), "Load Giỏ hàng thành công", Toast.LENGTH_SHORT).show();
        listCartItem = new ArrayList<>();
//        listkhoaHoc = CartDatabase.getInstance(MainActivity.getInstance()).cartDao().getAll();
//        adapter = new CartAdapter(MainActivity.getInstance(), listkhoaHoc);
        adapter= new CartAdapter(new iClickListener() {
            @Override
            public void deleteCartItem(CartItem khoaHoc) {
                clickDeleteFromCart(khoaHoc);
            }

            @Override
            public void updateBill(int count, int price) {
                binding.numCourse.setText(""+count);
                binding.sumTotal.setText("đ"+price);
                current_pay= price;
                current_count=count;
            }
        });
        loadData();
        binding.cartItemList.setHasFixedSize(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        binding.cartItemList.setLayoutManager(layoutManager);
        binding.cartItemList.setAdapter(adapter);
    }

    private void GetBalance() {
        apiService = RetrofitClient.getRetrofit().create(HocVienApi.class);
        soDu= new SoDuResponse();
        apiService.getBalance(MainActivity.hocVien.getMaHocVien()).enqueue(new Callback<SoDuResponse>() {
            @Override
            public void onResponse(Call<SoDuResponse> call, Response<SoDuResponse> response) {
                if(response.isSuccessful()) {
                    soDu = response.body();
                    binding.myPay.setText(""+soDu.getSoDu());
                    my_pay= soDu.getSoDu();
                }else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<SoDuResponse> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }

    private void clickDeleteFromCart(CartItem khoaHoc)
    {
        CartDatabase.getInstance(MainActivity.getInstance()).cartDao().delete(khoaHoc);
        loadData();
        Toast.makeText(MainActivity.getInstance(), "Đã xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        listCartItem= CartDatabase.getInstance(MainActivity.getInstance()).cartDao().getAll(MainActivity.userId);
        adapter.setData(listCartItem);
    }

    private void addRegisterBtn()
    {

        binding.checkOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBalance()){
                    Intent intent = new Intent(MainActivity.getInstance(), CourseRegisterActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("registerCourse", (Serializable) adapter.getRegisterCourse());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean checkBalance()
    {
        if (current_pay > my_pay)
        {
            Toast.makeText(MainActivity.getInstance(), "Tài khoản của bạn không đủ", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (current_count==0)
        {
            Toast.makeText(MainActivity.getInstance(), "Vui lòng chọn khóa học", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }
}