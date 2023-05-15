package vn.iotstar.finalproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.CourseRegisterActivity;
import vn.iotstar.finalproject.R;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.MyViewHolder> {
    List<KhoaHoc> array;
    Context context;

    public BillAdapter(CourseRegisterActivity courseRegisterActivity, List<KhoaHoc> khoaHocs)
    { this.array=khoaHocs;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        KhoaHoc khoaHoc= array.get(position);
        holder.courseName.setText(khoaHoc.getTenKhoaHoc());
        holder.coursePrice.setText(khoaHoc.getGiaTien()+"đ");
        holder.courseNum.setText(khoaHoc.getMaKhoaHoc());
    }

    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView courseName, coursePrice, courseNum;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            courseName = (TextView) itemView.findViewById(R.id.name);
            coursePrice = (TextView) itemView.findViewById(R.id.price);
            courseNum = (TextView) itemView.findViewById(R.id.stt);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Toast.makeText(context,"Bạn đã chọn Khóa học", Toast.LENGTH_SHORT).show();
//                    MainActivity.getInstance().goToCourseDetail((String) courseId.getText());
//
//                }
//            });
        }
    }
}
