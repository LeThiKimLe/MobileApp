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

import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.databinding.BillItemLayoutBinding;

import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class KhoaHocAdapter extends RecyclerView.Adapter<KhoaHocAdapter.MyViewHolder> {
    Context context;
    List<KhoaHoc> array;


    public KhoaHocAdapter(Context context, List<KhoaHoc> array)
    {
        this.context=context;
        this.array=array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        KhoaHoc product= array.get(position);
        Glide.with(context).load(product.getHinhAnhMoTa()).into(holder.images);
        holder.tenkh.setText(product.getTenKhoaHoc());
        holder.tengv.setText(product.getGiaoVien());
        holder.tenpm.setText(product.getPhanMon());
    }


    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView images;
        public TextView tenkh,tengv,tenpm;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            images = (ImageView) itemView.findViewById(R.id.coursePic);
            tenkh=(TextView)itemView.findViewById(R.id.courseName) ;
            tengv=(TextView)itemView.findViewById(R.id.teacherName) ;
            tenpm=(TextView)itemView.findViewById(R.id.typeName) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Bạn đã chọn Khóa học", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
