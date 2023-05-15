package vn.iotstar.finalproject.sidebar;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.iotstar.finalproject.BottomNav.HomePageFragment;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.SharedPrefManager;
import vn.iotstar.finalproject.databinding.MainLayoutBinding;
import vn.iotstar.finalproject.databinding.ProfileFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {

    TextView id, userName,name, userEmail, sdt, date;
    Button Update, Refuse;
    ImageView update_btn;
    //CircleImageView imageViewprofile;
    private static PersonalFragment instance;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ProfileFragmentBinding binding;

    public PersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalFragment newInstance(String param1, String param2) {
        PersonalFragment fragment = new PersonalFragment();
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
        binding = ProfileFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loadData();
        return root;
    }
    public void loadData()
    {
        if(SharedPrefManager.getInstance(MainActivity.getInstance()).isLoggedIn())
        {
            HocVien hocVien = SharedPrefManager.getInstance(MainActivity.getInstance()).getHocVien();
            binding.idBox.setText(String.valueOf(hocVien.getMaHocVien()));
            binding.usernameBox.setText(String.valueOf(hocVien.getTenHocVien()));
            binding.emailBox.setText(String.valueOf(hocVien.getEmail()));
            binding.sdtBox.setText(String.valueOf(hocVien.getSdt()));
            binding.dateBox.setText(String.valueOf(hocVien.getNgaySinh()));
            binding.tvName.setText(String.valueOf(hocVien.getTenHocVien()));
        }
    }

    int vitri = -1;
    public void update()
    {
        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}