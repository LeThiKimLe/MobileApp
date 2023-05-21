package vn.iotstar.finalproject.sidebar;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

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

    EditText id, userName, name, userEmail, sdt, date;
    Button Update, Refuse;
    ImageView update_btn;
    Uri imageUri;
    String myDownloadImage = null;
    FirebaseStorage storage;
    StorageReference storageReference;
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
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
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

    public void loadData() {
        HocVien hocVien = MainActivity.hocVien;
        binding.idBox.setText(String.valueOf(hocVien.getMaHocVien()));
        binding.usernameBox.setText(String.valueOf(hocVien.getTenHocVien()));
        binding.emailBox.setText(String.valueOf(hocVien.getEmail()));
        binding.sdtBox.setText(String.valueOf(hocVien.getSdt()));
        binding.dateBox.setText(String.valueOf(hocVien.getNgaySinh()));
        binding.tvName.setText(String.valueOf(hocVien.getTenHocVien()));
        if (hocVien.getImage() != null) {
            myDownloadImage = hocVien.getImage();
            binding.profilePic.setImageURI(Uri.parse(hocVien.getImage()));
        }
        Glide.with(getActivity().getApplicationContext()).load(hocVien.getImage()).into(binding.profilePic);
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

    public void TrangThai(boolean tt) {
        binding.usernameBox.setEnabled(tt);
        binding.emailBox.setEnabled(tt);
        binding.sdtBox.setEnabled(tt);
        binding.dateBox.setEnabled(tt);
    }

    int vitri = -1;

    public void Modify() {
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

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == -1) {
                            Intent data = result.getData();
                            imageUri = data.getData();
                            binding.profilePic.setImageURI(imageUri);
                            uploadPicture();
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        binding.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Uploading Image ...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference fileRef = storageReference.child("image/*" + randomKey);

        UploadTask uploadTask = fileRef.putFile(imageUri);

        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                //change made here
                return fileRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    myDownloadImage = downloadUri.toString();
                }
            }
        });
        uploadTask
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "Failed To Upload", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Percentage: " + (int) progressPercent + "%");
                    }
                });
    }

    public void update() {
        binding.updatebtn.setOnClickListener(view -> {
            String maUser = binding.idBox.getText().toString();
            String name = binding.usernameBox.getText().toString();
            String email = binding.emailBox.getText().toString();
            String sdt = binding.sdtBox.getText().toString();
            String ngaysinh = binding.dateBox.getText().toString();
            String image = myDownloadImage;
            Log.d("Image Download", image);


            tt = false;
            TrangThai(tt);

            hvApi.updateProfile(maUser, name, ngaysinh, sdt, email, image).enqueue(new Callback<HocVienReponse>() {
                @Override
                public void onResponse(Call<HocVienReponse> call, Response<HocVienReponse> response) {
                    if (response.isSuccessful()) {
                        hvReponse = response.body();
                        if (hvReponse.getResult().compareTo("success") == 0) {
                            Toast.makeText(MainActivity.getInstance(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.getInstance(), "Cập nhật dữ liệu chưa thành công", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.getInstance(), "Không kết nối", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<HocVienReponse> call, Throwable t) {
                    Toast.makeText(MainActivity.getInstance(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, t.getMessage());
                }
            });
        });
    }


    private void initComponents() {
        hvApi = vn.iotstar.finalproject.Retrofit.RetrofitClient.getRetrofit().create(HocVienApi.class);
    }
}