package vn.iotstar.finalproject.sidebar;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.iotstar.finalproject.BottomNav.HomePageFragment;
import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.HocVien;
import vn.iotstar.finalproject.Model.QuanTriVien;
import vn.iotstar.finalproject.PageActivity.LoginActivity;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.PageActivity.RegisterActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.HocVienReponse;
import vn.iotstar.finalproject.Retrofit.HocVienApi;
import vn.iotstar.finalproject.Storage.SharedPrefManager;
import vn.iotstar.finalproject.databinding.GvProfilelayoutBinding;
import vn.iotstar.finalproject.databinding.MainLayoutBinding;
import vn.iotstar.finalproject.databinding.ProfileFragmentBinding;
import vn.iotstar.finalproject.databinding.QtvProfilelayoutBinding;
import vn.iotstar.finalproject.ui.home.HomeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalFragment extends Fragment {

    EditText id, userName,name, userEmail, sdt, date;
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


    HocVienApi hvApi;

    HocVienReponse hvReponse;

    private ProfileFragmentBinding binding;
    private QtvProfilelayoutBinding bindingqtv;
    private GvProfilelayoutBinding bindinggv;
    String role;


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
        View root;

        role=MainActivity.role;

        if(role.equals("GV"))
        {
            bindinggv = GvProfilelayoutBinding.inflate(inflater, container, false);
            root = bindinggv.getRoot();
            loadDatagv();
            return  root;

        }
        else if(role.equals("QTV"))
        {
            bindingqtv = QtvProfilelayoutBinding.inflate(inflater, container, false);

            root = bindingqtv.getRoot();
            loadDataqtv();
            return  root;

        }
        binding = ProfileFragmentBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        initComponents();
        loadData();
        Modify();
        update();
        return root;
    }
    public void loadData()
    {
        HocVien hocVien = MainActivity.hocVien;
        binding.idBox.setText(String.valueOf(hocVien.getMaHocVien()));
        binding.usernameBox.setText(String.valueOf(hocVien.getTenHocVien()));
        binding.emailBox.setText(String.valueOf(hocVien.getEmail()));
        binding.sdtBox.setText(String.valueOf(hocVien.getSdt()));
        binding.dateBox.setText(String.valueOf(hocVien.getNgaySinh()));
        binding.tvName.setText(String.valueOf(hocVien.getTenHocVien()));
        TrangThai(tt);
    }
    public void loadDataqtv()
    {
        QuanTriVien qtv = MainActivity.quanTriVien;
        bindingqtv.idBox.setText(String.valueOf(qtv.getMaQtv()));
        bindingqtv.usernameBox.setText(String.valueOf(qtv.getHoTen()));
        bindingqtv.emailBox.setText(String.valueOf(qtv.getEmail()));
        bindingqtv.sdtBox.setText(String.valueOf(qtv.getSdt()));
        bindingqtv.diachiBox.setText(String.valueOf(qtv.getDiaChi()));
        bindingqtv.cccd.setText(String.valueOf(qtv.getCccd()));
        //TrangThai(tt);
    }
    public void loadDatagv()
    {
        GiaoVien gv = MainActivity.giaoVien;
        bindinggv.idBox.setText(String.valueOf(gv.getMaGiaoVien()));
        bindinggv.usernameBox.setText(String.valueOf(gv.getTenGiaoVien()));
        bindinggv.emailBox.setText(String.valueOf(gv.getEmail()));
        bindinggv.sdtBox.setText(String.valueOf(gv.getSdt()));
        bindinggv.diachiBox.setText(String.valueOf(gv.getDiaChi()));
        bindinggv.cccd.setText(String.valueOf(gv.getCccd()));
        bindinggv.majorBox.setText(String.valueOf(gv.getChuoiChuyenMon()));
        // TrangThai(tt);
    }

    boolean tt = false;
    public void TrangThai( boolean tt)
    {
        binding.usernameBox.setEnabled(tt);
        binding.emailBox.setEnabled(tt);
        binding.sdtBox.setEnabled(tt);
        binding.dateBox.setEnabled(tt);
    }
    int vitri = -1;
    public void Modify()
    {
        binding.modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tt = true;
                TrangThai(tt);

                for (int i = 0; i < binding.tableLayout.getChildCount(); i++) {
                    final int rowIndex = i;

                    TableRow tableRow = (TableRow) binding.tableLayout.getChildAt(i);
                    tableRow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
        });

    }

    public void update(){
        binding.updatebtn.setOnClickListener(view -> {
            String maUser = binding.idBox.getText().toString();
            String name = binding.usernameBox.getText().toString();
            String email = binding.emailBox.getText().toString();
            String sdt = binding.sdtBox.getText().toString();
            String ngaysinh = binding.dateBox.getText().toString();
            String images = binding.image2.toString();
            tt = false;
            TrangThai(tt);

            hvApi.updateProfile(maUser,name,ngaysinh,sdt,email,images).enqueue(new Callback<HocVienReponse>(){
                @Override
                public void onResponse(Call<HocVienReponse> call, Response<HocVienReponse> response)
                {
                    if (response.isSuccessful())
                    {
                        hvReponse = response.body();
                        if (hvReponse.getResult().compareTo("success")==0) {
                            Toast.makeText(MainActivity.getInstance(),"Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.getInstance(),"Cập nhật dữ liệu chưa thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.getInstance(),"Không kết nối", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<HocVienReponse> call, Throwable t)
                {
                    Toast.makeText(MainActivity.getInstance(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, t.getMessage());
                }
            });
        } );
    }


    private void initComponents() {
        hvApi = vn.iotstar.finalproject.Retrofit.RetrofitClient.getRetrofit().create(HocVienApi.class);
    }
}