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

import vn.iotstar.finalproject.Model.BaiHoc;
import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;

public class LessionAdapter extends RecyclerView.Adapter<LessionAdapter.MyViewHolder> {
    Context context;
    List<BaiHoc> array;

    public LessionAdapter(Context context, List<BaiHoc> array) {
        this.context = context;
        this.array = array;
    }


    @NonNull
    @Override
    public LessionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_item_layout, null);
        LessionAdapter.MyViewHolder myViewHolder= new LessionAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LessionAdapter.MyViewHolder holder, int position) {
        BaiHoc product= array.get(position);

        holder.tenbh.setText(product.getTenBaiHoc());
        holder.lessionid.setText(product.getMaBaiHoc());
    }


    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tenbh;

        public TextView lessionid;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            tenbh= (TextView)itemView.findViewById(R.id.textView_tenbaihoc);
            lessionid =(TextView)itemView.findViewById(R.id.id_lession);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Đăng ký khóa học để xem nội dung bài học", Toast.LENGTH_SHORT).show();
               }
            });
        }
    }
}
