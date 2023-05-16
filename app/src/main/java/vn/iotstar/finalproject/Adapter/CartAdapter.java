package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vn.iotstar.finalproject.Dao.iClickListener;
import vn.iotstar.finalproject.Database.CartDatabase;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.CartItem;
import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter .MyViewHolder> {
    List<KhoaHoc> array;
    Context context;

    List<KhoaHoc> regisCourse= new ArrayList<>();
    private iClickListener listener;

    private int courseCount=0;
    private int totalPrice=0;

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartAdapter(iClickListener listener){ this.listener = listener;}

    public void setData(List<CartItem> list)
    {
        this.array = convertToCourse(list);
        notifyDataSetChanged();
    }

    private  List<KhoaHoc> convertToCourse(List<CartItem> list)
    {
        List<KhoaHoc> array1= new ArrayList<>();
        if (list!=null) {
            for (int i = 0; i < list.size(); i++) {
                array1.add(list.get(i).getKhoaHoc());
            }
            return array1;
        }
        return null;
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
        CartItem item = (CartDatabase.getInstance(context).cartDao().checkCourse(khoaHoc.getMaKhoaHoc(), MainActivity.userId)).get(0);
        Glide.with(context).load(khoaHoc.getHinhAnhMoTa()).into(holder.coursePic);
        holder.courseId.setText(khoaHoc.getMaKhoaHoc());
        holder.courseName.setText(khoaHoc.getTenKhoaHoc());
        holder.coursePrice.setText(khoaHoc.getGiaTien()+"đ");
        holder.courseType.setText(khoaHoc.getPhanMon());
        holder.courseTeacher.setText(khoaHoc.getGiaoVien());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.chooseBox.isChecked()) {
                    courseCount -= 1;
                    totalPrice-= khoaHoc.getGiaTien();
                    regisCourse.remove(khoaHoc);
                }
                listener.updateBill(courseCount, totalPrice);
                listener.deleteCartItem(item);
            }
        });

        holder.chooseBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked) {
                    courseCount += 1;
                    totalPrice+=khoaHoc.getGiaTien();
                    regisCourse.add(khoaHoc);
                }
                else
                {
                    courseCount -= 1;
                    totalPrice-=khoaHoc.getGiaTien();
                    regisCourse.remove(khoaHoc);
                }
                listener.updateBill(courseCount, totalPrice);
            }
        }
        );
    }

    public List<KhoaHoc> getRegisterCourse(){
        return regisCourse;
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView coursePic;
        public TextView courseName, courseTeacher, courseType, coursePrice, deleteBtn, courseId;
        public CheckBox chooseBox;
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
            chooseBox = (CheckBox) itemView.findViewById(R.id.chooseCourse);
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
