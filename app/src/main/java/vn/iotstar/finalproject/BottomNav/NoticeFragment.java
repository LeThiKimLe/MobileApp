package vn.iotstar.finalproject.BottomNav;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.iotstar.finalproject.Adapter.NoticeAdapter;
import vn.iotstar.finalproject.Adapter.TypicalCourseAdapter;
import vn.iotstar.finalproject.Database.CartDatabase;
import vn.iotstar.finalproject.Database.NoticeDatabase;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.CartItem;
import vn.iotstar.finalproject.Storage.NoticeRecord;
import vn.iotstar.finalproject.databinding.CourseListLayoutBinding;
import vn.iotstar.finalproject.databinding.NotifyFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    NoticeAdapter adapter;

    NotifyFragmentBinding binding;

    private List<NoticeRecord> listNotice;

    public NoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
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
        binding = NotifyFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if (MainActivity.role.equals("HV"))
            loadData();
        return root;
    }

    public void loadData(){
        listNotice= NoticeDatabase.getInstance(MainActivity.getInstance()).noticeDao().loadAllByNotice(MainActivity.userId);
        if (listNotice!=null && !listNotice.isEmpty()) {
            adapter = new NoticeAdapter(MainActivity.getInstance(), listNotice);
            binding.rcNoticelist.setHasFixedSize(true);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.getInstance().getApplicationContext());
            binding.rcNoticelist.setLayoutManager(layoutManager);
            binding.rcNoticelist.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    private void loadData2()
    {

    }
}