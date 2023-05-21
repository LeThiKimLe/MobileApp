package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.DonHang;
import vn.iotstar.finalproject.Model.Feedback;
import vn.iotstar.finalproject.Model.GiaoDich;
import vn.iotstar.finalproject.Model.GiaoVien;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.NoticeRecord;
import vn.iotstar.finalproject.databinding.BillItemLayoutBinding;

import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> {
    Context context;
    List<GiaoVien> array;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    List<GiaoVien> needStore= new ArrayList<>();

    public TeacherAdapter(Context context, List<GiaoVien> array)
    {
        this.context=context;
        this.array=array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GiaoVien giaoVien = array.get(position);
        holder.tenGV.setText(giaoVien.getTenGiaoVien());
        holder.maGV.setText("Mã GV: "+ giaoVien.getMaGiaoVien());
        holder.ngayKyKet.setText("Thời gian đặt: "+ formatter.format(giaoVien.getNgayKyKet()));
        holder.chuyenMon.setText(giaoVien.getChuoiChuyenMon());
        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(giaoVien);
            }
        });
    }

    public void addItem(GiaoVien item)
    {
        array.add(item);
        notifyItemInserted(array.size()-1);
    }

    public void removeItem(GiaoVien item)
    {
        int index = array.indexOf(item);
        if(index<0)
            return;
        array.remove(index);
        notifyItemRemoved(index);
    }


    public void addRestore(GiaoVien gv)
    {
        this.needStore.add(gv);
    }

    public void restoreList()
    {
        if (needStore.size()!=0) {
            for (int i = 0; i < needStore.size(); i++)
            {
                addItem(needStore.get(i));
            }
        }
        needStore=new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tenGV, maGV, ngayKyKet, chuyenMon, btnDelete, btnView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            maGV=(TextView)itemView.findViewById(R.id.maGV);
            ngayKyKet=(TextView) itemView.findViewById(R.id.ngayKiKet);
            tenGV=(TextView) itemView.findViewById(R.id.tenGV);
            chuyenMon= (TextView)itemView.findViewById(R.id.chuyenMon);
            btnDelete= (TextView)itemView.findViewById(R.id.btnDeleteTeacher);
            btnView = (TextView)itemView.findViewById(R.id.btnViewDetailTeacher);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context,"Bạn đã chọn product", Toast.LENGTH_SHORT).show();
//                    MainActivity.getInstance().goToBillDetail((String) maHoaDon.getText());
                }
            });
        }
    }
}
