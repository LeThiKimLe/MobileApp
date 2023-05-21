package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Response.StatisticResponse;

public class TypicalCourseAdapter extends RecyclerView.Adapter<TypicalCourseAdapter.MyViewHolder>{
    Context context;
    List<StatisticResponse.SoLuongHocVienDangKyKhoaHoc> array;
    public TypicalCourseAdapter(Context context, List<StatisticResponse.SoLuongHocVienDangKyKhoaHoc> array)
    {
        this.context=context;
        this.array=array;
    }

    @NonNull
    @Override
    public TypicalCourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.typical_course_layout, null);
        TypicalCourseAdapter.MyViewHolder myViewHolder= new TypicalCourseAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TypicalCourseAdapter.MyViewHolder holder, int position) {
        StatisticResponse.SoLuongHocVienDangKyKhoaHoc course= array.get(position);
        Glide.with(context).load(course.getHinhAnhMoTa()).into(holder.images);
        holder.courseName.setText(course.getTenKhoaHoc());
        holder.teacherName.setText("Giáo viên "+course.getGiaoVien());
        holder.regisNumber.setText("Đã đăng ký: "+course.getSoLuongDangKy()+ " Học viên");
        holder.courseId.setText(course.getMaKhoaHoc());
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView courseName, teacherName, regisNumber, courseId;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.coursePic);
            courseName = (TextView) itemView.findViewById(R.id.courseName);
            teacherName = (TextView) itemView.findViewById(R.id.teacherName);
            regisNumber = (TextView) itemView.findViewById(R.id.deleteBtn);
            courseId = (TextView) itemView.findViewById(R.id.courseId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context,"Bạn đã chọn product", Toast.LENGTH_SHORT).show();
                    MainActivity.getInstance().goToCourseDetail(array.get(getAdapterPosition()).getMaKhoaHoc());
                }
            });
        }
    }
}
