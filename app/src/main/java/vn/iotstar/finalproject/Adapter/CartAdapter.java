package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.finalproject.Dao.iClickListener;
import vn.iotstar.finalproject.Database.CartDatabase;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;

import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter .MyViewHolder> {
    List<KhoaHoc> array;
    Context context;
    private iClickListener listener;
    public CartAdapter(iClickListener listener){ this.listener = listener;}

    public void setData(List<KhoaHoc> list)
    {
        this.array = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        KhoaHoc khoaHoc= array.get(position);
        Glide.with(context).load(khoaHoc.getHinhAnhMoTa()).into(holder.coursePic);
        holder.courseId.setText(khoaHoc.getMaKhoaHoc());
        holder.courseName.setText(khoaHoc.getTenKhoaHoc());
        holder.coursePrice.setText(khoaHoc.getGiaTien()+"đ");
        holder.courseType.setText(khoaHoc.getPhanMon());
        holder.courseTeacher.setText(khoaHoc.getGiaoVien());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.deleteCartItem(khoaHoc);
            }
        });
    }


    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView coursePic;
        public TextView courseName, courseTeacher, courseType, coursePrice, deleteBtn, courseId;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            coursePic = (ImageView) itemView.findViewById(R.id.coursePic);
            courseName = (TextView) itemView.findViewById(R.id.courseName);
            courseTeacher = (TextView) itemView.findViewById(R.id.teacherName);
            courseType = (TextView) itemView.findViewById(R.id.typeName);
            coursePrice = (TextView) itemView.findViewById(R.id.coursePricee);
            courseId = (TextView) itemView.findViewById(R.id.courseIdd);
            deleteBtn = (TextView) itemView.findViewById(R.id.deleteBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context,"Bạn đã chọn Khóa học", Toast.LENGTH_SHORT).show();
                    MainActivity.getInstance().goToCourseDetail((String) courseId.getText());

                }
            });
        }
    }
}
