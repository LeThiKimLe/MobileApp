package vn.iotstar.finalproject.sidebar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.finalproject.Adapter.CartAdapter;
import vn.iotstar.finalproject.BottomNav.HomePageFragment;
import vn.iotstar.finalproject.Database.CartDatabase;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
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

    private List<KhoaHoc> listkhoaHoc;

    private CartAdapter adapter;

    private CartLayoutBinding binding;

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
        return root;
    }

    public void getCart(){
        listkhoaHoc = new ArrayList<>();
        listkhoaHoc = CartDatabase.getInstance(MainActivity.getInstance()).cartDao().getAll();
        adapter = new CartAdapter(MainActivity.getInstance(), listkhoaHoc);
        binding.cartItemList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance());
        binding.cartItemList.setLayoutManager(layoutManager);
        binding.cartItemList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}