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

import java.text.SimpleDateFormat;
import java.util.List;

import vn.iotstar.finalproject.Model.KhoaHoc;
import vn.iotstar.finalproject.PageActivity.MainActivity;
import vn.iotstar.finalproject.R;
import vn.iotstar.finalproject.Storage.NoticeRecord;
import vn.iotstar.finalproject.databinding.BillItemLayoutBinding;

import vn.iotstar.finalproject.databinding.SignupLayoutBinding;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {
    Context context;
    List<NoticeRecord> array;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public NoticeAdapter(Context context, List<NoticeRecord> array)
    {
        this.context=context;
        this.array=array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notify_item_layout, null);
        MyViewHolder myViewHolder= new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NoticeRecord noticeRecord= array.get(position);
        holder.title.setText(noticeRecord.getTitleMsg());
        holder.content.setText(noticeRecord.getContent());
        holder.time.setText(noticeRecord.getNgayTao());
        holder.maHoaDon.setText(noticeRecord.getMaHoaDon());
    }


    @Override
    public int getItemCount() {
        return array==null? 0 :array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView title, content, time, maHoaDon;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.notice_title);
            content=(TextView)itemView.findViewById(R.id.notice_content);
            time=(TextView)itemView.findViewById(R.id.notice_time);
            maHoaDon = (TextView)itemView.findViewById(R.id.maHoaDon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context,"Bạn đã chọn product", Toast.LENGTH_SHORT).show();
                    MainActivity.getInstance().goToBillDetail((String) maHoaDon.getText());
                }
            });
        }
    }
}
